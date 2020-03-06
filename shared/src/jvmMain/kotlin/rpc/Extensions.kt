package rpc

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.set
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlinx.serialization.set
import kotlin.reflect.KClass
import kotlin.reflect.full.*

@Suppress("UNCHECKED_CAST")
fun <T : RPCService, R> Route.rpc(serviceClass: KClass<T>, serializer: KSerializer<R>) {
    val instance = serviceClass.createInstance()
    serviceClass.declaredMemberFunctions.filter { it.name.startsWith("get") }.map { function ->
        get(function.name) {
            val args = mutableListOf<Any>()
            args.add(instance)
            function.valueParameters.mapNotNullTo(args) { param ->
                param.name?.let { call.request.queryParameters[it] }
            }

            val result = function.callSuspend(*args.toTypedArray())
            val serializedResult = if (function.returnType.arguments.isNotEmpty()) {
                when {
                    function.returnType.isSubtypeOf(List::class.createType(function.returnType.arguments)) -> Json.stringify(
                        serializer.list,
                        result as List<R>
                    )
                    function.returnType.isSubtypeOf(Set::class.createType(function.returnType.arguments)) -> Json.stringify(
                        serializer.set,
                        result as Set<R>
                    )
                    else -> SerializationException("Method must return either List<R> or Set<R>, but it returns ${function.returnType}")
                }
            } else {
                Json.stringify(serializer, result as R)
            }
            call.respond(serializedResult)
        }
    }
}
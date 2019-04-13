import io.ktor.routing.PathSegmentConstantRouteSelector
import io.ktor.routing.Route
import kotlinx.serialization.Serializable
import org.junit.Test
import rpc.RPCService
import rpc.rpc
import kotlin.test.assertEquals

@Serializable
class TestItem

class TestService : RPCService {
    fun getItem(): TestItem {
        return TestItem()
    }

    fun getItems(): List<TestItem> {
        return emptyList()
    }
}

class RPCTests {
    @Test
    fun `Extension function adds routes`() {
        val route = Route(parent = null, selector = PathSegmentConstantRouteSelector("/"))
        assertEquals(route.children.size, 0)

        route.rpc(TestService::class, TestItem.serializer())
        assertEquals(route.children.size, 2)

        assertEquals("/getItem", route.children.first().toString())
    }
}
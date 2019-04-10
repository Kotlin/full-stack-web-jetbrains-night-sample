@file:JsModule("enzyme")
package contrib.enzyme

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import react.RComponent
import react.RProps
import react.RState

external interface EnzymeAdapter
external fun configure(options: EnzymeOptions): ReactWrapper
external fun mount(node: dynamic, options: MountOptions? = definedExternally): ReactWrapper

external interface ReactWrapper {
    fun at(index: Int): ReactWrapper
    fun debug(): String
    @JsName("instance")
    fun domInstance(): HTMLElement
    fun exists(): Boolean
    fun find(selector: String): ReactWrapper
    fun getDOMNode(): Element?
    fun html(): String
    @JsName("instance")
    fun <T : RComponent<*, *>> instance(): T
    fun last(): ReactWrapper
    fun <T> map(fn: (ReactWrapper) -> T): Array<T>
    fun <P : RProps> props(): P
    fun simulate(event: String)
    fun <S : RState> state(): S
    fun text(): String
    fun update()
}

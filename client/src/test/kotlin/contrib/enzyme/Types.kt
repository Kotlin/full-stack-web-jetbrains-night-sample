package contrib.enzyme

import org.w3c.dom.Element

external interface EnzymeOptions {
    val adapter: EnzymeAdapter
}

fun EnzymeOptions(adapter: EnzymeAdapter): EnzymeOptions = with(js("({})")) {
    this.adapter = adapter
    this
}

external interface MountOptions {
    val context: Any?
    val attachTo: Element?
}

fun MountOptions(context: Any? = null, attachTo: Element? = null): MountOptions = with(js("({})")) {
    this.context = context
    this.attachTo = attachTo
    this
}

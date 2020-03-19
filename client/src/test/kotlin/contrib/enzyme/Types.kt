package contrib.enzyme

import org.w3c.dom.Element

class EnzymeOptions(val adapter: EnzymeAdapter)

class MountOptions(val context: Any? = null, val attachTo: Element? = null)

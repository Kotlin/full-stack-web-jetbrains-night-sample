package view

import kotlinx.coroutines.CoroutineScope
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyleSheet
import styled.css
import styled.styledDiv

private object ApplicationStyles: StyleSheet("ApplicationStyles", isStatic = true) {
    val wrapper by css {
        padding(32.px, 16.px)
    }

    val post by css {
        marginBottom = 32.px
    }
}

interface ApplicationProps: RProps {
    var coroutineScope: CoroutineScope
}

class ApplicationState: RState

class ApplicationComponent: RComponent<ApplicationProps, ApplicationState>() {
    init {
        state = ApplicationState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    override fun RBuilder.render() {
        styledDiv {
            css {
                +ApplicationStyles.wrapper
            }

            +"Kotlin full stack application demo"
        }
    }
}
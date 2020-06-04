package view

import contrib.ringui.header.ringHeader
import contrib.ringui.header.ringLogo
import contrib.ringui.ringAlert
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.css.paddingLeft
import kotlinx.css.px
import model.PostWithComments
import model.User
import react.*
import services.CommentsService
import services.PostWithCommentsService
import services.UserService
import styled.css
import styled.styledA
import styled.styledDiv
import kotlin.random.Random

@JsExport
class ErrorBoundaryState : RState {
    var error: Throwable? = null
    var info: RErrorInfo? = null
}

@JsExport
class ErrorBoundaryComponent : RComponent<RProps, ErrorBoundaryState>() {
    init {
        state = ErrorBoundaryState()
    }

    override fun componentDidCatch(error: Throwable, info: RErrorInfo) {
        console.log("DID CATCH")
        console.log("error", error)
        console.log("info",info)
        setState {
            this.error = error
            this.info = info
        }
    }

    override fun RBuilder.render() {
        if (state.error != null) {
            console.log("ERROR RENDER")
            ringAlert {
                attrs {
                    type = "error"
                }
                +"Hello"
            }
            return
        }
        console.log("NOT ERROR RENDER")

        props.children()
    }
}
import kotlinext.js.invoke
import kotlinx.css.*
import styled.createGlobalStyle

object GlobalStyles {
    fun inject() {
        val styles = CssBuilder(allowClasses = false).apply {
            body {
                margin(0.px)
                padding(0.px)
            }
        }

        createGlobalStyle(styles.toString())
    }
}
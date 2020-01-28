package view

import kotlinx.css.FontWeight
import kotlinx.css.fontWeight
import kotlinx.css.marginRight
import kotlinx.css.px
import model.Comment
import react.RBuilder
import styled.DIVBuilder
import styled.css
import styled.styledDiv
import styled.styledSpan

fun RBuilder.commentView(comment: Comment, builder: DIVBuilder) {
    styledDiv {
        styledSpan {
            css {
                marginRight = 8.px

                fontWeight = FontWeight.bold
            }

            +comment.name
        }

        styledSpan {
            +comment.body
        }

        builder()
    }
}
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.FontWeight
import kotlinx.css.Image
import kotlinx.css.TextAlign
import kotlinx.css.backgroundImage
import kotlinx.css.backgroundPosition
import kotlinx.css.backgroundSize
import kotlinx.css.borderRadius
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.flexDirection
import kotlinx.css.flexGrow
import kotlinx.css.fontFamily
import kotlinx.css.fontSize
import kotlinx.css.fontWeight
import kotlinx.css.height
import kotlinx.css.margin
import kotlinx.css.marginBottom
import kotlinx.css.marginTop
import kotlinx.css.maxHeight
import kotlinx.css.minHeight
import kotlinx.css.pc
import kotlinx.css.px
import kotlinx.css.textAlign
import kotlinx.css.width
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1
import styled.css
import styled.styledDiv

data class CoverPhotosState(val presentations: List<Presentation>) : RState

data class Speaker(
    val name: String,
    val photoUrl: String,
)

sealed class Presentation {
    data class WithKnownSpeaker(
        val speaker: Speaker,
        val topic: String,
    ) : Presentation()

    object FreeSlot : Presentation()
}


@JsExport
class CoverPhotos(props: RProps) : RComponent<RProps, CoverPhotosState>(props) {

    init {
        state = CoverPhotosState(
            presentations = listOf(
                Presentation.WithKnownSpeaker(
                    speaker = Speaker(
                        name = "Piotr Krzemiński",
                        photoUrl = "Piotr.jpg",
                    ),
                    topic = "10 surprises when coming from Java"
                ),
                Presentation.WithKnownSpeaker(
                    speaker = Speaker(
                        name = "Grzegorz Witczak",
                        photoUrl = "GrzegorzWitczak.jpg",
                    ),
                    topic = "Kotlin Coroutines basics for Java developers",
                ),
            ),
        )
    }

    override fun RBuilder.render() {
        h1 { +"Facebook event cover (1920x1005)" }
        styledDiv {
            css {
                // https://louisem.com/5992/facebook-event-image-size
                width = 1920.px
                height = 1005.px
                display = Display.flex
                backgroundImage = Image("url('Kotlin_UG_pattern.png')")
                backgroundPosition = "center center"
            }
            state.presentations.forEach { presentation ->
                styledDiv {
                    css {
                        color = Color.white
                        fontSize = 70.px
                        fontFamily = "sans-serif"
                        display = Display.flex
                        flexDirection = FlexDirection.column
                        flexGrow = 1.0
                        width = 50.pc
                        textAlign = TextAlign.center
                    }
                    styledDiv {
                        css {
                            width = 550.px
                            minHeight = 550.px
                            height = 550.px
                            maxHeight = 550.px
                            borderRadius = 275.px
                            backgroundImage = when (presentation) {
                                is Presentation.WithKnownSpeaker ->
                                    Image("url('${presentation.speaker.photoUrl}')")
                                is Presentation.FreeSlot ->
                                    Image("url('https://www.nufcblog.com/wp-content/uploads/2015/01/mystery-man-225x300.png')")
                            }
                            backgroundPosition = "center center"
                            backgroundSize = "cover"
                            margin = "0 auto"
                            marginTop = 50.px
                            marginBottom = 30.px
                        }
                    }
                    styledDiv {
                        css {
                            fontWeight = FontWeight.bold
                        }
                        when (presentation) {
                            is Presentation.WithKnownSpeaker -> +presentation.speaker.name
                            is Presentation.FreeSlot -> +"Free slot!"
                        }
                    }
                    styledDiv {
                        if (presentation is Presentation.WithKnownSpeaker) {
                            +"\"${presentation.topic}\""
                        }
                    }
                }
            }
        }
        h1 { +"YouTube thumbnails (1280x720)" }
        youTubeThumbnail(state.presentations)
        state.presentations.forEach { presentation ->
            youTubeThumbnail(state.presentations.filterNot { it == presentation })
        }
    }

    private fun RBuilder.youTubeThumbnail(presentations: List<Presentation>) {
        styledDiv {
            css {
                // https://support.google.com/youtube/answer/72431?hl=en#zippy=%2Cimage-size-resolution
                width = 1280.px
                height = 720.px
                display = Display.flex
                backgroundImage = Image("url('Kotlin_UG_pattern.png')")
                backgroundPosition = "center center"
                backgroundSize = "cover"
            }
            presentations.forEach { presentation ->
                styledDiv {
                    css {
                        color = Color.white
                        fontSize = 40.px
                        fontFamily = "sans-serif"
                        display = Display.flex
                        flexDirection = FlexDirection.column
                        flexGrow = 1.0
                        width = 50.pc
                        textAlign = TextAlign.center
                    }
                    styledDiv {
                        css {
                            width = 450.px
                            minHeight = 450.px
                            height = 450.px
                            maxHeight = 450.px
                            borderRadius = 225.px
                            backgroundImage = when (presentation) {
                                is Presentation.WithKnownSpeaker ->
                                    Image("url('${presentation.speaker.photoUrl}')")
                                is Presentation.FreeSlot ->
                                    Image("url('https://www.nufcblog.com/wp-content/uploads/2015/01/mystery-man-225x300.png')")
                            }
                            backgroundPosition = "center center"
                            backgroundSize = "cover"
                            margin = "0 auto"
                            marginTop = 50.px
                            marginBottom = 30.px
                        }
                    }
                    styledDiv {
                        css {
                            fontWeight = FontWeight.bold
                        }
                        when (presentation) {
                            is Presentation.WithKnownSpeaker -> +presentation.speaker.name
                            is Presentation.FreeSlot -> +"Free slot!"
                        }
                    }
                    styledDiv {
                        if (presentation is Presentation.WithKnownSpeaker) {
                            +"\"${presentation.topic}\""
                        }
                    }
                }
            }
        }
        styledDiv {
            css {
                height = 50.px
            }
        }
    }
}

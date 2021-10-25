import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.FontWeight
import kotlinx.css.Image
import kotlinx.css.Position
import kotlinx.css.TextAlign
import kotlinx.css.backgroundColor
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
import kotlinx.css.left
import kotlinx.css.margin
import kotlinx.css.marginBottom
import kotlinx.css.marginTop
import kotlinx.css.maxHeight
import kotlinx.css.minHeight
import kotlinx.css.pc
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.textAlign
import kotlinx.css.top
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
                        name = "Andrzej Zabost",
                        photoUrl = "AndrzejZabost.jpg",
                    ),
                    topic = "Forward compatible enums serialization in Kotlin"
                ),
                Presentation.WithKnownSpeaker(
                    speaker = Speaker(
                        name = "Piotr Prus",
                        photoUrl = "PiotrPrus.jpg",
                    ),
                    topic = "Meet Kotlin Multiplatform Mobile (KMM)",
                ),
            ),
        )
    }

    override fun RBuilder.render() {
        meetupPhoto()

        facebookCoverPhoto()

        h1 { +"YouTube thumbnails (1280x720)" }
        youTubeThumbnail(state.presentations)
        state.presentations.forEach { presentation ->
            youTubeThumbnail(state.presentations.filterNot { it == presentation })
        }
    }

    private fun RBuilder.meetupPhoto() {
        h1 { +"Meetup photo (1200x675)" }
        styledDiv {
            css {
                width = 1200.px
                height = 675.px
                position = Position.relative
                backgroundImage = Image("url('Kotlin_UG_pattern.png')")
                backgroundPosition = "center center"
                color = Color.white
                fontFamily = "sans-serif"
            }
            styledDiv {
                css {
                    position = Position.absolute
                    width = 70.px
                    height = 70.px
                    right = 0.px
                    top = 0.px
                    backgroundImage = Image("url('Kotlin_UG_avatar.svg')")
                }
            }
            styledDiv {
                css {
                    position = Position.absolute
                    width = 70.px
                    height = 70.px
                    left = 0.px
                    top = 0.px
                    backgroundImage = Image("url('Dynatrace_Logo.svg')")
                    backgroundColor = Color.white
                    backgroundSize = "cover"
                }
            }
            styledDiv {
                css {
                    display = Display.flex
                }
                state.presentations.forEach { presentation ->
                    styledDiv {
                        css {
                            fontSize = 40.px
                            display = Display.flex
                            flexDirection = FlexDirection.column
                            flexGrow = 1.0
                            width = 50.pc
                            textAlign = TextAlign.center
                        }
                        styledDiv {
                            css {
                                width = 300.px
                                minHeight = 300.px
                                height = 300.px
                                maxHeight = 300.px
                                borderRadius = 150.px
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
                    fontSize = 25.px
                    textAlign = TextAlign.center
                }
                styledDiv {
                    css {
                        fontWeight = FontWeight.bold
                    }
                    +"Where"
                }
                styledDiv {
                    +"Dynatrace Office, al. Grunwaldzka 411, Gdańsk"
                }
                styledDiv {
                    css {
                        fontWeight = FontWeight.bold
                    }
                    +"When"
                }
                styledDiv {
                    +"December 2nd, 17:30"
                }
            }
        }
    }

    private fun RBuilder.facebookCoverPhoto() {
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

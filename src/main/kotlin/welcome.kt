import csstype.BackgroundImage
import csstype.BackgroundPosition
import csstype.BackgroundSize.Companion.cover
import csstype.Display
import csstype.FlexDirection
import csstype.FlexGrow
import csstype.FontFamily
import csstype.FontWeight.Companion.bold
import csstype.Length
import csstype.Margin
import csstype.NamedColor
import csstype.Position
import csstype.TextAlign
import csstype.pc
import csstype.px
import react.ChildrenBuilder
import react.FC
import react.Props
import react.State
import react.css.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.useState

data class CoverPhotosState(val presentations: List<Presentation>) : State

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

val CoverPhotos = FC<Props>("CoverPhotos") {
    val (state, setState) = useState(
        CoverPhotosState(
            presentations = listOf(
                Presentation.WithKnownSpeaker(
                    speaker = Speaker(
                        name = "Piotr Mionskowski",
                        photoUrl = "PiotrMionskowski.jpeg",
                    ),
                    topic = "KSP for lazy devs"
                ),
                Presentation.WithKnownSpeaker(
                    speaker = Speaker(
                        name = "Piotr Krzemiński",
                        photoUrl = "Piotr.jpg",
                    ),
                    topic = "Non-functional aspects of Kotlin",
                ),
            ),
        )
    )

    meetupPhoto(state)

    facebookCoverPhoto(state)

    h1 { +"YouTube thumbnails (1280x720)" }
    youTubeThumbnail(state.presentations)
    state.presentations.forEach { presentation ->
        youTubeThumbnail(state.presentations.filterNot { it == presentation })
    }
}

fun ChildrenBuilder.meetupPhoto(state: CoverPhotosState) {
    h1 { +"Meetup photo (1200x675)" }
    div {
        css {
            width = 1200.px
            height = 675.px
            position = Position.relative
            backgroundImage = "url('Kotlin_UG_pattern.png')".unsafeCast<BackgroundImage>()
            backgroundPosition = BackgroundPosition.center
            color = NamedColor.white
            fontFamily = FontFamily.sansSerif
        }
        div {
            css {
                position = Position.absolute
                width = 70.px
                height = 70.px
                right = 0.px
                top = 0.px
                backgroundImage = "url('Kotlin_UG_avatar.svg')".unsafeCast<BackgroundImage>()
            }
        }
        div {
            css {
                position = Position.absolute
                width = 70.px
                height = 70.px
                left = 0.px
                top = 0.px
                backgroundImage = "url('Dynatrace_Logo.svg')".unsafeCast<BackgroundImage>()
                backgroundColor = NamedColor.white
                backgroundSize = cover
            }
        }
        div {
            css {
                display = Display.flex
            }
            state.presentations.forEach { presentation ->
                div {
                    css {
                        fontSize = 40.px
                        display = Display.flex
                        flexDirection = FlexDirection.column
                        flexGrow = FlexGrow(1.0)
                        width = 50.pc
                        textAlign = TextAlign.center
                    }
                    div {
                        css {
                            width = 300.px
                            minHeight = 300.px
                            height = 300.px
                            maxHeight = 300.px
                            borderRadius = 150.px
                            backgroundImage = when (presentation) {
                                is Presentation.WithKnownSpeaker ->
                                    "url('${presentation.speaker.photoUrl}')"
                                is Presentation.FreeSlot ->
                                    "url('https://www.nufcblog.com/wp-content/uploads/2015/01/mystery-man-225x300.png')"
                            }.unsafeCast<BackgroundImage>()
                            backgroundPosition = BackgroundPosition.center
                            backgroundSize = cover
                            margin = Margin(0.px, Length.auto)
                            marginTop = 50.px
                            marginBottom = 30.px
                        }
                    }
                    div {
                        css {
                            fontWeight = bold
                        }
                        when (presentation) {
                            is Presentation.WithKnownSpeaker -> +presentation.speaker.name
                            is Presentation.FreeSlot -> +"Free slot!"
                        }
                    }
                    div {
                        if (presentation is Presentation.WithKnownSpeaker) {
                            +"\"${presentation.topic}\""
                        }
                    }
                }
            }
        }
        div {
            css {
                fontSize = 25.px
                textAlign = TextAlign.center
            }
            div {
                css {
                    fontWeight = bold
                }
                +"Where"
            }
            div {
                +"Dynatrace Office, al. Grunwaldzka 411, Gdańsk"
            }
            div {
                css {
                    fontWeight = bold
                }
                +"When"
            }
            div {
                +"March 3rd, 17:30"
            }
        }
    }
}

fun ChildrenBuilder.facebookCoverPhoto(state: CoverPhotosState) {
    h1 { +"Facebook event cover (1920x1005)" }
    div {
        css {
            // https://louisem.com/5992/facebook-event-image-size
            width = 1920.px
            height = 1005.px
            display = Display.flex
            backgroundImage = "url('Kotlin_UG_pattern.png')".unsafeCast<BackgroundImage>()
            backgroundPosition = BackgroundPosition.center
        }
        state.presentations.forEach { presentation ->
            div {
                css {
                    color = NamedColor.white
                    fontSize = 70.px
                    fontFamily = FontFamily.sansSerif
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    flexGrow = FlexGrow(1.0)
                    width = 50.pc
                    textAlign = TextAlign.center
                }
                div {
                    css {
                        width = 550.px
                        minHeight = 550.px
                        height = 550.px
                        maxHeight = 550.px
                        borderRadius = 275.px
                        backgroundImage = when (presentation) {
                            is Presentation.WithKnownSpeaker ->
                                "url('${presentation.speaker.photoUrl}')"
                            is Presentation.FreeSlot ->
                                "url('https://www.nufcblog.com/wp-content/uploads/2015/01/mystery-man-225x300.png')"
                        }.unsafeCast<BackgroundImage>()
                        backgroundPosition = BackgroundPosition.center
                        backgroundSize = cover
                        margin = Margin(0.0.px, Length.auto)
                        marginTop = 50.px
                        marginBottom = 30.px
                    }
                }
                div {
                    css {
                        fontWeight = bold
                    }
                    when (presentation) {
                        is Presentation.WithKnownSpeaker -> +presentation.speaker.name
                        is Presentation.FreeSlot -> +"Free slot!"
                    }
                }
                div {
                    if (presentation is Presentation.WithKnownSpeaker) {
                        +"\"${presentation.topic}\""
                    }
                }
            }
        }
    }
}

fun ChildrenBuilder.youTubeThumbnail(presentations: List<Presentation>) {
    div {
        css {
            // https://support.google.com/youtube/answer/72431?hl=en#zippy=%2Cimage-size-resolution
            width = 1280.px
            height = 720.px
            display = Display.flex
            backgroundImage = "url('Kotlin_UG_pattern.png')".unsafeCast<BackgroundImage>()
            backgroundPosition = BackgroundPosition.center
            backgroundSize = cover
        }
        presentations.forEach { presentation ->
            div {
                css {
                    color = NamedColor.white
                    fontSize = 40.px
                    fontFamily = FontFamily.sansSerif
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    flexGrow = FlexGrow(1.0)
                    width = 50.pc
                    textAlign = TextAlign.center
                }
                div {
                    css {
                        width = 450.px
                        minHeight = 450.px
                        height = 450.px
                        maxHeight = 450.px
                        borderRadius = 225.px
                        backgroundImage = when (presentation) {
                            is Presentation.WithKnownSpeaker ->
                                "url('${presentation.speaker.photoUrl}')"
                            is Presentation.FreeSlot ->
                                "url('https://www.nufcblog.com/wp-content/uploads/2015/01/mystery-man-225x300.png')"
                        }.unsafeCast<BackgroundImage>()
                        backgroundPosition = BackgroundPosition.center
                        backgroundSize = cover
                        margin = Margin(0.0.px, Length.auto)
                        marginTop = 50.px
                        marginBottom = 30.px
                    }
                }
                div {
                    css {
                        fontWeight = bold
                    }
                    when (presentation) {
                        is Presentation.WithKnownSpeaker -> +presentation.speaker.name
                        is Presentation.FreeSlot -> +"Free slot!"
                    }
                }
                div {
                    if (presentation is Presentation.WithKnownSpeaker) {
                        +"\"${presentation.topic}\""
                    }
                }
            }
        }
    }
    div {
        css {
            height = 50.px
        }
    }
}

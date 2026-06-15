package uk.adbsalam.portfolio.shared.features.home.components.data

internal data class HomeScreenItem(
    val tags: List<String>,
    val title: String,
    val type: HomeItemType,
    val res: String,
    val body: String,
    val deeplink: String,
)

internal enum class HomeItemType(
    val type: String,
) {
    IMAGE_CARD("image"),
    LOTTI_CARD("lotti"),
    LOTTI_SINGLE("single_anim_lotti"),
}

internal val homeItemsList =
    listOf(
        HomeScreenItem(
            tags = listOf("gallery", "transformable", "compose"),
            title = "Transformable Gallery Sample",
            type = HomeItemType.IMAGE_CARD,
            res = "ic_in_progress",
            body = "This is a sample of gallery view, includes container transformation transitions, Full screen image views. Transformation such as pan/zoom on full screen images. Created using a custom Modifier. You can checkout the example on my github!",
            deeplink = "/gallery",
        ),
        HomeScreenItem(
            tags = listOf("Android", "Nearby Share", "Comms"),
            title = "Walkie Talkie",
            type = HomeItemType.IMAGE_CARD,
            res = "walkie",
            body = "Checkout this sample that converts your device into walkie talkie. You would need two devices, turn on this feature on both and enjoy talking to your buddy over a  wireless communication",
            deeplink = "/walkie",
        ),
        HomeScreenItem(
            tags = listOf("Android", "Paparazzi", "Snapshot", "open source", "plugin", "maven"),
            title = "SnapIt Snapshot Library",
            type = HomeItemType.IMAGE_CARD,
            res = "snapit",
            body = "Snapit is a powerful tool designed to automate the generation of Paparazzi tests by simple adding @Snapit Annotation on @Preview Functions, significantly reducing the time and resources required for creating snapshot tests. It leverages the @Preview Composables feature from the codebase, making it easy to implement and utilize. With Snapit, you can streamline your testing process and ensure the quality and reliability of your code.",
            deeplink = "blog/snapit",
        ),
        HomeScreenItem(
            tags = listOf("c++", "infra red", "arduino", "gesture", "tv"),
            title = "Gesture Remote Control",
            type = HomeItemType.IMAGE_CARD,
            res = "lotti_tv",
            body = "Gesture Remote Control for TV Application. Follow the github link for instructions, setup all your components and install code from repo. Bingo, Hand gesture remote control is ready!",
            deeplink = "/gesture",
        ),
        HomeScreenItem(
            tags = listOf("Android", "Compose", "Saogss"),
            title = "Patrolla Android Application",
            type = HomeItemType.IMAGE_CARD,
            res = "lotti_patrolla",
            body = "Patrolla is an Android Application I recently created for one of my clients (Sagoss Group). Patrolla allows functionality to allocate parking tickets to vehicles. Developed fully in compose using MVVM and MVI Design pattern. For further details please click view button.",
            deeplink = "/patrolla",
        ),
        HomeScreenItem(
            tags = listOf("youtube", "travel", "videos"),
            title = "Checkout my YouTube Channel",
            type = HomeItemType.IMAGE_CARD,
            res = "youtube",
            body = "Have a look at my travel diaries, I love to travel when I am not working. I have explored some of the most amazing places in Croatia, Italy , Switzerland and different parts of UK. Have a look and you mind find something you would like to see.",
            deeplink = "/youtube",
        ),
    )

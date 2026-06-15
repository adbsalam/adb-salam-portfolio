package uk.adbsalam.portfolio.shared.features.home

import org.jetbrains.compose.resources.DrawableResource
import portfolio.features.home.generated.resources.Res
import portfolio.features.home.generated.resources.ic_snap_it
import portfolio.features.home.generated.resources.ic_walkie_talkie
import portfolio.features.home.generated.resources.ic_youtube_channel

internal fun getDrawableRes(res: String): DrawableResource =
    when (res) {
        "snapit" -> Res.drawable.ic_snap_it
        "youtube" -> Res.drawable.ic_youtube_channel
        "walkie" -> Res.drawable.ic_walkie_talkie
        else -> Res.drawable.ic_snap_it
    }

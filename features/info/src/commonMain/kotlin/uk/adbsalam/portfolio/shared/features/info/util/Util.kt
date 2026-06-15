package uk.adbsalam.portfolio.shared.features.info.util

import org.jetbrains.compose.resources.DrawableResource
import portfolio.features.info.generated.resources.Res
import portfolio.features.info.generated.resources.ic_android
import portfolio.features.info.generated.resources.ic_bit_bucket
import portfolio.features.info.generated.resources.ic_c_plus
import portfolio.features.info.generated.resources.ic_compose_logo
import portfolio.features.info.generated.resources.ic_firebase
import portfolio.features.info.generated.resources.ic_java
import portfolio.features.info.generated.resources.ic_kotlin
import portfolio.features.info.generated.resources.ic_shop
import portfolio.features.info.generated.resources.ic_wordpress

internal fun workIcon(tag: String): DrawableResource =
    when (tag) {
        "ic_android" -> Res.drawable.ic_android
        "ic_kotlin" -> Res.drawable.ic_kotlin
        "ic_compose" -> Res.drawable.ic_compose_logo
        "ic_java" -> Res.drawable.ic_java
        "ic_firebase" -> Res.drawable.ic_firebase
        "ic_cplus" -> Res.drawable.ic_c_plus
        "ic_wordpress" -> Res.drawable.ic_wordpress
        "ic_bitbucket" -> Res.drawable.ic_bit_bucket
        "ic_shop" -> Res.drawable.ic_shop
        else -> Res.drawable.ic_android
    }

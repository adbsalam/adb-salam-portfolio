package com.adb.salam.clubmanager.shared.ui.theming

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import portfolio.ui.theming.generated.resources.Res
import portfolio.ui.theming.generated.resources.monotserrat_bold
import portfolio.ui.theming.generated.resources.monotserrat_medium
import portfolio.ui.theming.generated.resources.monotserrat_regular
import portfolio.ui.theming.generated.resources.monotserrat_semibold

@Composable
fun AppFontFamily(): FontFamily =
    FontFamily(
        fonts =
            listOf(
                Font(
                    Res.font.monotserrat_regular,
                    weight = FontWeight.W400,
                ),
                Font(
                    Res.font.monotserrat_medium,
                    weight = FontWeight.W500,
                ),
                Font(
                    Res.font.monotserrat_semibold,
                    weight = FontWeight.W600,
                ),
                Font(
                    Res.font.monotserrat_bold,
                    weight = FontWeight.W700,
                ),
            ),
    )

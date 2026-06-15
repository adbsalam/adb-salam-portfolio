package uk.adbsalam.portfolio.shared.features.info.data

data class InfographicsData(
    val infoGraphics: List<Info>,
) {
    data class Info(
        val name: String,
        val value: Float,
    )

    companion object {
        val data =
            InfographicsData(
                listOf(
                    Info(name = "Coroutine", value = 1f),
                    Info(name = "CI/CD", value = 0.9f),
                    Info(name = "REST", value = 1f),
                    Info(name = "Unit Testing", value = 1f),
                    Info(name = "E2E Testing", value = 0.8f),
                    Info(name = "Modularisation", value = 0.8f),
                    Info(name = "Flows", value = 0.9f),
                    Info(name = "Flutter", value = 0.6f),
                    Info(name = "SwiftUI", value = 0.7f),
                ),
            )
    }
}

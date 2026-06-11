package uk.adbsalam.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
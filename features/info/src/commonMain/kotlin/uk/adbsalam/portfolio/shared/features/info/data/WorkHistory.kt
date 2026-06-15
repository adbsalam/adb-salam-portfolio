package uk.adbsalam.portfolio.shared.features.info.data

import uk.adbsalam.portfolio.shared.features.info.data.WorkHistory.Work.WorkTag

data class WorkHistory(
    val workHistory: List<Work>,
) {
    data class Work(
        val company: String,
        val duration: String,
        val color: String,
        val description: String,
        val tags: List<WorkTag>,
    ) {
        data class WorkTag(
            val tag: String,
            val icon: String,
        )
    }

    companion object {
        val data =
            WorkHistory(
                workHistory =
                    listOf(
                        Work(
                            company = "AutoTrader",
                            duration = "(2023 - Current)",
                            color = "green",
                            description = "Working in a mix of Android and backend. Working on Android technologies as Kotlin, Jetpack Compose, Coroutines, MVVM, MVP, and KMP. Working on a number of backend services, its a opportunity for me to not just work/learn android but expand my logic for backend",
                            tags =
                                listOf(
                                    WorkTag(tag = "Android", icon = "ic_android"),
                                    WorkTag(tag = "Kotlin", icon = "ic_kotlin"),
                                    WorkTag(tag = "Compose", icon = "ic_compose"),
                                ),
                        ),
                        Work(
                            company = "NBrown Group Plc",
                            duration = "(2021 - 2023)",
                            color = "orange",
                            description = "Worked on Android E-Commerce apps, maintaining features and upgrades on multi-flavour Android applications. I worked closely with Data Teams to maintain and improve our Data Reporting using Firebase and Big Query. My duties included maintianing pipeline which gave me knowledge and exposure to CI/CD side of technolgy",
                            tags =
                                listOf(
                                    WorkTag(tag = "Android", icon = "ic_android"),
                                    WorkTag(tag = "Kotlin", icon = "ic_kotlin"),
                                    WorkTag(tag = "Compose", icon = "ic_compose"),
                                    WorkTag(tag = "E-Commerce", icon = "ic_shop"),
                                    WorkTag(tag = "Firebase", icon = "ic_firebase"),
                                ),
                        ),
                        Work(
                            company = "Sagoss Group",
                            duration = "(2020 - 2021)",
                            color = "purple",
                            description = "Building Parking apps from scratch that are beign used across thousands of devices in UK. I got a chance to develop apps from scrach and create app Architectures. My duties included designing apps from scratch, UX, Architectures, Test Coverage and CI/CD",
                            tags =
                                listOf(
                                    WorkTag(tag = "Android", icon = "ic_android"),
                                    WorkTag(tag = "Kotlin", icon = "ic_kotlin"),
                                    WorkTag(tag = "BitBucket", icon = "ic_bitbucket"),
                                    WorkTag(tag = "Firebase", icon = "ic_firebase"),
                                ),
                        ),
                        Work(
                            company = "Payzone Bill Payments",
                            duration = "(2019 - 2020)",
                            color = "pink",
                            description = "Building Apps for a custom built Android device that uses hardware implementations such as Bluetooth and Card payments. My experience includes working with NDK C++ to manage device hardware using Android OS. I worked on Web application alongside Android, which improved my WebView and WebApp side of knowledge",
                            tags =
                                listOf(
                                    WorkTag(tag = "Android", icon = "ic_android"),
                                    WorkTag(tag = "Kotlin", icon = "ic_kotlin"),
                                    WorkTag(tag = "Java", icon = "ic_java"),
                                    WorkTag(tag = "C++", icon = "ic_cplus"),
                                    WorkTag(tag = "Firebase", icon = "ic_firebase"),
                                ),
                        ),
                        Work(
                            company = "Free Lancing",
                            duration = "(2017 - 2019)",
                            color = "green",
                            description = "Worked as a free lancer to create Websites and Apps on a rather smaller scale for small businesses. Most of my work included WordPress and Android Native app support",
                            tags =
                                listOf(
                                    WorkTag(tag = "Android", icon = "ic_android"),
                                    WorkTag(tag = "Java", icon = "ic_java"),
                                    WorkTag(tag = "WordPress", icon = "ic_wordpress"),
                                    WorkTag(tag = "Firebase", icon = "ic_firebase"),
                                ),
                        ),
                    ),
            )
    }
}

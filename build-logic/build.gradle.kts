plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibraryConvention") {
            id = "convention.android.library"
            implementationClass = "convention.AndroidLibraryConventionPlugin"
        }
    }
}

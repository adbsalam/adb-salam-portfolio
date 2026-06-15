package convention

import com.android.build.api.dsl.androidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            project.extensions.configure<KotlinMultiplatformExtension> {
                androidLibrary {
                    namespace = deriveNamespaceFromPath(project)
                    compileSdk = 36
                    minSdk = 26
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_21)
                    }
                    androidResources {
                        enable = true
                    }
                    withHostTest {
                        isIncludeAndroidResources = true
                    }
                }
            }
        }
    }

    /**
     * Derives a namespace from the project path.
     * Example: ":ui:theming" becomes "uk.adbsalam.portfolio.ui.theming"
     */
    private fun deriveNamespaceFromPath(project: Project): String {
        val rootPackage = "uk.adbsalam.portfolio"
        val projectPath =
            project.path
                .removePrefix(":")
                .replace(":", ".")

        return if (projectPath.isNotEmpty()) {
            "$rootPackage.$projectPath"
        } else {
            rootPackage
        }
    }
}

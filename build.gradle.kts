plugins {
    kotlin("js") version "1.4.10"
}

group = "it.krzeminski"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

val kotlinVersion = "1.4.10"
val reactVersion = "17.0.1"
val jsWrappersVersion = "pre.136"

dependencies {
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains:kotlin-react:$reactVersion-$jsWrappersVersion-kotlin-$kotlinVersion")
    implementation("org.jetbrains:kotlin-react-dom:$reactVersion-$jsWrappersVersion-kotlin-$kotlinVersion")
    implementation("org.jetbrains:kotlin-styled:5.2.0-$jsWrappersVersion-kotlin-$kotlinVersion")
    implementation("org.jetbrains:kotlin-css-js:1.0.0-$jsWrappersVersion-kotlin-$kotlinVersion")
}

kotlin {
    js(LEGACY) {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}
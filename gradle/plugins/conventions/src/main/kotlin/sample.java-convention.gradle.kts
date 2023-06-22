plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

// https://github.com/gradle/gradle/issues/15383
val libs = the<VersionCatalogsExtension>().named("libs")
dependencies {
    implementation(libs.findLibrary("servlet.api").get())
}

testing {
    suites {
        @Suppress("UNUSED_VARIABLE", "UnstableApiUsage")
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

plugins {
    id("sample.java-convention")
    application
}

application {
    mainClass.set("sample.SampleTomcatApplication")
}

dependencies {
    implementation(projects.sampleSupport)
    implementation(libs.tomcat.embed.core)
}

plugins {
    id("sample.java-convention")
    application
}

application {
    mainClass.set("sample.SampleJettyApplication")
}

dependencies {
    implementation(platform(libs.jetty.bom))
    implementation(projects.sampleSupport)
    implementation(libs.jetty.servlet)
}

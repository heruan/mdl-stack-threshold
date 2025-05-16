plugins {
    java
    alias(libs.plugins.vaadin)
    alias(libs.plugins.spring.boot)
}

repositories {
    mavenCentral()
    maven("https://maven.vaadin.com/vaadin-prereleases")
}

dependencies {
    implementation(platform(libs.flow.bom))
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.bundles.vaadin)
    implementation(libs.datafaker)
}

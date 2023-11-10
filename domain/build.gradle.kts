plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.javax)

    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.mockito)
    testImplementation(libs.coroutines.test)
}

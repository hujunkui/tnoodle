import configurations.CompilerSettings.KOTLIN_JVM_TARGET
import configurations.Languages.attachRemoteRepositories

import crypto.BuildVerification.SIGNATURE_PACKAGE
import crypto.BuildVerification.SIGNATURE_SUFFIX

description = "Embeddable webserver built around the Kotlin ktor framework"

attachRemoteRepositories()

plugins {
    kotlin("jvm")
    `maven-publish`
    alias(libs.plugins.kotlin.serialization)
    id("kotlinx-atomicfu")
}

dependencies {
    api(libs.ktor.server.core)
    api(libs.kotlinx.serialization.json)
    api(libs.tnoodle.scrambles)

//    implementation(libs.kotlinx.coroutines.core)
//    implementation(libs.ktor.server.content.negotiation)
//    implementation(libs.ktor.serialization.kotlinx.json)
//    implementation(libs.ktor.server.host.common)
//    implementation(libs.ktor.server.default.headers)
//    implementation(libs.ktor.server.cors)
//    implementation(libs.ktor.server.servlet)
//    implementation(libs.bouncycastle)

    runtimeOnly(libs.logback.classic)
}

kotlin {
    jvmToolchain(KOTLIN_JVM_TARGET)
}

tasks.create("deleteSignatures") {
    doLast {
        delete(fileTree("src/main/resources/$SIGNATURE_PACKAGE").matching {
            include("**/*.$SIGNATURE_SUFFIX")
        })
    }
}

tasks.withType<ProcessResources> {
    mustRunAfter(":registerReleaseTag")
    finalizedBy("deleteSignatures")
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            group = project.group
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
        }
    }
    repositories {
        maven {
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials {
                username = System.getenv("NEXUS_USERNAME")
                password = System.getenv("NEXUS_PASSWORD")
            }
        }
    }
}




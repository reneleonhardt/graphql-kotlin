description = "GraphQL Kotlin common utilities to generate a client."

plugins {
    id("com.expediagroup.graphql.conventions")
}

dependencies {
    api(projects.graphqlKotlinClient)
    api(libs.graphql.java) {
        exclude(group = "com.graphql-java", module = "java-dataloader")
    }
    api(libs.poet)
    api(libs.kotlinx.serialization.json)
    implementation(libs.jackson)
    implementation(libs.ktor.client.apache)
    implementation(libs.ktor.serialization.jackson) {
        exclude(group = "com.fasterxml.jackson.core", module = "jackson-databind")
        exclude(group = "com.fasterxml.jackson.module", module = "jackson-module-kotlin")
    }
    implementation(libs.ktor.client.content)
    implementation(libs.slf4j)
    testImplementation(projects.graphqlKotlinClientJackson)
    testImplementation(projects.graphqlKotlinClientSerialization)
    testImplementation(libs.wiremock.lib)
    testImplementation(libs.compile.testing)
    testImplementation(libs.icu)
    testImplementation(libs.junit.params)
    // compile testing workaround -> explicit dependencies for compiler/annotation-processing
    testImplementation(libs.kotlin.annotation.processing)
    testImplementation(libs.kotlin.compiler)
    testImplementation(libs.kotlin.serialization)
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    constraints {
        implementation("commons-codec:commons-codec") {
            version {
                strictly("[1.13, 2[")
                prefer("1.16.0")
            }
            because("Cxeb68d52e-5509: Apache commons-codec before 1.13 is vulnerable to information exposure. https://devhub.checkmarx.com/cve-details/Cxeb68d52e-5509/")
        }
    }
}

tasks {
    jacocoTestCoverageVerification {
        violationRules {
            rule {
                limit {
                    counter = "INSTRUCTION"
                    value = "COVEREDRATIO"
                    minimum = "0.90".toBigDecimal()
                }
                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.75".toBigDecimal()
                }
            }
        }
    }
}

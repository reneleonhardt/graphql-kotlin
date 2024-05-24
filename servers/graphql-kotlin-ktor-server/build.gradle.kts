description = "GraphQL plugin for Ktor servers"

plugins {
    id("com.expediagroup.graphql.conventions")
}

dependencies {
    api(projects.graphqlKotlinServer)
    api(projects.graphqlKotlinFederation)
    api(libs.ktor.serialization.jackson)
    api(libs.ktor.server.core)
    api(libs.ktor.server.content)
    api(libs.ktor.server.websockets)
    api(libs.ktor.server.statuspages)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.ktor.client.content)
    testImplementation(libs.ktor.client.websockets)
    testImplementation(libs.ktor.server.cio)
    testImplementation(libs.ktor.server.test.host)
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
                    minimum = "0.70".toBigDecimal()
                }
                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.45".toBigDecimal()
                }
            }
        }
    }
}

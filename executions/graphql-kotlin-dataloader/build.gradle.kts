description = "Graphql Kotlin Data Loader"

plugins {
    id("com.expediagroup.graphql.conventions")
}

dependencies {
    api(libs.dataloader)
    api(libs.graphql.java)
    testImplementation(libs.reactor.core)
    testImplementation(libs.reactor.extensions)
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    jacocoTestCoverageVerification {
        violationRules {
            rule {
                limit {
                    counter = "INSTRUCTION"
                    value = "COVEREDRATIO"
                    minimum = "0.52".toBigDecimal()
                }
                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.50".toBigDecimal()
                }
            }
        }
    }
}

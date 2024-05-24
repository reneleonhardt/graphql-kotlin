description = "Automatic Persisted Queries"

plugins {
    id("com.expediagroup.graphql.conventions")
}

dependencies {
    api(libs.graphql.java)
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
                    minimum = "0.76".toBigDecimal()
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

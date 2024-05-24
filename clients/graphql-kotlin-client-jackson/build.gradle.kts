description = "GraphQL client serializer based on Jackson"

plugins {
    id("com.expediagroup.graphql.conventions")
}

dependencies {
    api(projects.graphqlKotlinClient)
    api(libs.jackson)
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
                    minimum = "0.85".toBigDecimal()
                }
            }
        }
    }
}

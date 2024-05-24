description = "GraphQL client serializer based on kotlinx.serialization"

plugins {
    id("com.expediagroup.graphql.conventions")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.graphqlKotlinClient)
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.serialization.json)
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
                    // increase it when https://github.com/Kotlin/kotlinx.serialization/issues/961 is resolved
                    minimum = "0.70".toBigDecimal()
                }
            }
        }
    }
}

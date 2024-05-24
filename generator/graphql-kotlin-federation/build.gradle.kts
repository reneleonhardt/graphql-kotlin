description = "Federated GraphQL schema generator"

plugins {
    id("com.expediagroup.graphql.conventions")
}

dependencies {
    api(projects.graphqlKotlinSchemaGenerator)
    api(libs.federation) {
        exclude(group = "com.graphql-java", module = "graphql-java")
    }
    api(libs.graphql.java)
    testImplementation(libs.reactor.core)
    testImplementation(libs.reactor.extensions)
    testImplementation(libs.junit.params)
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
                    minimum = "0.95".toBigDecimal()
                }
                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.80".toBigDecimal()
                }
            }
        }
    }
}

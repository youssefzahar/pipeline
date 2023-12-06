pipeline {
    agent any
    environment {
        registry = "youssefzz/testdocker"
        registryCredential = '64ca017e-ac96-4507-9616-f9dfc2a84c4f'
        dockerImage = ''

    }
    tools {
            nodejs 'nodejs21.2.0'
        }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/youssefzahar/pipeline.git'
            }
        }
        stage('MVN Clean') {
            steps {
                sh "mvn clean"
            }
        }
        stage('MVN Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('MVN Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('MVN SONARQUBE / Nexus') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
               // sh 'mvn -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml sonar:sonar -Pcoverage'
             //   sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar && mvn -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml sonar:sonar -Pcoverage"
                }
        }
        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'

            }
        }

    }
}

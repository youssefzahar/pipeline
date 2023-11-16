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

        stage('Test with JACOCO') {
                    steps {
                        sh "mvn test jacoco:report"
                    }
                }

        stage('JACOCO Coverage') {
                    steps {
                        jacoco(execPattern: '**/target/jacoco.exec')
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



        stage('Building image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Deploy image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Docker Compose Up') {
            steps {
                script {
                    sh "docker compose up -d"
                }
            }
        }
stage('Execute Front-Build Pipeline') {
            steps {
                echo 'Executing Front-Build'

                // Trigger the secondary pipeline (PipelineB)
                build job: 'Front-Build', wait: true

                // Note: 'wait: true' waits for the completion of the triggered pipeline
                // You can omit it if you want to trigger and move on without waiting
            }
        }

    }

    post {
        success {
            emailext subject: 'Pipeline Successful',
                body: 'The Jenkins Pipeline for My Spring Boot App succeeded!',
                to: 'youssef.zahar@esprit.tn'
        }
        failure {
            emailext subject: 'Pipeline Failed',
                body: 'The Jenkins Pipeline for My Spring Boot App failed.',
                to: 'youssef.zahar@esprit.tn'
        }
    }
}

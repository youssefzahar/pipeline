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

    }
}

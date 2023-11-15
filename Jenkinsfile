pipeline {
    agent any
    environment {
        registry = "youssefzz/testdocker"
        registryCredential = '64ca017e-ac96-4507-9616-f9dfc2a84c4f'
        dockerImage = ''
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
        stage('MVN SONARQUBE') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }
        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
        stage('Build Front') {
                steps {
                    git branch: 'front', url: 'https://github.com/youssefzahar/pipeline.git'
                    script {
                        sh 'npm install -g npm@latest'
                        sh 'npm install --force'
                        sh 'npm run build'
                    }
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

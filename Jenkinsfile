pipeline {
    agent any
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
        stage('MVN SONARQUBE') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }
         stage('Test') {
            steps {
                sh "mvn test"
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
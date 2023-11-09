pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/youssefzahar/pipeline.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
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

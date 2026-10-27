pipeline {
    agent any

    stages {
        stage('Check Tools') {
            steps {
                bat 'whoami'
                bat 'mvn -version'
                bat 'docker --version'
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t devops-cicd-app:1.0 .'
            }
        }
    }

    post {
        success {
            echo 'Build and Docker image creation completed!'
        }
        failure {
            echo 'Pipeline failed. Check the console output.'
        }
    }
}
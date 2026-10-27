
pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.11'
        MAVEN_HOME = 'C:\\Users\\HP\\Downloads\\apache-maven-3.10.0-bin\\apache-maven-3.10.0'
        PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Check Tools') {
            steps {
                bat 'whoami'
                bat 'java -version'
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

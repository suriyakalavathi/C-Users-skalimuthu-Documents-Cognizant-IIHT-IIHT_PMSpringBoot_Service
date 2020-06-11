
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /Users/suriya/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Publish') {
            steps {
                echo 'Publishing...'
            }
        }
        stage('Deployment') {
            steps {
                echo 'Deploying...'
            }
        }
        stage('Delivery') {
            steps {
                echo 'Delivering...'
            }
        }
    }
}
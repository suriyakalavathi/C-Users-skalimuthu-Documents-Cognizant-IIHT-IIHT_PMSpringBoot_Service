pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /Users/skalimuthu/.m2:/root/.m2'
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
                echo 'Verification In Progress...'
            }
        }
        stage('Publish') {
            steps {
                echo 'Publish In Progress...'
            }
        }
        stage('Deployment') {
            steps {
                echo 'Deployment In Progress...'
            }
        }
        stage('Delivery') {
            steps {
                echo 'Delivery In Progress...'
            }
        }
    }
}
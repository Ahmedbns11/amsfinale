pipeline {
    agent any

    tools {
        // Configure Maven tool (must be configured in Jenkins Global Tool Configuration)
        maven 'Maven-3.9' // Replace with your Maven installation name in Jenkins
        jdk 'JDK-21'      // Replace with your JDK installation name in Jenkins
    }

    environment {
        IMAGE_NAME = "ahmedbns23/ams"
        IMAGE_TAG = "v1.${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Ahmedbns11/amsfinale.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-cred',
                                                 usernameVariable: 'DOCKER_USER',
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
                    sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker compose down || true'
                sh "IMAGE_TAG=${IMAGE_TAG} docker compose up -d --build"
            }
        }
    }

    post {
        success {
            echo '✅ Déploiement réussi !'
        }
        failure {
            echo '❌ Erreur dans le pipeline !'
        }
        always {
            cleanWs()
        }
    }
}
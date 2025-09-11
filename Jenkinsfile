pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-cred')
        IMAGE_NAME = "ahmedbns23/ams"
        IMAGE_TAG = "v1.${BUILD_NUMBER}"
    }

    stages {

        stage('Création image Docker') {
            steps {
                sh "docker build -t amsfinale ."
            }
        }

        stage('Lancement de la Stack Docker-Compose') {
            steps {
                sh 'docker compose -f Docker-compose.yml down || true'
                sh 'docker compose -f Docker-compose.yml up -d'
            }
        }

        stage('Tag and Push Image to Docker Hub') {
            steps {
                echo "Tag and push image..."
                sh "docker tag amsfinale ${IMAGE_NAME}:${IMAGE_TAG}"
                sh "echo \$DOCKERHUB_CREDENTIALS_PSW | docker login -u \$DOCKERHUB_CREDENTIALS_USR --password-stdin"
                sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                sh "docker logout"
            }
            post {
                success {
                    echo "====++++ Success ++++===="
                }
                failure {
                    echo "====++++ Failed ++++===="
                }
            }
        }
    }
}

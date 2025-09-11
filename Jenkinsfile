pipeline {
    agent any

    environment {
        IMAGE_NAME = "ahmedbns23/ams"
        IMAGE_TAG = "v1.${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout to the branch') {
            steps {
                // Assurez-vous que credentialsId correspond à ton credential GitHub
                git(branch: 'master',
                    url: 'https://github.com/Ahmedbns11/amsfinale.git',
                    credentialsId: 'github-cred')
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
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
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker compose down || true'
                sh "IMAGE_TAG=${IMAGE_TAG} IMAGE_NAME=${IMAGE_NAME} docker compose up -d --build"
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

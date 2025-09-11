pipeline {
     environment {
            DOCKERHUB_CREDENTIALS = credentials('dockerhub_ahmedbns23')
        }
    agent any
    stages {

        stage('Création image Docker') {
            steps {
                sh 'docker build -t amsfinale .'
            }
        }
         stage('Lancement de la Stack Docker-Compose') {
                    steps {
                        sh 'docker compose -f Docker-compose.yml down'
                        sh 'docker compose -f Docker-compose.yml up -d'
                    }
         }
         stage('tag and push image to dockerhub') {
                     steps {
                         echo "tag and push image ..."
                         sh "docker tag ahmedbns23/ams amsfinale"
                         sh "docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW"
                         sh "docker push ahmedbns23/ams"
                         sh "docker logout"
                     }
                     post {
                         success {
                             echo "====++++success++++===="
                         }
                         failure {
                             echo "====++++failed++++===="
                         }
                     }
          }
    }
}

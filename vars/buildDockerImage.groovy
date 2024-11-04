#!/usr/bin/env groovy

// def call() {
//     echo "building the docker image..."
//     withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
//         sh 'docker build -t {docker-repo}{app name}:{appversion}.'
//         sh "echo $PASS | docker login -u $USER --password-stdin"
//         sh 'docker push {docker-repo}{app name}:{appversion}'
//     }
// }


def call(String credentialsId, String dockerFile, String imageName) {
    echo "Building the Docker image in directory 'greenlight'"
    
    dir('greenlight') {
        withCredentials([usernamePassword(credentialsId: credentialsId, passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            // Build Docker image
            sh "docker build -f ${dockerFile} -t ${imageName} ."

            // Login and push the Docker image
            sh "echo \$PASS | docker login -u \$USER --password-stdin"
            sh "docker push ${imageName}"
        }
    }
}


def buildApp() {
    echo 'building the application'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t evgenk25/demo-app:jma-2.0 ."
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh "docker push evgenk25/demo-app:jma-2.0"
    }
}

def testApp() {
    echo 'testing the application'
}

def deployApp() {
    echo 'deploying the application...'
    echo 'deploying version ${params.VERSION}'
}
return this
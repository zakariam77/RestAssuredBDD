pipeline {
    agent any

    tools{
        maven '3.9.12'
        jdk 'JDK25'
    }

    stages {
        stage('test') {

            steps {
                script{
                    if(isUnix()){
                        sh 'mvn test'
                    }else{
                        bat 'mvn test'
                    }
                }
            }
        }
    }
            post {
                always{
                     junit '**/target/surefire-reports/*.xml'
                     allure results: [[path: 'target/allure-results']]
                }
                success {
                   echo 'build success'
                }
                failure {
                        echo 'build fail'

                }
            }
}
pipeline {
    agent any

    tools{
        maven '3.9.14'
        jdk 'JDK25'
    }

    stages {
        stage('compile') {
            steps {
                script{
                    if(isUnix()){
                        sh 'mvn clean compile'
                    }else{
                        bat 'mvn clean compile'
                    }
                }
            }
        }
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
                     junit '**/target/surefire-reports/TEST-*.xml'
                     archiveArtifacts 'target/*.jar'
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
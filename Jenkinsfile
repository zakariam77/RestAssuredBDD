pipeline {
    agent any

    tools{
        maven '3.9.14'
        jdk 'JDK21'
        git '2.53.0'
    }
    parameters {
    string(name: 'TAG', defaultValue: '@Smoke', description: 'run smoke tests')
    }

    stages {
    stage('test') {

            steps {
                script{
                    if(isUnix()){
                        sh 'mvn test -Dcucumber.filter.tags="${params.TAG}"'
                    }else{
                        bat 'mvn test -Dcucumber.filter.tags="${params.TAG}"'
                    }
                }
            }
        }
    }
            post {
                always{
                        bat 'cd'
                        bat 'dir /s TEST-*.xml'

                     junit '**/target/surefire-reports/TEST-*.xml'
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
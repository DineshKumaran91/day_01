pipeline{
    agent any
    tools{
        maven 'Maven3.8.4'
    }
    stages{
        stage("Execute shell"){
            steps{
                sh '''echo "This is my first Job"
                echo $JOB_NAME'''
            }
            post{
                success{
                    echo "shell successfully completed=="
                }
                failure{
                    echo "shell failed"
                }
            }
        }
        stage ("code checkout"){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/DineshKumaran91/day_01.git']]])
            }
            post{
                success{
                    echo "successfully cloned from Repo"
                }
                failure{
                    echo "failed to clone"
                }
            }
        }
        stage('packaging'){
            steps{
                sh 'mvn -B -DskipTests clean package'
            }
            post{
                success{
                    echo "build completed"
                    archive 'target/*.jar'
                }
                failure{
                    echo "build failed"
                }
            }
        }
    }
}
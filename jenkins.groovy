pipeline{
    agent none
    stages{
        stage("Test"){
            agent any
            options {
                skipDefaultCheckout()
            }
            steps{
                echo "Hello world"
            }
        }
    }
}

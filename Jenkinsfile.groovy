pipeline {
    agent any
    stages {
		
		
		// ******ALL CODE TO BE ADDED BELOW THIS COMMENT******
		//Code starts for stage clean
    stage('Clean'){
      steps{
        sh 'mvn clean'
      }
    }
    //Code ends for stage clean

    //Code starts for stage PMD
	  stage('PMD'){
		  steps{
			  sh 'mvn site'
		  }
	  }
    //Code ends for stage PMD

    //Code starts for stage Unit Test
    stage('Unit Test'){
      steps{
        sh 'mvn test'
      }
      post{
        always{
          junit 'target/surefire-reports/*.xml'
        }
      }
    }
		//Code ends for stage Unit Test
	
	
		// ******ALL CODE TO BE ADDED ABOVE THIS COMMENT******
    }
}
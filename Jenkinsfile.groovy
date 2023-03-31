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
    // PMD is a source code analyzer to find common programming flaws like
    // unused variables, empty catch blocks, unnecessary object creation, 
    // and so forth.
    // It supports Java, JavaScript, Salesforce.com Apex, PLSQL, 
    // Apache Velocity, XML, XSL.
	  // stage('PMD'){
		//   steps{
		// 	  sh 'mvn site'
		//   }
	  // }
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

    //Code starts for stage Code Coverage
		stage('Code Coverage'){
      steps{
        sh 'mvn jacoco:report'
      }
    }
		//Code ends for stage Code Coverage
		
		//Code starts for stage Install
		stage('Install'){
      steps{
        sh 'mvn install -DskipTests'
      }
    }
		//Code ends for stage Install
		
		//Code starts for stage Launch Tomcat Server
		stage('Launch Tomcat Server'){
      steps{
        sh '/tmp/apache-tomcat-9.0.73/bin/startup.sh'
      }
    }
		//Code ends for stage Launch Tomcat Server

    //Code starts for stage system tests
		stage('Sysytem Test'){
      steps{
        git url: 'https://github.com/pradeep-tv/EMSystemTests-ex.git'
        sh 'mvn -Dtest=ExpenseManagerSystemTest test'
      }
    }
		//Code ends for stage system tests
	
	
		// ******ALL CODE TO BE ADDED ABOVE THIS COMMENT******
    }
}
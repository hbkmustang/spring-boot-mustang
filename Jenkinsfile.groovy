node {
    stage ("CHECKOUT") {
        // withCredentials([usernamePassword(credentialsId: '6c7686a5-6762-426c-968e-1758974df0f9', passwordVariable: 'Password', usernameVariable: 'Username')]) {
        sh " git clone https://github.com/hbkmustang/spring-boot-mustang-copy"
        //    }
		// checkout scm
    }

    stage ("BUILD") {
        sh "mvn clean install -f spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/"
    }
    
    stage ("putfileinJenkins") {
        // def workDir = sh(returnStdout: true, script: "pwd").trim()
        archiveArtifacts artifacts: "**/target/*.jar", fingerprint: true
    }
	
	
}

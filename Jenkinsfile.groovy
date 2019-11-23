//  Generic trigger uncheck after once
//properties([
//    pipelineTriggers([
//    ])
//])

node () {
    stage ("CHECKOUT") {
        sh "pwd"
        sh "rm -rf spring-boot-mustang/"
        // withCredentials([usernamePassword(credentialsId: '6c7686a5-6762-426c-968e-1758974df0f9', passwordVariable: 'Password', usernameVariable: 'Username')]) {
        sh " git clone https://github.com/hbkmustang/spring-boot-mustang"
        //    }
		// checkout scm
    }

	
    stage ("BUILD") {
        sh "pwd"
        sh "mvn clean install -f spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/"
    }

    
    stage ("putfileinJenkins") {
        // def workDir = sh(returnStdout: true, script: "pwd").trim()
        archiveArtifacts artifacts: "**/target/*.jar", fingerprint: true
    }

	
}


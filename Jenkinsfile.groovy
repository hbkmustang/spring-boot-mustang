//      Generic trigger uncheck after once
//properties([
//    pipelineTriggers([
//    ])
//])


node () {

    stage ("CHECKOUT") {
        // sh "pwd"
        sh "rm -rf spring-boot-mustang/"
        sh "git clone https://github.com/hbkmustang/spring-boot-mustang"
    }
	
    stage ("BUILD") {
        // sh "pwd"
        sh "mvn clean install -f spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/"
        // sh "mv spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/target/spring-boot-smoke-test-web-ui-2.2.1.BUILD-SNAPSHOT.jar spring-boot-mustang/spring-project.1.0.${env.BUILD_NUMBER}.jar"
        // sh "ls -lh spring-boot-mustang/"
        // def versionbuild = env.BUILD_NUMBER
        // echo versionbuild
    }
    
    // stage ("putfileinJenkins") {
    //    // def workDir = sh(returnStdout: true, script: "pwd").trim()
    //   archiveArtifacts artifacts: "**/target/*.jar", fingerprint: true
    // }

    stage ("UPLOAD ARTIFACT") {
        // echo "Version Build: " versionbuild
        // sh "pwd"
        nexusArtifactUploader(
            nexusVersion: 'nexus3',
            protocol: 'http',
            nexusUrl: '127.0.0.1:8081',
            groupId: 'GW',
            // version: '1.0.'+env.BUILD_NUMBER,
            version: '1.0.1',
            repository: 'spring-repo',
            credentialsId: 'nexus-credentials',
            artifacts: [
                [artifactId: 'spring-project',
                classifier: '',
                file: 'spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/target/spring-boot-smoke-test-web-ui-2.2.1.BUILD-SNAPSHOT.jar',
                type: 'jar']
            ]
        )
    }

}

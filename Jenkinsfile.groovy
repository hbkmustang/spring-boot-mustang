//      Generic trigger uncheck after once
// properties([
//    pipelineTriggers([
//    ])
//])

node () {

    stage ("CHECKOUT") {
        sh "pwd"
        sh "rm -rf spring-boot-mustang/"
        sh " git clone https://github.com/hbkmustang/spring-boot-mustang"
    }

	
    stage ("BUILD") {
        sh "pwd"
        sh "mvn clean install -f spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/"
    }


    stage ("putfileinJenkins") {
        // def workDir = sh(returnStdout: true, script: "pwd").trim()
       archiveArtifacts artifacts: "**/target/*.jar", fingerprint: true
    }


    stage ("publish to nexus") {
        nexusArtifactUploader(
            nexusVersion: 'nexus3',
            protocol: 'http',
            nexusUrl: '127.0.0.1:8081',
            groupId: 'org.springframework.boot',
            version: '2.2.1.BUILD-SNAPSHOT',
            repository: 'spring-repo',
            credentialsId: 'nexus-admin',
            artifacts: [
                [artifactId: spring-boot-smoke-test-web-ui,
                classifier: '',
                file: 'spring-boot-smoke-test-web-ui-' + version + '.jar',
                type: 'jar']
            ]
        )
    }


}

// properties([pipelineTriggers([cron('*/5 * * * *')])])

properties([
pipelineTriggers([])
])

node () {
    stage ("average") {
        sh"uptime"
        deleteDir()
    }
    stage () {
        sshagent(['uuuuuuuuuuuuu']) {
            sh "git clone -b master git@github.com:/tests.git ."
        }
    }   
    input("Please approved deploy to...")
}
node {
    stage ("gitclone") {
        withCredentials([usernamePassword(credentialsId: '6c7686a5-6762-426c-968e-1758974df0f9', passwordVariable: 'Password', usernameVariable: 'Username')]) {
            sh " git clone https://$Username:$Password@github.com/hbkmustang/spring-boot-mustang-copy"
            // sh " git clone https://$Username:$Password@github.com/hbkmustang/testtesttest"
            }
    }
    // stage ("workdir") {
    //     def workDir = sh(returnStdout: true, script: "pwd").trim()
    //     sh "cd $workDir && cd testtesttest && ls -lh"
    // }
    stage ("buildgw") {
        sh "mvn clean install -f spring-boot-mustang-copy/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/"
    }
    stage ("putfileinJenkins") {
        // def workDir = sh(returnStdout: true, script: "pwd").trim()
        archiveArtifacts artifacts: "**/target/*.jar", fingerprint: true
    } 
}

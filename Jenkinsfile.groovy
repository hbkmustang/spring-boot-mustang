properties([
    pipelineTriggers([
        GenericTrigger(
            causeString: 'Push to master', 
            genericVariables: [[
                defaultValue: '',
                key: 'ref', 
                regexpFilter: '', 
                value: '$.ref'
            ]], 
            printContributedVariables: true, 
            printPostContent: true, 
            regexpFilterExpression: 'master$', 
            regexpFilterText: '$ref', 
            silentResponse: true, 
            token: '71B6B68DFC8C34235B642BE12346ifjgk'
        )
    ])
])

node () {
	currentBuild.displayName = "#${BUILD_NUMBER} text1"
	ansiColor('xterm') {
        printlnGreen "ttexttt"
    }

    stage ("average") {
        sh"uptime"
        deleteDir()
    }
    // stage () {
    //    sshagent(['uuuuuuuuuuuuu']) {
    //        sh "git clone -b master git@github.com:/tests.git ."
    //    }
    // }   
    // input("Please approved deploy to...")
}

node {
    stage ("gitclone") {
        // withCredentials([usernamePassword(credentialsId: '6c7686a5-6762-426c-968e-1758974df0f9', passwordVariable: 'Password', usernameVariable: 'Username')]) {
        //    sh " git clone https://$Username:$Password@github.com/hbkmustang/spring-boot-mustang-copy"
        //    // sh " git clone https://$Username:$Password@github.com/hbkmustang/testtesttest"
        //    }
		sh "ls -lh"
		checkout scm
		sh "ls -lh"
    }
    // stage ("workdir") {
    //     def workDir = sh(returnStdout: true, script: "pwd").trim()
    //     sh "cd $workDir && cd testtesttest && ls -lh"
    // }
    stage ("buildgw") {
        sh "mvn clean install -f spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/"
    }
    stage ("putfileinJenkins") {
        // def workDir = sh(returnStdout: true, script: "pwd").trim()
        archiveArtifacts artifacts: "**/target/*.jar", fingerprint: true
    } 
}

def printlnGreen(text) {
    println "\033[1;4;37;42m$text\033[0m"
}


//      Generic trigger uncheck after once
//properties([
//    pipelineTriggers([
//     ])
//])



node () {

    stage ("CHECKOUT") {
        // sh "pwd"
        sh "rm -rf spring-boot-mustang/"
        sh "git clone https://github.com/hbkmustang/spring-boot-mustang"
    }
	
    stage ("BUILD") {
    
        // withMaven(
        //    // Maven installation declared in the Jenkins "Global Tool Configuration"
        //    maven: 'maven-3') {
        //    // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
        //    // We recommend to define Maven settings.xml globally at the folder level using
        //    // navigating to the folder configuration in the section "Pipeline Maven Configuration / Override global Maven configuration"
        //    // or globally to the entire master navigating to  "Manage Jenkins / Global Tools Configuration"
        //    // mavenSettingsConfig: 'my-maven-settings') {
 
        //    // Run the maven build
        //    sh "mvn clean install -f spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/" 
        //    // sh "pwd"
        //    // sh "mv spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/target/spring-boot-smoke-test-web-ui-2.2.1.BUILD-SNAPSHOT.jar spring-boot-mustang/spring-project.1.0.${env.BUILD_NUMBER}.jar"
        //    // sh "ls -lh spring-boot-mustang/"
        //    // def versionbuild = env.BUILD_NUMBER
        //    // echo versionbuild
        

        // } 
        // Run the maven build
        sh "mvn clean install -f spring-boot-mustang/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-web-ui/" 

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
            nexusUrl: '172.17.0.1:8081',
            groupId: 'GW',
            version: '1.0.'+env.BUILD_NUMBER,
            // version: '1.0.9',
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

    stage ("BUILD AND UPLOAD CONTAINER WITH ARTIFACT TO DTR") {

        // build 'docker-Instance/image-build-push'

    }

}


node {

    stage ("User choices version of docker image for deploy to proceed") {
        fileContent = sh (
            script: '/usr/local/GraduationWork/select-version/select-artifact-version.sh',
            returnStdout: true
        ).trim()

        userInputArtifact = input(id: 'userInput',    
                  message: 'Choose version of artifact for deploy (if timeout - latest will be selected): ',
                  parameters: [
                    [$class:               'ChoiceParameterDefinition', choices: fileContent, name: 'Version of Artifact']
                         ]  
        )
        echo 'User choiced version of Artifact '+userInputArtifact
    }

    stage ("User choices version of Artifact for deploy to proceed") {
        fileContent = sh (
            script: '/usr/local/GraduationWork/select-image/main.sh',
            returnStdout: true
        ).trim()

        userInputImage = input(id: 'userInput',    
                  message: 'Choose version of image for deploy (if timeout - latest will be selected): ',
                  parameters: [
                    [$class:               'ChoiceParameterDefinition', choices: fileContent, name: 'Version of Image']
                         ]  
        )
        echo 'User choiced version of Image '+userInputImage
    }

    stage ("CI DEPLOY") {
        // build 'ci-Instance/create'
        // build 'ci-Instance/provision'

        // build 'docker-Instance/create'
        // build 'docker-Instance/provision'

        // sh "/usr/local/GraduationWork/select-image/main.sh > /usr/local/GraduationWork/select-image/list-images-versions"
        // build 'Select-Image'
                
        // build 'ci-Instance/deploy'
        // build 'ci-Instance/deploy_in_docker'

        // build 'qa-Instance/deploy'
        // build 'qa-Instance/deploy_in_docker'

        // build 'docker-Instance/deploy_in_docker_repo'
    }

}

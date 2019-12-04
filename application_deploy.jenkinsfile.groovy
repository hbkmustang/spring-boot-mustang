properties([
    parameters([
        [
            $class: 'ChoiceParameter', 
            choiceType: 'PT_SINGLE_SELECT', 
            description: '', 
            filterable: false, 
            name: 'Artifact_Version', 
            randomName: 'choice-parameter-21337077649621572', 
            script: [
                $class: 'GroovyScript', 
                fallbackScript: '', 
                script: 'def sout = new StringBuilder(), serr = new StringBuilder()
                           def proc = "/usr/local/GraduationWork/select-version/select-artifact-version.sh".execute()
                           proc.consumeProcessOutput(sout, serr)
                           proc.waitForOrKill(1000)
                           println "out> $sout err> $serr"'
            ]
        ]
    ])
])

pipeline {
    agent any
    stages {
//        stage ("Find all artifact and image versions") {
//            steps {
//                fileContent = sh (
//                    script: '/usr/local/GraduationWork/select-version/select-artifact-version.sh',
//                    returnStdout: true
//                ).trim()
//            }
//            steps {
//                fileContent2 = sh (
//                    script: '/usr/local/GraduationWork/select-version/select-image-version.sh',
//                    returnStdout: true
//                ).trim()
//           }
//        }
//    }
//
//    parameters {
//        activeChoiceParam('Artifact_Version') {
//            description('Allows user choose Artifact version')
//            filterable()
//            choiceType('SINGLE_SELECT')
//            groovyScript {
//                script('filecontent')
//                fallbackScript('"fallback choice"')
//            }
//        }
//        activeChoiceParam('Image_Version') {
//            description('Allows user choose Image version')
//            filterable()
//            choiceType('SINGLE_SELECT')
//            groovyScript {
//                script('filecontent2')
//                fallbackScript('"fallback choice"')
//            }
//        }
//    }

    stage ("ENVIRONMENT VARS") {
        steps {
            echo "${env.Artifact_Version}"
            echo "${env.Image_Version}"
        }
    }


//    stages ("DEPLOY") {
//        stage ("") {
//        // build 'ci-Instance/deploy'
//        // build 'ci-Instance/deploy_in_docker'
//
//        // build 'qa-Instance/deploy'
//        // build 'qa-Instance/deploy_in_docker'
//
//        // build 'docker-Instance/deploy_in_docker_repo'
//        
//        parallel CI_Branch: {
//            stage ("CI_Deploy") {
//                build job:'ci-Instance/deploy', parameters: [string(name: 'ArtifactVersion', value: )]
//            }
//        }, Docker_Branch: {
//            stage ("Docker_Deploy") {
//                build job: 'docker-Instance/deploy_in_docker_repo', parameters: [string(name: 'ImageVersion', value: userInputImage)]
//            }
//        }, failFast: true
    }

}

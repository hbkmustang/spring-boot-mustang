// properties([
//    parameters([
//        [
//            $class: 'ChoiceParameter', 
//            choiceType: 'PT_SINGLE_SELECT', 
//            description: '', 
//            filterable: false, 
//            name: 'Artifact_Version', 
//            randomName: 'choice-parameter-21337077649621572', 
//            script: [
//                $class: 'GroovyScript', 
//                fallbackScript: '', 
//                script: '''def command = "/usr/local/GraduationWork/select-version/select-artifact-version.sh"
//                         def process = command.execute ( )
//                         process.waitFor() 
//                         def var_arim = [ ]
//                         var_arim = "${process.in.text}" .eachLine { line ->
//                         var_arim << line
//}'''
//            ]
//        ]
//    ])
//])

pipeline {
    agent any
//    parameters {
//        choice(
//            name: 'Versions',
//            choices:"3.4\n4.4",
//            description: "Build for which version?" )
//        activechoice(
//            name: 'Versions_2',
//            choices:"3.4\n4.4",
//            description: "Build for which version 2?" )
//    }        
options([parameters([[$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT', description: 'PLEASE, SELECT YOUR VERSION OF ARTIFACT FOR DEPLOY.', filterLength: 1, filterable: false, name: 'Artifact_Version', randomName: 'choice-parameter-535167446217461', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: 'return[\'error\']'], script: [classpath: [], sandbox: false, script: '''def command = "/usr/local/GraduationWork/select-version/select-artifact-version.sh"
def process = command.execute ( )
process.waitFor() 
def var_arim = [ ]
var_arim = "${process.in.text}" .eachLine { line ->
    var_arim << line
}''']]], [$class: 'ChoiceParameter', choiceType: 'PT_SINGLE_SELECT', description: 'PLEASE, SELECT YOUR VERSION OF IMAGE DOCKER FOR DEPLOY.', filterLength: 1, filterable: false, name: 'Image_Version', randomName: 'choice-parameter-535167449001810', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: 'return[\'error\']'], script: [classpath: [], sandbox: false, script: '''def command = "/usr/local/GraduationWork/select-version/select-image-version.sh"
def process = command.execute ( )
process.waitFor() 
def var_arim = [ ]
var_arim= "${process.in.text}" .eachLine { line ->
    var_arim << line
}''']]]])])

    
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

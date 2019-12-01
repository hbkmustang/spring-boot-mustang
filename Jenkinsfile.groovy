//      Generic trigger uncheck after once
//properties([
//    pipelineTriggers([
//     ])
//])



node () {

    stage ("CI DEPLOY") {
        // build 'ci-Instance/create'
        // build 'ci-Instance/provision'

        // build 'docker-Instance/create'
        // build 'docker-Instance/provision'

        sh "/usr/local/GraduationWork/select-image/main.sh > /usr/local/GraduationWork/select-image/list-images-versions"
        build 'Select-Image'
        echo env.IMAGE_VERSION
                
        // build 'ci-Instance/deploy'
        // build 'ci-Instance/deploy_in_docker'

        // build 'docker-Instance/deploy_in_docker_repo'
    }

}

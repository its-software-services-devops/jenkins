folder('GTT')

folder('GTT/DevOps-Build')

multibranchPipelineJob("GTT/DevOps-Build/astro-devops-training") {
    branchSources {
        git {
            id("astro-devops-training")
            remote("git@bitbucket.org:astrosolutions-co-th/astro-devops-training.git")
            credentialsId("PJames-Bitbucket-SSH-Key")
            includes("develop main")
        }
    }
}

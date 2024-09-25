folder('GTT')

folder('GTT/DevOps-Build')

multibranchPipelineJob('astro-devops-training) {
    branchSources {
      branchSource {
        source {
        git {
            id('astro-devops-training')
            remote('git@bitbucket.org:astrosolutions-co-th/astro-devops-training.git')
                credentialsId('PJames-Bitbucket-SSH-Key')
        }
    }
        strategy {
                    defaultBranchPropertyStrategy {
                        props {
                            noTriggerBranchProperty()
                    }
                }
            }
        }
    }
    configure {
        def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
        traits << 'jenkins.plugins.git.traits.BranchDiscoveryTrait' {}
    }
        factory {
            workflowBranchProjectFactory {
                scriptPath('Jenkinsfile')
            }
        }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
}
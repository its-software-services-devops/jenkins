folder('GTT')

folder('GTT/DevOps-Build')

multibranchPipelineJob("GTT/DevOps-Build/astro-devops-training") {
    branchSources {
        git {
            id("astro-devops-training")
            remote("git@bitbucket.org:astrosolutions-co-th/astro-devops-training.git")
            credentialsId("PJames-Bitbucket-SSH-Key")
            includes("develop, main")
        }
    }

    factory {
        remoteJenkinsFileWorkflowBranchProjectFactory {
            remoteJenkinsFile("Jenkinsfile_Common")
            localMarker("")
            matchBranches(false)
            fallbackBranch("master")
        }
    }

    configure { project ->
        def factoryNode = project.factory
        factoryNode << new org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject(owner: project)

        factoryNode << new hudson.plugins.git.GitSCM {
            userRemoteConfigs {
                new hudson.plugins.git.UserRemoteConfig {
                    url('git@bitbucket.org:astrosolutions-co-th/astro-gtt-jenkins-files.git')
                    credentialsId('PJames-Bitbucket-SSH-Key')
                }
            }

            branches {
                new hudson.plugins.git.BranchSpec {
                    name('main')
                }
            }                    
        }
    }

    triggers {
        cron('*/5 * * * *')
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
}

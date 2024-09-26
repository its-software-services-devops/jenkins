multibranchPipelineJob("astro-devops-training") 
    {
    branchSources {
        git {
            id = "astro-devops-training"
            remote("git@bitbucket.org:astrosolutions-co-th/astro-devops-training.git")
            credentialsId("PJames-Bitbucket-SSH-Key")
            includes("develop main")
        }
    }
    factory{
        remoteJenkinsFileWorkflowBranchProjectFactory{
        localMarker('')
        remoteJenkinsFile("Jenkinsfile_Common")
        remoteJenkinsFileSCM{
            gitSCM{
            userRemoteConfigs{
                userRemoteConfig{
                name("origin")
                url("git@bitbucket.org:astrosolutions-co-th/astro-gtt-jenkins-files.git")
                refspec("+refs/heads/main:refs/remotes/origin/main")
                credentialsId("PJames-Bitbucket-SSH-Key")
                }
            }
            branches {
                branchSpec {
                    name("main")
                }
            }
            browser{}
            gitTool("")
            }
        }
        }
    }
    triggers
    {
        cron('*/5 * * * *')
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
}
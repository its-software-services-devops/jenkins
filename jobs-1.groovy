folder('GTT')

folder('GTT/DevOps-Build')

multibranchPipelineJob("astro-devops-training")
{
  branchSources {
    git {
      id = "premium-service-v2"
      remote("git@bitbucket.org:astrosolutions-co-th/astro-devops-training.git")
      credentialsId("PJames-Bitbucket-SSH-Key")
      includes("development sit uat production")
    }
  }
  factory
  {
      remoteJenkinsFileWorkflowBranchProjectFactory 
      {
          remoteJenkinsFile("Jenkinsfile_Common")
          localMarker("")
          matchBranches(false)
          fallbackBranch("master")
      }
  }
  configure { project ->
      def factoryNode = project / 'factory'
      factoryNode << owner(class:'org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject', reference:'../..')
  
      factoryNode << remoteJenkinsFileSCM(class:'hudson.plugins.git.GitSCM') {
          userRemoteConfigs 
          {
              'hudson.plugins.git.UserRemoteConfig' 
              {
                  url('git@bitbucket.org:astrosolutions-co-th/astro-gtt-jenkins-files.git')
                  credentialsId('PJames-Bitbucket-SSH-Key')
              }
          }
          branches 
          {
              'hudson.plugins.git.BranchSpec' 
              {
                  name('main')
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
# Cascading IDEA Plugin

The Cascading IDEA Plugin is an add-on for the IntelliJ IDE. 

## Installing

As a work in progress (wip) release, this plugin is currently available by adding a custom plugin repository to
the IntelliJ IDEA project settings.

To add the plugin repository, see these instructions:

  * [http://www.jetbrains.com/idea/webhelp/managing-enterprise-plugin-repositories.html](http://www.jetbrains.com/idea/webhelp/managing-enterprise-plugin-repositories.html)

With this repository URL:
    
    http://files.concurrentinc.com/cascading-idea-plugin/updatePlugins.xml    

Alternately, the latest plugin can be downloaded and installed manually by calling:
    
    > wget -i http://files.concurrentinc.com/cascading-idea-plugin/1.0/latest.txt

Once the plugin is final, it will be added to the main JetBrains repository. The above repository will always point to 
wip builds.

## Features

The current release is rather simplistic. It will detect if the Driven plugin is in the current project classpath,
if so, then enable a 'View in Driven' button.

Driven is a free services for monitoring and diagnosing issues with Cascading applications.

To learn more about enabling Driven, visit:

  * http://docs.cascading.io/driven/1.0/getting-started/

### To Do

Features we would like to see by the 1.0 release:

  * Ability to run a Cascading app on a remote cluster from the IDE
  * Automatically add the latest Driven plugin to a Run/Debug configuration classpath
  * Allow for users to set an api-key in a Run/Debug configuration
  * Add any useful "intentions" or other IDE enhancements

## Building

To build using Gradle, check out the code and from the download directory do:

  > git submodule update --init
  > gradle jar
 
To run/debug in Intellij, first create an IntelliJ project:
 
  > gradle idea
  
Open the .ipr file in IntelliJ. 

A new SDK should be created in the 'Project Structure' pop up. See 
[this page](http://confluence.jetbrains.com/display/IDEADEV/Getting+Started+with+Plugin+Development#GettingStartedwithPluginDevelopment-anchor2).
for details.





# Cascading Ide Plugin

The Cascading Idea Plugin is an add-on for the IntelliJ IDE. 

## Installing

As a work in progress (wip) release, this plugin is currently only available by adding a custom plugin repository to
the IntelliJ IDEA project settings.

To add the plugin repository, see these instructions:

  * [http://www.jetbrains.com/idea/webhelp/managing-enterprise-plugin-repositories.html](http://www.jetbrains.com/idea/webhelp/managing-enterprise-plugin-repositories.html)

With this repository URL:
    
    http://files.concurrentinc.com/cascading-idea-plugin/updatePlugins.xml    
    
Once the plugin is final, it will be added to the main JetBrains repository. This repository will always point to 
wip builds.

## Features

The current release is rather simplistic. It will detect if the Driven plugin is in the current project classpath,
if so, then enable a 'View in Driven' button.

## Building

To build using Gradle, check out the code and from the download directory do:

  > git submodule update --init
  > gradle jar
 
To debug in Intellij, first create an IntelliJ project:
 
  > gradle idea
  
Open the .ipr file in Intellij. 

A new SDK should be created in the 'Project Structure' pop up. See 
[this page](http://confluence.jetbrains.com/display/IDEADEV/Getting+Started+with+Plugin+Development#GettingStartedwithPluginDevelopment-anchor2).
for details.





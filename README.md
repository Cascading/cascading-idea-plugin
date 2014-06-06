cascading-idea-plugin
=====================

The Cascading Idea Plugin is an add-on for the IntelliJ IDE. This plugin can automatically download
and add the Cascading jar files to the project's library. Users can also configure which Cascading jars
they want to add and which ones they want to exclude. Apart from the Cascading jars, this plugin also 
adds the Driven jar to the project's library. The debug pane contains a button to launch the Driven UI, 
which allows users to quickly debug their Cascading applications.

Building
---------------------

There are two ways to build this plugin--using Gradle from the command line, or using IntelliJ.

To build using Gradle, check out the code and from the download directory do:

$ gradle jar

This should assemble the plugin's jar in the build/libs/ directory. 

If you want to extend this plugin or see how it works, it is a good idea to build it using IntelliJ. To do this, check out the code, and prepare the IntelliJ workspace by:

$ gradle idea

Doing this will generate the .iml, .ipr and other files. Next, open this project in IntelliJ and select the 
SDK type to be "IDEA IC-XXXX" in your project's settings. The "XXXX" number depends on the version of IntelliJ you're using, for example IDEA IC-135.690.

Since the Gradle Idea plugin generates module files for "normal" app development, the generated .iml file
needs to be modified for IntelliJ to understand that this is a Plugin Module. To do so, replace the driven-idea-plugin.iml with [this file](https://gist.github.com/DhruvKumar/ba2047e1e0fe89844789). This will cause IntelliJ to reload the project as well. (Proceed)

Next, go  to Build menu and click on "Prepare Plugin Module cascading-idea-plugin for Deployment." This will build the plugin and create a driven-idea-plugin.jar in the root directory of the plugin.

Installing
---------------------
After building the plugin, you can add the plugin's jar using your plugin manager and you're good to go.






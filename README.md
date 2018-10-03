# README #

This is a repo with T-Systems Java School preliminary examination tasks.
Code points where you solution is to be located are marked with TODOs.

The solution is to be written using Java 1.8 only, external libraries are forbidden. 
You can add dependencies with scope "test" if it's needed to write new unit-tests.

The exam includes 3 tasks to be done: [Calculator](/tasks/Calculator.md), [Pyramid](/tasks/Pyramid.md), and 
[Subsequence](/tasks/Subsequence.md)

### Result ###

* Author name : Konstantin Bayda
* Codeship : [ ![Codeship Status for tschool/javaschoolexam](https://app.codeship.com/projects/09e4f110-4b07-0136-3dac-7eadad3282ef/status?branch=master)](https://app.codeship.com/projects/292825)

### How to start?  ###
* Install [GIT](https://git-scm.com/) and [Maven](https://maven.apache.org)
* Fork the repository 
* You're ready to go!

### How can I submit the result?  ###

* Make sure your code can be built and all tests are green (example command: "mvn clean install")
* Commit and push all changes to your repository
* Configure the build on CI server like Codeship
* Add build badge and your name to the README.md under Result section
* Check that the badge shows green build. 

### Tips and tricks for Codeship CI  ###

* Codeship uses Java 7 by default. Please refer to [this article](https://documentation.codeship.com/basic/languages-frameworks/java-and-jvm-based-languages/) to set up Java 8
* jdk_switcher is to be used in "Setup Commands" of project configuration
* Test command is "mvn -B test"
* Markdown code to add badge to your README is located in Project Settings -> Notification -> Status images 

### Useful links ###

* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)
* [Codeship](https://codeship.com)

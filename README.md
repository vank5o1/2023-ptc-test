#API-automation
Automation Testing Practical Assignment for API.

### Prerequisites
Make sure you have installed all of the following prerequisites on your development machine:
* JDK 8 - [Download and install JDK 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
* Maven - [Download & Install Maven](http://maven.apache.org/)
* JAVA_HOME System variable - [Setup & Verification](https://mkyong.com/java/how-to-set-java_home-on-windows-10/)
* MAVEN_HOME System variable - [Setup & Verification](https://mkyong.com/maven/how-to-install-maven-in-windows/)

### Run Test
From your terminal/command prompt, change directory to folder where you clone the project.
```
mvn clean test
or:
mvn clean test -DsuiteXmlFile=test-suite.xml

```

### Reporting
After run test, you can go to ``target/cucumber-report-html/cucumber-html-reports`` and see the ``file-src-test-features`` html file, then open with web browser to see the details of report.
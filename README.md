![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780) BrowserStack

# BrowserStack Examples Selenide Junit5 with Gradle
Selenide JUnit5 Gradle Integration with BrowserStack.


## Setup
* Clone the repo
* Update *.conf.json files inside the `src/test/resources/conf` directory with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings). 

## Running your tests
* To run a single test, run `gradle single` OR `./gradlew single` 
* To run local tests, run `gradle local` OR `./gradlew local` 
* To run parallel tests, run `gradle parallel` OR `./gradlew parallel` 

 Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)

## Notes
* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
* To test on a different set of browsers, check out our [platform configurator](https://www.browserstack.com/automate/java#setting-os-and-browser)
* You can export the environment variables for the Username and Access Key of your BrowserStack account. 

  ```
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```
## Parallel Execution Junit5 (Method 1 -> using @Test)
* Include files to be run in Parallel in the build.gradle file under 'parallel' task.
* Also add 'junit.jupiter.execution.parallel.enabled': 'true' in the build.gradle file to have parallel execution enabled.
* There are 2 parallel classes (Parallel1.java & Parallel2.java),each of Parallel classes have 2 tests (under @Test Tag). These methods have the capabilities specified.
* These Parallel classes have a tag called @Execution(ExecutionMode.CONCURRENT) which allows methods under these classes to run in concurrent fashion.
* Old method: multiple/ParallelWithCaps.java
* To run parallel tests with Capabilities: `gradle multiple`

## Parallel Execution Junit5 (Method 2 -> using @RepeatedTest)
* we can mention the iteration count for each method that has to be run in parallel using @RepeatedTest.
* For instance, if you want to run a single test across 3 OS/Browser/Device combination you can mention the count as 3 in @RepeatedTest and it will pick the environments from the parallel.json file and run it in parallel.
* It will work in the same fashion for all the methods that you add under @RepeatedTest Tag with the count.
* If you want to run 2 methods across 2 different OS/Browser/Device combination you can use Method 1.

## Addtional Resources
* [Documentation for writing Automate test scripts in Java](https://www.browserstack.com/automate/java)
* [Customizing your tests on BrowserStack](https://www.browserstack.com/automate/capabilities)
* [Browsers & mobile devices for selenium testing on BrowserStack](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate)
* [Using REST API to access information about your tests via the command-line interface](https://www.browserstack.com/automate/rest-api)


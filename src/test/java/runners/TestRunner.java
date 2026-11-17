package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * The entry point. JUnit starts this class and Cucumber then runs the feature files.
 *
 * Run only some scenarios by tag:  mvn test -Dcucumber.filter.tags="@smoke"
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",        // where the .feature files are
        glue = {"stepdefinitions", "hooks"},              // packages that contain steps and hooks
        plugin = {
                "pretty",                                  // readable output in the console
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true                                  // no strange colour characters in the console
)
public class TestRunner {
    //  empty - the annotations above do all the work
}

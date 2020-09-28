package za.co.interstellar.transport.shortest.path;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/"},
        glue = {"src/test/resources/"},
        plugin = {
            "pretty",
            "html:build/reports/cucumber",
             "json:build/reports/cucumber-tests/test.json"}
)
public class ShortestPathScenarioTest {

}

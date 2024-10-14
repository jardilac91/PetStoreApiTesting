package petStoreApi.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/user"},
        glue = {"petStoreApi.stepdefinitions.user"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class UserRunner {
}

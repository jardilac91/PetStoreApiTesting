package petStoreApi.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/store"},
        glue = {"petStoreApi.stepdefinitions.store"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class StoreRunner {
}

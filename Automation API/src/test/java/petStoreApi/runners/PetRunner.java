package petStoreApi.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/pets"},
        glue = {"petStoreApi.stepdefinitions.pets"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class PetRunner {
}

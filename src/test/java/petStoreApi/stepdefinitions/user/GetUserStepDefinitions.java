package petStoreApi.stepdefinitions.user;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.co.entity.user.User;
import org.co.questions.ResponseContent;
import org.co.tasks.SendGetRequestWithParams;
import petStoreApi.config.MakeAnApiRequest;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.USERNAME;
import static org.hamcrest.Matchers.equalTo;

public class GetUserStepDefinitions extends MakeAnApiRequest {

    User user = new User();

    @When("The user sends a get request to search the data of the user {string}.")
    public void theUserSendsAGetRequestToSearchTheDataOfTheUser(String username) {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(USERNAME, "username", username)
        );
    }

    @And("The id returned is {string}, the firstname is {string}.")
    public void theIdReturnedIsTheFirstnameIs(String id, String firstName) {
        user = actor.asksFor(
                ResponseContent.ofType(User.class)
        );
        actor.should(
                seeThat(
                        "The user id is: ",
                        userId -> user.getId().toString(), equalTo(id)
                ),
                seeThat(
                        "The user firstname is: ",
                        userFirstname -> user.getFirstName(), equalTo(firstName)
                )
        );
    }
}

package petStoreApi.stepdefinitions.user;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.co.questions.ResponseCode;
import org.co.tasks.SendGetRequestWithoutId;
import org.co.tasks.SendLoginUserRequest;
import petStoreApi.config.MakeAnApiRequest;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.*;
import static org.hamcrest.Matchers.equalTo;

public class LoginUserStepDefinitions extends MakeAnApiRequest {

    @Given("That the user was able to send requests to the PetStore API.")
    public void that_the_user_can_send_requests_dummy_rest_api_example() {
        sendRequests(API_URL);
    }

    @When("The user sends a get request to login using username {string} and password {string}")
    public void theUserSendsAGetRequestToLoginUsingUsernameAndPassword(String username, String password) {
        actor.attemptsTo(
                SendLoginUserRequest.using(LOGIN_USER, username, password)
        );
    }

    @When("The user sends a get request to logout the user.")
    public void theUserSendsAGetRequestToLogoutTheUser() {
        actor.attemptsTo(
                SendGetRequestWithoutId.using(LOGOUT_USER)
        );
    }


    @Then("The user should see that the response has the status {int}.")
    public void theResponseShouldReturnTheStatus(int status) {
        actor.should(
                seeThat(
                        "The status code: ", new ResponseCode(), equalTo(status)
                )
        );

    }
}


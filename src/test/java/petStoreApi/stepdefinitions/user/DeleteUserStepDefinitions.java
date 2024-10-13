package petStoreApi.stepdefinitions.user;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.co.entity.user.User;
import org.co.questions.ResponseCode;
import org.co.tasks.SendDeleteRequestWithId;
import org.co.tasks.SendGetRequestWithParams;
import org.co.tasks.SendPostRequest;
import org.co.utils.DataGenerator;
import petStoreApi.config.MakeAnApiRequest;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.*;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserStepDefinitions extends MakeAnApiRequest {

    User user = new User();
    @Given("The user created an user with random data.")
    public void theUserCreatedAnOrderWithRandomData() {
        String userId = DataGenerator.generateRandomId();
        String userName = DataGenerator.generateRandomName();
        String firstName = DataGenerator.generateRandomUserName();
        String lastName = DataGenerator.generateRandomLastName();
        String email = DataGenerator.generateRandomEmail();
        String password = DataGenerator.generateRandomPassword();
        String phone = DataGenerator.generateRandomPhone();
        String userStatus = DataGenerator.generateRandomId();


        user.setId(parseInt(userId));
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(parseInt(userStatus));

        actor.attemptsTo(
                SendPostRequest.to(CREATE_ORDER).withData(user)
        );
    }

    @When("The user sends a Delete request to delete the user created")
    public void theUserSendsADeleteRequestToDeleteTheOrderCreated() {
        actor.attemptsTo(
                SendDeleteRequestWithId.withParam(USERNAME, "username", user.getUsername())
        );
    }

    @When("The user sends a Delete request to delete a non-existent user.")
    public void theUserSendsADeleteRequestToDeleteANonExistentUser() {
        actor.attemptsTo(
                SendDeleteRequestWithId.withParam(USERNAME,"username", "user60")
        );
    }
    @And("Should see a {int} status when he searches for the user's username.")
    public void shouldSeeAStatusWhenHeSearchesForTheOrderSId(int status) {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(USERNAME, "username", user.getUsername())
        );
        actor.should(
                seeThat(
                        "The status code: ", new ResponseCode(), equalTo(status)
                )
        );
    }


}

package petStoreApi.stepdefinitions.user;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.co.entity.user.User;
import org.co.questions.ResponseContent;
import org.co.tasks.SendGetRequestWithParams;
import org.co.tasks.SendPostRequest;
import org.co.utils.DataGenerator;
import petStoreApi.config.MakeAnApiRequest;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.*;
import static org.hamcrest.Matchers.equalTo;

public class PostUserStepDefinitions extends MakeAnApiRequest {

    User user = new User();

    @Given("The user wants to create an user with random data.")
    public void theUserWantsToCreateAnUserWithRandomData() {
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

    }

    @When("The user sends a Post request to create the user.")
    public void theUserSendsAPostRequestToCreateTheUser() {
        actor.attemptsTo(
                SendPostRequest.to(CREATE_USER).withData(user)
        );
    }

    @When("The user sends a Post request to create the user with invalid id..")
    public void theUserSendsAPostRequestToCreateTheUserWithInvalidId() {
        actor.attemptsTo(
                SendPostRequest.to(CREATE_USER).withData(INVALID_USER_CREATION_REQUEST)
        );
    }

    @And("should see that the user was created in the platform.")
    public void shouldSeeThatTheUserWasCreatedInThePlatform() {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(USERNAME, "username", user.getUsername())
        );
        User responseUser = actor.asksFor(
                ResponseContent.ofType(User.class)
        );
        actor.should(
                seeThat(
                        "The user get the user Id: ",
                        orderQuantity -> responseUser.getId(), equalTo(user.getId())
                ),
                seeThat(
                        "The user get the user firstname: ",
                        orderStatus -> responseUser.getFirstName(), equalTo(user.getFirstName())
                )
        );
    }
}

package petStoreApi.stepdefinitions.pets;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

import org.co.entity.pets.Pet;
import org.co.questions.ResponseCode;
import org.co.questions.ResponseContent;
import org.co.tasks.SendGetRequestWithParams;
import petStoreApi.config.MakeAnApiRequest;


import static org.co.utils.PetStoreApiConstants.*;
import static org.hamcrest.Matchers.equalTo;

public class GetPetStepDefinitions extends MakeAnApiRequest {

    @Given("That the user was able to send requests to the PetStore API.")
    public void that_the_user_can_send_requests_dummy_rest_api_example() {
        sendRequests(API_URL);
    }


    @When("The user sends a get request using the pet id {string}.")
    public void theUserSendsAGetRequestUsingThePetId(String id) {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(PET_BY_ID, "id", id)
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

    @And("The response should contains the name {string} and the status {string}")
    public void theResponseShouldContainsTheNameAndTheStatus(String name, String status) {
        Pet responsePet = actor.asksFor(
                ResponseContent.ofType(Pet.class)
        );
        actor.should(
                seeThat(
                        "The user get the pet name: ",
                        petName -> responsePet.getName(), equalTo(name)
                ),
                seeThat(
                        "The user get the pet status: ",
                        petStatus -> responsePet.getStatus(), equalTo(status)
                )
        );
    }





}

package petStoreApi.stepdefinitions.store;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.co.entity.store.Order;
import org.co.questions.ResponseCode;
import org.co.questions.ResponseContent;
import org.co.tasks.SendGetRequestWithParams;
import petStoreApi.config.MakeAnApiRequest;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.*;
import static org.hamcrest.Matchers.equalTo;

public class GetOrderStepDefinitions extends MakeAnApiRequest {

    @Given("That the user was able to send requests to the PetStore API.")
    public void that_the_user_can_send_requests_dummy_rest_api_example() {
        sendRequests(API_URL);
    }


    @When("The user sends a get request using the order id {string}.")
    public void theUserSendsAGetRequestUsingThePetId(String id) {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(ORDER_BY_ID, "id", id)
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

    @And("The response should contains the quantity {int} and the status {string}.")
    public void theResponseShouldContainsTheQuantityAndTheStatus(int quantity, String status) {
        Order responseOrder = actor.asksFor(
                ResponseContent.ofType(Order.class)
        );
        actor.should(
                seeThat(
                        "The user get the order quantity: ",
                        orderQuantity -> responseOrder.getQuantity(), equalTo(quantity)
                ),
                seeThat(
                        "The user get the pet status: ",
                        orderStatus -> responseOrder.getStatus(), equalTo(status)
                )
        );
    }

}

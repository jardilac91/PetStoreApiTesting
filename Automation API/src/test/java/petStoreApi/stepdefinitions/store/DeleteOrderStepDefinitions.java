package petStoreApi.stepdefinitions.store;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.co.entity.store.Order;
import org.co.questions.ResponseCode;
import org.co.tasks.SendDeleteRequestWithParams;
import org.co.tasks.SendGetRequestWithParams;
import org.co.tasks.SendPostRequest;
import org.co.utils.DataGenerator;
import petStoreApi.config.MakeAnApiRequest;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.*;
import static org.hamcrest.Matchers.equalTo;

public class DeleteOrderStepDefinitions extends MakeAnApiRequest {

    private Order order;

    @Given("The user created an order with random data")
    public void theUserCreatedAnOrderWithRandomData() {
        String orderId = DataGenerator.generateRandomId();
        String petId = DataGenerator.generateRandomId();
        String quantity = DataGenerator.generateRandomId();
        String shipDate = DataGenerator.generateDate();
        String status = DataGenerator.generateRandomStatus();
        Boolean complete = DataGenerator.getRandomBoolean();

        order = new Order();
        order.setId(parseInt(orderId));
        order.setPetId(parseInt(petId));
        order.setQuantity(parseInt(quantity));
        order.setShipDate(shipDate);
        order.setStatus(status);
        order.setComplete(complete);

        actor.attemptsTo(
                SendPostRequest.to(CREATE_ORDER).withData(order)
        );
    }

    @When("The user sends a Delete request to delete the order created")
    public void theUserSendsADeleteRequestToDeleteTheOrderCreated() {
        actor.attemptsTo(
                SendDeleteRequestWithParams.withParam(ORDER_BY_ID, "id", order.getId().toString())
        );
    }

    @When("The user sends a Delete request to delete a non-existent order.")
    public void theUserSendsADeleteRequestToDeleteANonExistentOrder() {

        actor.attemptsTo(
                SendDeleteRequestWithParams.withParam(ORDER_BY_ID,"id", "2024")
        );
    }

    @And("Should see a {int} status when he searches for the order's id.")
    public void shouldSeeAStatusWhenHeSearchesForTheOrderSId(int status) {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(ORDER_BY_ID, "id", order.getId().toString())
        );
        actor.should(
                seeThat(
                        "The status code: ", new ResponseCode(), equalTo(status)
                )
        );
    }


}

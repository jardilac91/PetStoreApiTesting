package petStoreApi.stepdefinitions.store;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.co.entity.store.Order;
import org.co.entity.store.Inventory;
import org.co.questions.ResponseContent;
import org.co.tasks.SendGetRequestWithParams;
import org.co.tasks.SendGetRequestWithoutParams;
import org.co.tasks.SendPostRequest;
import org.co.utils.DataGenerator;
import petStoreApi.config.MakeAnApiRequest;

import java.util.Set;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class PostOrderStepDefinitions extends MakeAnApiRequest {

    private Order order;
    private Inventory oldInventory;
    private Inventory newInventory;
    private final Faker faker = new Faker();
    private String randomStatus;
    private String invalidOrderId;

    @Given("The user wants to create an order with random data.")
    public void theUserWantsToCreateAPetWithRandomData() {
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
    }

    @Given("The user wants to create an order with random data and a new status.")
    public void theUserWantsToCreateAnOrderWithRandomDataAndANewStatus() {
        actor.attemptsTo(
                SendGetRequestWithoutParams.using(STORE_INVENTORY)
        );
        oldInventory = actor.asksFor(
                ResponseContent.ofType(Inventory.class)
        );

        String orderId = DataGenerator.generateRandomId();
        String petId = DataGenerator.generateRandomId();
        String quantity = DataGenerator.generateRandomId();
        String shipDate = DataGenerator.generateDate();
        Boolean complete = DataGenerator.getRandomBoolean();

        Set<String> existingStatuses = oldInventory.getInventoryStatus().keySet();

        do{
            randomStatus = faker.lorem().word();
        } while (existingStatuses.contains(randomStatus));

        order = new Order();
        order.setId(parseInt(orderId));
        order.setPetId(parseInt(petId));
        order.setQuantity(parseInt(quantity));
        order.setShipDate(shipDate);
        order.setStatus(randomStatus);
        order.setComplete(complete);
    }

    @And("get the pet inventories by status")
    public void getThePetInventoriesByStatus() {
        actor.attemptsTo(
                SendGetRequestWithoutParams.using(STORE_INVENTORY)
        );

        oldInventory = actor.asksFor(
                ResponseContent.ofType(Inventory.class)
        );

    }

    @When("The user sends a Post request to create the order.")
    public void theUserSendsAPostRequestToCreateAnOrder() {
        actor.attemptsTo(
                SendPostRequest.to(CREATE_ORDER).withData(order)
        );
    }

    @When("The user sends a Post request to create the order with an invalid id.")
    public void theUserSendsAPostRequestToCreateTheOrderWithAnInvalidId() {
        actor.attemptsTo(
                SendPostRequest.to(CREATE_ORDER).withData(INVALID_ORDER_REQUEST)
        );

    }

    @And("should see that the order was created in the platform.")
    public void shouldSeeThatTheOrderWasCreatedInThePlatform() {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(ORDER_BY_ID, "id", order.getId().toString())
        );

        Order responseOrder = actor.asksFor(
                ResponseContent.ofType(Order.class)
        );

        actor.should(
                seeThat(
                        "The user get the order quantity: ",
                        orderQuantity -> responseOrder.getQuantity(), equalTo(order.getQuantity())
                ),
                seeThat(
                        "The user get the order status: ",
                        orderStatus -> responseOrder.getStatus(), equalTo(order.getStatus())
                )
        );

    }

    @And("should see that the status in the inventory increases with quantity field in the order created")
    public void shouldSeeThatTheStatusInTheInventoryIncreasesWithQuantityFieldInTheOrderCreated() {
        actor.attemptsTo(
                SendGetRequestWithoutParams.using(STORE_INVENTORY)
        );

        newInventory = actor.asksFor(
                ResponseContent.ofType(Inventory.class)
        );

        String orderStatus = order.getStatus();
        int quantity = order.getQuantity();

        actor.should(
                seeThat("The inventory has increased",
                        status -> newInventory.getStatus(orderStatus),
                        equalTo(oldInventory.getStatus(orderStatus) + quantity)
                )
        );

    }

    @And("The new status was added to the inventory.")
    public void theNewStatusWasAddedToTheInventory() {
        actor.attemptsTo(
                SendGetRequestWithoutParams.using(STORE_INVENTORY)
        );

        newInventory = actor.asksFor(
                ResponseContent.ofType(Inventory.class)
        );

        actor.should(
                seeThat("The inventory has increased",
                        inventory -> newInventory.getInventoryStatus(),
                        hasKey(order.getStatus())
                )
        );
    }



}

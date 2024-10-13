package org.co.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SendGetRequestWithId implements Task {

    private final String endpoint;
    private final String id;

    public SendGetRequestWithId(String endpoint, String id){
        this.endpoint = endpoint;
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint).with(
                        request -> request.header("Content-Type", "application/json")
                                .pathParam("id", id)

                )
        );
    }

    public static Performable using(String endpoint, String id){
        return instrumented(SendGetRequestWithId.class, endpoint, id);
    }
}

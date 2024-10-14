package org.co.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SendGetRequestWithoutParams implements Task {

    private final String endpoint;

    public SendGetRequestWithoutParams(String endpoint){
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint).with(
                        request -> request.header("Content-Type", "application/json")
                )
        );
    }

    public static Performable using(String endpoint){

        return instrumented(SendGetRequestWithoutParams.class, endpoint);
    }
}

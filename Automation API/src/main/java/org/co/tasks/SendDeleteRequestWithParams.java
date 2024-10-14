package org.co.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SendDeleteRequestWithParams implements Task  {

    private final String endpoint;

    private final String paramName;
    private final String paramValue;

    public SendDeleteRequestWithParams(String endpoint, String paramName, String paramValue){
        this.endpoint = endpoint;
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(endpoint)
                        .with(request -> request.
                                header("Content-Type", "application/json")
                                .pathParam(paramName, paramValue)
                        )
        );
    }

    public static Performable withParam(String endpoint, String paramName, String paramValue){
        return instrumented(SendDeleteRequestWithParams.class, endpoint, paramName, paramValue);
    }
}

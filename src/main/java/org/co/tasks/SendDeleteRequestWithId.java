package org.co.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.co.utils.PetStoreApiConstants.PET_BY_ID;

public class SendDeleteRequestWithId implements Task  {

    private final String endpoint;
    private final String id;

    public SendDeleteRequestWithId(String endpoint, String id){
        this.endpoint = endpoint;
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(endpoint)
                        .with(request -> request.
                                header("Content-Type", "application/json")
                                .pathParam("id", id)
                        )
        );
    }

    public static Performable using(String endpoint, String id){
        return instrumented(SendDeleteRequestWithId.class, endpoint, id);
    }
}

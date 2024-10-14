package org.co.tasks.pets;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.co.utils.PetStoreApiConstants.PET_BY_ID;

public class UpdatePetbyId implements Task  {
    private final String id;
    private final String name;
    private final String status;

    public UpdatePetbyId(String id, String name, String status){
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(PET_BY_ID)
                        .with(request -> request.
                                header("Content-Type", "application/json")
                                .pathParam("id", id)
                                .queryParam("name", name)
                                .queryParam("status", status)
                        )
        );
    }

    public static Performable withData(String id, String name, String status){
        return instrumented(UpdatePetbyId.class, id, name, status);
    }
}

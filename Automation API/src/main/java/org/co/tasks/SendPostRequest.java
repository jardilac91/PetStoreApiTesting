package org.co.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SendPostRequest<T> implements Task {

    private final T entity;
    private final String endpoint;

    public SendPostRequest(T entity, String endpoint){

        this.entity = entity;
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endpoint)
                        .with(request -> request.
                                header("Content-Type", "application/json")
                                .body(entity)
                        )
        );
    }

    public static SendPostRequestBuilder to(String endpoint){
        return new SendPostRequestBuilder(endpoint);
    }

    public static class SendPostRequestBuilder{
        private final String endpoint;

        public SendPostRequestBuilder(String endpoint){
            this.endpoint = endpoint;
        }

        public <T> Performable withData(T entity){
            return instrumented(SendPostRequest.class, entity, endpoint);
        }
    }

}

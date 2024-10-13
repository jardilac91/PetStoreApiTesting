package org.co.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SendLoginUserRequest implements Task {

    private String endpoint;
    private String username;

    private String password;



    public SendLoginUserRequest(String endpoint, String username, String password) {
        this.endpoint = endpoint;
        this.username = username;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint).with(
                        request -> request.header("Content-Type", "application/json")
                                .queryParam("username", username)
                                .queryParam("password", password)

                )
        );
    }

    public static Performable using(String endpoint, String username, String password){
        return instrumented(SendLoginUserRequest.class, endpoint, username, password);
    }

}

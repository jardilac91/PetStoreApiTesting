package petStoreApi.config;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
public class MakeAnApiRequest {
    protected static Actor actor = new Actor("User");

    protected void sendRequests(String url){
            actor.can(CallAnApi.at(url));
    }

}

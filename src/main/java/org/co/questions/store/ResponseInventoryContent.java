package org.co.questions.store;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.co.models.store.Inventory;
import org.co.models.store.Order;

public class ResponseInventoryContent implements Question<Inventory> {
    @Override
    public Inventory answeredBy(Actor actor){
        return SerenityRest.lastResponse().as(Inventory.class);
    }
}

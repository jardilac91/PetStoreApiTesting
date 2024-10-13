package org.co.questions.store;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.co.entity.store.Order;

public class ResponseOrderContent implements Question<Order> {
    @Override
    public Order answeredBy(Actor actor){
        return SerenityRest.lastResponse().as(Order.class);
    }
}

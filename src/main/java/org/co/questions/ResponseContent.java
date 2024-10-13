package org.co.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.co.models.pets.Pet;

public class ResponseContent<T> implements Question<T> {

    private final Class<T> entity;

    public ResponseContent(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public T answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(entity);
    }

    public static <T> ResponseContent<T> ofType(Class<T> entity) {
        return new ResponseContent<>(entity);
    }

}

package org.co.questions.pets;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.co.models.pets.Pet;

public class ResponsePetContent implements Question<Pet> {

    @Override
    public Pet answeredBy(Actor actor){
        return SerenityRest.lastResponse().as(Pet.class);
    }
}

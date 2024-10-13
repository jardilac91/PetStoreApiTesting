package org.co.questions.pets;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;


public class ResponsePetList implements Question<Integer>{

    public static Question<Integer> is(){
        return new ResponsePetList();
    }
    @Override
    public Integer answeredBy(Actor actor){
        return SerenityRest.lastResponse().jsonPath().getList("$").size();
    }
}

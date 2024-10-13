package petStoreApi.stepdefinitions.pets;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.co.models.pets.Pet;
import org.co.questions.pets.ResponsePetContent;
import org.co.tasks.SendGetRequestWithId;
import org.co.tasks.SendPutRequest;
import org.co.tasks.pets.UpdatePetbyId;
import org.co.utils.DataGenerator;
import petStoreApi.config.MakeAnApiRequest;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.ADD_OR_UPDATE_PET;
import static org.co.utils.PetStoreApiConstants.PET_BY_ID;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class PutPetStepDefinitions extends MakeAnApiRequest {
    Pet pet;
    String oldName;
    String oldStatus;
    String petId;


    @Given("The user sent a get request using the pet id {string}.")
    public void theUserSentAGetRequestUsingThePetId(String id) {
        petId = id;
        actor.attemptsTo(
                SendGetRequestWithId.using(PET_BY_ID, petId)
        );

        pet = SerenityRest.lastResponse().as(Pet.class);
        oldName = pet.getName();
        oldStatus = pet.getStatus();
    }


    @And("The user wants to update the pet's name and status.")
    public void theUserWantsToUpdateThePetWithTheData() {
        String randomPetName = DataGenerator.generateRandomPetName();
        String randomStatus = DataGenerator.generateRandomStatus();
        while(randomStatus.equals(oldStatus)){
            randomStatus = DataGenerator.generateRandomStatus();
        }
        pet.setName(randomPetName);
        pet.setStatus(randomStatus);
    }

    @When("The user sends a Put request to update the pet.")
    public void theUserSendsAPutRequestToUpdateThePet() {
        actor.attemptsTo(
                SendPutRequest.to(ADD_OR_UPDATE_PET).withData(pet)
        );
    }

    @When("The user sends a post request to update the pet's name and status.")
    public void theUserSendsAPostRequestToUpdateThePetSNameAndStatus() {
        String randomPetName = DataGenerator.generateRandomPetName();
        String randomStatus = DataGenerator.generateRandomStatus();
        while(randomStatus.equals(oldStatus)){
            randomStatus = DataGenerator.generateRandomStatus();
        }
        actor.attemptsTo(
                UpdatePetbyId.withData(petId, randomPetName, randomStatus)
        );
    }

    @And("The user should see that the pet's name and status were updated.")
    public void theUserShouldSeeThatThePetSNameWasUpdated() {
        Pet updatedPet = new ResponsePetContent().answeredBy(actor);
        actor.should(
                seeThat(
                        "The pet name is updated: ",
                        petname -> updatedPet.getName(), not(equalTo(oldName))
                ),
                seeThat(
                        "The pet status is updated: ",
                        petname -> updatedPet.getStatus(), not(equalTo(oldStatus))
                )
        );
    }


}
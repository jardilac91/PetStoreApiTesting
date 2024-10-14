package petStoreApi.stepdefinitions.pets;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.co.entity.pets.Category;
import org.co.entity.pets.Pet;
import org.co.entity.pets.Tag;
import org.co.questions.ResponseCode;
import org.co.tasks.SendDeleteRequestWithParams;
import org.co.tasks.SendPostRequest;
import org.co.tasks.SendGetRequestWithParams;
import org.co.utils.DataGenerator;
import petStoreApi.config.MakeAnApiRequest;

import java.util.List;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.ADD_OR_UPDATE_PET;
import static org.co.utils.PetStoreApiConstants.PET_BY_ID;
import static org.hamcrest.Matchers.equalTo;

public class DeletePetStepDefinitions extends MakeAnApiRequest {

    Pet pet;

    @Given("The user created a pet with random data")
    public void theUserCreatedAPetWithrandomData() {
        String petId = DataGenerator.generateRandomId();
        String petName = DataGenerator.generateRandomName();
        String categoryId = DataGenerator.generateRandomId();
        String categoryName = DataGenerator.generateRandomAnimalCategory();
        List<String> photoUrls = DataGenerator.generateRandomPhotoUrlList();
        List<Tag> tags = DataGenerator.generateRandomTagsList();
        String petStatus = DataGenerator.generateRandomStatus();

        pet = new Pet();
        pet.setId(parseInt(petId));
        pet.setName(petName);
        pet.setCategory(new Category(parseInt(categoryId), categoryName));
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus(petStatus);

        actor.attemptsTo(
                SendPostRequest.to(ADD_OR_UPDATE_PET).withData(pet)
        );
    }

    @When("The user sends a Delete request to delete the pet created")
    public void theUserSendsADeleteRequestToDeleteThePetCreated() {
        actor.attemptsTo(
                SendDeleteRequestWithParams.withParam(PET_BY_ID, "id", pet.getId().toString())
        );
    }

    @When("The user sends a Delete request to delete a non-existent pet.")
    public void theUserSendsADeleteRequestToDeleteANonExistentPet() {
        actor.attemptsTo(
                SendDeleteRequestWithParams.withParam(PET_BY_ID, "id", "2024")
        );
    }

    @And("Should see a {int} status when he searches for the pet's id.")
    public void shouldSeeAStatusWhenHeSearchesForThePetId(int status) {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(PET_BY_ID, "id", pet.getId().toString())
        );
        actor.should(
                seeThat(
                        "The status code: ", new ResponseCode(), equalTo(status)
                )
        );
    }


}

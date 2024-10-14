package petStoreApi.stepdefinitions.pets;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.co.entity.pets.Category;
import org.co.entity.pets.Pet;
import org.co.entity.pets.Tag;
import org.co.questions.ResponseContent;
import org.co.tasks.SendPostRequest;
import org.co.tasks.SendGetRequestWithParams;

import org.co.utils.DataGenerator;
import petStoreApi.config.MakeAnApiRequest;

import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.co.utils.PetStoreApiConstants.ADD_OR_UPDATE_PET;
import static org.co.utils.PetStoreApiConstants.PET_BY_ID;
import static org.hamcrest.Matchers.equalTo;

public class PostPetStepDefinitions extends MakeAnApiRequest {

    Pet pet;

    @Given("The user wants to create a pet with random data.")
    public void theUserWantsToCreateAPetWithRandomData() {
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
    }

    @Given("The user wants to create a pet with the data:")
    public void theUserWantsToCreateAPetWith(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        List<String> data = rows.get(1);
        Integer petId = (data.get(0) != null && !data.get(0).isEmpty()) ? parseInt(data.get(0)) : null;
        String petName = data.get(1);
        int categoryId = parseInt(data.get(2));
        String categoryName = data.get(3);
        String petStatus = data.get(4);
        List<String> photoUrls = Arrays.asList(data.get(5).split(","));
        List<Tag> tags = Arrays.stream(data.get(6).split(","))
                .map(tagData -> {
                    String[] tagParts = tagData.split("-");
                    return new Tag(parseInt(tagParts[0]), tagParts[1]);
                })
                .toList();

        pet = new Pet();
        pet.setId(petId);
        pet.setName(petName);
        pet.setCategory(new Category(categoryId, categoryName));
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus(petStatus);
    }

    @When("The user sends a Post request to create the pet.")
    public void theUserSendsAPostRequestToCreateAPet() {
        actor.attemptsTo(
                SendPostRequest.to(ADD_OR_UPDATE_PET).withData(pet)
        );

    }

    @And("should see that the pet was created in the platform.")
    public void shouldSeeThatThePetWasCreatedInThePlatform() {
        actor.attemptsTo(
                SendGetRequestWithParams.withParam(PET_BY_ID, "id", pet.getId().toString())
        );
        Pet responsePet = actor.asksFor(ResponseContent.ofType(Pet.class));
        actor.should(
                seeThat(
                        "The user get the pet name: ",
                        petName -> responsePet.getName(), equalTo(pet.getName())
                ),
                seeThat(
                        "The user get the pet status: ",
                        petStatus -> responsePet.getStatus(), equalTo(pet.getStatus())
                )
        );
    }


}

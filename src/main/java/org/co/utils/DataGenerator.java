package org.co.utils;

import com.github.javafaker.Faker;
import org.co.entity.pets.Tag;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DataGenerator {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    private static final List<String> PET_STATUS_OPTIONS = Arrays.asList(
            "Available", "Pending", "Sold"
    );

    private static final List<String> PHOTO_URL_LIST = Arrays.asList(
            "https://example.com/photo1.jpg",
            "https://example.com/photo2.jpg",
            "https://example.com/photo3.jpg",
            "https://example.com/photo4.jpg",
            "https://example.com/photo5.jpg"
    );

    private static final List<Tag> TAG_LIST = Arrays.asList(
            new Tag(1, "tag1"),
            new Tag(2, "tag2"),
            new Tag(3, "tag3"),
            new Tag(4, "tag4"),
            new Tag(5, "tag5")
    );

    private static final List<String> ORDER_STATUS_OPTIONS = Arrays.asList(
            "approved", "placed", "delivered"
    );

    public static String generateRandomId(){
        return String.valueOf(random.nextInt(85)+16);
    }

    public static String generateRandomName(){
        return faker.name().firstName();
    }

    public static String generateRandomUserName(){
        return faker.name().username();
    }
    public static String generateRandomLastName(){
        return faker.name().lastName();
    }

    public static String generateRandomEmail(){
        return faker.internet().emailAddress();
    }

    public static String generateRandomPassword(){
        return faker.internet().password();
    }

    public static String generateRandomPhone(){
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateRandomStatus(){
        return PET_STATUS_OPTIONS.get(random.nextInt(PET_STATUS_OPTIONS.size()));
    }

    public static String generateRandomAnimalCategory(){
        return faker.animal().name();
    }

    public static List<String> generateRandomPhotoUrlList(){
        Random random = new Random();
        return random.ints(1,PHOTO_URL_LIST.size())
                .distinct()
                .limit(random.nextInt(3)+1)
                .mapToObj(PHOTO_URL_LIST::get)
                .collect(Collectors.toList());
    }

    public static List<Tag> generateRandomTagsList(){
        return random.ints(1,TAG_LIST.size())
                .distinct()
                .limit(random.nextInt(3)+1)
                .mapToObj(TAG_LIST::get)
                .collect(Collectors.toList());
    }

    public static String generateDate(){
        ZonedDateTime currentDateTime = ZonedDateTime.now(java.time.ZoneOffset.UTC);
        return currentDateTime.format(DateTimeFormatter.ISO_INSTANT);
    }

    public static boolean getRandomBoolean(){
        return random.nextBoolean();
    }

    public static String generateRandomOrderStatus(){
        return ORDER_STATUS_OPTIONS.get(random.nextInt(ORDER_STATUS_OPTIONS.size()));
    }






}

package org.co.utils;

public class PetStoreApiConstants {

    private PetStoreApiConstants(){
        throw new IllegalStateException("Utility class");
    }

    public static String API_URL = "http://127.0.0.1:8080/api/v3/";
    public static String ADD_OR_UPDATE_PET = "pet";
    public static String PET_BY_ID = "pet/{id}";
    public static String ORDER_BY_ID = "store/order/{id}";
    public static String CREATE_ORDER = "store/order";
    public static String STORE_INVENTORY = "store/inventory";

    public static String CREATE_USER = "user";
    public static String LOGIN_USER = "user/login";
    public static String LOGOUT_USER = "user/logout";
    public static String USERNAME = "user/{username}";

    public static String INVALID_ORDER_REQUEST = "{\n" +
            "  \"id\": \"abc\",\n" +
            "  \"quantity\": 5,\n" +
            "  \"shipDate\": \"2024-09-12T17:32:06.284Z\",\n" +
            "  \"status\": \"placed\",\n" +
            "  \"completed\": true\n" +
            "}";

    public static String INVALID_USER_CREATION_REQUEST = "{\n" +
            "  \"id\": \"abc\",\n" +
            "  \"username\": userJhon,\n" +
            "  \"firstName\": \"John\",\n" +
            "  \"lastName\": \"James\",\n" +
            "  \"email\": \"johne@mail.com\",\n" +
            "  \"password\": \"12345\",\n" +
            "  \"phone\": \"12345\",\n" +
            "  \"userStatus\": 1\n" +
            "}";

}

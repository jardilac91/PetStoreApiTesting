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

}

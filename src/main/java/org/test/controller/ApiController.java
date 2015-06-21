package org.test.controller;

public class ApiController {

    public static final String UNUSED = "unused ";
    private static final String API_PATH = "/api/v1";

    public static final String ERRORS_URL = API_PATH + "/errors";
    public static final String CATEGORIES_URL = API_PATH + "/categories";
    public static final String SUBCATEGORIES_URL = API_PATH + "/subcategories";

    private ApiController() {
        throw new AssertionError();
    }
}

package com.sapiofan.surveys;

public class Routes {

    private Routes() {
        throw new AssertionError("non-instantiable class");
    }

    public static final String ROOT = "/main/**";

    public static final String LIST = "/list/**";

    public static final String SURVEY = "/survey/**";

    public static final String LISTOFQUESTIONS =  "/listOfQuestions/**";
}

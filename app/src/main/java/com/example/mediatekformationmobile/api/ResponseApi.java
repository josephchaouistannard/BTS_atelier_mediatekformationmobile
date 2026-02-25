package com.example.mediatekformationmobile.api;

/**
 * Structure de la réponse reçue de l'api
 * @param <T> type de la partie result
 */
public class ResponseApi<T> {
    /**
     * code standard HTTP (200, 500, ...)
     */
    private int code;
    /**
     * message correspondant au code
     */
    private String message;
    /**
     * contenu de la réponse (formats possibles : array|int|string|null
     */
    private T result;

    // Getters et setters
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getResult() { return result; }
}

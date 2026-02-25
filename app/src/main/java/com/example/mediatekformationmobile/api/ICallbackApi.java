package com.example.mediatekformationmobile.api;

/**
 * Interface pour réaliser des traitements
 * suivant le retour de l'API (succès ou erreur)
 */
public interface ICallbackApi<T> {
    void onSuccess(T result);
    void onError();
}

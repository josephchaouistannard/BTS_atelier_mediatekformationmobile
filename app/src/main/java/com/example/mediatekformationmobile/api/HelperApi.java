package com.example.mediatekformationmobile.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Interface pour accéder à l'API
 */
public class HelperApi {

    private HelperApi() {}

    // Crée l'objet d'accès à l'api avec les différentes méthodes d'accès
    private static final IRequestApi api = FormationApi.getRetrofit() //récupère l'instance unique d'accès à l'api
            .create(IRequestApi.class); // crée une instance d'une classe ananyme qui implémente l'interface

    /**
     * Envoie d'une demande à l'api et récupération de la réponse
     * @param call méthode d'envoi
     * @param callback retour
     * @param <T> type de result reçu
     */
    public static <T> void call(Call<ResponseApi<T>> call, ICallbackApi<T> callback) {
        call.enqueue(new Callback<ResponseApi<T>>() {
            @Override
            public void onResponse(Call<ResponseApi<T>> call, Response<ResponseApi<T>> response) {
                Log.d("API", "code : " + response.body().getCode() +
                        " message : " + response.body().getMessage() +
                        " result : " + response.body().getResult()
                );
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getResult());
                } else {
                    callback.onError();
                    Log.e("API", "Erreur API: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ResponseApi<T>> call, Throwable throwable) {
                callback.onError();
                Log.e("API", "Erreur API", throwable);

            }
        });
    }

    /**
     *
     * @return api
     */
    public static IRequestApi getApi(){
        return api;
    }
}

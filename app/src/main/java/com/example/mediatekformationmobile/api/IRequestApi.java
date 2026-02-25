package com.example.mediatekformationmobile.api;

import com.example.mediatekformationmobile.model.Formation;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface contenant les signatures des méthodes pour échanger avec l'API
 */
public interface IRequestApi {
    /**
     * Envoi en GET pour récupérer la liste des formations
     * le nom de la table ("formation") est ajouté à l'url
     * @return objet Call permettant d'exécuter la requête
     *         (la réponse au format ApirResponse dont la partie result
     *         contiendra une liste de Formations)
     */
    @GET("formation")
    Call<ResponseApi<List<Formation>>> getFormations();

}

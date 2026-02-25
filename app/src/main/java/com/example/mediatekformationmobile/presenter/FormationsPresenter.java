package com.example.mediatekformationmobile.presenter;

import com.example.mediatekformationmobile.api.HelperApi;
import com.example.mediatekformationmobile.api.ICallbackApi;
import com.example.mediatekformationmobile.contract.IFormationsView;
import com.example.mediatekformationmobile.model.Formation;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 'presenter dédié' à la vue qui affiche la liste des formations (FormationsActivity)
 */
public class FormationsPresenter {
    private IFormationsView vue;

    /**
     * Constructeur : valorise la propriété qui permet d'accéder à la vue
     * @param vue
     */
    public FormationsPresenter(IFormationsView vue){
        this.vue = vue;
    }

    private List<Formation> lesFormations;

    /**
     * Récupère les formations de la BDD distante et les envoie à la vue
     */
    public void chargerFormations() {
        // sollicite l'api et récupère la réponse
        HelperApi.call(HelperApi.getApi().getFormations(), new ICallbackApi<List<Formation>>(){
            @Override
            public void onSuccess(List<Formation> result) {
                if(result != null){
                    List<Formation> formations = result;
                    if (formations != null && !formations.isEmpty()) {
                        Collections.sort(formations, (p1, p2) -> p2.getPublishedAt().compareTo(p1.getPublishedAt()));
                        vue.afficherListe(formations);
                        lesFormations = formations;
                    }else{
                        vue.afficherMessage("échec chargement formations");
                    }
                }else{
                    vue.afficherMessage("échec chargement formations");
                }
            }
            @Override
            public void onError() {
                vue.afficherMessage("échec chargement formations");
            }
        });
    }

    /**
     * Filtre les formations en local dont le titre contient une chaîne passé en paramètre
     * @param titre
     */
    public void filtrerFormations(String titre) {
        if (titre != null && !titre.isEmpty()) {
            List<Formation> lesFormationsFiltrees = new ArrayList<>();
            lesFormations.forEach(formation -> {
                if (formation.getTitle().toLowerCase().contains(titre.toLowerCase())) {
                    lesFormationsFiltrees.add(formation);
                }
            });
            vue.afficherListe(lesFormationsFiltrees);
        } else {
            vue.afficherListe(lesFormations);
        }
    }

    /**
     * Demnde de transfert de la formation vers une autre activity
     * @param formation
     */
    public void transfertFormation(Formation formation){
        vue.transfertFormation(formation);
    }
}

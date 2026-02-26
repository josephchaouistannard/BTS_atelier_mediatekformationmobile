package com.example.mediatekformationmobile.contract;

import com.example.mediatekformationmobile.model.Formation;

import java.util.List;

/**
 * Contrat pour que le FormationsPresenter puisse envoyer des informations à la vue
 */
public interface IFormationsView extends IAllView {
    /**
     * Méthode permettant le transfert de la liste des formations pour affichage
     * @param formations
     */
    void afficherListe(List<Formation> formations);

    /**
     * Méthode permettant le transfert d'une formation vers une activity
     *
     * @param formation
     */
    public void transfertFormation(Formation formation);
}

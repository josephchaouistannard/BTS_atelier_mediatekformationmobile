package com.example.mediatekformationmobile.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediatekformationmobile.R;
import com.example.mediatekformationmobile.contract.IFormationsView;
import com.example.mediatekformationmobile.model.Formation;
import com.example.mediatekformationmobile.presenter.FormationsPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FormationListAdapter extends RecyclerView.Adapter<FormationListAdapter.ViewHolder> {

    private List<Formation> formations;
    private IFormationsView vue;
    private static final String ROUGE = "rouge";
    private static final String GRIS = "gris";
    private boolean ecranFavoris;

    /**
     * Constructeur : valorise les propriétés privées
     * @param formations
     * @param vue
     */
    public FormationListAdapter(List<Formation> formations, IFormationsView vue, boolean ecranFavoris){
        this.vue = vue;
        this.ecranFavoris = ecranFavoris;
        if (ecranFavoris) {
            List<Formation> lesFavoris = new ArrayList<>();
            formations.forEach(formation -> {
                if (formation.isFavorite()) {
                    lesFavoris.add(formation);
                }
                this.formations = lesFavoris;
            });
        } else {
            this.formations = formations;
        }
    }

    /**
     * Construction de la ligne
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        LayoutInflater layout = LayoutInflater.from(parentContext);
        View view = layout.inflate(R.layout.layout_liste_formation, parent, false);
        return new FormationListAdapter.ViewHolder(view);
    }

    /**
     * Remplissage de la ligne
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Formation formation = formations.get(position);
        // récupération du titre pour l'affichage
        String title = formation.getTitle();
        holder.txtListeTitle.setText(title);
        // récupération de la date pour l'affichage
        Date publishedAt = formation.getPublishedAt();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateFormatee = sdf.format(publishedAt);
        holder.txtListPublishedAt.setText(dateFormatee);
        if (formation.isFavorite()) {
            couleurCoeur(ROUGE, holder.btnListFavori);
        } else {
            couleurCoeur(GRIS, holder.btnListFavori);
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return nombre de lignes dans la liste
     */
    @Override
    public int getItemCount() {
        return formations.size();
    }

    private void couleurCoeur(String couleur, ImageButton btn) {
        if (couleur.equals(ROUGE)) {
            btn.setImageResource(R.drawable.coeur_rouge);
        } else {
            btn.setImageResource(R.drawable.coeur_gris);
        }
    }

    /**
     * Classe interne pour gérer une ligne (affichage, événements)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageButton btnListFavori;
        public final TextView txtListPublishedAt;
        public final TextView txtListeTitle;
        private FormationsPresenter presenter;

        /**
         * Constructeur : crée un lien avec les objets graphiques de la ligne
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtListeTitle = (TextView)itemView.findViewById(R.id.txtListTitle);
            txtListPublishedAt = (TextView)itemView.findViewById(R.id.txtListPublishedAt);
            btnListFavori = (ImageButton)itemView.findViewById(R.id.btnListFavori);
            init();
        }

        /**
         * initialisations
         */
        private void init(){
            presenter = new FormationsPresenter(vue, itemView.getContext());
            txtListeTitle.setOnClickListener(v -> txtListeTitleOrPublishedAtClic());
            txtListPublishedAt.setOnClickListener(v -> txtListeTitleOrPublishedAtClic());
            btnListFavori.setOnClickListener(v ->  btnListFavoriClic());
        }

        private void btnListFavoriClic() {
            int position = getBindingAdapterPosition();
            Formation formation = formations.get(position);
            if (formation.isFavorite()) {
                formation.setFavorite(false);
                presenter.supprimerFavorite(formation.getId());
                couleurCoeur(GRIS, btnListFavori);
                if (ecranFavoris) {
                    formations.remove(position);
                    notifyItemRemoved(position);
                }
            } else {
                formation.setFavorite(true);
                presenter.ajouterFavorite(formation);
                couleurCoeur(ROUGE, btnListFavori);
            }
        }

        /**
         * Clic sur un des textes de la ligne :
         * transfert de la formation vers l'activity UneFormationActivity
         */
        private void txtListeTitleOrPublishedAtClic(){
            int position = getBindingAdapterPosition();
            presenter.transfertFormation(formations.get(position));
        }

    }
}

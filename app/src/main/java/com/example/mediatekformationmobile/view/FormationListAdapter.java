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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FormationListAdapter extends RecyclerView.Adapter<FormationListAdapter.ViewHolder> {

    private List<Formation> formations;
    private IFormationsView vue;

    /**
     * Constructeur : valorise les propriétés privées
     * @param formations
     * @param vue
     */
    public FormationListAdapter(List<Formation> formations, IFormationsView vue){
        this.formations = formations;
        this.vue = vue;
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
        // récupération du titre pour l'affichage
        String title = formations.get(position).getTitle();
        holder.txtListeTitle.setText(title);
        // récupération de la date pour l'affichage
        Date publishedAt = formations.get(position).getPublishedAt();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateFormatee = sdf.format(publishedAt);
        holder.txtListPublishedAt.setText(dateFormatee);

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
            presenter = new FormationsPresenter(vue);
            txtListeTitle.setOnClickListener(v -> txtListeTitleOrPublishedAt_clic());
            txtListPublishedAt.setOnClickListener(v -> txtListeTitleOrPublishedAt_clic());
        }

        /**
         * Clic sur un des textes de la ligne :
         * transfert de la formation vers l'activity UneFormationActivity
         */
        private void txtListeTitleOrPublishedAt_clic(){
            int position = getBindingAdapterPosition();
            presenter.transfertFormation(formations.get(position));
        }

    }
}

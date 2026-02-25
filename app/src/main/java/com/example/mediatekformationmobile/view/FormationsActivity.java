package com.example.mediatekformationmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediatekformationmobile.R;
import com.example.mediatekformationmobile.contract.IFormationsView;
import com.example.mediatekformationmobile.model.Formation;
import com.example.mediatekformationmobile.presenter.FormationsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity pour afficher la liste des formations
 */
public class FormationsActivity extends AppCompatActivity implements IFormationsView {

    private FormationsPresenter presenter;
    private boolean ecranFavoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formations);
        Intent intent = getIntent();
        ecranFavoris = intent.getBooleanExtra("ecranFavoris", false);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private Button btnFiltrer;
    private EditText txtFiltre;
    /**
     * Stocke les élements graphiques nécessaires dans des propriétés privées
     */
    private void chargerObjetsGraphiques() {
        this.btnFiltrer = (Button) findViewById(R.id.btnFiltrer);
        this.txtFiltre = (EditText) findViewById(R.id.txtFiltre);
    }

    /**
     * Traitements nécessaires dès la création de l'activity
     */
    private void init(){
        presenter = new FormationsPresenter(this, this);
        presenter.chargerFormations();
        chargerObjetsGraphiques();
        btnFiltrer.setOnClickListener(v ->
                presenter.filtrerFormations(this.txtFiltre.getText().toString()));
    }

    /**
     * Méthode permettant le transfert de la liste des formations pour affichage
     *
     * @param formations
     */
    @Override
    public void afficherListe(List formations) {
        if (formations != null){
            RecyclerView lstHisto = (RecyclerView) findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(formations, FormationsActivity.this, ecranFavoris);
            lstHisto.setAdapter(adapter);
            lstHisto.setLayoutManager(new LinearLayoutManager(FormationsActivity.this));
        }
    }

    /**
     * Méthode permettant le transfert d'une formation vers une activity
     *
     * @param formation
     */
    @Override
    public void transfertFormation(Formation formation) {
        Intent intent = new Intent(FormationsActivity.this, UneFormationActivity.class);
        intent.putExtra("formation", formation);
        startActivity(intent);
    }

    /**
     * Méthode permettant d'afficher un message de type Toast
     *
     * @param message
     */
    @Override
    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
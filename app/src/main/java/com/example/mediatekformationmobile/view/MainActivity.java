package com.example.mediatekformationmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mediatekformationmobile.R;

/**
 * Activity qui affiche me menu
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton btnFormations;
    private ImageButton btnFavoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    /**
     * Traitements nécessaires dès la création de l'activity
     */
    private void init(){
        chargeObjetsGraphiques();
        creerMenu();
    }

    /**
     * Récupération des objets graphiques
     */
    private void chargeObjetsGraphiques(){
        btnFormations = findViewById(R.id.btnFormations);
        btnFavoris = findViewById(R.id.btnFavoris);
    }

    /**
     * Demande de création des écoutes sur les boutons
     */
    private void creerMenu(){
        btnFormations.setOnClickListener(v -> ecouteMenu(FormationsActivity.class));

    }

    /**
     * Ouvre l'activity correspondant au paramètre
     * @param classe
     */
    private void ecouteMenu(Class classe){
        Intent intent = new Intent(MainActivity.this, classe);
        startActivity(intent);
    }
}
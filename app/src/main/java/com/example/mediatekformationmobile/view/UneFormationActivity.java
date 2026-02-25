package com.example.mediatekformationmobile.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mediatekformationmobile.R;
import com.example.mediatekformationmobile.model.Formation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UneFormationActivity extends AppCompatActivity {

    private TextView txtPublishedAt;
    private TextView txtTitle;
    private TextView txtDescription;
    private ImageButton btnPicture;
    private Formation formation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_une_formation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Traitements nécessaires dès la création de l'activity
     */
    private void init(){
        chargeObjetsGraphiques();
        btnPicture.setOnClickListener(v -> btnPicture_clic());
        recupFormation();
    }

    /**
     * Récupération des objets graphiques
     */
    private void chargeObjetsGraphiques(){
        txtPublishedAt = (TextView) findViewById(R.id.txtPublishedAt);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        btnPicture = findViewById(R.id.btnPicture);
    }

    /**
     * Traitements réalisés lors du clic sur l'image
     */
    private void btnPicture_clic(){
        if(formation != null) {
            Intent intent = new Intent(UneFormationActivity.this, VideoActivity.class);
            intent.putExtra("formation", formation);
            startActivity(intent);
        }
    }

    /**
     * Récupère la formation envoyée par une autre activity (FormationsActivity)
     */
    private void recupFormation(){
        formation = (Formation) getIntent().getSerializableExtra("formation");
        if(formation!=null) {
            Date datePublication = formation.getPublishedAt();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateFormatee = sdf.format(datePublication);
            txtPublishedAt.setText(dateFormatee);
            txtTitle.setText(formation.getTitle());
            txtDescription.setText(formation.getDescription());
            loadMapPreview(btnPicture, formation.getPicture());
        }
    }

    /**
     * Charge une imagge à partir d'une url
     * @param img
     * @param url
     */
    private void loadMapPreview (ImageButton img, String url) {
        //start a background thread for networking
        new Thread(new Runnable() {
            public void run(){
                try {
                    //download the drawable
                    final Drawable drawable = Drawable.createFromStream((InputStream) new URL(url).getContent(), "src");
                    //edit the view in the UI thread
                    img.post(new Runnable() {
                        public void run() {
                            img.setImageDrawable(drawable);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
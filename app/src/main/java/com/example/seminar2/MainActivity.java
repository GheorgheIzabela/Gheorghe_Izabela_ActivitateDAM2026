package com.example.seminar2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_DATE = "date";
    private static final String EXTRA_GRADINA = "gradina";
    private static final String EXTRA_LISTA = "lista";

    private final ArrayList<InventarGeneralGradina> listaGradini =
            new ArrayList<>();

    private TextView textRezultat;


    private final ActivityResultLauncher<Intent> launcherAdaugare =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    rezultat -> {
                        if (rezultat.getResultCode() == RESULT_OK
                                && rezultat.getData() != null) {

                            Bundle date = rezultat.getData()
                                    .getBundleExtra(EXTRA_DATE);

                            if (date != null) {
                                InventarGeneralGradina gradina =
                                        (InventarGeneralGradina)
                                                date.getSerializable(EXTRA_GRADINA);

                                if (gradina != null) {
                                    listaGradini.add(gradina);
                                    textRezultat.setText(gradina.toString());
                                    deschideLista();
                                }
                            }
                        }
                    }
            );

    // Primește lista actualizată când revenim din ListActivity.
    private final ActivityResultLauncher<Intent> launcherLista =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    rezultat -> {
                        if (rezultat.getResultCode() == RESULT_OK
                                && rezultat.getData() != null) {

                            Bundle date = rezultat.getData()
                                    .getBundleExtra(EXTRA_DATE);

                            if (date != null) {
                                @SuppressWarnings("unchecked")
                                ArrayList<InventarGeneralGradina> listaPrimita =
                                        (ArrayList<InventarGeneralGradina>)
                                                date.getSerializable(EXTRA_LISTA);

                                if (listaPrimita != null) {
                                    listaGradini.clear();
                                    listaGradini.addAll(listaPrimita);
                                }
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnAdaugaAI = findViewById(R.id.btnAdaugaAI);
        textRezultat = findViewById(R.id.textRezultat);

        btnAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddActivity.class
            );
            launcherAdaugare.launch(intent);
        });

        btnAdaugaAI.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddAIActivity.class
            );
            launcherAdaugare.launch(intent);
        });
    }

    private void deschideLista() {
        Intent intent = new Intent(
                MainActivity.this,
                ListActivity.class
        );

        Bundle pachet = new Bundle();
        pachet.putSerializable(EXTRA_LISTA, listaGradini);


        intent.putExtra(EXTRA_DATE, pachet);

        launcherLista.launch(intent);
    }
}
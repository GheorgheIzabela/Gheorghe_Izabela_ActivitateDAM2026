package com.example.seminar2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Legam elementele din XML cu Java
        EditText scule = findViewById(R.id.editScule);
        EditText insecte = findViewById(R.id.editInsecte);
        EditText plante = findViewById(R.id.editPlante);
        EditText suprafata = findViewById(R.id.editSuprafata);

        Switch vremePloaie = findViewById(R.id.switchVremePloaie);
        Button salveaza = findViewById(R.id.btnSalveaza);

        // Ce se intampla cand apasam butonul Salveaza
        salveaza.setOnClickListener(v -> {

            // Citim datele introduse
            String s = scule.getText().toString().trim();
            String i = insecte.getText().toString().trim();
            String p = plante.getText().toString().trim();
            String textSuprafata = suprafata.getText().toString().trim();

            // Verificam campurile obligatorii
            if (s.isEmpty() || i.isEmpty() ||
                    p.isEmpty() || textSuprafata.isEmpty()) {

                Toast.makeText(this,
                        "Completeaza toate campurile!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Transformam suprafata din String in int
            int suprafataGradina;

            try {
                suprafataGradina = Integer.parseInt(textSuprafata);
            } catch (NumberFormatException e) {
                suprafata.setError("Suprafata invalida");
                return;
            }

            // Verificam daca suprafata este pozitiva
            if (suprafataGradina <= 0) {
                suprafata.setError("Introdu o suprafata mai mare ca 0");
                return;
            }

            // Citim valoarea Switch-ului
            boolean ploua = vremePloaie.isChecked();

            // Cream obiectul gradina
            InventarGeneralGradina gradina =
                    new InventarGeneralGradina(
                            s,
                            i,
                            p,
                            suprafataGradina,
                            ploua
                    );

            // Punem obiectul intr-un Bundle
            Bundle date = new Bundle();
            date.putSerializable("gradina", gradina);

            // Trimitem rezultatul catre MainActivity
            Intent intent = new Intent();
            intent.putExtra("date", date);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
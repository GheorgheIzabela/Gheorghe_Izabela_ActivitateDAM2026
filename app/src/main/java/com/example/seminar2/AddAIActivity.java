package com.example.seminar2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAIActivity extends AppCompatActivity {

    private static final String TAG = "IGG_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ai);

        Log.d(TAG, "AddAIActivity a fost deschisa");

        EditText scule = findViewById(R.id.aiScule);
        EditText insecte = findViewById(R.id.aiInsecte);
        EditText plante = findViewById(R.id.aiPlante);
        EditText suprafata = findViewById(R.id.aiSuprafata);

        Switch vremePloaie = findViewById(R.id.aiVremePloaie);
        Button salveaza = findViewById(R.id.aiSalveaza);

        // Verificam daca elementele exista in XML
        if (scule == null || insecte == null ||
                plante == null || suprafata == null ||
                vremePloaie == null || salveaza == null) {

            Log.e(TAG, "Lipsesc elemente din activity_add_ai.xml");

            Toast.makeText(this,
                    "Eroare la incarcarea formularului!",
                    Toast.LENGTH_LONG).show();

            finish();
            return;
        }

        salveaza.setOnClickListener(v -> {

            String s = scule.getText().toString().trim();
            String i = insecte.getText().toString().trim();
            String p = plante.getText().toString().trim();
            String textSuprafata = suprafata.getText().toString().trim();

            if (s.isEmpty() || i.isEmpty() ||
                    p.isEmpty() || textSuprafata.isEmpty()) {

                Toast.makeText(this,
                        "Completeaza toate campurile!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int suprafataGradina;

            try {
                suprafataGradina = Integer.parseInt(textSuprafata);
            } catch (NumberFormatException e) {
                suprafata.setError("Suprafata invalida");
                return;
            }

            if (suprafataGradina <= 0) {
                suprafata.setError("Suprafata trebuie sa fie pozitiva");
                return;
            }

            boolean ploua = vremePloaie.isChecked();

            InventarGeneralGradina gradina =
                    new InventarGeneralGradina(
                            s,
                            i,
                            p,
                            suprafataGradina,
                            ploua
                    );

            Bundle date = new Bundle();
            date.putSerializable("gradina", gradina);

            Intent intent = new Intent();
            intent.putExtra("date", date);

            Log.d(TAG, "Gradina a fost creata si trimisa");

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
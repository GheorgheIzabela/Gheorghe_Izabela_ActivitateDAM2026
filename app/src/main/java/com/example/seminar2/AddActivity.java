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


        EditText scule = findViewById(R.id.editScule);
        EditText insecte = findViewById(R.id.editInsecte);
        EditText plante = findViewById(R.id.editPlante);
        EditText suprafata = findViewById(R.id.editSuprafata);

        Switch vremePloaie = findViewById(R.id.switchVremePloaie);
        Button salveaza = findViewById(R.id.btnSalveaza);


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
                suprafata.setError("Introdu o suprafata mai mare ca 0");
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

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
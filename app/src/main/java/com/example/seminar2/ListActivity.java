package com.example.seminar2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private final ArrayList<InventarGeneralGradina> listaGradini =
            new ArrayList<>();

    private ArrayAdapter<InventarGeneralGradina> adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ListView listView = findViewById(R.id.listViewGradini);


        Bundle pachet = getIntent().getBundleExtra("date");

        if (pachet != null) {
            @SuppressWarnings("unchecked")
            ArrayList<InventarGeneralGradina> listaPrimita =
                    (ArrayList<InventarGeneralGradina>)
                            pachet.getSerializable("lista");

            if (listaPrimita != null) {
                listaGradini.addAll(listaPrimita);
            }
        }


        adaptor = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaGradini
        );

        listView.setAdapter(adaptor);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        InventarGeneralGradina gradina =
                                listaGradini.get(position);

                        Toast.makeText(
                                ListActivity.this,
                                gradina.toString(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );


        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        listaGradini.remove(position);
                        adaptor.notifyDataSetChanged();

                        return true;
                    }
                }
        );


        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Intent rezultat = new Intent();

                        Bundle pachetRezultat = new Bundle();
                        pachetRezultat.putSerializable(
                                "lista",
                                listaGradini
                        );

                        rezultat.putExtra("date", pachetRezultat);
                        setResult(RESULT_OK, rezultat);
                        finish();
                    }
                }
        );
    }
}
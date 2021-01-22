package com.example.calculadora_gmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Creditos extends AppCompatActivity {

    TextView tv;
    String credito;
    int contador = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        tv  = findViewById(R.id.textView);
    }

    public void creditos(View view){
        if(contador == 4) {
            credito = ("Programador y Diseñador: \n\n Gonzalo Marcos Regidor \n\n");
            tv.setText(credito);
        }else if(contador == 3){
            credito = credito + "Ayudantes en diseño: \n\n Danito Prk \n Mohamed Ben Ahmed \n\n";
            tv.setText(credito);
        }else if(contador == 2){
            credito = credito + "GBR: \n\n Fernando guerrero(Siempre confio en mi) \n\n";
            tv.setText(credito);
        }else if(contador == 1){
            credito = credito + "Otros: \n\n Mariusz Gromada(Contigo empezo todo) \n El Panas";
            tv.setText(credito);
        }else
            onBackPressed();
        contador--;
    }

}
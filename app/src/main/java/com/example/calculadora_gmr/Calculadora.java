package com.example.calculadora_gmr;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Calculadora extends AppCompatActivity {

    private boolean pasarAsegundo = false, cigual = false, prisim = false,raiz = false;
    private String simbolo = "", simbolo2 = "", operacion = "", numero = "", numero2 = "",codcalcu="";
    private int operacionrealizada = 1,contador = 9,posicionspinner;
    private double resultado = 0.0;
    private TextView tvoperacion;
    private Spinner historial;
    private ArrayList<String> HisCalculos,codCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_prueba);
        tvoperacion = findViewById(R.id.textViewoperacion);
        historial = findViewById(R.id.spinner2);

        HisCalculos = new ArrayList();
        codCalculo = new ArrayList();
        RellenarArray();
    }

    /*
    *Metodos para el menu de borrar todos
     */

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Borrar_Todos:
                DialogoBorrarTodos();
        }
        return true;
    }

    /**
     * Metodos para asignar un nuevo digito a la calculadora, el boolean pasar a segundo sirve para comprobar si es a partir del segundo digito que pide la calculadora
     */

    public void Numero1(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "1";
        } else{
            numero = numero + "1";
        }
        operacion = operacion + "1";
        tvoperacion.setText(operacion);
    }

    public void Numero2(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "2";
        } else {
            numero = numero + "2";
        }
        operacion = operacion + "2";
        tvoperacion.setText(operacion);
    }

    public void Numero3(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "3";
        } else {
            numero = numero + "3";
        }
        operacion = operacion + "3";
        tvoperacion.setText(operacion);
    }

    public void Numero4(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "4";
        } else {
            numero = numero + "4";
        }
        operacion = operacion + "4";
        tvoperacion.setText(operacion);
    }

    public void Numero5(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "5";
        } else {
            numero = numero + "5";
        }
        operacion = operacion + "5";
        tvoperacion.setText(operacion);
    }

    public void Numero6(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "6";
        } else {
            numero = numero + "6";
        }
        operacion = operacion + "6";
        tvoperacion.setText(operacion);
    }

    public void Numero7(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "7";
        } else {
            numero = numero + "7";
        }
        operacion = operacion + "7";
        tvoperacion.setText(operacion);
    }

    public void Numero8(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "8";
        } else {
            numero = numero + "8";
        }
        operacion = operacion + "8";
        tvoperacion.setText(operacion);
    }

    public void Numero9(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "9";
        } else {
            numero = numero + "9";
        }
        operacion = operacion + "9";
        tvoperacion.setText(operacion);
    }

    public void Numero0(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = numero2 + "0";
        } else {
            numero = numero + "0";
        }
        operacion = operacion + "0";
        tvoperacion.setText(operacion);
    }

    public void Numeropi(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            numero2 = String.valueOf(Math.PI);
        } else {
            numero = String.valueOf(Math.PI);
        }
        operacion = operacion + Math.PI;
        tvoperacion.setText(operacion);
    }

    public void decimal(View view) {
        if(cigual) {
            borrar_todo(null);
            cigual = false;
        }
        if (pasarAsegundo) {
            if (!numero2.contains(".")) {
                numero2 = numero2 + ".";
                operacion = operacion + ".";
            }
        } else {
            if (!numero.contains(".")) {
                numero = numero + ".";
                operacion = operacion + ".";
            }
        }
        tvoperacion.setText(operacion);
    }

    /*
    *Metodo calcular, se ejecuta cada vez que metes un nuevo simbolo o le das a igual, y comprueba en que numero de operacion se encuentra
    * por si tiene que trabajar con un resultado anterior o con los dos primeros numeros
     */

    public void calcular() {
        try {
            if (raiz) {
                if (simbolo.equals(""))
                    resultado = Math.sqrt(Double.parseDouble(numero2));
                else if(operacion.startsWith("√")){
                    resultado = Math.sqrt(Double.parseDouble(numero2));
                }else{
                    numero2 = String.valueOf(Math.sqrt(Double.parseDouble(numero2)));
                }
                raiz = false;
            }
            if (simbolo.equals("+")) {
                if (operacionrealizada >= 2)
                    resultado = resultado + Double.parseDouble(numero2);
                else {
                    resultado = Double.parseDouble(numero) + Double.parseDouble(numero2);
                }
            } else if (simbolo.equals("-")) {
                if (operacionrealizada >= 2)
                    resultado = resultado - Double.parseDouble(numero2);
                else {
                    resultado = Double.parseDouble(numero) - Double.parseDouble(numero2);
                }
            } else if (simbolo.equals("*")) {
                if (operacionrealizada >= 2)
                    resultado = resultado * Double.parseDouble(numero2);
                else {
                    resultado = Double.parseDouble(numero) * Double.parseDouble(numero2);
                }
            } else if (simbolo.equals("/")) {
                if (operacionrealizada >= 2)
                    resultado = resultado / Double.parseDouble(numero2);
                else {
                    resultado = Double.parseDouble(numero) / Double.parseDouble(numero2);
                }
            }else if (simbolo.equals("%")) {
                if (operacionrealizada >= 2) {
                    resultado = (resultado / 100) * Double.parseDouble(numero2);
                } else {
                    resultado = (Double.parseDouble(numero) / 100) * Double.parseDouble(numero2);
                }
            } else if (simbolo.equals("x2")) {
                if (operacionrealizada >= 2) {
                    resultado = Math.pow(resultado, 2);
                } else {
                    resultado = Math.pow(Double.parseDouble(numero), 2);
                }
            } else if (simbolo.equals("xy")) {
                if (operacionrealizada >= 2) {
                    resultado = Math.pow(resultado, Double.parseDouble(numero2));
                } else {
                    resultado = Math.pow(Double.parseDouble(numero), Double.parseDouble(numero2));
                }
            }
            operacionrealizada++;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Operación Invalida", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    *Metodo para sumar, en el cual comprueba si es el primer simbolo que se introduce a la calculadora para trabajar como primer simbolo,
    * ademas de comprobar si es el primer digito despues de un igual para no sumar ds veces lo mismo
     */

    public void suma(View view) {
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%") && !operacion.endsWith("√") && !operacion.endsWith("^") && !operacion.endsWith("²")) {
            if (!prisim) {
                pasarAsegundo = true;
                simbolo2 = "+";
                prisim = true;
                operacion = operacion + "+";
            }
            if (cigual) {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "+";
                cigual = false;
                operacion = resultado + "+";
            } else {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "+";
                calcular();
                if(prisim && operacionrealizada>1)
                    operacion = operacion + "+";
            }
            tvoperacion.setText(operacion);
            numero2 = "";
        }
    }

    public void restar(View view) {
        if (operacion.endsWith("x") || operacion.endsWith("/") || operacion.endsWith("^")) {
            numero2 = numero2 + "-";
            operacion = operacion + "-";
            tvoperacion.setText(operacion);
        }
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%") && !operacion.endsWith("√") && !operacion.endsWith("²")) {
            if (operacion == "")
                numero = numero + "-";
            else {
                if (!prisim) {
                    pasarAsegundo = true;
                    simbolo2 = "-";
                    prisim = true;
                    operacion = operacion + "-";
                }
                if (cigual) {
                    pasarAsegundo = true;
                    simbolo = simbolo2;
                    simbolo2 = "-";
                    cigual = false;
                    operacion = resultado + "-";
                } else {
                    pasarAsegundo = true;
                    simbolo = simbolo2;
                    simbolo2 = "-";
                    calcular();
                    if(prisim && operacionrealizada>1)
                        operacion = operacion + "-";
                }
            }
            tvoperacion.setText(operacion);
            numero2 = "";
        }
    }

    public void multiplicar(View view) {
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%") && !operacion.endsWith("√") && !operacion.endsWith("^") && !operacion.endsWith("²")) {
            if (!prisim) {
                pasarAsegundo = true;
                simbolo2 = "*";
                prisim = true;
                operacion = operacion + "x";
            }
            if (cigual) {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "*";
                cigual = false;
                operacion = resultado + "x";
            } else {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "*";
                calcular();
                if(prisim && operacionrealizada>1)
                    operacion = operacion + "x";
            }
            tvoperacion.setText(operacion);
            numero2 = "";
        }
    }

    public void dividir(View view) {
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%") && !operacion.endsWith("√") && !operacion.endsWith("^") && !operacion.endsWith("²")) {
            if (!prisim) {
                pasarAsegundo = true;
                simbolo2 = "/";
                prisim = true;
                operacion = operacion + "/";
            }
            if (cigual) {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "/";
                cigual = false;
                operacion = resultado + "/";
            } else {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "/";
                calcular();
                if(prisim && operacionrealizada>1)
                    operacion = operacion + "/";
            }
            tvoperacion.setText(operacion);
            numero2 = "";
        }
    }

    public void raiz(View view) {
        if(!operacion.endsWith("√") && !operacion.endsWith("0") && !operacion.endsWith("1") && !operacion.endsWith("2") && !operacion.endsWith("3") && !operacion.endsWith("4") && !operacion.endsWith("5") && !operacion.endsWith("6") && !operacion.endsWith("7") && !operacion.endsWith("8") && !operacion.endsWith("9")) {
            if (!raiz) {
                if(!prisim)
                    prisim = true;
                if (cigual) {
                    cigual = false;
                    Toast.makeText(this, "No se puede hacer la raiz de un resultado, esa opción se implementara en una proxima update disculpen la molestia", Toast.LENGTH_SHORT).show();
                }
                raiz = true;
                pasarAsegundo = true;
                operacion = operacion + "√";
            }
            tvoperacion.setText(operacion);
        }
    }

    public void porcentaje(View view) {
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%") && !operacion.endsWith("√") && !operacion.endsWith("^") && !operacion.endsWith("²")) {
            if (!prisim) {
                pasarAsegundo = true;
                simbolo2 = "%";
                prisim = true;
                operacion = operacion + "%";
            }
            if (cigual) {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "%";
                cigual = false;
                operacion = resultado + "%";
            } else {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "%";
                calcular();
                if(prisim && operacionrealizada > 1)
                    operacion = operacion + "%";
            }
            tvoperacion.setText(operacion);
            numero2 = "";
        }
    }

    public void elevado2(View view) {
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%") && !operacion.endsWith("√") && !operacion.endsWith("^") && !operacion.endsWith("²")) {
            if (!prisim) {
                pasarAsegundo = true;
                simbolo2 = "x2";
                prisim = true;
                operacion = operacion + "²";
            }
            if (cigual) {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "x2";
                cigual = false;
                operacion = resultado + "²";
            } else {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "x2";
                calcular();
                if(prisim && operacionrealizada>1)
                    operacion = operacion + "²";
            }
            tvoperacion.setText(operacion);
            numero2 = "";
        }
    }

    public void elevadoy(View view) {
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%") && !operacion.endsWith("√") && !operacion.endsWith("^") && !operacion.endsWith("²")) {
            numero2 = "";
            if (!prisim) {
                pasarAsegundo = true;
                simbolo2 = "xy";
                prisim = true;
                operacion = operacion + "^";
            }
            if (cigual) {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "xy";
                cigual = false;
                operacion = resultado + "^";
            } else {
                pasarAsegundo = true;
                simbolo = simbolo2;
                simbolo2 = "xy";
                calcular();
                if(prisim && operacionrealizada>1)
                    operacion = operacion + "^";
            }
            tvoperacion.setText(operacion);
            numero2 = "";
        }
    }

    /*
    *Metodo borrar numero que borra el ultimo digito de la calculadora o en su lugar el ultimo simbolo
     */

    public void borrar_numero(View view) {
        if(operacion.endsWith("√")) {
            operacion = operacion.substring(0, operacion.length() - 1);
            raiz = false;
        }
        if (operacion.endsWith("+") || operacion.endsWith("-") || operacion.endsWith("x") || operacion.endsWith("/") || operacion.endsWith("%") || operacion.endsWith("^") || operacion.endsWith("²")) {
            operacion = operacion.substring(0, operacion.length() - 1);
        } else {
            if (pasarAsegundo) {
                operacion = operacion.substring(0, operacion.length() - numero2.length());
                numero2 = "";
            } else {
                operacion = operacion.substring(0, operacion.length() - numero.length());
                operacionrealizada = 1;
                numero = "";
            }
        }
        if(cigual) borrar_todo(null);
        tvoperacion.setText(operacion);
    }

    public void borrar_todo(View view) {
        prisim = false;
        pasarAsegundo = false;
        operacionrealizada = 1;
        raiz = false;
        numero = "";
        numero2 = "";
        resultado = 0.0;
        operacion = "";
        tvoperacion.setText(operacion);
    }

    /*
    *Metodo que devuelve un resultado y en caso de ser valido guardara el calculo
     */

    public void igual(View view) {
        if (!operacion.endsWith("+") && !operacion.endsWith("-") && !operacion.endsWith("x") && !operacion.endsWith("/") && !operacion.endsWith("%")&&!operacion.endsWith("√")) {
            simbolo = simbolo2;
            if (!cigual) {
                cigual = true;
                calcular();
                operacion = operacion + "=" + resultado;
                tvoperacion.setText(operacion);
                if(String.valueOf(resultado) == "NaN" || String.valueOf(resultado) == "Infinity"){
                    Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                    borrar_todo(null);
                }else guardarcalculo();
            }
        }else Toast.makeText(this, "Operación invalida introduce el ultimo digito", Toast.LENGTH_SHORT).show();
    }

    public void guardarcalculo() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Operaciones", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Date fecha = Calendar.getInstance().getTime();
        Cursor fila = BaseDeDatos.rawQuery("select calculo from calculos", null);
        int cod = 1;
        if (fila.moveToFirst()) {
            do {
                cod++;
            } while (fila.moveToNext());
        }
        ContentValues registro = new ContentValues();
        registro.put("calculo", operacion);
        registro.put("cod", cod);
        registro.put("resultado", resultado);

        BaseDeDatos.insert("calculos", null, registro);
        BaseDeDatos.close();

        HisCalculos.clear();
        codCalculo.clear();
        RellenarArray();
    }

    /*
    *Metodo que rellena los arrays para rellenar el spinner ademas de coger el item seleccionado del spinner
     */

    public void RellenarArray() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Operaciones", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Cursor fila = BaseDeDatos.rawQuery("select calculo,cod from calculos", null);

        if (fila.moveToFirst()) {
            do {
                HisCalculos.add(fila.getString(0));
                codCalculo.add(fila.getString(1));
            } while (fila.moveToNext());
        }

        BaseDeDatos.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, HisCalculos);
        historial.setAdapter(adapter);

        historial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicionspinner = position;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogoBorrar();
                        codcalcu = codCalculo.get(posicionspinner);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void borrar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Operaciones", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        int cantidad = BaseDatabase.delete("calculos", "cod='" + codcalcu + "'", null);
        BaseDatabase.close();

        if(cantidad == 1){
            Toast.makeText(this, "Operación eliminada exitosamente", Toast.LENGTH_SHORT).show();
            RellenarArray();
        } else {
            Toast.makeText(this, "El calculo no existe", Toast.LENGTH_SHORT).show();
        }
    }

    private void borrartodos(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Operaciones", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        BaseDatabase.execSQL("delete from calculos");
        BaseDatabase.close();

        Toast.makeText(this, "Operación eliminada exitosamente", Toast.LENGTH_SHORT).show();
    }

    /*
    *Metodo que utiliza el dialogo borrar
     */

    private void Modificar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Operaciones", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        int pos  = 1 + posicionspinner;
        Cursor fila = BaseDeDatos.rawQuery("select calculo,resultado from calculos where cod='" + pos +"'", null);

        if (fila.moveToFirst()) {
                operacion = fila.getString(0);
                resultado = fila.getDouble(1);
        }
        tvoperacion.setText(operacion);
        BaseDeDatos.close();

        prisim = false;
        pasarAsegundo = true;
        cigual = true;
        operacionrealizada = 2;
    }

    /*
     *Metodo que borra o modifica el calculo que selecciones en el spinner
     */

    private void DialogoBorrar(){
        new AlertDialog.Builder(this).setTitle("Borrar Calculo").setMessage("¿Desea borrar este calculo? (Selecciona fuera para modificar)")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        borrar();
                        HisCalculos.clear();
                        codCalculo.clear();
                        RellenarArray();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Calculadora.this, "Calculo no borrado", Toast.LENGTH_SHORT).show();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Modificar();
                        }
                    })
                .show();
    }

    private void DialogoBorrarTodos(){
        new AlertDialog.Builder(this).setTitle("Borrar Todos").setMessage("¿Seguro que desea borrar todos los calculos?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        borrartodos();
                        HisCalculos.clear();
                        codCalculo.clear();
                        RellenarArray();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Calculadora.this, "Calculos no borrados", Toast.LENGTH_SHORT).show();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(Calculadora.this, "Calculos no borrados", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    /*
    *Metodo que abre los creditos finales de la calculadora
     */

    public void creditos(View view){
        contador--;
        if(contador <= 0){
            Intent siguiente = new Intent(this,Creditos.class);
            startActivity(siguiente);
        }else if(contador < 4)
            Toast.makeText(this, "Te quedan " + contador + " pulsaciones para acceder a los creditos", Toast.LENGTH_SHORT).show();
    }
}
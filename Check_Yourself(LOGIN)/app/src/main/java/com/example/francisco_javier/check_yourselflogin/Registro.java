package com.example.francisco_javier.check_yourselflogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.view.MenuItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Registro extends AppCompatActivity {

    private AutoCompleteTextView mRutView;
    private AutoCompleteTextView mNombreView;
    private AutoCompleteTextView mMailView;
    private AutoCompleteTextView mTelefonoView;
    private AutoCompleteTextView mPassView;
    private AutoCompleteTextView mPass2View;
    private Button DoneButton;

    private String IP = "localhost";
    private String baseDatos = "/intro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mRutView = (AutoCompleteTextView) findViewById(R.id.rut2);
        mNombreView = (AutoCompleteTextView) findViewById(R.id.nombre2);
        mMailView = (AutoCompleteTextView) findViewById(R.id.mail2);
        mTelefonoView = (AutoCompleteTextView) findViewById(R.id.telefono2);
        mPassView = (AutoCompleteTextView) findViewById(R.id.contrasena2);
        mPass2View = (AutoCompleteTextView) findViewById(R.id.contrasena22);

        DoneButton = (Button)this.findViewById(R.id.done2_button);

        DoneButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mMailView.getText().toString();
                String pass = mPassView.getText().toString();
                String rut = mRutView.getText().toString();
                String nombre = mNombreView.getText().toString();
                String telefono = mTelefonoView.getText().toString();
                String tipo = "1";
                String id = "0";
                new ConexionDB().execute(IP,baseDatos, email, pass, rut, nombre, telefono, tipo, id );

                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);

            }
        });
    }

    public class ConexionDB extends AsyncTask<String,Void,ResultSet>{
        @Override
        protected ResultSet doInBackground(String... strings)  {
        // ip, bd, mail, pass, rut, nombre, telefono , tipo, id
            try {
                Connection conn;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://"+strings[0]+strings[1], "root", "");

                Statement estado = conn.createStatement();
                System.out.println("Conexion establecida");
                String peticion ="insert into usuarios values (" +strings[8]+","+strings[3]+","+strings[5]+","+strings[6]+","+strings[2]+","+strings[7]+")";
                ResultSet result = estado.executeQuery(peticion);
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}


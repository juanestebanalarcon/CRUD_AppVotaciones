package com.uao.parcial1alarconjuan_sqliteappcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.uao.parcial1alarconjuan_sqliteappcrud.sqlConn.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    private EditText cedula, nombreCompleto, institucion, mesa;
    private Button btnCreate, btnRead, btnUpdate, btnDelete;
    AdminSQLiteOpenHelper sqlAdmin = new AdminSQLiteOpenHelper(this, "uaoDB.sqlite", null, 1);
    SQLiteDatabase db = sqlAdmin.getWritableDatabase();
    ContentValues register = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cedula = findViewById(R.id.cedula);
        nombreCompleto = findViewById(R.id.nombreCompleto);
        institucion = findViewById(R.id.institucion);
        mesa = findViewById(R.id.mesa);
    } //fin OnCreate
        //CRUD Operations
        public void create (View v){
            int txtcedula, txtmesa;
            String txtnombreCompleto, txtinstitucion;
            txtcedula = Integer.parseInt(cedula.getText().toString());
            txtnombreCompleto = nombreCompleto.getText().toString();
            txtinstitucion = institucion.getText().toString();
            txtmesa = Integer.parseInt(mesa.getText().toString());
            register.put("cedula", txtcedula);
            register.put("nombre", txtnombreCompleto);
            register.put("colegio", txtinstitucion);
            register.put("nromesas", txtmesa);
            try {
                db.insert("votantes", null, register);
                //   db.close();
            } catch (SQLException e) {
                System.out.println(e);
            }

            cedula.setText("");
            nombreCompleto.setText("");
            institucion.setText("");
            mesa.setText("");
            Toast.makeText(this, "Se realizó el registro exitosamente", Toast.LENGTH_SHORT).show();
        }
        public void read (View v){
            int ced = Integer.parseInt(cedula.getText().toString());
            try {
                Cursor row = db.rawQuery("select nombre,colegio,nromesa from votantes where cedula=" + ced, null);
                if (row.moveToFirst()) {
                    nombreCompleto.setText(row.getString(0));
                    institucion.setText(row.getString(1));
                    mesa.setText(row.getString(2));
                } else {
                    Toast.makeText(this, "No se encontraron registros con ese documento.", Toast.LENGTH_SHORT).show();

                }
                //    db.close();
            } catch (SQLException e) {
                System.out.println(e);
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        public void update (View v){
            int txtcedula, cant, txtmesa;
            String txtnombreCompleto, txtinstitucion;
            txtcedula = Integer.parseInt(cedula.getText().toString());
            txtnombreCompleto = nombreCompleto.getText().toString();
            txtinstitucion = institucion.getText().toString();
            txtmesa = Integer.parseInt(mesa.getText().toString());
            register.put("nombre", txtnombreCompleto);
            register.put("colegio", txtinstitucion);
            register.put("nromesas", txtmesa);
            cant = db.update("votantes", register, "cedula=" + txtcedula, null);
            //    db.close();
            if (cant > 0) {
                Toast.makeText(this, "Se modificaron los registros con ese documento.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se encontraron registros con ese documento.", Toast.LENGTH_SHORT).show();
            }
        }
        public void delete (View v){
            int txtcedula = Integer.parseInt(cedula.getText().toString());
            int cant = db.delete("votantes", "cedula=" + txtcedula, null);
            //     db.close();
            cedula.setText("");
            nombreCompleto.setText("");
            institucion.setText("");
            mesa.setText("");
            if (cant > 0) {
                Toast.makeText(this, "Se eliminó el registro con ese documento.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se encontraron registros con ese documento.", Toast.LENGTH_SHORT).show();
            }
        }

}


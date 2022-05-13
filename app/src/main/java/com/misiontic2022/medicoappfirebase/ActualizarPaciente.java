package com.misiontic2022.medicoappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ActualizarPaciente extends AppCompatActivity {
    private DatabaseReference Base_de_datos;
    private Button btn_actualizar_paciente;
    private EditText act_nombres, act_apellidos, act_fecha_nacimiento,act_identificacion,act_valor_cuota,act_fecha_cita;
    private Spinner spinner_booleano,act_elegir_paciente;
    private String[]booleano={"si","no"};
    private String[]lista_pacientes={"Paciente 1","Paciente 2","Paciente 3","Paciente 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_paciente);
        Base_de_datos = FirebaseDatabase.getInstance().getReference();
        act_nombres=(EditText) findViewById(R.id.act_nombres);
        act_apellidos=(EditText) findViewById(R.id.act_apellidos);
        act_fecha_nacimiento=(EditText) findViewById(R.id.act_fecha_nacimiento);
        act_identificacion=(EditText) findViewById(R.id.act_nombres);
        act_valor_cuota=(EditText) findViewById(R.id.act_valor_cuota);
        act_fecha_cita=(EditText) findViewById(R.id.act_fecha_cita);

        spinner_booleano = (Spinner) findViewById(R.id.spinner_booleano);
        ArrayAdapter<String> Adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booleano);
        spinner_booleano.setAdapter(Adaptador);

        act_elegir_paciente = (Spinner) findViewById(R.id.act_elegir_paciente);
        ArrayAdapter<String> Adaptador_paciente = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista_pacientes);
        act_elegir_paciente.setAdapter(Adaptador_paciente);

        Llenar_datos();
    }
    private void Llenar_datos() {
        btn_actualizar_paciente=(Button) findViewById(R.id.btn_actualizar_paciente);
        btn_actualizar_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> Datos_paciente = new HashMap<>();
                act_elegir_paciente.getSelectedItem().toString().trim();
                Datos_paciente.put("nombres",(act_nombres.getText().toString().trim()));
                Datos_paciente.put("apellidos",(act_apellidos.getText().toString().trim()));
                Datos_paciente.put("fecha_nacimiento", (act_fecha_nacimiento.getText().toString().trim()));
                Datos_paciente.put("identificacion", (act_identificacion.getText().toString().trim()));
                Datos_paciente.put("valor_Cuota", (act_valor_cuota.getText().toString().trim()));
                Datos_paciente.put("fecha_cita", (act_fecha_cita.getText().toString().trim()));
                Datos_paciente.put("esta_en_tratamiento", (spinner_booleano.getSelectedItem().toString().trim()));
                Validar_y_enviar_datos(Datos_paciente);
                Intent i = new Intent(ActualizarPaciente.this, ListaPacientesActivity.class);
                startActivity(i);
            }
        });
    }
    private void Validar_y_enviar_datos(Map<String, Object> Datos_paciente) {
        if(act_nombres.getText().toString().trim().equals("")||act_apellidos.getText().toString().trim().equals("")||act_fecha_nacimiento.getText().toString().trim().equals("")||act_identificacion.getText().toString().trim().equals("")||act_valor_cuota.getText().toString().trim().equals("")||act_fecha_cita.getText().toString().trim().equals("")) {
            Toast.makeText(ActualizarPaciente.this, "Hay espacios vacios", Toast.LENGTH_SHORT).show();
        }else{
            if(act_elegir_paciente.getSelectedItem().toString().trim().equals("Paciente 1")){
                Toast.makeText(ActualizarPaciente.this, "Actualizado Paciente 1", Toast.LENGTH_SHORT).show();
                Base_de_datos.child("Pacientes").child("1").updateChildren(Datos_paciente);
            }if (act_elegir_paciente.getSelectedItem().toString().trim().equals("Paciente 2")) {
                Base_de_datos.child("Pacientes").child("2").updateChildren(Datos_paciente);
                Toast.makeText(ActualizarPaciente.this, "Actualizado Paciente 2", Toast.LENGTH_SHORT).show();
            }if(act_elegir_paciente.getSelectedItem().toString().trim().equals("Paciente 3")) {
                Base_de_datos.child("Pacientes").child("3").updateChildren(Datos_paciente);
                Toast.makeText(ActualizarPaciente.this, "Actualizado Paciente 3", Toast.LENGTH_SHORT).show();
            }if (act_elegir_paciente.getSelectedItem().toString().trim().equals("Paciente 4")) {
                Base_de_datos.child("Pacientes").child("4").updateChildren(Datos_paciente);
                Toast.makeText(ActualizarPaciente.this, "Actualizado Paciente 4", Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(ActualizarPaciente.this, ListaPacientesActivity.class);
            startActivity(i);
            }
        }
    }

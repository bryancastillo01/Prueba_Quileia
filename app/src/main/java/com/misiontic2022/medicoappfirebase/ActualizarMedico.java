package com.misiontic2022.medicoappfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ActualizarMedico extends AppCompatActivity {
    private DatabaseReference Base_de_datos;
    private Button btn_actualizar_medico;
    private EditText act_codigo_tarjeta_txt, act_años_exp_txt, act_consultorio_txt;
    private Spinner act_spinner_especialidad,act_atiende_domocilio,act_elegir_medico;
    private String[]lista_medicos={"Medico 1","Medico 2","Medico 3","Medico 4"};
    private String[]booleano_esp={"Dermatología.","Cardiología","Psiquiatría","Pediatría"};
    private String[]booleano={"si","no"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_medico);
        Base_de_datos = FirebaseDatabase.getInstance().getReference();
        act_codigo_tarjeta_txt = (EditText) findViewById(R.id.act_codigo_tarjeta_txt);
        act_años_exp_txt = (EditText) findViewById(R.id.act_años_exp_txt);
        act_consultorio_txt = (EditText) findViewById(R.id.act_consultorio_txt);

        act_spinner_especialidad = (Spinner) findViewById(R.id.act_spinner_especialidad);
        ArrayAdapter<String> Adaptador_esp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booleano_esp);
        act_spinner_especialidad.setAdapter(Adaptador_esp);

        act_elegir_medico = (Spinner) findViewById(R.id.act_elegir_medico);
        ArrayAdapter<String> Adaptador_Elegir_medico = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista_medicos);
        act_elegir_medico.setAdapter(Adaptador_Elegir_medico);

        act_atiende_domocilio = (Spinner) findViewById(R.id.act_atiende_domicilio);
        ArrayAdapter<String> Adaptador_Booleano = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booleano);
        act_atiende_domocilio.setAdapter(Adaptador_Booleano);

        Llenar_datos();
    }
    private void Llenar_datos() {
        btn_actualizar_medico=(Button) findViewById(R.id.btn_actualizar_medico);
        btn_actualizar_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> Datos_Medico = new HashMap<>();
                act_elegir_medico.getSelectedItem().toString().trim();
                Datos_Medico.put("atiende_Domicilio", (act_atiende_domocilio.getSelectedItem().toString()));
                Datos_Medico.put("codigo_Tarjeta_Profesional", act_codigo_tarjeta_txt.getText().toString().trim());
                Datos_Medico.put("consultorio", (act_consultorio_txt.getText().toString().trim()));
                Datos_Medico.put("años_De_Experiencia", (act_años_exp_txt.getText().toString().trim()));
                Datos_Medico.put("especialidad", (act_spinner_especialidad.getSelectedItem().toString().trim()));
                Validar_y_enviar_datos(Datos_Medico);
                Intent i = new Intent(ActualizarMedico.this, ListaMedicosActivity.class);
                startActivity(i);
            }
        });
    }
    private void Validar_y_enviar_datos(Map<String, Object> Datos_Medico) {
        if(act_codigo_tarjeta_txt.getText().toString().trim().equals("")||act_consultorio_txt.getText().toString().trim().equals("")||act_años_exp_txt.getText().toString().trim().equals("")){
            Toast.makeText(ActualizarMedico.this,"Hay espacios vacios", Toast.LENGTH_SHORT).show();
        }else{
        if (act_elegir_medico.getSelectedItem().toString().trim().equals("Medico 1")){
                Toast.makeText(ActualizarMedico.this, "Actualizado Medico 1", Toast.LENGTH_SHORT).show();
                Base_de_datos.child("Medicos").child("1").updateChildren(Datos_Medico);
        }if (act_elegir_medico.getSelectedItem().toString().trim().equals("Medico 2")) {
                Base_de_datos.child("Medicos").child("2").updateChildren(Datos_Medico);
                Toast.makeText(ActualizarMedico.this, "Actualizado Medico 2", Toast.LENGTH_SHORT).show();
        }if(act_elegir_medico.getSelectedItem().toString().trim().equals("Medico 3")) {
                Base_de_datos.child("Medicos").child("3").updateChildren(Datos_Medico);
                Toast.makeText(ActualizarMedico.this, "Actualizado Medico 3", Toast.LENGTH_SHORT).show();
        }if (act_elegir_medico.getSelectedItem().toString().trim().equals("Medico 4")) {
                Base_de_datos.child("Medicos").child("4").updateChildren(Datos_Medico);
                Toast.makeText(ActualizarMedico.this, "Actualizado Medico 4", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(ActualizarMedico.this, ListaMedicosActivity.class);
        startActivity(i);
    }
    }
}
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

public class RegistroMedicoActivity extends AppCompatActivity {
    private DatabaseReference Base_de_datos;
    private EditText codigo_tarjeta_profesional_txt,años_exp_txt,consultorio_txt;
    private Button btn_guardar_medico,btn_ir_lista;
    private Spinner spinner_especialidad,spinner_booleano;
    private String[]booleano_esp={"Dermatología.","Cardiología","Psiquiatría","Pediatría"};
    private String[]booleano={"si","no"};
    Medicos medicos;
    private long id=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_medico);
        medicos = new Medicos();

        codigo_tarjeta_profesional_txt=(EditText)findViewById(R.id.codigo_tarjeta_profesional_txt);
        años_exp_txt=(EditText)findViewById(R.id.años_exp_txt);
        consultorio_txt=(EditText)findViewById(R.id.consultorio_txt);

        spinner_booleano = (Spinner) findViewById(R.id.spinner_booleano);
        ArrayAdapter<String> Adaptador_bol = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booleano);
        spinner_booleano.setAdapter(Adaptador_bol);

        spinner_especialidad = (Spinner) findViewById(R.id.spinner_especialidad);
        ArrayAdapter<String> Adaptador_esp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booleano_esp);
        spinner_especialidad.setAdapter(Adaptador_esp);
        Base_de_datos = FirebaseDatabase.getInstance().getReference().child("Medicos");
        ContadorRegistrosMedicos();
        LlenarDatos();

    }
    private void ContadorRegistrosMedicos() {
        Base_de_datos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    id=(snapshot.getChildrenCount());
            }@Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void LlenarDatos() {
        btn_guardar_medico=(Button)findViewById(R.id.btn_guardar_medico);
        btn_guardar_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicos.setCodigo_Tarjeta_Profesional(codigo_tarjeta_profesional_txt.getText().toString().trim());
                medicos.setAños_De_Experiencia(años_exp_txt.getText().toString().trim());
                medicos.setEspecialidad(spinner_especialidad.getSelectedItem().toString().trim());
                medicos.setConsultorio(consultorio_txt.getText().toString().trim());
                medicos.setAtiende_Domicilio(spinner_booleano.getSelectedItem().toString());
                Validar_Y_Subir();
                Intent i = new Intent(RegistroMedicoActivity.this, ListaMedicosActivity.class);
                startActivity(i);
            }
        });
    }
    private void Validar_Y_Subir() {
        if (codigo_tarjeta_profesional_txt.getText().toString().equals("")||años_exp_txt.getText().toString().equals("")||consultorio_txt.getText().toString().equals("")){
        Toast.makeText(RegistroMedicoActivity.this,"Hay espacios vacios", Toast.LENGTH_SHORT).show();
        }else{
            Base_de_datos.child(String.valueOf(id+1)).setValue(medicos);
            Toast.makeText(RegistroMedicoActivity.this,"Registrado Medico", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(RegistroMedicoActivity.this, ListaMedicosActivity.class);
            startActivity(i);
    }
    }


}

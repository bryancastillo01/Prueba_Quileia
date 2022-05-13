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


public class RegistroPacienteActivity extends AppCompatActivity {
    private EditText nombre_txt,apellido_txt,fecha_nacimiento_txt,identificacion_txt,couta_moderadora_txt,fecha_cita_txt;
    private Button btn_guardar_paciente;
    private DatabaseReference Base_De_Datos;
    private Spinner spinner_si_no;
    private String[]booleano={"si","no"};
    Pacientes pacientes;
    private long ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);
        pacientes = new Pacientes();

        nombre_txt = (EditText) findViewById(R.id.nombre_txt);
        apellido_txt = (EditText) findViewById(R.id.apellido_txt);
        fecha_nacimiento_txt = (EditText) findViewById(R.id.fecha_nacimiento_txt);
        identificacion_txt = (EditText) findViewById(R.id.identificacion_txt);
        couta_moderadora_txt = (EditText)findViewById(R.id.couta_moderadora_txt);
        fecha_cita_txt = (EditText)findViewById(R.id.fecha_cita_txt);

        spinner_si_no = (Spinner) findViewById(R.id.spinner_si_no);
        ArrayAdapter<String> Adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booleano);
        spinner_si_no.setAdapter(Adaptador);

        Base_De_Datos = FirebaseDatabase.getInstance().getReference().child("Pacientes");
        ContadorRegistrosPacientes();
        LlenarDatos();
    }
    private void ContadorRegistrosPacientes() {
        Base_De_Datos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    ID = (dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void LlenarDatos(){
        btn_guardar_paciente = (Button) findViewById(R.id.btn_guardar_paciente);
        btn_guardar_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pacientes.setNombres(nombre_txt.getText().toString().trim());
                pacientes.setApellidos(apellido_txt.getText().toString().trim());
                pacientes.setFecha_Nacimiento(fecha_nacimiento_txt.getText().toString().trim());
                pacientes.setIdentificacion(identificacion_txt.getText().toString().trim());
                pacientes.setEsta_en_tratamiento(spinner_si_no.getSelectedItem().toString().trim());
                pacientes.setValor_Cuota(couta_moderadora_txt.getText().toString().trim());
                pacientes.setFecha_cita(fecha_cita_txt.getText().toString().trim());
                Validar_Y_Subir();
            }
        });
    }
    private void Validar_Y_Subir() {
            if (nombre_txt.getText().toString().equals("")||apellido_txt.getText().toString().equals("")||fecha_nacimiento_txt.getText().toString().equals("")||identificacion_txt.getText().toString().equals("")||couta_moderadora_txt.getText().toString().equals("")||fecha_nacimiento_txt.getText().toString().equals("")){
                Toast.makeText(RegistroPacienteActivity.this,"Hay espacios vacios", Toast.LENGTH_SHORT).show();
            }else{
                Base_De_Datos.child(String.valueOf(ID+1)).setValue(pacientes);
                Toast.makeText(RegistroPacienteActivity.this,"Registrado Paciente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegistroPacienteActivity.this, ListaPacientesActivity.class);
                startActivity(i);
            }
    }
}



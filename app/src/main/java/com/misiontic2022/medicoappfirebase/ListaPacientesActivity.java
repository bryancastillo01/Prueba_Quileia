package com.misiontic2022.medicoappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListaPacientesActivity extends AppCompatActivity {
    private Button btn_agregar_paciente,btn_volver_inicio,btn_actualizar_pacientes,btn_eliminar_pacientes,eliminar01,eliminar02,eliminar03,eliminar04;
    private TextView Texto1, Texto2, Texto3, Texto4;
    private DatabaseReference Base_de_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);
        Texto1 = (TextView) findViewById(R.id.dato_01);
        Texto2 = (TextView) findViewById(R.id.dato_02);
        Texto3 = (TextView) findViewById(R.id.dato_03);
        Texto4 = (TextView) findViewById(R.id.dato_04);
        Base_de_datos = FirebaseDatabase.getInstance().getReference();

        btn_volver_inicio= (Button) findViewById(R.id.btn_volver_inicio1);
        btn_volver_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ListaPacientesActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
        btn_agregar_paciente= (Button) findViewById(R.id.btn_agregar_paciente);
        btn_agregar_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ListaPacientesActivity.this,RegistroPacienteActivity.class);
                startActivity(i);
            }
        });
        btn_actualizar_pacientes=(Button)findViewById(R.id.btn_actualizar_pacientes);
        btn_actualizar_pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Intent i = new Intent(ListaPacientesActivity.this, ActualizarPaciente.class);
                startActivity(i);
            }
        });
        eliminar01=(Button)findViewById(R.id.eliminar01);
        eliminar01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto1.getText().toString().equals("")){
                    Toast.makeText(ListaPacientesActivity.this, "Registros Medicos Borrados", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Pacientes").child("1").removeValue();
                    Texto1.setText("");
                    Toast.makeText(ListaPacientesActivity.this, "Paciente 1 removido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        eliminar02=(Button)findViewById(R.id.eliminar02);
        eliminar02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto2.getText().toString().equals("")){
                    Toast.makeText(ListaPacientesActivity.this, "Registros Medicos Borrados", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Pacientes").child("2").removeValue();
                    LlenarDatos();
                    Toast.makeText(ListaPacientesActivity.this, "Paciente 2 removido", Toast.LENGTH_SHORT).show();
                }
            }

        });
        eliminar03=(Button)findViewById(R.id.eliminar03);
        eliminar03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto3.getText().toString().equals("")){
                    Toast.makeText(ListaPacientesActivity.this, "Registros Medicos Borrados", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Pacientes").child("3").removeValue();
                    LlenarDatos();
                    Toast.makeText(ListaPacientesActivity.this, "Paciente 3 removido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        eliminar04=(Button)findViewById(R.id.eliminar04);
        eliminar04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto4.getText().toString().equals("")){
                    Toast.makeText(ListaPacientesActivity.this, "Registros Medicos Borrados", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Pacientes").child("4").removeValue();
                    LlenarDatos();
                    Toast.makeText(ListaPacientesActivity.this, "Paciente 4 removido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LlenarDatos();
        Eliminar_Registros();

    }
    private void Eliminar_Registros() {
        btn_eliminar_pacientes = (Button)findViewById(R.id.btn_eliminar_pacientes);
        btn_eliminar_pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaPacientesActivity.this);
                builder.setMessage("Desea Confirmar?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int witch) {
                        Base_de_datos.child("Pacientes").removeValue();
                        Toast.makeText(ListaPacientesActivity.this, "Registros Medicos Borrados", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ListaPacientesActivity.this, ListaPacientesActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int witch) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }});
    }
    private void LlenarDatos() {
        Base_de_datos.child("Pacientes").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String Nombres = snapshot.child("nombres").getValue().toString();
                    String Apellidos = snapshot.child("apellidos").getValue().toString();
                    String Fecha_nacimiento = snapshot.child("fecha_nacimiento").getValue().toString();
                    String Identificacion = snapshot.child("identificacion").getValue().toString();
                    String Esta_en_tratamiento = snapshot.child("esta_en_tratamiento").getValue().toString();
                    String Valor_couta = snapshot.child("valor_Cuota").getValue().toString();
                    String Fecha_cita = snapshot.child("fecha_cita").getValue().toString();
                    Texto1.setText("Nombres:" + Nombres + "\n" +
                            "Apellidos: " + Apellidos + "\n" +
                            "Fecha_nacimiento: " + Fecha_nacimiento + "\n" +
                            "Identificacion: " + Identificacion + "\n" +
                            "Esta_en_tratamiento: " + Esta_en_tratamiento+ "\n" +
                            "Valor_couta :"+ Valor_couta+"\n" +
                            "Fecha_cita :"+Fecha_cita);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Base_de_datos.child("Pacientes").child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                if (snapshot1.exists()) {
                    String Nombres = snapshot1.child("nombres").getValue().toString();
                    String Apellidos = snapshot1.child("apellidos").getValue().toString();
                    String Fecha_nacimiento = snapshot1.child("fecha_nacimiento").getValue().toString();
                    String Identificacion = snapshot1.child("identificacion").getValue().toString();
                    String Esta_en_tratamiento = snapshot1.child("esta_en_tratamiento").getValue().toString();
                    String Valor_couta = snapshot1.child("valor_Cuota").getValue().toString();
                    String Fecha_cita = snapshot1.child("fecha_cita").getValue().toString();
                    Texto2.setText("Nombres:" + Nombres + "\n" +
                            "Apellidos: " + Apellidos + "\n" +
                            "Fecha_nacimiento: " + Fecha_nacimiento + "\n" +
                            "Identificacion: " + Identificacion + "\n" +
                            "Esta_en_tratamiento: " + Esta_en_tratamiento+ "\n" +
                            "Valor_couta :"+ Valor_couta+"\n" +
                            "Fecha_cita :"+Fecha_cita);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Base_de_datos.child("Pacientes").child("3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                if (snapshot2.exists()) {
                    String Nombres = snapshot2.child("nombres").getValue().toString();
                    String Apellidos = snapshot2.child("apellidos").getValue().toString();
                    String Fecha_nacimiento = snapshot2.child("fecha_nacimiento").getValue().toString();
                    String Identificacion = snapshot2.child("identificacion").getValue().toString();
                    String Esta_en_tratamiento = snapshot2.child("esta_en_tratamiento").getValue().toString();
                    String Valor_couta = snapshot2.child("valor_Cuota").getValue().toString();
                    String Fecha_cita = snapshot2.child("fecha_cita").getValue().toString();
                    Texto3.setText("Nombres:" + Nombres + "\n" +
                            "Apellidos: " + Apellidos + "\n" +
                            "Fecha_nacimiento: " + Fecha_nacimiento + "\n" +
                            "Identificacion: " + Identificacion + "\n" +
                            "Esta_en_tratamiento: " + Esta_en_tratamiento+ "\n" +
                            "Valor_couta :"+ Valor_couta+"\n" +
                            "Fecha_cita :"+Fecha_cita);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Base_de_datos.child("Pacientes").child("4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot3) {
                if (snapshot3.exists()) {
                    String Nombres = snapshot3.child("nombres").getValue().toString();
                    String Apellidos = snapshot3.child("apellidos").getValue().toString();
                    String Fecha_nacimiento = snapshot3.child("fecha_nacimiento").getValue().toString();
                    String Identificacion = snapshot3.child("identificacion").getValue().toString();
                    String Esta_en_tratamiento = snapshot3.child("esta_en_tratamiento").getValue().toString();
                    String Valor_couta = snapshot3.child("valor_Cuota").getValue().toString();
                    String Fecha_cita = snapshot3.child("fecha_cita").getValue().toString();
                    Texto4.setText("Nombres:" + Nombres + "\n" +
                            "Apellidos: " + Apellidos + "\n" +
                            "Fecha_nacimiento: " + Fecha_nacimiento + "\n" +
                            "Identificacion: " + Identificacion + "\n" +
                            "Esta_en_tratamiento: " + Esta_en_tratamiento+ "\n" +
                            "Valor_couta :"+ Valor_couta+"\n" +
                            "Fecha_cita :"+Fecha_cita);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}
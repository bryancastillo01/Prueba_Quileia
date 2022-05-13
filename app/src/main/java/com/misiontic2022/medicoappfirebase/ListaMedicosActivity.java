package com.misiontic2022.medicoappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ListaMedicosActivity extends AppCompatActivity {
    private Button btn_volver_inicio, btn_agregar_medico, btn_eliminar_medicos,btn_actualizar_medicos,eliminar_id1,eliminar_id2,eliminar_id3,eliminar_id4;
    private TextView Texto1, Texto2, Texto3, Texto4;
    private DatabaseReference Base_de_datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
        Texto1 = (TextView) findViewById(R.id.dato_1);
        Texto2 = (TextView) findViewById(R.id.dato_2);
        Texto3 = (TextView) findViewById(R.id.dato_3);
        Texto4 = (TextView) findViewById(R.id.dato_4);
        Base_de_datos = FirebaseDatabase.getInstance().getReference();

        btn_volver_inicio = (Button) findViewById(R.id.btn_volver_inicio2);
        btn_volver_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaMedicosActivity.this, HomeActivity.class);
                startActivity(i);
            }

        });
        btn_agregar_medico = (Button) findViewById(R.id.btn_agregar_medico);
        btn_agregar_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaMedicosActivity.this, RegistroMedicoActivity.class);
                startActivity(i);
            }
        });
        btn_actualizar_medicos = (Button) findViewById(R.id.btn_actualizar_medico);
        btn_actualizar_medicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Intent i = new Intent(ListaMedicosActivity.this, ActualizarMedico.class);
                startActivity(i);
            }
        });

        eliminar_id1=(Button)findViewById(R.id.eliminar_id1);
        eliminar_id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto1.getText().toString().equals("")){
                    Toast.makeText(ListaMedicosActivity.this, "No hay Medico Registrado", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Medicos").child("1").removeValue();
                    Texto1.setText("");
                    Toast.makeText(ListaMedicosActivity.this, "Medico 1 removido", Toast.LENGTH_SHORT).show();
                }
                }
        });

        eliminar_id2=(Button)findViewById(R.id.eliminar_id2);
        eliminar_id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto2.getText().toString().equals("")){
                    Toast.makeText(ListaMedicosActivity.this, "No hay Medico Registrado", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Medicos").child("2").removeValue();
                    Texto2.setText("");
                    Toast.makeText(ListaMedicosActivity.this, "Medico 2 removido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminar_id3=(Button)findViewById(R.id.eliminar_id3);
        eliminar_id3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto3.getText().toString().equals("")){
                    Toast.makeText(ListaMedicosActivity.this, "No hay Medico Registrado", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Medicos").child("3").removeValue();
                    Texto3.setText("");
                    Toast.makeText(ListaMedicosActivity.this, "Medico 3 removido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminar_id4=(Button)findViewById(R.id.eliminar_id4);
        eliminar_id4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Texto4.getText().toString().equals("")){
                    Toast.makeText(ListaMedicosActivity.this, "No hay Medico Registrado", Toast.LENGTH_SHORT).show();
                }else{
                    Base_de_datos.child("Medicos").child("4").removeValue();
                    Texto4.setText("");
                    Toast.makeText(ListaMedicosActivity.this, "Medico 4 removido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LlenarDatos();
        Eliminar_Registros();

    }
    private void Eliminar_Registros() {
        btn_eliminar_medicos = (Button)findViewById(R.id.btn_eliminar_medicos);
        btn_eliminar_medicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaMedicosActivity.this);
                builder.setMessage("Desea Confirmar?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int witch) {
                        Base_de_datos.child("Medicos").removeValue();
                        Toast.makeText(ListaMedicosActivity.this, "Registros Medicos Borrados", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ListaMedicosActivity.this, ListaMedicosActivity.class);
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
        Base_de_datos.child("Medicos").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String Código_de_Tarjeta_Profesional = snapshot.child("codigo_Tarjeta_Profesional").getValue().toString();
                    String especialidad = snapshot.child("especialidad").getValue().toString();
                    String Años_de_Experiencia = snapshot.child("años_De_Experiencia").getValue().toString();
                    String Consultorio = snapshot.child("consultorio").getValue().toString();
                    String Atiende_a_domicilio = snapshot.child("especialidad").getValue().toString();
                    Texto1.setText("Código de Tarjeta Profesional: " + Código_de_Tarjeta_Profesional + "\n" +
                            "Especialidad: " + especialidad + "\n" +
                            "Años de Experiencia: " + Años_de_Experiencia + "\n" +
                            "Consultorio: " + Consultorio + "\n" +
                            "Atiende a domicilio: " + Atiende_a_domicilio);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Base_de_datos.child("Medicos").child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                if (snapshot2.exists()) {
                    String Código_de_Tarjeta_Profesional = snapshot2.child("codigo_Tarjeta_Profesional").getValue().toString();
                    String especialidad = snapshot2.child("especialidad").getValue().toString();
                    String Años_de_Experiencia = snapshot2.child("años_De_Experiencia").getValue().toString();
                    String Consultorio = snapshot2.child("consultorio").getValue().toString();
                    String Atiende_a_domicilio = snapshot2.child("especialidad").getValue().toString();
                    Texto2.setText("Código de Tarjeta Profesional: " + Código_de_Tarjeta_Profesional + "\n" +
                            "Especialidad: " + especialidad + "\n" +
                            "Años de Experiencia: " + Años_de_Experiencia + "\n" +
                            "Consultorio: " + Consultorio + "\n" +
                            "Atiende a domicilio: " + Atiende_a_domicilio);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Base_de_datos.child("Medicos").child("3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot3) {
                if (snapshot3.exists()) {
                    String Código_de_Tarjeta_Profesional = snapshot3.child("codigo_Tarjeta_Profesional").getValue().toString();
                    String especialidad = snapshot3.child("especialidad").getValue().toString();
                    String Años_de_Experiencia = snapshot3.child("años_De_Experiencia").getValue().toString();
                    String Consultorio = snapshot3.child("consultorio").getValue().toString();
                    String Atiende_a_domicilio = snapshot3.child("especialidad").getValue().toString();
                    Texto3.setText("Código de Tarjeta Profesional: " + Código_de_Tarjeta_Profesional + "\n" +
                            "Especialidad: " + especialidad + "\n" +
                            "Años de Experiencia: " + Años_de_Experiencia + "\n" +
                            "Consultorio: " + Consultorio + "\n" +
                            "Atiende a domicilio: " + Atiende_a_domicilio);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Base_de_datos.child("Medicos").child("4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot4) {
                if (snapshot4.exists()) {
                    String Código_de_Tarjeta_Profesional = snapshot4.child("codigo_Tarjeta_Profesional").getValue().toString();
                    String especialidad = snapshot4.child("especialidad").getValue().toString();
                    String Años_de_Experiencia = snapshot4.child("años_De_Experiencia").getValue().toString();
                    String Consultorio = snapshot4.child("consultorio").getValue().toString();
                    String Atiende_a_domicilio = snapshot4.child("especialidad").getValue().toString();
                    Texto4.setText("Código de Tarjeta Profesional: " + Código_de_Tarjeta_Profesional + "\n" +
                            "Especialidad: " + especialidad + "\n" +
                            "Años de Experiencia: " + Años_de_Experiencia + "\n" +
                            "Consultorio: " + Consultorio + "\n" +
                            "Atiende a domicilio: " + Atiende_a_domicilio);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}

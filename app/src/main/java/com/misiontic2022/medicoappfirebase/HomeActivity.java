package com.misiontic2022.medicoappfirebase;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    private DatabaseReference Base_de_datos;
    Button btn_ir_paciente;
    Button btn_ir_medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Base_de_datos = FirebaseDatabase.getInstance().getReference();
        btn_ir_paciente = (Button) findViewById(R.id.btn_ir_paciente);
            btn_ir_paciente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Base_de_datos.child("Pacientes").removeValue();
                    Intent i =new Intent(HomeActivity.this,ListaPacientesActivity.class);
                    startActivity(i);
                }
        });
        btn_ir_medico= (Button) findViewById(R.id.btn_ir_medico);
            btn_ir_medico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Base_de_datos.child("Medicos").removeValue();
                    Intent i =new Intent(HomeActivity.this,ListaMedicosActivity.class);
                    startActivity(i);
                }
            });



    }
}
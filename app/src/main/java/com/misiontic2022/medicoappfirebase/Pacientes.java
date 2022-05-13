package com.misiontic2022.medicoappfirebase;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Date;

public class Pacientes {
    private String Nombres;
    private String Apellidos;
    private String Fecha_Nacimiento;
    private String Identificacion;
    private String Esta_en_tratamiento;
    private String Valor_Cuota;
    private String Fecha_cita;

    public String getEsta_en_tratamiento() {
        return Esta_en_tratamiento;
    }

    public void setEsta_en_tratamiento(String esta_en_tratamiento) {
        Esta_en_tratamiento = esta_en_tratamiento;
    }



    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String identificacion) {
        Identificacion = identificacion;
    }



    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(String fecha_Nacimiento) {
        Fecha_Nacimiento = fecha_Nacimiento;
    }


    public String getValor_Cuota() {
        return Valor_Cuota;
    }

    public void setValor_Cuota(String valor_Cuota) {
        Valor_Cuota = valor_Cuota;
    }

    public String getFecha_cita() {
        return Fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        Fecha_cita = fecha_cita;
    }




    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {

        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

}

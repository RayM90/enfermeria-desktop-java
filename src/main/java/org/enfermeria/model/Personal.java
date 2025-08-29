package org.enfermeria.model;

import java.sql.Date;

public class Personal {

    private int id_personal;
    private int id_tipodocumento;
    private String numero_documento;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String correo;
    private String telefono;
    private Date fecha_contratacion;
    private Date fecha_nacimiento;

    // Constructor vacío
    public Personal() {
    }

    // Constructor completo
    public Personal(int id_personal, int id_tipodocumento, String numero_documento,
                    String nombres, String apellidos, String especialidad,
                    String correo, String telefono, Date fecha_contratacion, Date fecha_nacimiento) {
        this.id_personal = id_personal;
        this.id_tipodocumento = id_tipodocumento;
        this.numero_documento = numero_documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_contratacion = fecha_contratacion;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // Getters y Setters
    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public int getId_tipodocumento() {
        return id_tipodocumento;
    }

    public void setId_tipodocumento(int id_tipodocumento) {
        this.id_tipodocumento = id_tipodocumento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}

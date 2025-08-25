package org.enfermeria.model;

import java.util.Date;

public class Paciente {
    private int idPacientes;
    private int idTipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellido;
    private Date fechaNacimiento;
    private int idSexo;
    private String correo;
    private String direccion;
    private String referido;

    // Constructor vacío
    public Paciente() {}

    // Constructor completo
    public Paciente(int idPacientes, int idTipoDocumento, String numeroDocumento, String nombres, String apellido,
                    Date fechaNacimiento, int idSexo, String correo, String direccion, String referido) {
        this.idPacientes = idPacientes;
        this.idTipoDocumento = idTipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.idSexo = idSexo;
        this.correo = correo;
        this.direccion = direccion;
        this.referido = referido;
    }

    // Getters y Setters
    public int getIdPacientes() { return idPacientes; }
    public void setIdPacientes(int idPacientes) { this.idPacientes = idPacientes; }

    public int getIdTipoDocumento() { return idTipoDocumento; }
    public void setIdTipoDocumento(int idTipoDocumento) { this.idTipoDocumento = idTipoDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int getIdSexo() { return idSexo; }
    public void setIdSexo(int idSexo) { this.idSexo = idSexo; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getReferido() { return referido; }
    public void setReferido(String referido) { this.referido = referido; }
}

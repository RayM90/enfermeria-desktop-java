package org.enfermeria.model;

import java.sql.Date;

public class Consulta {
    private int idConsultas;
    private Date fechaConsulta;
    private String motivoConsulta;
    private String diagnostico;
    private String tratamientoSugerido;
    private String notasAdicionales;
    private int idPacientes;  // FK hacia Paciente
    private int idPersonal;   // FK hacia Personal

    // Constructor vacío
    public Consulta() {}

    // Constructor completo
    public Consulta(int idConsultas, Date fechaConsulta, String motivoConsulta, String diagnostico,
                    String tratamientoSugerido, String notasAdicionales, int idPacientes, int idPersonal) {
        this.idConsultas = idConsultas;
        this.fechaConsulta = fechaConsulta;
        this.motivoConsulta = motivoConsulta;
        this.diagnostico = diagnostico;
        this.tratamientoSugerido = tratamientoSugerido;
        this.notasAdicionales = notasAdicionales;
        this.idPacientes = idPacientes;
        this.idPersonal = idPersonal;
    }

    // Constructor sin ID (para insertar nuevos registros)
    public Consulta(Date fechaConsulta, String motivoConsulta, String diagnostico,
                    String tratamientoSugerido, String notasAdicionales, int idPacientes, int idPersonal) {
        this.fechaConsulta = fechaConsulta;
        this.motivoConsulta = motivoConsulta;
        this.diagnostico = diagnostico;
        this.tratamientoSugerido = tratamientoSugerido;
        this.notasAdicionales = notasAdicionales;
        this.idPacientes = idPacientes;
        this.idPersonal = idPersonal;
    }

    // Getters y Setters
    public int getIdConsultas() {
        return idConsultas;
    }

    public void setIdConsultas(int idConsultas) {
        this.idConsultas = idConsultas;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamientoSugerido() {
        return tratamientoSugerido;
    }

    public void setTratamientoSugerido(String tratamientoSugerido) {
        this.tratamientoSugerido = tratamientoSugerido;
    }

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }

    public int getIdPacientes() {
        return idPacientes;
    }

    public void setIdPacientes(int idPacientes) {
        this.idPacientes = idPacientes;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "idConsultas=" + idConsultas +
                ", fechaConsulta=" + fechaConsulta +
                ", motivoConsulta='" + motivoConsulta + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamientoSugerido='" + tratamientoSugerido + '\'' +
                ", notasAdicionales='" + notasAdicionales + '\'' +
                ", idPacientes=" + idPacientes +
                ", idPersonal=" + idPersonal +
                '}';
    }
}

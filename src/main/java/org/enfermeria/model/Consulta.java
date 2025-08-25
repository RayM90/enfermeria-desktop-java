package org.enfermeria.model;

import java.util.Date;

public class Consulta {
    private int idConsultas;
    private Date fechaConsulta;
    private String motivoConsulte;       // Ajustado
    private String diagnostico;
    private String tratamientoSeguido;   // Ajustado
    private String notasAdicionales;     // Opcional: notas_adicionales si quieres exacto
    private int idPacientes;
    private int idPersonal;

    // Constructor vacío
    public Consulta() {}

    // Constructor completo
    public Consulta(int idConsultas, Date fechaConsulta, String motivoConsulte, String diagnostico,
                    String tratamientoSeguido, String notasAdicionales, int idPacientes, int idPersonal) {
        this.idConsultas = idConsultas;
        this.fechaConsulta = fechaConsulta;
        this.motivoConsulte = motivoConsulte;
        this.diagnostico = diagnostico;
        this.tratamientoSeguido = tratamientoSeguido;
        this.notasAdicionales = notasAdicionales;
        this.idPacientes = idPacientes;
        this.idPersonal = idPersonal;
    }

    // Getters y Setters
    public int getIdConsultas() { return idConsultas; }
    public void setIdConsultas(int idConsultas) { this.idConsultas = idConsultas; }

    public Date getFechaConsulta() { return fechaConsulta; }
    public void setFechaConsulta(Date fechaConsulta) { this.fechaConsulta = fechaConsulta; }

    public String getMotivoConsulte() { return motivoConsulte; }
    public void setMotivoConsulte(String motivoConsulte) { this.motivoConsulte = motivoConsulte; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamientoSeguido() { return tratamientoSeguido; }
    public void setTratamientoSeguido(String tratamientoSeguido) { this.tratamientoSeguido = tratamientoSeguido; }

    public String getNotasAdicionales() { return notasAdicionales; }
    public void setNotasAdicionales(String notasAdicionales) { this.notasAdicionales = notasAdicionales; }

    public int getIdPacientes() { return idPacientes; }
    public void setIdPacientes(int idPacientes) { this.idPacientes = idPacientes; }

    public int getIdPersonal() { return idPersonal; }
    public void setIdPersonal(int idPersonal) { this.idPersonal = idPersonal; }
}

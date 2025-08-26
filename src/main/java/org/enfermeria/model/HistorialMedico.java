package org.enfermeria.model;

import java.util.Date;

public class HistorialMedico {
    private int idHistorialMedico;
    private Date fechaRegistro;
    private String diagnostico;
    private String tratamiento;
    private String alergias;
    private String condicionesMedicasPrevias;
    private String medicamentosActuales;
    private int idPaciente;

    // 🔹 Constructor vacío
    public HistorialMedico() {}

    // 🔹 Constructor completo
    public HistorialMedico(int idHistorialMedico, Date fechaRegistro, String diagnostico,
                           String tratamiento, String alergias, String condicionesMedicasPrevias,
                           String medicamentosActuales, int idPaciente) {
        this.idHistorialMedico = idHistorialMedico;
        this.fechaRegistro = fechaRegistro;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.alergias = alergias;
        this.condicionesMedicasPrevias = condicionesMedicasPrevias;
        this.medicamentosActuales = medicamentosActuales;
        this.idPaciente = idPaciente;
    }

    // 🔹 Getters y Setters
    public int getIdHistorialMedico() {
        return idHistorialMedico;
    }

    public void setIdHistorialMedico(int idHistorialMedico) {
        this.idHistorialMedico = idHistorialMedico;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicionesMedicasPrevias() {
        return condicionesMedicasPrevias;
    }

    public void setCondicionesMedicasPrevias(String condicionesMedicasPrevias) {
        this.condicionesMedicasPrevias = condicionesMedicasPrevias;
    }

    public String getMedicamentosActuales() {
        return medicamentosActuales;
    }

    public void setMedicamentosActuales(String medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public String toString() {
        return "HistorialMedico{" +
                "idHistorialMedico=" + idHistorialMedico +
                ", fechaRegistro=" + fechaRegistro +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", alergias='" + alergias + '\'' +
                ", condicionesMedicasPrevias='" + condicionesMedicasPrevias + '\'' +
                ", medicamentosActuales='" + medicamentosActuales + '\'' +
                ", idPaciente=" + idPaciente +
                '}';
    }
}

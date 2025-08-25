package org.enfermeria.model;

import java.util.Date;

public class HistorialMedico {

    private int id_historialmedicos;
    private int id_pacientes;
    private String diagnostico;
    private String tratamiento;
    private String alergias;
    private String condiciones_medicas_previas;
    private String medicamentos_actuales;
    private Date fecha_registro;

    // Constructor vacío
    public HistorialMedico() {}

    // Constructor completo
    public HistorialMedico(int id_historialmedicos, int id_pacientes, String diagnostico, String tratamiento,
                           String alergias, String condiciones_medicas_previas, String medicamentos_actuales,
                           Date fecha_registro) {
        this.id_historialmedicos = id_historialmedicos;
        this.id_pacientes = id_pacientes;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.alergias = alergias;
        this.condiciones_medicas_previas = condiciones_medicas_previas;
        this.medicamentos_actuales = medicamentos_actuales;
        this.fecha_registro = fecha_registro;
    }

    // Getters y Setters
    public int getId_historialmedicos() { return id_historialmedicos; }
    public void setId_historialmedicos(int id_historialmedicos) { this.id_historialmedicos = id_historialmedicos; }

    public int getId_pacientes() { return id_pacientes; }
    public void setId_pacientes(int id_pacientes) { this.id_pacientes = id_pacientes; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getCondiciones_medicas_previas() { return condiciones_medicas_previas; }
    public void setCondiciones_medicas_previas(String condiciones_medicas_previas) { this.condiciones_medicas_previas = condiciones_medicas_previas; }

    public String getMedicamentos_actuales() { return medicamentos_actuales; }
    public void setMedicamentos_actuales(String medicamentos_actuales) { this.medicamentos_actuales = medicamentos_actuales; }

    public Date getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(Date fecha_registro) { this.fecha_registro = fecha_registro; }

    @Override
    public String toString() {
        return "HistorialMedico{" +
                "id_historialmedicos=" + id_historialmedicos +
                ", id_pacientes=" + id_pacientes +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", alergias='" + alergias + '\'' +
                ", condiciones_medicas_previas='" + condiciones_medicas_previas + '\'' +
                ", medicamentos_actuales='" + medicamentos_actuales + '\'' +
                ", fecha_registro=" + fecha_registro +
                '}';
    }
}

package org.enfermeria.model;

import java.sql.Date;

public class Paciente {

    private int id_pacientes;
    private int id_tipodocumento;
    private String numero_documento;
    private String nombres;
    private String apellidos;
    private Date fecha_nacimiento;
    private int id_sexo;
    private int id_tiposangre;
    private String correo;
    private String telefono; // 🔹 Nuevo campo

    // Constructor vacío
    public Paciente() {}

    // Constructor completo
    public Paciente(int id_pacientes, int id_tipodocumento, String numero_documento,
                    String nombres, String apellidos, Date fecha_nacimiento,
                    int id_sexo, int id_tiposangre, String correo, String telefono) {
        this.id_pacientes = id_pacientes;
        this.id_tipodocumento = id_tipodocumento;
        this.numero_documento = numero_documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_sexo = id_sexo;
        this.id_tiposangre = id_tiposangre;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getId_pacientes() { return id_pacientes; }
    public void setId_pacientes(int id_pacientes) { this.id_pacientes = id_pacientes; }

    public int getId_tipodocumento() { return id_tipodocumento; }
    public void setId_tipodocumento(int id_tipodocumento) { this.id_tipodocumento = id_tipodocumento; }

    public String getNumero_documento() { return numero_documento; }
    public void setNumero_documento(String numero_documento) { this.numero_documento = numero_documento; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public Date getFecha_nacimiento() { return fecha_nacimiento; }
    public void setFecha_nacimiento(Date fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }

    public int getId_sexo() { return id_sexo; }
    public void setId_sexo(int id_sexo) { this.id_sexo = id_sexo; }

    public int getId_tiposangre() { return id_tiposangre; }
    public void setId_tiposangre(int id_tiposangre) { this.id_tiposangre = id_tiposangre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}

package org.enfermeria.model;

import java.sql.Date;
import java.sql.Timestamp;

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
    private String telefono;
    private String direccion;         // 🔹 Nuevo campo
    private String cargo;             // 🔹 Nuevo campo
    private Timestamp fecha_registro; // 🔹 Autogenerado por la BD

    // Constructor vacío
    public Paciente() {}

    // Constructor completo para lecturas desde BD (incluye fecha_registro y cargo)
    public Paciente(int id_pacientes, int id_tipodocumento, String numero_documento,
                    String nombres, String apellidos, Date fecha_nacimiento,
                    int id_sexo, int id_tiposangre, String correo, String telefono,
                    String direccion, String cargo, Timestamp fecha_registro) {
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
        this.direccion = direccion;
        this.cargo = cargo;
        this.fecha_registro = fecha_registro;
    }

    // Constructor para crear nuevo paciente (sin fecha_registro)
    public Paciente(int id_pacientes, int id_tipodocumento, String numero_documento,
                    String nombres, String apellidos, Date fecha_nacimiento,
                    int id_sexo, int id_tiposangre, String correo, String telefono,
                    String direccion, String cargo) {
        this(id_pacientes, id_tipodocumento, numero_documento, nombres, apellidos,
                fecha_nacimiento, id_sexo, id_tiposangre, correo, telefono, direccion, cargo, null);
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

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Timestamp getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(Timestamp fecha_registro) { this.fecha_registro = fecha_registro; }
}

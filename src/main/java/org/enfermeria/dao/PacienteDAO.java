package org.enfermeria.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Paciente;

public class PacienteDAO {

    // Crear/Insertar
    public void insertarPaciente(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO pacientes (id_tipo_documento, numero_documento, nombres, apellido, fecha_nacimiento, id_sexo, correo, direccion, referido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paciente.getIdTipoDocumento());
            ps.setString(2, paciente.getNumeroDocumento());
            ps.setString(3, paciente.getNombres());
            ps.setString(4, paciente.getApellido());
            ps.setDate(5, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            ps.setInt(6, paciente.getIdSexo());
            ps.setString(7, paciente.getCorreo());
            ps.setString(8, paciente.getDireccion());
            ps.setString(9, paciente.getReferido());

            ps.executeUpdate();
        }
    }

    // Leer/Listar todos los pacientes
    public List<Paciente> listarPacientes() throws SQLException {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdPacientes(rs.getInt("id_pacientes"));
                p.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
                p.setNumeroDocumento(rs.getString("numero_documento"));
                p.setNombres(rs.getString("nombres"));
                p.setApellido(rs.getString("apellido"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                p.setIdSexo(rs.getInt("id_sexo"));
                p.setCorreo(rs.getString("correo"));
                p.setDireccion(rs.getString("direccion"));
                p.setReferido(rs.getString("referido"));
                lista.add(p);
            }
        }
        return lista;
    }

    // Actualizar
    public void actualizarPaciente(Paciente paciente) throws SQLException {
        String sql = "UPDATE pacientes SET id_tipo_documento=?, numero_documento=?, nombres=?, apellido=?, fecha_nacimiento=?, id_sexo=?, correo=?, direccion=?, referido=? WHERE id_pacientes=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paciente.getIdTipoDocumento());
            ps.setString(2, paciente.getNumeroDocumento());
            ps.setString(3, paciente.getNombres());
            ps.setString(4, paciente.getApellido());
            ps.setDate(5, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            ps.setInt(6, paciente.getIdSexo());
            ps.setString(7, paciente.getCorreo());
            ps.setString(8, paciente.getDireccion());
            ps.setString(9, paciente.getReferido());
            ps.setInt(10, paciente.getIdPacientes());

            ps.executeUpdate();
        }
    }

    // Eliminar
    public void eliminarPaciente(int id) throws SQLException {
        String sql = "DELETE FROM pacientes WHERE id_pacientes=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

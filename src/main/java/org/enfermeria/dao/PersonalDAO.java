package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Personal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalDAO {

    // Crear/Insertar
    public void insertarPersonal(Personal personal) throws SQLException {
        String sql = "INSERT INTO personal (id_tipo_documento, numero_documento, nombres, apellidos, especialidad, correo, telefono, fecha_nacimiento, fecha_contratacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = org.enfermeria.config.ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, personal.getIdTipoDocumento());
            ps.setString(2, personal.getNumeroDocumento());
            ps.setString(3, personal.getNombres());
            ps.setString(4, personal.getApellidos());
            ps.setString(5, personal.getEspecialidad());
            ps.setString(6, personal.getCorreo());
            ps.setString(7, personal.getTelefono());
            ps.setDate(8, new java.sql.Date(personal.getFechaNacimiento().getTime()));
            ps.setDate(9, new java.sql.Date(personal.getFechaContratacion().getTime()));

            ps.executeUpdate();
        }
    }

    // Leer/Listar todos los personal
    public List<Personal> listarPersonal() throws SQLException {
        List<Personal> lista = new ArrayList<>();
        String sql = "SELECT * FROM personal";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Personal p = new Personal();
                p.setIdPersonal(rs.getInt("id_personal"));
                p.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
                p.setNumeroDocumento(rs.getString("numero_documento"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setEspecialidad(rs.getString("especialidad"));
                p.setCorreo(rs.getString("correo"));
                p.setTelefono(rs.getString("telefono"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                p.setFechaContratacion(rs.getDate("fecha_contratacion"));
                lista.add(p);
            }
        }
        return lista;
    }

    // Actualizar
    public void actualizarPersonal(Personal personal) throws SQLException {
        String sql = "UPDATE personal SET id_tipo_documento=?, numero_documento=?, nombres=?, apellidos=?, especialidad=?, correo=?, telefono=?, fecha_nacimiento=?, fecha_contratacion=? WHERE id_personal=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, personal.getIdTipoDocumento());
            ps.setString(2, personal.getNumeroDocumento());
            ps.setString(3, personal.getNombres());
            ps.setString(4, personal.getApellidos());
            ps.setString(5, personal.getEspecialidad());
            ps.setString(6, personal.getCorreo());
            ps.setString(7, personal.getTelefono());
            ps.setDate(8, new java.sql.Date(personal.getFechaNacimiento().getTime()));
            ps.setDate(9, new java.sql.Date(personal.getFechaContratacion().getTime()));
            ps.setInt(10, personal.getIdPersonal());

            ps.executeUpdate();
        }
    }

    // Eliminar
    public void eliminarPersonal(int id) throws SQLException {
        String sql = "DELETE FROM personal WHERE id_personal=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

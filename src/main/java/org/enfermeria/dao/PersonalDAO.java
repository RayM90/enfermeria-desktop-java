package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Personal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalDAO {

    // Crear nuevo personal
    public boolean crearPersonal(Personal p) {
        String sql = "INSERT INTO personal (id_tipodocumento, numero_documento, nombres, apellidos, especialidad, correo, telefono, fecha_contratacion, fecha_nacimiento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId_tipodocumento());
            ps.setString(2, p.getNumero_documento());
            ps.setString(3, p.getNombres());
            ps.setString(4, p.getApellidos());
            ps.setString(5, p.getEspecialidad());
            ps.setString(6, p.getCorreo());
            ps.setString(7, p.getTelefono());

            // Manejo seguro de fechas
            ps.setDate(8, p.getFecha_contratacion());
            ps.setDate(9, p.getFecha_nacimiento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear personal: " + e.getMessage());
            return false;
        }
    }

    // Obtener personal por ID
    public Personal obtenerPersonalPorId(int id) {
        String sql = "SELECT * FROM personal WHERE id_personal = ?";
        Personal personal = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    personal = new Personal(
                            rs.getInt("id_personal"),
                            rs.getInt("id_tipodocumento"),
                            rs.getString("numero_documento"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("especialidad"),
                            rs.getString("correo"),
                            rs.getString("telefono"),
                            rs.getDate("fecha_contratacion"),
                            rs.getDate("fecha_nacimiento")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener personal por ID: " + e.getMessage());
        }

        return personal;
    }

    // Actualizar personal
    public boolean actualizarPersonal(Personal p) {
        String sql = "UPDATE personal SET id_tipodocumento=?, numero_documento=?, nombres=?, apellidos=?, especialidad=?, correo=?, telefono=?, fecha_contratacion=?, fecha_nacimiento=? " +
                "WHERE id_personal=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId_tipodocumento());
            ps.setString(2, p.getNumero_documento());
            ps.setString(3, p.getNombres());
            ps.setString(4, p.getApellidos());
            ps.setString(5, p.getEspecialidad());
            ps.setString(6, p.getCorreo());
            ps.setString(7, p.getTelefono());
            ps.setDate(8, p.getFecha_contratacion());
            ps.setDate(9, p.getFecha_nacimiento());
            ps.setInt(10, p.getId_personal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar personal: " + e.getMessage());
            return false;
        }
    }

    // Eliminar personal
    public boolean eliminarPersonal(int id) {
        String sql = "DELETE FROM personal WHERE id_personal=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar personal: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los personales
    public List<Personal> listarPersonal() {
        String sql = "SELECT * FROM personal";
        List<Personal> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Personal p = new Personal(
                        rs.getInt("id_personal"),
                        rs.getInt("id_tipodocumento"),
                        rs.getString("numero_documento"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("especialidad"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_contratacion"),
                        rs.getDate("fecha_nacimiento")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar personal: " + e.getMessage());
        }

        return lista;
    }
}

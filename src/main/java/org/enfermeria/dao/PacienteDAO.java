package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // Crear nuevo paciente
    public boolean crearPaciente(Paciente p) {
        String sql = "INSERT INTO pacientes (id_tipodocumento, numero_documento, nombres, apellidos, fecha_nacimiento, id_sexo, id_tiposangre, correo, telefono, direccion, cargo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId_tipodocumento());
            ps.setString(2, p.getNumero_documento());
            ps.setString(3, p.getNombres());
            ps.setString(4, p.getApellidos());
            ps.setDate(5, p.getFecha_nacimiento());
            ps.setInt(6, p.getId_sexo());
            ps.setInt(7, p.getId_tiposangre());
            ps.setString(8, p.getCorreo() != null ? p.getCorreo() : "");
            ps.setString(9, p.getTelefono() != null ? p.getTelefono() : "");
            ps.setString(10, p.getDireccion() != null ? p.getDireccion() : "");
            ps.setString(11, p.getCargo() != null ? p.getCargo() : "");

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear paciente: " + e.getMessage());
            return false;
        }
    }

    // Obtener paciente por ID
    public Paciente obtenerPacientePorId(int id) {
        String sql = "SELECT * FROM pacientes WHERE id_pacientes = ?";
        Paciente paciente = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente = new Paciente(
                        rs.getInt("id_pacientes"),
                        rs.getInt("id_tipodocumento"),
                        rs.getString("numero_documento"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getInt("id_sexo"),
                        rs.getInt("id_tiposangre"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("cargo"),
                        rs.getTimestamp("fecha_registro")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener paciente: " + e.getMessage());
        }

        return paciente;
    }

    // Actualizar paciente
    public boolean actualizarPaciente(Paciente p) {
        String sql = "UPDATE pacientes SET id_tipodocumento=?, numero_documento=?, nombres=?, apellidos=?, fecha_nacimiento=?, id_sexo=?, id_tiposangre=?, correo=?, telefono=?, direccion=?, cargo=? " +
                "WHERE id_pacientes=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId_tipodocumento());
            ps.setString(2, p.getNumero_documento());
            ps.setString(3, p.getNombres());
            ps.setString(4, p.getApellidos());
            ps.setDate(5, p.getFecha_nacimiento());
            ps.setInt(6, p.getId_sexo());
            ps.setInt(7, p.getId_tiposangre());
            ps.setString(8, p.getCorreo() != null ? p.getCorreo() : "");
            ps.setString(9, p.getTelefono() != null ? p.getTelefono() : "");
            ps.setString(10, p.getDireccion() != null ? p.getDireccion() : "");
            ps.setString(11, p.getCargo() != null ? p.getCargo() : "");
            ps.setInt(12, p.getId_pacientes());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
            return false;
        }
    }

    // Eliminar paciente
    public boolean eliminarPaciente(int id) {
        String sql = "DELETE FROM pacientes WHERE id_pacientes=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los pacientes
    public List<Paciente> listarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente(
                        rs.getInt("id_pacientes"),
                        rs.getInt("id_tipodocumento"),
                        rs.getString("numero_documento"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getInt("id_sexo"),
                        rs.getInt("id_tiposangre"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("cargo"),
                        rs.getTimestamp("fecha_registro")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pacientes: " + e.getMessage());
        }

        return lista;
    }
}

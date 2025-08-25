package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.HistorialMedico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedicoDAO {

    public void insertarHistorial(HistorialMedico h) throws SQLException {
        String sql = "INSERT INTO historial_medico (fecha_registro, diagnostico, tratamiento, alergias, condiciones_medicas_previas, medicamentos_actuales, id_pacientes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(h.getFecha_registro().getTime()));
            ps.setString(2, h.getDiagnostico());
            ps.setString(3, h.getTratamiento());
            ps.setString(4, h.getAlergias());
            ps.setString(5, h.getCondiciones_medicas_previas());
            ps.setString(6, h.getMedicamentos_actuales());
            ps.setInt(7, h.getId_pacientes());

            ps.executeUpdate();
        }
    }

    public List<HistorialMedico> listarHistorial() throws SQLException {
        List<HistorialMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HistorialMedico h = new HistorialMedico();
                h.setId_historialmedicos(rs.getInt("id_historialmedicos"));
                h.setFecha_registro(rs.getDate("fecha_registro"));
                h.setDiagnostico(rs.getString("diagnostico"));
                h.setTratamiento(rs.getString("tratamiento"));
                h.setAlergias(rs.getString("alergias"));
                h.setCondiciones_medicas_previas(rs.getString("condiciones_medicas_previas"));
                h.setMedicamentos_actuales(rs.getString("medicamentos_actuales"));
                h.setId_pacientes(rs.getInt("id_pacientes"));
                lista.add(h);
            }
        }
        return lista;
    }

    public void actualizarHistorial(HistorialMedico h) throws SQLException {
        String sql = "UPDATE historial_medico SET fecha_registro=?, diagnostico=?, tratamiento=?, alergias=?, condiciones_medicas_previas=?, medicamentos_actuales=?, id_pacientes=? WHERE id_historialmedicos=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(h.getFecha_registro().getTime()));
            ps.setString(2, h.getDiagnostico());
            ps.setString(3, h.getTratamiento());
            ps.setString(4, h.getAlergias());
            ps.setString(5, h.getCondiciones_medicas_previas());
            ps.setString(6, h.getMedicamentos_actuales());
            ps.setInt(7, h.getId_pacientes());
            ps.setInt(8, h.getId_historialmedicos());

            ps.executeUpdate();
        }
    }

    public void eliminarHistorial(int id) throws SQLException {
        String sql = "DELETE FROM historial_medico WHERE id_historialmedicos=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
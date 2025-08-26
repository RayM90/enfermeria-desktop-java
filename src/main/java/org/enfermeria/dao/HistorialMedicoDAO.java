package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.HistorialMedico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedicoDAO {

    // 🔹 Insertar un historial médico
    public void insertar(HistorialMedico historial) {
        String sql = "INSERT INTO historial_medico (fecha_registro, diagnostico, tratamiento, alergias, " +
                "condiciones_medicas_previas, medicamentes_actuales, id_pacientes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, new java.sql.Date(historial.getFechaRegistro().getTime()));
            stmt.setString(2, historial.getDiagnostico());
            stmt.setString(3, historial.getTratamiento());
            stmt.setString(4, historial.getAlergias());
            stmt.setString(5, historial.getCondicionesMedicasPrevias());
            stmt.setString(6, historial.getMedicamentosActuales());
            stmt.setInt(7, historial.getIdPaciente());

            stmt.executeUpdate();

            // recuperar id autogenerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    historial.setIdHistorialMedico(rs.getInt(1));
                }
            }

            System.out.println("✅ Historial médico insertado correctamente.");

        } catch (SQLException e) {
            System.err.println("❌ Error insertando historial médico: " + e.getMessage());
        }
    }

    // 🔹 Obtener historial por ID
    public HistorialMedico obtenerPorId(int id) {
        String sql = "SELECT * FROM historial_medico WHERE id_historialmedico = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error obteniendo historial médico: " + e.getMessage());
        }
        return null;
    }

    // 🔹 Listar todos los historiales
    public List<HistorialMedico> listarTodos() {
        List<HistorialMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error listando historiales médicos: " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Actualizar historial médico
    public void actualizar(HistorialMedico historial) {
        String sql = "UPDATE historial_medico SET fecha_registro=?, diagnostico=?, tratamiento=?, alergias=?, " +
                "condiciones_medicas_previas=?, medicamentes_actuales=?, id_pacientes=? WHERE id_historialmedico=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(historial.getFechaRegistro().getTime()));
            stmt.setString(2, historial.getDiagnostico());
            stmt.setString(3, historial.getTratamiento());
            stmt.setString(4, historial.getAlergias());
            stmt.setString(5, historial.getCondicionesMedicasPrevias());
            stmt.setString(6, historial.getMedicamentosActuales());
            stmt.setInt(7, historial.getIdPaciente());
            stmt.setInt(8, historial.getIdHistorialMedico());

            stmt.executeUpdate();
            System.out.println("✅ Historial médico actualizado correctamente.");

        } catch (SQLException e) {
            System.err.println("❌ Error actualizando historial médico: " + e.getMessage());
        }
    }

    // 🔹 Eliminar historial médico
    public void eliminar(int id) {
        String sql = "DELETE FROM historial_medico WHERE id_historialmedico=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Historial médico eliminado correctamente.");

        } catch (SQLException e) {
            System.err.println("❌ Error eliminando historial médico: " + e.getMessage());
        }
    }

    // 🔹 Método privado para mapear ResultSet -> HistorialMedico
    private HistorialMedico mapResultSet(ResultSet rs) throws SQLException {
        HistorialMedico historial = new HistorialMedico();
        historial.setIdHistorialMedico(rs.getInt("id_historialmedico"));
        historial.setFechaRegistro(rs.getDate("fecha_registro"));
        historial.setDiagnostico(rs.getString("diagnostico"));
        historial.setTratamiento(rs.getString("tratamiento"));
        historial.setAlergias(rs.getString("alergias"));
        historial.setCondicionesMedicasPrevias(rs.getString("condiciones_medicas_previas"));
        historial.setMedicamentosActuales(rs.getString("medicamentes_actuales"));
        historial.setIdPaciente(rs.getInt("id_pacientes"));
        return historial;
    }
}

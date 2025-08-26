package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    // INSERTAR nueva consulta
    public void insertar(Consulta consulta) {
        String sql = "INSERT INTO consultas (fecha_consulta, motivo_consulta, diagnostico, " +
                "tratamiento_sugerido, notas_adicionales, id_pacientes, id_personal) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, consulta.getFechaConsulta());
            ps.setString(2, consulta.getMotivoConsulta());
            ps.setString(3, consulta.getDiagnostico());
            ps.setString(4, consulta.getTratamientoSugerido());
            ps.setString(5, consulta.getNotasAdicionales());
            ps.setInt(6, consulta.getIdPacientes());
            ps.setInt(7, consulta.getIdPersonal());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        consulta.setIdConsultas(rs.getInt(1));
                    }
                }
            }
            System.out.println("✅ Consulta insertada correctamente: " + consulta);

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar consulta: " + e.getMessage());
        }
    }

    // OBTENER consulta por ID
    public Consulta obtenerPorId(int id) {
        String sql = "SELECT * FROM consultas WHERE id_consultas = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearConsulta(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener consulta: " + e.getMessage());
        }
        return null;
    }

    // LISTAR todas las consultas
    public List<Consulta> listar() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consultas";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearConsulta(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar consultas: " + e.getMessage());
        }
        return lista;
    }

    // ACTUALIZAR consulta
    public void actualizar(Consulta consulta) {
        String sql = "UPDATE consultas SET fecha_consulta = ?, motivo_consulta = ?, diagnostico = ?, " +
                "tratamiento_sugerido = ?, notas_adicionales = ?, id_pacientes = ?, id_personal = ? " +
                "WHERE id_consultas = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, consulta.getFechaConsulta());
            ps.setString(2, consulta.getMotivoConsulta());
            ps.setString(3, consulta.getDiagnostico());
            ps.setString(4, consulta.getTratamientoSugerido());
            ps.setString(5, consulta.getNotasAdicionales());
            ps.setInt(6, consulta.getIdPacientes());
            ps.setInt(7, consulta.getIdPersonal());
            ps.setInt(8, consulta.getIdConsultas());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Consulta actualizada correctamente");
            } else {
                System.out.println("⚠️ No se encontró la consulta con ID: " + consulta.getIdConsultas());
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar consulta: " + e.getMessage());
        }
    }

    // ELIMINAR consulta
    public void eliminar(int id) {
        String sql = "DELETE FROM consultas WHERE id_consultas = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("🗑️ Consulta eliminada correctamente");
            } else {
                System.out.println("⚠️ No se encontró la consulta con ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar consulta: " + e.getMessage());
        }
    }

    // MÉTODO AUXILIAR para mapear ResultSet → Consulta
    private Consulta mapearConsulta(ResultSet rs) throws SQLException {
        return new Consulta(
                rs.getInt("id_consultas"),
                rs.getDate("fecha_consulta"),
                rs.getString("motivo_consulta"),
                rs.getString("diagnostico"),
                rs.getString("tratamiento_sugerido"),
                rs.getString("notas_adicionales"),
                rs.getInt("id_pacientes"),
                rs.getInt("id_personal")
        );
    }
}

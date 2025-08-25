package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void insertarConsulta(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consultas (fecha_consulta, motivo_consulte, diagnostico, tratamiento_seguido, notas_adicionales, id_pacientes, id_personal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(consulta.getFechaConsulta().getTime()));
            ps.setString(2, consulta.getMotivoConsulte());
            ps.setString(3, consulta.getDiagnostico());
            ps.setString(4, consulta.getTratamientoSeguido());
            ps.setString(5, consulta.getNotasAdicionales());
            ps.setInt(6, consulta.getIdPacientes());
            ps.setInt(7, consulta.getIdPersonal());

            ps.executeUpdate();
        }
    }

    public List<Consulta> listarConsultas() throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consultas";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setIdConsultas(rs.getInt("id_consultas"));
                c.setFechaConsulta(rs.getDate("fecha_consulta"));
                c.setMotivoConsulte(rs.getString("motivo_consulte"));
                c.setDiagnostico(rs.getString("diagnostico"));
                c.setTratamientoSeguido(rs.getString("tratamiento_seguido"));
                c.setNotasAdicionales(rs.getString("notas_adicionales"));
                c.setIdPacientes(rs.getInt("id_pacientes"));
                c.setIdPersonal(rs.getInt("id_personal"));
                lista.add(c);
            }
        }
        return lista;
    }

    public void actualizarConsulta(Consulta consulta) throws SQLException {
        String sql = "UPDATE consultas SET fecha_consulta=?, motivo_consulte=?, diagnostico=?, tratamiento_seguido=?, notas_adicionales=?, id_pacientes=?, id_personal=? WHERE id_consultas=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(consulta.getFechaConsulta().getTime()));
            ps.setString(2, consulta.getMotivoConsulte());
            ps.setString(3, consulta.getDiagnostico());
            ps.setString(4, consulta.getTratamientoSeguido());
            ps.setString(5, consulta.getNotasAdicionales());
            ps.setInt(6, consulta.getIdPacientes());
            ps.setInt(7, consulta.getIdPersonal());
            ps.setInt(8, consulta.getIdConsultas());

            ps.executeUpdate();
        }
    }

    public void eliminarConsulta(int id) throws SQLException {
        String sql = "DELETE FROM consultas WHERE id_consultas=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

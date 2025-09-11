package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.HistorialMedico;
import org.enfermeria.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HistorialMedicoDAO {

    // ================= CRUD =================

    public boolean crearHistorial(HistorialMedico h) {
        String sql = "INSERT INTO historial_medico (fecha_registro, diagnostico, tratamiento, alergias, " +
                "condiciones_medicas_previas, medicamentos_actuales, id_pacientes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, new java.sql.Date(h.getFechaRegistro().getTime()));
            ps.setString(2, h.getDiagnostico());
            ps.setString(3, h.getTratamiento());
            ps.setString(4, h.getAlergias());
            ps.setString(5, h.getCondicionesMedicasPrevias());
            ps.setString(6, h.getMedicamentosActuales());
            ps.setInt(7, h.getIdPaciente());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        h.setIdHistorialMedico(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al crear historial médico: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizarHistorial(HistorialMedico h) {
        String sql = "UPDATE historial_medico SET fecha_registro=?, diagnostico=?, tratamiento=?, alergias=?, " +
                "condiciones_medicas_previas=?, medicamentos_actuales=?, id_pacientes=? WHERE id_historialmedico=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(h.getFechaRegistro().getTime()));
            ps.setString(2, h.getDiagnostico());
            ps.setString(3, h.getTratamiento());
            ps.setString(4, h.getAlergias());
            ps.setString(5, h.getCondicionesMedicasPrevias());
            ps.setString(6, h.getMedicamentosActuales());
            ps.setInt(7, h.getIdPaciente());
            ps.setInt(8, h.getIdHistorialMedico());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar historial médico: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarHistorial(int id) {
        String sql = "DELETE FROM historial_medico WHERE id_historialmedico=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar historial médico: " + e.getMessage());
            return false;
        }
    }

    public HistorialMedico obtenerPorId(int id) {
        String sql = "SELECT * FROM historial_medico WHERE id_historialmedico=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapearHistorial(rs);

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener historial médico: " + e.getMessage());
        }
        return null;
    }

    public List<HistorialMedico> listarTodos() {
        List<HistorialMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapearHistorial(rs));

        } catch (SQLException e) {
            System.err.println("❌ Error al listar historiales médicos: " + e.getMessage());
        }
        return lista;
    }

    // ================= Métodos auxiliares =================

    private HistorialMedico mapearHistorial(ResultSet rs) throws SQLException {
        HistorialMedico h = new HistorialMedico();
        h.setIdHistorialMedico(rs.getInt("id_historialmedico"));
        h.setFechaRegistro(rs.getDate("fecha_registro"));
        h.setDiagnostico(rs.getString("diagnostico"));
        h.setTratamiento(rs.getString("tratamiento"));
        h.setAlergias(rs.getString("alergias"));
        h.setCondicionesMedicasPrevias(rs.getString("condiciones_medicas_previas"));
        h.setMedicamentosActuales(rs.getString("medicamentos_actuales"));
        h.setIdPaciente(rs.getInt("id_pacientes"));
        return h;
    }

    // ================= Métodos interactivos =================

    public void crearHistorialInteractivo(Scanner sc, PacienteDAO pacienteDAO) {
        HistorialMedico h = new HistorialMedico();

        // Fecha registro
        java.sql.Date fecha = null;
        while (fecha == null) {
            System.out.print("Fecha de registro (yyyy-mm-dd): ");
            String f = sc.nextLine();
            try { fecha = java.sql.Date.valueOf(f); } catch (IllegalArgumentException e) { System.out.println("❌ Formato incorrecto"); }
        }
        h.setFechaRegistro(fecha);

        System.out.print("Diagnóstico: "); h.setDiagnostico(sc.nextLine());
        System.out.print("Tratamiento: "); h.setTratamiento(sc.nextLine());
        System.out.print("Alergias: "); h.setAlergias(sc.nextLine());
        System.out.print("Condiciones médicas previas: "); h.setCondicionesMedicasPrevias(sc.nextLine());
        System.out.print("Medicamentos actuales: "); h.setMedicamentosActuales(sc.nextLine());

        // Selección de paciente
        List<Paciente> listaPacientes = pacienteDAO.listarPacientes();
        if (listaPacientes.isEmpty()) {
            System.out.println("❌ No hay pacientes registrados. Debes crear uno primero.");
            return;
        }

        System.out.println("Pacientes disponibles:");
        listaPacientes.forEach(p ->
                System.out.printf("ID: %d - %s %s%n", p.getId_pacientes(), p.getNombres(), p.getApellidos())
        );

        int idPaciente;
        boolean valido;
        do {
            System.out.print("Selecciona ID del paciente: ");
            idPaciente = sc.nextInt(); sc.nextLine();
            int finalIdPaciente = idPaciente;
            valido = listaPacientes.stream().anyMatch(p -> p.getId_pacientes() == finalIdPaciente);
            if (!valido) System.out.println("❌ ID inválido");
        } while (!valido);
        h.setIdPaciente(idPaciente);

        // Guardar
        if (crearHistorial(h)) System.out.println("✅ Historial médico creado correctamente");
        else System.out.println("❌ Error al crear historial médico");
    }

    public void listarHistorialesInteractivo(PacienteDAO pacienteDAO) {
        List<HistorialMedico> lista = listarTodos();
        if (lista.isEmpty()) { System.out.println("No hay historiales registrados."); return; }

        System.out.printf("%-5s %-12s %-20s %-20s %-20s %-20s %-5s%n",
                "ID", "F.Registro", "Diagnóstico", "Tratamiento", "Alergias",
                "CondicionesPrevias", "PacienteID");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (HistorialMedico h : lista) {
            System.out.printf("%-5d %-12s %-20s %-20s %-20s %-20s %-5d%n",
                    h.getIdHistorialMedico(),
                    h.getFechaRegistro(),
                    h.getDiagnostico(),
                    h.getTratamiento(),
                    h.getAlergias(),
                    h.getCondicionesMedicasPrevias(),
                    h.getIdPaciente());
        }
    }

    public void actualizarHistorialInteractivo(Scanner sc, HistorialMedico h) {
        System.out.println("Dejar en blanco para no modificar el campo.");

        System.out.print("Diagnóstico [" + h.getDiagnostico() + "]: ");
        String diag = sc.nextLine();
        if (!diag.isBlank()) h.setDiagnostico(diag);

        System.out.print("Tratamiento [" + h.getTratamiento() + "]: ");
        String trat = sc.nextLine();
        if (!trat.isBlank()) h.setTratamiento(trat);

        System.out.print("Alergias [" + h.getAlergias() + "]: ");
        String alerg = sc.nextLine();
        if (!alerg.isBlank()) h.setAlergias(alerg);

        System.out.print("Condiciones médicas previas [" + h.getCondicionesMedicasPrevias() + "]: ");
        String cond = sc.nextLine();
        if (!cond.isBlank()) h.setCondicionesMedicasPrevias(cond);

        System.out.print("Medicamentos actuales [" + h.getMedicamentosActuales() + "]: ");
        String med = sc.nextLine();
        if (!med.isBlank()) h.setMedicamentosActuales(med);

        System.out.print("Fecha registro [" + (h.getFechaRegistro() == null ? "-" : h.getFechaRegistro()) + "]: ");
        String f = sc.nextLine().trim();
        if (!f.isEmpty()) h.setFechaRegistro(Date.valueOf(f));

        if (actualizarHistorial(h)) System.out.println("✅ Historial actualizado correctamente");
        else System.out.println("❌ Error al actualizar historial");
    }

    public void eliminarHistorialInteractivo(Scanner sc) {
        System.out.print("Ingrese ID del historial a eliminar: ");
        int id = sc.nextInt(); sc.nextLine();
        if (eliminarHistorial(id)) System.out.println("✅ Historial eliminado");
        else System.out.println("❌ No se pudo eliminar historial");
    }
}

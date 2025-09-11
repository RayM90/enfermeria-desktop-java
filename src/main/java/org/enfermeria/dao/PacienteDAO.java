package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Paciente;
import org.enfermeria.model.TipoDocumento;
import org.enfermeria.model.Sexo;
import org.enfermeria.model.TipoSangre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PacienteDAO {

    // ================= CRUD =================
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

    public Paciente obtenerPacientePorDocumento(String numero_documento) {
        String sql = "SELECT * FROM pacientes WHERE numero_documento=?";
        Paciente paciente = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, numero_documento);
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
            System.err.println("Error al obtener paciente por documento: " + e.getMessage());
        }

        return paciente;
    }

    public List<Paciente> listarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Paciente(
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
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pacientes: " + e.getMessage());
        }

        return lista;
    }

    // ================= Métodos interactivos =================
    public void crearPacienteInteractivo(Scanner sc, TipoDocumentoDAO tdDAO, SexoDAO sexoDAO, TipoSangreDAO tsDAO) {
        Paciente p = new Paciente();

        // Tipo documento
        tdDAO.listarTiposDocumento().forEach(td -> System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento()));
        System.out.print("Selecciona ID tipo documento: "); p.setId_tipodocumento(Integer.parseInt(sc.nextLine()));

        // Número de documento único
        String nroDoc;
        do {
            System.out.print("Número de documento: ");
            nroDoc = sc.nextLine();
            if (obtenerPacientePorDocumento(nroDoc) != null) {
                System.out.println("❌ Este número de documento ya está registrado."); nroDoc = null;
            }
        } while (nroDoc == null);
        p.setNumero_documento(nroDoc);

        // Nombres y apellidos
        System.out.print("Nombres: "); p.setNombres(sc.nextLine());
        System.out.print("Apellidos: "); p.setApellidos(sc.nextLine());

        // Fecha nacimiento
        Date fechaNac = null;
        while (fechaNac == null) {
            System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
            try { fechaNac = Date.valueOf(sc.nextLine()); } catch (IllegalArgumentException e) { System.out.println("❌ Formato incorrecto"); }
        }
        p.setFecha_nacimiento(fechaNac);

        // Sexo
        sexoDAO.listarSexos().forEach(s -> System.out.println(s.getId_sexo() + " - " + s.getDescripcion()));
        System.out.print("Selecciona ID sexo: "); p.setId_sexo(Integer.parseInt(sc.nextLine()));

        // Tipo de sangre
        int tipoSangreId;
        do {
            tsDAO.listarTiposSangre().forEach(ts -> System.out.println(ts.getId_tiposangre() + " - " + ts.getTipo_sangre()));
            System.out.print("Selecciona ID tipo de sangre: "); tipoSangreId = Integer.parseInt(sc.nextLine());
            if (tipoSangreId < 1 || tipoSangreId > 8) System.out.println("❌ Opción inválida");
        } while (tipoSangreId < 1 || tipoSangreId > 8);
        p.setId_tiposangre(tipoSangreId);

        // Correo
        String correo;
        do {
            System.out.print("Correo: "); correo = sc.nextLine();
            if (!correo.contains("@") || (!correo.endsWith(".com") && !correo.endsWith(".net") && !correo.endsWith(".org"))) {
                System.out.println("❌ Correo inválido"); correo = null;
            }
        } while (correo == null);
        p.setCorreo(correo);

        // Teléfono
        String tel;
        do {
            System.out.print("Teléfono (11 dígitos): "); tel = sc.nextLine();
            if (!tel.matches("\\d{11}")) { System.out.println("❌ Teléfono inválido"); tel = null; }
        } while (tel == null);
        p.setTelefono(tel);

        // Dirección y cargo
        System.out.print("Dirección: "); p.setDireccion(sc.nextLine());
        System.out.print("Cargo: "); p.setCargo(sc.nextLine());

        // Guardar
        if (crearPaciente(p)) System.out.println("✅ Paciente creado correctamente");
        else System.out.println("❌ Error al crear paciente");
    }

    public void listarPacientesInteractivo(TipoDocumentoDAO tdDAO, SexoDAO sexoDAO, TipoSangreDAO tsDAO) {
        List<Paciente> lista = listarPacientes();
        if (lista.isEmpty()) { System.out.println("No hay pacientes registrados."); return; }

        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-12s %-10s %-15s %-25s %-12s %-20s %-15s %-20s%n",
                "ID", "TipoDoc", "NroDoc", "Nombres", "Apellidos", "F.Nac", "Sexo", "TipoSangre",
                "Correo", "Teléfono", "Dirección", "Cargo", "F.Registro");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Paciente p : lista) {
            String tipoDoc = tdDAO.obtenerTipoDocumentoPorId(p.getId_tipodocumento());
            String sexo = sexoDAO.obtenerSexoPorId(p.getId_sexo());
            String tipoSangre = tsDAO.obtenerTipoSangrePorId(p.getId_tiposangre());

            System.out.printf("%-5d %-15s %-15s %-15s %-15s %-12s %-10s %-15s %-25s %-12s %-20s %-15s %-20s%n",
                    p.getId_pacientes(), tipoDoc, p.getNumero_documento(), p.getNombres(),
                    p.getApellidos(), p.getFecha_nacimiento(), sexo, tipoSangre,
                    p.getCorreo(), p.getTelefono(), p.getDireccion(), p.getCargo(),
                    p.getFecha_registro());
        }
    }

    public void buscarPacienteInteractivo(Scanner sc, TipoDocumentoDAO tdDAO, SexoDAO sexoDAO, TipoSangreDAO tsDAO) {
        System.out.print("Ingrese número de documento: ");
        String nro = sc.nextLine();
        Paciente p = obtenerPacientePorDocumento(nro);
        if (p == null) { System.out.println("❌ Paciente no encontrado"); return; }

        String tipoDoc = tdDAO.obtenerTipoDocumentoPorId(p.getId_tipodocumento());
        String sexo = sexoDAO.obtenerSexoPorId(p.getId_sexo());
        String tipoSangre = tsDAO.obtenerTipoSangrePorId(p.getId_tiposangre());

        System.out.println("\n--- DATOS DEL PACIENTE ---");
        System.out.println("ID: " + p.getId_pacientes());
        System.out.println("Tipo Documento: " + tipoDoc);
        System.out.println("Número Documento: " + p.getNumero_documento());
        System.out.println("Nombres: " + p.getNombres());
        System.out.println("Apellidos: " + p.getApellidos());
        System.out.println("Fecha Nacimiento: " + p.getFecha_nacimiento());
        System.out.println("Sexo: " + sexo);
        System.out.println("Tipo Sangre: " + tipoSangre);
        System.out.println("Correo: " + p.getCorreo());
        System.out.println("Teléfono: " + p.getTelefono());
        System.out.println("Dirección: " + p.getDireccion());
        System.out.println("Cargo: " + p.getCargo());
        System.out.println("Fecha Registro: " + p.getFecha_registro());
    }

    public void editarPacienteInteractivo(Scanner sc, TipoDocumentoDAO tdDAO, SexoDAO sexoDAO, TipoSangreDAO tsDAO) {
        System.out.print("Ingrese número de documento del paciente a editar: ");
        String nro = sc.nextLine();
        Paciente p = obtenerPacientePorDocumento(nro);
        if (p == null) { System.out.println("❌ Paciente no encontrado"); return; }

        System.out.println("Dejar en blanco para no modificar el campo.");

        System.out.print("Nombres [" + p.getNombres() + "]: "); String nombres = sc.nextLine();
        if (!nombres.isBlank()) p.setNombres(nombres);

        System.out.print("Apellidos [" + p.getApellidos() + "]: "); String apellidos = sc.nextLine();
        if (!apellidos.isBlank()) p.setApellidos(apellidos);

        System.out.print("Correo [" + p.getCorreo() + "]: "); String correo = sc.nextLine();
        if (!correo.isBlank()) p.setCorreo(correo);

        System.out.print("Teléfono [" + p.getTelefono() + "]: "); String tel = sc.nextLine();
        if (!tel.isBlank()) p.setTelefono(tel);

        System.out.print("Dirección [" + p.getDireccion() + "]: "); String dir = sc.nextLine();
        if (!dir.isBlank()) p.setDireccion(dir);

        System.out.print("Cargo [" + p.getCargo() + "]: "); String cargo = sc.nextLine();
        if (!cargo.isBlank()) p.setCargo(cargo);

        if (actualizarPaciente(p)) System.out.println("✅ Paciente actualizado correctamente");
        else System.out.println("❌ Error al actualizar paciente");
    }

    public void eliminarPacienteInteractivo(Scanner sc) {
        System.out.print("Ingrese número de documento del paciente a eliminar: ");
        String nro = sc.nextLine();
        Paciente p = obtenerPacientePorDocumento(nro);
        if (p == null) { System.out.println("❌ Paciente no encontrado"); return; }

        if (eliminarPaciente(p.getId_pacientes())) System.out.println("✅ Paciente eliminado correctamente");
        else System.out.println("❌ Error al eliminar paciente");
    }
}

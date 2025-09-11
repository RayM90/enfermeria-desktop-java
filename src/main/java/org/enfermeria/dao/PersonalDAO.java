package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Personal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalDAO {

    // ================= CRUD =================
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
            ps.setDate(8, p.getFecha_contratacion());
            ps.setDate(9, p.getFecha_nacimiento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear personal: " + e.getMessage());
            return false;
        }
    }

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

    public Personal obtenerPersonalPorId(int id) {
        String sql = "SELECT * FROM personal WHERE id_personal=?";
        Personal p = null;
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Personal(
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

        } catch (SQLException e) {
            System.err.println("Error al obtener personal por ID: " + e.getMessage());
        }
        return p;
    }

    public List<Personal> listarPersonal() {
        List<Personal> lista = new ArrayList<>();
        String sql = "SELECT * FROM personal";
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

    // ================= Métodos interactivos =================
    public void crearPersonalInteractivo(Scanner sc, TipoDocumentoDAO tdDAO) {
        Personal p = new Personal();

        // ===== Tipo de documento =====
        int tipoDoc = -1;
        do {
            System.out.println("Tipos de documento disponibles:");
            tdDAO.listarTiposDocumento().forEach(td ->
                    System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento())
            );
            System.out.print("Selecciona ID tipo documento (4=V / 5=E) o 0 para cancelar: ");

            String input = sc.nextLine().trim();
            if (input.equals("0")) {
                System.out.println("❌ Operación cancelada, volviendo al menú...");
                return;
            }

            if (input.length() != 1) { // solo un dígito permitido
                System.out.println("❌ Solo un dígito permitido (4 o 5). Intenta nuevamente.");
                continue;
            }

            try {
                tipoDoc = Integer.parseInt(input);
                if (tipoDoc != 4 && tipoDoc != 5) {
                    System.out.println("❌ Solo 4 o 5 son válidos.");
                    tipoDoc = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Ingresa un número válido.");
            }

        } while (tipoDoc == -1);
        p.setId_tipodocumento(tipoDoc);

        // ===== Número de documento =====
        System.out.print("Número de documento (o 0 para cancelar): ");
        String numDoc = sc.nextLine().trim();
        if (numDoc.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
        p.setNumero_documento(numDoc);

        // ===== Nombres =====
        System.out.print("Nombres (o 0 para cancelar): ");
        String nombres = sc.nextLine().trim();
        if (nombres.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
        p.setNombres(nombres);

        // ===== Apellidos =====
        System.out.print("Apellidos (o 0 para cancelar): ");
        String apellidos = sc.nextLine().trim();
        if (apellidos.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
        p.setApellidos(apellidos);

        // ===== Especialidad =====
        System.out.print("Especialidad (o 0 para cancelar): ");
        String especialidad = sc.nextLine().trim();
        if (especialidad.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
        p.setEspecialidad(especialidad);

        // ===== Correo =====
        System.out.print("Correo (o 0 para cancelar): ");
        String correo = sc.nextLine().trim();
        if (correo.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
        p.setCorreo(correo);

        // ===== Teléfono =====
        System.out.print("Teléfono (o 0 para cancelar): ");
        String telefono = sc.nextLine().trim();
        if (telefono.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
        p.setTelefono(telefono);

        // ===== Fecha de contratación =====
        while (true) {
            System.out.print("Fecha de contratación (yyyy-mm-dd, dejar vacío si no aplica, 0 para cancelar): ");
            String fc = sc.nextLine().trim();
            if (fc.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
            if (fc.isEmpty()) {
                p.setFecha_contratacion(null);
                break;
            }
            try {
                p.setFecha_contratacion(Date.valueOf(fc));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Formato de fecha incorrecto. Intenta nuevamente.");
            }
        }

        // ===== Fecha de nacimiento =====
        while (true) {
            System.out.print("Fecha de nacimiento (yyyy-mm-dd, dejar vacío si no aplica, 0 para cancelar): ");
            String fn = sc.nextLine().trim();
            if (fn.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
            if (fn.isEmpty()) {
                p.setFecha_nacimiento(null);
                break;
            }
            try {
                p.setFecha_nacimiento(Date.valueOf(fn));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Formato de fecha incorrecto. Intenta nuevamente.");
            }
        }

        // ===== Guardar =====
        if (crearPersonal(p)) System.out.println("✅ Personal creado correctamente");
        else System.out.println("❌ Error al crear personal");
    }


    public void listarPersonalInteractivo() {
        List<Personal> lista = listarPersonal();
        if (lista.isEmpty()) { System.out.println("No hay personal registrado."); return; }

        System.out.printf("%-5s %-10s %-15s %-15s %-15s %-20s %-15s %-15s %-15s%n",
                "ID", "TipoDoc", "NroDoc", "Nombres", "Apellidos", "Especialidad", "Correo", "Teléfono", "F. Contrat.");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Personal p : lista) {
            System.out.printf("%-5d %-10d %-15s %-15s %-15s %-20s %-15s %-15s %-15s%n",
                    p.getId_personal(), p.getId_tipodocumento(), p.getNumero_documento(),
                    p.getNombres(), p.getApellidos(), p.getEspecialidad(), p.getCorreo(),
                    p.getTelefono(), p.getFecha_contratacion());
        }
    }

    public void editarPersonalInteractivo(Scanner sc, TipoDocumentoDAO tdDAO) {
        System.out.print("Ingrese ID del personal a editar: ");
        String idInput = sc.nextLine().trim();
        if (idInput.equals("0")) { System.out.println("❌ Operación cancelada"); return; }

        int id;
        try { id = Integer.parseInt(idInput); }
        catch (NumberFormatException e) { System.out.println("❌ ID inválido"); return; }

        Personal p = obtenerPersonalPorId(id);
        if (p == null) { System.out.println("❌ Personal no encontrado"); return; }

        System.out.println("Dejar en blanco para no modificar el campo. Ingresa 0 para cancelar cualquier campo.");

        // ===== Tipo documento =====
        while (true) {
            System.out.print("Tipo de documento [" + p.getId_tipodocumento() + "]: ");
            String input = sc.nextLine().trim();
            if (input.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
            if (input.isBlank()) break;
            try {
                int tipo = Integer.parseInt(input);
                if (tipo == 4 || tipo == 5) { p.setId_tipodocumento(tipo); break; }
                else System.out.println("❌ Solo 4 o 5 son válidos.");
            } catch (NumberFormatException e) { System.out.println("❌ Entrada inválida."); }
        }

        System.out.print("Nombres [" + p.getNombres() + "]: "); String nombres = sc.nextLine(); if (!nombres.isBlank() && !nombres.equals("0")) p.setNombres(nombres);
        System.out.print("Apellidos [" + p.getApellidos() + "]: "); String apellidos = sc.nextLine(); if (!apellidos.isBlank() && !apellidos.equals("0")) p.setApellidos(apellidos);
        System.out.print("Especialidad [" + p.getEspecialidad() + "]: "); String especialidad = sc.nextLine(); if (!especialidad.isBlank() && !especialidad.equals("0")) p.setEspecialidad(especialidad);
        System.out.print("Correo [" + p.getCorreo() + "]: "); String correo = sc.nextLine(); if (!correo.isBlank() && !correo.equals("0")) p.setCorreo(correo);
        System.out.print("Teléfono [" + p.getTelefono() + "]: "); String tel = sc.nextLine(); if (!tel.isBlank() && !tel.equals("0")) p.setTelefono(tel);

        // ===== Fecha de contratación =====
        while (true) {
            System.out.print("Fecha de contratación [" + p.getFecha_contratacion() + "] (yyyy-mm-dd, 0 para cancelar): ");
            String fc = sc.nextLine().trim();
            if (fc.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
            if (fc.isBlank()) break;
            try { p.setFecha_contratacion(Date.valueOf(fc)); break; }
            catch (IllegalArgumentException e) { System.out.println("❌ Formato incorrecto."); }
        }

        // ===== Fecha de nacimiento =====
        while (true) {
            System.out.print("Fecha de nacimiento [" + p.getFecha_nacimiento() + "] (yyyy-mm-dd, 0 para cancelar): ");
            String fn = sc.nextLine().trim();
            if (fn.equals("0")) { System.out.println("❌ Operación cancelada"); return; }
            if (fn.isBlank()) break;
            try { p.setFecha_nacimiento(Date.valueOf(fn)); break; }
            catch (IllegalArgumentException e) { System.out.println("❌ Formato incorrecto."); }
        }

        if (actualizarPersonal(p)) System.out.println("✅ Personal actualizado correctamente");
        else System.out.println("❌ Error al actualizar personal");
    }

    public void eliminarPersonalInteractivo(Scanner sc) {
        System.out.print("Ingrese ID del personal a eliminar (0 para cancelar): ");
        String input = sc.nextLine().trim();
        if (input.equals("0")) { System.out.println("❌ Operación cancelada"); return; }

        int id;
        try { id = Integer.parseInt(input); }
        catch (NumberFormatException e) { System.out.println("❌ ID inválido"); return; }

        if (eliminarPersonal(id)) System.out.println("✅ Personal eliminado correctamente");
        else System.out.println("❌ Error al eliminar personal");
    }
}

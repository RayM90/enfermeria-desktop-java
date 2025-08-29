package org.enfermeria.service;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.dao.*;
import org.enfermeria.model.Paciente;
import org.enfermeria.model.Personal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ================= Conexión inicial =================
        try (Connection conn = ConexionBD.getConnection()) {
            if (conn != null) {
                System.out.println("⚙️ Configuración DB cargada correctamente");
                System.out.println("✅ Conexión establecida con MySQL");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);

        // ================= DAOs =================
        PacienteDAO pacienteDAO = new PacienteDAO();
        TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
        SexoDAO sexoDAO = new SexoDAO();
        TipoSangreDAO tipoSangreDAO = new TipoSangreDAO();
        HistorialMedicoDAO historialDAO = new HistorialMedicoDAO();
        PersonalDAO personalDAO = new PersonalDAO();

        int opcionPrincipal;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Pacientes");
            System.out.println("2. Historial Médico");
            System.out.println("3. Personal");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcionPrincipal = sc.nextInt();
            sc.nextLine();

            switch (opcionPrincipal) {
                case 1 -> menuPacientes(sc, pacienteDAO, tipoDocumentoDAO, sexoDAO, tipoSangreDAO);
                case 2 -> menuHistorialMedico(sc, historialDAO, pacienteDAO);
                case 3 -> menuPersonal(sc, personalDAO, tipoDocumentoDAO);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcionPrincipal != 0);

        sc.close();
    }

    // ================= Menú Pacientes =================
    private static void menuPacientes(Scanner sc, PacienteDAO pacienteDAO,
                                      TipoDocumentoDAO tipoDocumentoDAO, SexoDAO sexoDAO,
                                      TipoSangreDAO tipoSangreDAO) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PACIENTES ===");
            System.out.println("1. Crear paciente");
            System.out.println("2. Listar pacientes");
            System.out.println("3. Buscar paciente por documento");
            System.out.println("4. Actualizar paciente");
            System.out.println("5. Eliminar paciente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> pacienteDAO.crearPacienteInteractivo(sc, tipoDocumentoDAO, sexoDAO, tipoSangreDAO);
                case 2 -> pacienteDAO.listarPacientesInteractivo(tipoDocumentoDAO, sexoDAO, tipoSangreDAO);
                case 3 -> pacienteDAO.buscarPacienteInteractivo(sc, tipoDocumentoDAO, sexoDAO, tipoSangreDAO);
                case 4 -> pacienteDAO.editarPacienteInteractivo(sc, tipoDocumentoDAO, sexoDAO, tipoSangreDAO);
                case 5 -> pacienteDAO.eliminarPacienteInteractivo(sc);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    // ================= Menú Historial Médico =================
    private static void menuHistorialMedico(Scanner sc, HistorialMedicoDAO historialDAO, PacienteDAO pacienteDAO) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ HISTORIAL MÉDICO ===");
            System.out.println("1. Crear historial médico");
            System.out.println("2. Listar historiales médicos");
            System.out.println("3. Actualizar historial médico");
            System.out.println("4. Eliminar historial médico");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> historialDAO.crearHistorialInteractivo(sc, pacienteDAO);
                case 2 -> historialDAO.listarHistorialesInteractivo(pacienteDAO);
                case 3 -> {
                    System.out.print("Ingrese ID del historial a actualizar: ");
                    int id = sc.nextInt(); sc.nextLine();
                    var h = historialDAO.obtenerPorId(id);
                    if (h != null) {
                        System.out.println("Actualización completa de este historial:");
                        historialDAO.actualizarHistorial(h);
                    } else {
                        System.out.println("❌ Historial no encontrado");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese ID del historial a eliminar: ");
                    int id = sc.nextInt(); sc.nextLine();
                    if (historialDAO.eliminarHistorial(id)) System.out.println("✅ Historial eliminado");
                    else System.out.println("❌ No se pudo eliminar historial");
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    // ================= Menú Personal =================
    private static void menuPersonal(Scanner sc, PersonalDAO personalDAO, TipoDocumentoDAO tipoDocumentoDAO) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PERSONAL ===");
            System.out.println("1. Crear personal");
            System.out.println("2. Listar personal");
            System.out.println("3. Actualizar personal");
            System.out.println("4. Eliminar personal");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> crearPersonalInteractivo(sc, personalDAO, tipoDocumentoDAO);
                case 2 -> listarPersonalInteractivo(personalDAO, tipoDocumentoDAO);
                case 3 -> actualizarPersonalInteractivo(sc, personalDAO, tipoDocumentoDAO);
                case 4 -> eliminarPersonalInteractivo(sc, personalDAO);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    // ================= Métodos interactivos Personal =================
    private static void crearPersonalInteractivo(Scanner sc, PersonalDAO personalDAO, TipoDocumentoDAO tipoDocumentoDAO) {
        Personal p = new Personal();

        tipoDocumentoDAO.listarTiposDocumento().forEach(td ->
                System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento())
        );
        System.out.print("Selecciona ID tipo documento: ");
        p.setId_tipodocumento(sc.nextInt());
        sc.nextLine();

        System.out.print("Número de documento: "); p.setNumero_documento(sc.nextLine());
        System.out.print("Nombres: "); p.setNombres(sc.nextLine());
        System.out.print("Apellidos: "); p.setApellidos(sc.nextLine());
        System.out.print("Especialidad: "); p.setEspecialidad(sc.nextLine());
        System.out.print("Correo: "); p.setCorreo(sc.nextLine());
        System.out.print("Teléfono: "); p.setTelefono(sc.nextLine());

        // Fechas opcionales
        System.out.print("Fecha de contratación (yyyy-mm-dd, dejar vacío si no aplica): ");
        String fc = sc.nextLine().trim();
        p.setFecha_contratacion(fc.isEmpty() ? null : Date.valueOf(fc));

        System.out.print("Fecha de nacimiento (yyyy-mm-dd, dejar vacío si no aplica): ");
        String fn = sc.nextLine().trim();
        p.setFecha_nacimiento(fn.isEmpty() ? null : Date.valueOf(fn));

        if(personalDAO.crearPersonal(p)) System.out.println("✅ Personal creado correctamente");
        else System.out.println("❌ Error al crear personal");
    }

    private static void listarPersonalInteractivo(PersonalDAO personalDAO, TipoDocumentoDAO tipoDocumentoDAO) {
        List<Personal> lista = personalDAO.listarPersonal();
        if (lista.isEmpty()) { System.out.println("No hay personal registrado."); return; }

        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-20s %-15s %-15s %-12s%n",
                "ID", "TipoDoc", "NroDoc", "Nombres", "Apellidos", "Especialidad", "Correo", "Teléfono", "F. Contratación");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        for (Personal p : lista) {
            String tipoDoc = tipoDocumentoDAO.obtenerTipoDocumentoPorId(p.getId_tipodocumento());
            System.out.printf("%-5d %-15s %-15s %-15s %-15s %-20s %-15s %-15s %-12s%n",
                    p.getId_personal(), tipoDoc, p.getNumero_documento(), p.getNombres(),
                    p.getApellidos(), p.getEspecialidad(), p.getCorreo(), p.getTelefono(),
                    p.getFecha_contratacion() == null ? "-" : p.getFecha_contratacion());
        }
    }

    private static void actualizarPersonalInteractivo(Scanner sc, PersonalDAO personalDAO, TipoDocumentoDAO tipoDocumentoDAO) {
        System.out.print("Ingrese ID del personal a actualizar: ");
        int id = sc.nextInt(); sc.nextLine();
        Personal p = personalDAO.obtenerPersonalPorId(id);
        if (p == null) { System.out.println("❌ Personal no encontrado"); return; }

        System.out.println("Dejar en blanco para no modificar el campo.");

        System.out.print("Nombres [" + p.getNombres() + "]: "); String nombres = sc.nextLine(); if (!nombres.isBlank()) p.setNombres(nombres);
        System.out.print("Apellidos [" + p.getApellidos() + "]: "); String apellidos = sc.nextLine(); if (!apellidos.isBlank()) p.setApellidos(apellidos);
        System.out.print("Especialidad [" + p.getEspecialidad() + "]: "); String esp = sc.nextLine(); if (!esp.isBlank()) p.setEspecialidad(esp);
        System.out.print("Correo [" + p.getCorreo() + "]: "); String correo = sc.nextLine(); if (!correo.isBlank()) p.setCorreo(correo);
        System.out.print("Teléfono [" + p.getTelefono() + "]: "); String tel = sc.nextLine(); if (!tel.isBlank()) p.setTelefono(tel);

        // Fechas opcionales
        System.out.print("Fecha de contratación [" + (p.getFecha_contratacion() == null ? "-" : p.getFecha_contratacion()) + "]: ");
        String fc = sc.nextLine().trim();
        if (!fc.isEmpty()) p.setFecha_contratacion(Date.valueOf(fc));

        System.out.print("Fecha de nacimiento [" + (p.getFecha_nacimiento() == null ? "-" : p.getFecha_nacimiento()) + "]: ");
        String fn = sc.nextLine().trim();
        if (!fn.isEmpty()) p.setFecha_nacimiento(Date.valueOf(fn));

        if(personalDAO.actualizarPersonal(p)) System.out.println("✅ Personal actualizado correctamente");
        else System.out.println("❌ Error al actualizar personal");
    }

    private static void eliminarPersonalInteractivo(Scanner sc, PersonalDAO personalDAO) {
        System.out.print("Ingrese ID del personal a eliminar: ");
        int id = sc.nextInt(); sc.nextLine();
        if(personalDAO.eliminarPersonal(id)) System.out.println("✅ Personal eliminado");
        else System.out.println("❌ No se pudo eliminar personal");
    }
}

package org.enfermeria.main;

import org.enfermeria.dao.*;
import org.enfermeria.model.Paciente;
import org.enfermeria.model.Personal;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // DAOs
        PacienteDAO pacienteDAO = new PacienteDAO();
        PersonalDAO personalDAO = new PersonalDAO();
        TipoDocumentoDAO tdDAO = new TipoDocumentoDAO();
        SexoDAO sexoDAO = new SexoDAO();
        TipoSangreDAO tsDAO = new TipoSangreDAO();
        HistorialMedicoDAO historialDAO = new HistorialMedicoDAO();

        int opcionPrincipal = -1;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Pacientes");
            System.out.println("2. Historial Médico");
            System.out.println("3. Personal");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcionPrincipal = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Opción inválida, ingresa un número.");
                continue;
            }

            switch (opcionPrincipal) {
                case 1 -> menuPacientes(sc, pacienteDAO, tdDAO, sexoDAO, tsDAO);
                case 2 -> menuHistorialMedico(sc, historialDAO, pacienteDAO);
                case 3 -> menuPersonal(sc, personalDAO, tdDAO);
                case 0 -> System.out.println("Saliendo del sistema... 👋");
                default -> System.out.println("❌ Opción no válida, intenta de nuevo.");
            }

        } while (opcionPrincipal != 0);

        sc.close();
    }

    // ================= Menú Pacientes =================
    private static void menuPacientes(Scanner sc, PacienteDAO pacienteDAO,
                                      TipoDocumentoDAO tdDAO, SexoDAO sexoDAO,
                                      TipoSangreDAO tsDAO) {
        int opcion = -1;
        do {
            System.out.println("\n=== MENÚ PACIENTES ===");
            System.out.println("1. Crear paciente");
            System.out.println("2. Listar pacientes");
            System.out.println("3. Buscar paciente por documento");
            System.out.println("4. Actualizar paciente");
            System.out.println("5. Eliminar paciente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Opción inválida, ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> pacienteDAO.crearPacienteInteractivo(sc, tdDAO, sexoDAO, tsDAO);
                case 2 -> pacienteDAO.listarPacientesInteractivo(tdDAO, sexoDAO, tsDAO);
                case 3 -> pacienteDAO.buscarPacienteInteractivo(sc, tdDAO, sexoDAO, tsDAO);
                case 4 -> pacienteDAO.editarPacienteInteractivo(sc, tdDAO, sexoDAO, tsDAO);
                case 5 -> pacienteDAO.eliminarPacienteInteractivo(sc);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("❌ Opción no válida");
            }

        } while (opcion != 0);
    }

    // ================= Menú Historial Médico =================
    private static void menuHistorialMedico(Scanner sc, HistorialMedicoDAO historialDAO, PacienteDAO pacienteDAO) {
        int opcion = -1;
        do {
            System.out.println("\n=== MENÚ HISTORIAL MÉDICO ===");
            System.out.println("1. Crear historial médico");
            System.out.println("2. Listar historiales médicos");
            System.out.println("3. Actualizar historial médico");
            System.out.println("4. Eliminar historial médico");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Opción inválida, ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> historialDAO.crearHistorialInteractivo(sc, pacienteDAO);
                case 2 -> historialDAO.listarHistorialesInteractivo(pacienteDAO);
                case 3 -> {
                    System.out.print("Ingrese ID del historial a actualizar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    var h = historialDAO.obtenerPorId(id);
                    if (h != null) {
                        historialDAO.actualizarHistorialInteractivo(sc, h);
                    } else {
                        System.out.println("❌ Historial no encontrado");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese ID del historial a eliminar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    if (historialDAO.eliminarHistorial(id)) System.out.println("✅ Historial eliminado");
                    else System.out.println("❌ No se pudo eliminar historial");
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("❌ Opción no válida");
            }

        } while (opcion != 0);
    }

    // ================= Menú Personal =================
    private static void menuPersonal(Scanner sc, PersonalDAO personalDAO, TipoDocumentoDAO tdDAO) {
        int opcion = -1;
        do {
            System.out.println("\n=== MENÚ PERSONAL ===");
            System.out.println("1. Crear personal");
            System.out.println("2. Listar personal");
            System.out.println("3. Actualizar personal");
            System.out.println("4. Eliminar personal");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Opción inválida, ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> crearPersonalInteractivo(sc, personalDAO, tdDAO);
                case 2 -> listarPersonalInteractivo(personalDAO, tdDAO);
                case 3 -> actualizarPersonalInteractivo(sc, personalDAO, tdDAO);
                case 4 -> eliminarPersonalInteractivo(sc, personalDAO);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("❌ Opción no válida");
            }

        } while (opcion != 0);
    }

    // ================= Métodos interactivos Personal =================
    private static void crearPersonalInteractivo(Scanner sc, PersonalDAO personalDAO, TipoDocumentoDAO tdDAO) {
        Personal p = new Personal();

        tdDAO.listarTiposDocumento().forEach(td ->
                System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento())
        );
        System.out.print("Selecciona ID tipo documento: ");
        p.setId_tipodocumento(Integer.parseInt(sc.nextLine()));

        System.out.print("Número de documento: "); p.setNumero_documento(sc.nextLine());
        System.out.print("Nombres: "); p.setNombres(sc.nextLine());
        System.out.print("Apellidos: "); p.setApellidos(sc.nextLine());
        System.out.print("Especialidad: "); p.setEspecialidad(sc.nextLine());
        System.out.print("Correo: "); p.setCorreo(sc.nextLine());
        System.out.print("Teléfono: "); p.setTelefono(sc.nextLine());

        System.out.print("Fecha de contratación (yyyy-mm-dd, dejar vacío si no aplica): ");
        String fc = sc.nextLine().trim();
        p.setFecha_contratacion(fc.isEmpty() ? null : Date.valueOf(fc));

        System.out.print("Fecha de nacimiento (yyyy-mm-dd, dejar vacío si no aplica): ");
        String fn = sc.nextLine().trim();
        p.setFecha_nacimiento(fn.isEmpty() ? null : Date.valueOf(fn));

        if(personalDAO.crearPersonal(p)) System.out.println("✅ Personal creado correctamente");
        else System.out.println("❌ Error al crear personal");
    }

    private static void listarPersonalInteractivo(PersonalDAO personalDAO, TipoDocumentoDAO tdDAO) {
        List<Personal> lista = personalDAO.listarPersonal();
        if (lista.isEmpty()) { System.out.println("No hay personal registrado."); return; }

        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-20s %-15s %-15s %-12s%n",
                "ID", "TipoDoc", "NroDoc", "Nombres", "Apellidos", "Especialidad", "Correo", "Teléfono", "F. Contratación");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        for (Personal p : lista) {
            String tipoDoc = tdDAO.obtenerTipoDocumentoPorId(p.getId_tipodocumento());
            System.out.printf("%-5d %-15s %-15s %-15s %-15s %-20s %-15s %-15s %-12s%n",
                    p.getId_personal(), tipoDoc, p.getNumero_documento(), p.getNombres(),
                    p.getApellidos(), p.getEspecialidad(), p.getCorreo(), p.getTelefono(),
                    p.getFecha_contratacion() == null ? "-" : p.getFecha_contratacion());
        }
    }

    private static void actualizarPersonalInteractivo(Scanner sc, PersonalDAO personalDAO, TipoDocumentoDAO tdDAO) {
        System.out.print("Ingrese ID del personal a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Personal p = personalDAO.obtenerPersonalPorId(id);
        if (p == null) { System.out.println("❌ Personal no encontrado"); return; }

        System.out.println("Dejar en blanco para no modificar el campo.");

        System.out.print("Nombres [" + p.getNombres() + "]: "); String nombres = sc.nextLine(); if (!nombres.isBlank()) p.setNombres(nombres);
        System.out.print("Apellidos [" + p.getApellidos() + "]: "); String apellidos = sc.nextLine(); if (!apellidos.isBlank()) p.setApellidos(apellidos);
        System.out.print("Especialidad [" + p.getEspecialidad() + "]: "); String esp = sc.nextLine(); if (!esp.isBlank()) p.setEspecialidad(esp);
        System.out.print("Correo [" + p.getCorreo() + "]: "); String correo = sc.nextLine(); if (!correo.isBlank()) p.setCorreo(correo);
        System.out.print("Teléfono [" + p.getTelefono() + "]: "); String tel = sc.nextLine(); if (!tel.isBlank()) p.setTelefono(tel);

        System.out.print("Fecha de contratación [" + (p.getFecha_contratacion() == null ? "-" : p.getFecha_contratacion()) + "]: ");
        String fc = sc.nextLine().trim(); if (!fc.isEmpty()) p.setFecha_contratacion(Date.valueOf(fc));

        System.out.print("Fecha de nacimiento [" + (p.getFecha_nacimiento() == null ? "-" : p.getFecha_nacimiento()) + "]: ");
        String fn = sc.nextLine().trim(); if (!fn.isEmpty()) p.setFecha_nacimiento(Date.valueOf(fn));

        if(personalDAO.actualizarPersonal(p)) System.out.println("✅ Personal actualizado correctamente");
        else System.out.println("❌ Error al actualizar personal");
    }

    private static void eliminarPersonalInteractivo(Scanner sc, PersonalDAO personalDAO) {
        System.out.print("Ingrese ID del personal a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        if(personalDAO.eliminarPersonal(id)) System.out.println("✅ Personal eliminado");
        else System.out.println("❌ No se pudo eliminar personal");
    }
}

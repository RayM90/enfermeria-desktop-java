package org.enfermeria.service;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.dao.PacienteDAO;
import org.enfermeria.dao.TipoDocumentoDAO;
import org.enfermeria.dao.SexoDAO;
import org.enfermeria.dao.TipoSangreDAO;
import org.enfermeria.model.Paciente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Mensaje de conexión inicial
        try (Connection conn = ConexionBD.getConnection()) {
            if (conn != null) {
                System.out.println("⚙️ Configuración DB cargada correctamente");
                System.out.println("✅ Conexión establecida con MySQL");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            return; // salir si no hay conexión
        }

        Scanner sc = new Scanner(System.in);
        PacienteDAO pacienteDAO = new PacienteDAO();
        TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
        SexoDAO sexoDAO = new SexoDAO();
        TipoSangreDAO tipoSangreDAO = new TipoSangreDAO();

        int opcion;

        do {
            System.out.println("\n=== MENÚ PACIENTES ===");
            System.out.println("1. Crear paciente");
            System.out.println("2. Listar pacientes");
            System.out.println("3. Buscar paciente por documento");
            System.out.println("4. Actualizar paciente");
            System.out.println("5. Eliminar paciente");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    Paciente p = new Paciente();

                    // Mini-menú TipoDocumento
                    tipoDocumentoDAO.listarTiposDocumento().forEach(td ->
                            System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento())
                    );
                    System.out.print("Selecciona ID tipo documento: ");
                    p.setId_tipodocumento(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Número de documento: ");
                    p.setNumero_documento(sc.nextLine());
                    System.out.print("Nombres: ");
                    p.setNombres(sc.nextLine());
                    System.out.print("Apellidos: ");
                    p.setApellidos(sc.nextLine());

                    System.out.print("Correo: ");
                    p.setCorreo(sc.nextLine());
                    System.out.print("Teléfono: ");
                    p.setTelefono(sc.nextLine());
                    System.out.print("Dirección: ");
                    p.setDireccion(sc.nextLine());
                    System.out.print("Cargo: ");
                    p.setCargo(sc.nextLine());

                    System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
                    p.setFecha_nacimiento(java.sql.Date.valueOf(sc.nextLine()));

                    // Mini-menú Sexo
                    sexoDAO.listarSexos().forEach(s ->
                            System.out.println(s.getId_sexo() + " - " + s.getDescripcion())
                    );
                    System.out.print("Selecciona ID sexo: ");
                    p.setId_sexo(sc.nextInt());
                    sc.nextLine();

                    // Mini-menú TipoSangre
                    tipoSangreDAO.listarTiposSangre().forEach(ts ->
                            System.out.println(ts.getId_tiposangre() + " - " + ts.getTipo_sangre())
                    );
                    System.out.print("Selecciona ID tipo de sangre: ");
                    p.setId_tiposangre(sc.nextInt());
                    sc.nextLine();

                    if (pacienteDAO.crearPaciente(p)) {
                        System.out.println("✅ Paciente creado correctamente");
                    } else {
                        System.out.println("❌ Error al crear paciente");
                    }
                }

                case 2 -> {
                    List<Paciente> lista = pacienteDAO.listarPacientes();

                    if (lista.isEmpty()) {
                        System.out.println("No hay pacientes registrados.");
                    } else {
                        // Encabezado de la tabla
                        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-12s %-10s %-15s %-25s %-12s %-20s %-15s %-20s%n",
                                "ID", "TipoDoc", "NroDoc", "Nombres", "Apellidos", "F.Nac", "Sexo", "TipoSangre",
                                "Correo", "Teléfono", "Dirección", "Cargo", "F.Registro");

                        // Separador
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

                        // Filas
                        for (Paciente p : lista) {
                            String tipoDoc = tipoDocumentoDAO.obtenerTipoDocumentoPorId(p.getId_tipodocumento());
                            String sexo = sexoDAO.obtenerSexoPorId(p.getId_sexo());
                            String tipoSangre = tipoSangreDAO.obtenerTipoSangrePorId(p.getId_tiposangre());

                            System.out.printf("%-5d %-15s %-15s %-15s %-15s %-12s %-10s %-15s %-25s %-12s %-20s %-15s %-20s%n",
                                    p.getId_pacientes(),
                                    tipoDoc != null ? tipoDoc : "N/A",
                                    p.getNumero_documento(),
                                    p.getNombres(),
                                    p.getApellidos(),
                                    p.getFecha_nacimiento(),
                                    sexo != null ? sexo : "N/A",
                                    tipoSangre != null ? tipoSangre : "N/A",
                                    p.getCorreo(),
                                    p.getTelefono(),
                                    p.getDireccion(),
                                    p.getCargo(),
                                    p.getFecha_registro()
                            );
                        }
                    }
                }

                case 3 -> {
                    System.out.print("Ingresa número de documento del paciente: ");
                    String doc = sc.nextLine();
                    Paciente paciente = pacienteDAO.obtenerPacientePorDocumento(doc);
                    if (paciente != null) {
                        String tipoDoc = tipoDocumentoDAO.obtenerTipoDocumentoPorId(paciente.getId_tipodocumento());
                        String sexo = sexoDAO.obtenerSexoPorId(paciente.getId_sexo());
                        String tipoSangre = tipoSangreDAO.obtenerTipoSangrePorId(paciente.getId_tiposangre());

                        System.out.println("ID: " + paciente.getId_pacientes()
                                + ", Nombre: " + paciente.getNombres() + " " + paciente.getApellidos()
                                + ", Documento: " + paciente.getNumero_documento()
                                + ", Tipo Doc: " + (tipoDoc != null ? tipoDoc : "N/A")
                                + ", Sexo: " + (sexo != null ? sexo : "N/A")
                                + ", Tipo Sangre: " + (tipoSangre != null ? tipoSangre : "N/A")
                                + ", Correo: " + paciente.getCorreo()
                                + ", Teléfono: " + paciente.getTelefono()
                                + ", Dirección: " + paciente.getDireccion()
                                + ", Cargo: " + paciente.getCargo()
                                + ", Fecha registro: " + paciente.getFecha_registro());
                    } else {
                        System.out.println("❌ Paciente no encontrado");
                    }
                }

                case 4 -> {
                    System.out.print("Ingresa número de documento del paciente a actualizar: ");
                    String doc = sc.nextLine();
                    Paciente paciente = pacienteDAO.obtenerPacientePorDocumento(doc);

                    if (paciente != null) {
                        int subOpcion;
                        do {
                            System.out.println("\n--- EDITAR PACIENTE ---");
                            System.out.println("1. Nombres");
                            System.out.println("2. Apellidos");
                            System.out.println("3. Correo");
                            System.out.println("4. Teléfono");
                            System.out.println("5. Dirección");
                            System.out.println("6. Cargo");
                            System.out.println("7. Tipo de documento");
                            System.out.println("8. Sexo");
                            System.out.println("9. Tipo de sangre");
                            System.out.println("0. Salir");
                            System.out.print("Selecciona el campo a editar: ");
                            subOpcion = sc.nextInt();
                            sc.nextLine();

                            switch (subOpcion) {
                                case 1 -> paciente.setNombres(sc.nextLine());
                                case 2 -> paciente.setApellidos(sc.nextLine());
                                case 3 -> paciente.setCorreo(sc.nextLine());
                                case 4 -> paciente.setTelefono(sc.nextLine());
                                case 5 -> paciente.setDireccion(sc.nextLine());
                                case 6 -> paciente.setCargo(sc.nextLine());
                                case 7 -> {
                                    tipoDocumentoDAO.listarTiposDocumento().forEach(td ->
                                            System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento())
                                    );
                                    paciente.setId_tipodocumento(sc.nextInt());
                                    sc.nextLine();
                                }
                                case 8 -> {
                                    sexoDAO.listarSexos().forEach(s ->
                                            System.out.println(s.getId_sexo() + " - " + s.getDescripcion())
                                    );
                                    paciente.setId_sexo(sc.nextInt());
                                    sc.nextLine();
                                }
                                case 9 -> {
                                    tipoSangreDAO.listarTiposSangre().forEach(ts ->
                                            System.out.println(ts.getId_tiposangre() + " - " + ts.getTipo_sangre())
                                    );
                                    paciente.setId_tiposangre(sc.nextInt());
                                    sc.nextLine();
                                }
                                case 0 -> System.out.println("Saliendo de edición...");
                                default -> System.out.println("Opción no válida");
                            }
                        } while (subOpcion != 0);

                        if (pacienteDAO.actualizarPaciente(paciente)) {
                            System.out.println("✅ Paciente actualizado correctamente");
                        } else {
                            System.out.println("❌ Error al actualizar paciente");
                        }

                    } else {
                        System.out.println("❌ Paciente no encontrado");
                    }
                }

                case 5 -> {
                    System.out.print("Ingresa ID del paciente a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (pacienteDAO.eliminarPaciente(id)) {
                        System.out.println("✅ Paciente eliminado");
                    } else {
                        System.out.println("❌ Error al eliminar paciente");
                    }
                }

                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 0);

        sc.close();
    }
}

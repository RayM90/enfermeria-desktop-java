package org.enfermeria.service;

import org.enfermeria.dao.PacienteDAO;
import org.enfermeria.dao.TipoDocumentoDAO;
import org.enfermeria.dao.SexoDAO;
import org.enfermeria.dao.TipoSangreDAO;
import org.enfermeria.model.Paciente;
import org.enfermeria.model.TipoDocumento;
import org.enfermeria.model.Sexo;
import org.enfermeria.model.TipoSangre;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
                    List<TipoDocumento> listaTD = tipoDocumentoDAO.listarTiposDocumento();
                    System.out.println("\n--- TIPOS DE DOCUMENTO ---");
                    for (TipoDocumento td : listaTD) {
                        System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento());
                    }
                    System.out.print("Selecciona ID tipo documento: ");
                    p.setId_tipodocumento(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Número de documento: ");
                    p.setNumero_documento(sc.nextLine());
                    System.out.print("Nombres: ");
                    p.setNombres(sc.nextLine());
                    System.out.print("Apellidos: ");
                    p.setApellidos(sc.nextLine());

                    // Nuevos campos
                    System.out.print("Correo: ");
                    p.setCorreo(sc.nextLine());
                    System.out.print("Teléfono: ");
                    p.setTelefono(sc.nextLine());
                    System.out.print("Dirección: ");
                    p.setDireccion(sc.nextLine());
                    System.out.print("Cargo: ");
                    p.setCargo(sc.nextLine());

                    System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
                    String fecha = sc.nextLine();
                    p.setFecha_nacimiento(java.sql.Date.valueOf(fecha));

                    // Mini-menú Sexo
                    List<Sexo> listaSexo = sexoDAO.listarSexos();
                    System.out.println("\n--- SEXO ---");
                    for (Sexo s : listaSexo) {
                        System.out.println(s.getId_sexo() + " - " + s.getDescripcion());
                    }
                    System.out.print("Selecciona ID sexo: ");
                    p.setId_sexo(sc.nextInt());
                    sc.nextLine();

                    // Mini-menú TipoSangre
                    List<TipoSangre> listaTS = tipoSangreDAO.listarTiposSangre();
                    System.out.println("\n--- TIPO DE SANGRE ---");
                    for (TipoSangre ts : listaTS) {
                        System.out.println(ts.getId_tiposangre() + " - " + ts.getTipo_sangre());
                    }
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
                    System.out.println("\n--- LISTA DE PACIENTES ---");
                    for (Paciente paciente : lista) {
                        System.out.println("ID: " + paciente.getId_pacientes()
                                + ", Nombre: " + paciente.getNombres() + " " + paciente.getApellidos()
                                + ", Documento: " + paciente.getNumero_documento()
                                + ", Correo: " + paciente.getCorreo()
                                + ", Teléfono: " + paciente.getTelefono()
                                + ", Dirección: " + paciente.getDireccion()
                                + ", Cargo: " + paciente.getCargo()
                                + ", Fecha registro: " + paciente.getFecha_registro());
                    }
                }

                case 3 -> {
                    System.out.print("Ingresa número de documento del paciente: ");
                    String doc = sc.nextLine();
                    Paciente paciente = pacienteDAO.obtenerPacientePorDocumento(doc);
                    if (paciente != null) {
                        System.out.println("ID: " + paciente.getId_pacientes()
                                + ", Nombre: " + paciente.getNombres() + " " + paciente.getApellidos()
                                + ", Documento: " + paciente.getNumero_documento()
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
                                case 1 -> { System.out.print("Nuevo nombre: "); paciente.setNombres(sc.nextLine()); }
                                case 2 -> { System.out.print("Nuevo apellido: "); paciente.setApellidos(sc.nextLine()); }
                                case 3 -> { System.out.print("Nuevo correo: "); paciente.setCorreo(sc.nextLine()); }
                                case 4 -> { System.out.print("Nuevo teléfono: "); paciente.setTelefono(sc.nextLine()); }
                                case 5 -> { System.out.print("Nueva dirección: "); paciente.setDireccion(sc.nextLine()); }
                                case 6 -> { System.out.print("Nuevo cargo: "); paciente.setCargo(sc.nextLine()); }
                                case 7 -> {
                                    List<TipoDocumento> listaTD = tipoDocumentoDAO.listarTiposDocumento();
                                    System.out.println("\n--- TIPOS DE DOCUMENTO ---");
                                    for (TipoDocumento td : listaTD) {
                                        System.out.println(td.getId_tipodocumento() + " - " + td.getTipo_documento());
                                    }
                                    System.out.print("Selecciona nuevo ID tipo documento: ");
                                    paciente.setId_tipodocumento(sc.nextInt());
                                    sc.nextLine();
                                }
                                case 8 -> {
                                    List<Sexo> listaSexo = sexoDAO.listarSexos();
                                    System.out.println("\n--- SEXO ---");
                                    for (Sexo s : listaSexo) {
                                        System.out.println(s.getId_sexo() + " - " + s.getDescripcion());
                                    }
                                    System.out.print("Selecciona nuevo ID sexo: ");
                                    paciente.setId_sexo(sc.nextInt());
                                    sc.nextLine();
                                }
                                case 9 -> {
                                    List<TipoSangre> listaTS = tipoSangreDAO.listarTiposSangre();
                                    System.out.println("\n--- TIPO DE SANGRE ---");
                                    for (TipoSangre ts : listaTS) {
                                        System.out.println(ts.getId_tiposangre() + " - " + ts.getTipo_sangre());
                                    }
                                    System.out.print("Selecciona nuevo ID tipo de sangre: ");
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

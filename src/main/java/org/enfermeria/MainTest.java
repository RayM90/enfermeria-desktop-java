package com.enfermeria;

import org.enfermeria.dao.PacienteDAO;
import org.enfermeria.dao.PersonalDAO;
import org.enfermeria.dao.ConsultaDAO;
import org.enfermeria.dao.HistorialMedicoDAO;

import org.enfermeria.model.Paciente;
import org.enfermeria.model.Personal;
import org.enfermeria.model.Consulta;
import org.enfermeria.model.HistorialMedico;

import java.util.Date;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        try {
            System.out.println("🔍 Probando CRUD completo de la base de datos...");

            // ===========================
            // 1️⃣ Paciente
            // ===========================
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = new Paciente();
            paciente.setIdTipoDocumento(1);
            paciente.setNumeroDocumento("V12345678");
            paciente.setNombres("Juan");
            paciente.setApellido("Perez");
            paciente.setFechaNacimiento(new Date(2000-1900, 5, 15)); // Año, mes-1, día
            paciente.setIdSexo(1);
            paciente.setCorreo("juan.perez@mail.com");
            paciente.setDireccion("Caracas, Venezuela");
            paciente.setReferido("Dr. Gómez");
            pacienteDAO.insertarPaciente(paciente);
            System.out.println("✅ Paciente insertado");

            List<Paciente> listaPacientes = pacienteDAO.listarPacientes();
            listaPacientes.forEach(System.out::println);

            int idPacienteInsertado = listaPacientes.get(listaPacientes.size() - 1).getIdPacientes();

            // ===========================
            // 2️⃣ Personal
            // ===========================
            PersonalDAO personalDAO = new PersonalDAO();
            Personal personal = new Personal();
            personal.setNombres("Maria");
            personal.setApellidos("Gonzalez");
            personal.setEspecialidad("Pediatría");
            personal.setCorreo("maria.gonzalez@mail.com");
            personal.setTelefono("04141234567");
            personal.setFechaNacimiento(new Date(1995-1900, 3, 20));
            personal.setFechaContratacion(new Date());
            personalDAO.insertarPersonal(personal);
            System.out.println("✅ Personal insertado");

            List<Personal> listaPersonal = personalDAO.listarPersonal();
            listaPersonal.forEach(System.out::println);

            int idPersonalInsertado = listaPersonal.get(listaPersonal.size() - 1).getIdPersonal();

            // ===========================
            // 3️⃣ Consulta
            // ===========================
            ConsultaDAO consultaDAO = new ConsultaDAO();
            Consulta consulta = new Consulta();
            consulta.setFechaConsulta(new Date());
            consulta.setMotivoConsulte("Dolor de cabeza");
            consulta.setDiagnostico("Migraña leve");
            consulta.setTratamientoSeguido("Paracetamol 500mg");
            consulta.setNotasAdicionales("Controlar presión arterial");
            consulta.setIdPacientes(idPacienteInsertado);
            consulta.setIdPersonal(idPersonalInsertado);
            consultaDAO.insertarConsulta(consulta);
            System.out.println("✅ Consulta insertada");

            List<Consulta> listaConsultas = consultaDAO.listarConsultas();
            listaConsultas.forEach(System.out::println);

            // ===========================
            // 4️⃣ Historial Médico
            // ===========================
            // Historial Médico
            HistorialMedicoDAO historialDAO = new HistorialMedicoDAO();
            HistorialMedico historial = new HistorialMedico();
            historial.setFecha_registro(new Date());
            historial.setDiagnostico("Asma leve");
            historial.setTratamiento("Inhalador");
            historial.setAlergias("Polen");
            historial.setCondiciones_medicas_previas("Ninguna");
            historial.setMedicamentos_actuales("Inhalador diario");
            historial.setId_pacientes(idPacienteInsertado);
            historialDAO.insertarHistorial(historial);
            System.out.println("✅ Historial médico insertado");

            List<HistorialMedico> listaHistorial = historialDAO.listarHistorial();
            listaHistorial.forEach(System.out::println);

            System.out.println("🎉 CRUD completo probado con éxito!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
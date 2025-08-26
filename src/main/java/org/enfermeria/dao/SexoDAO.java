package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.Sexo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SexoDAO {

    public List<Sexo> listarSexos() {
        List<Sexo> lista = new ArrayList<>();
        String sql = "SELECT * FROM sexo";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sexo s = new Sexo();
                s.setId_sexo(rs.getInt("id_sexo"));
                s.setDescripcion(rs.getString("descripcion"));
                lista.add(s);
            }

        } catch (Exception e) {
            System.out.println("Error al listar sexos: " + e.getMessage());
        }

        return lista;
    }
}

package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.TipoSangre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoSangreDAO {

    public List<TipoSangre> listarTiposSangre() {
        List<TipoSangre> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_sangre";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoSangre ts = new TipoSangre();
                ts.setId_tiposangre(rs.getInt("id_tiposangre"));
                ts.setTipo_sangre(rs.getString("tipo_sangre"));
                lista.add(ts);
            }

        } catch (Exception e) {
            System.out.println("Error al listar tipos de sangre: " + e.getMessage());
        }

        return lista;
    }
}

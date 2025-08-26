package org.enfermeria.dao;

import org.enfermeria.config.ConexionBD;
import org.enfermeria.model.TipoDocumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDAO {

    public List<TipoDocumento> listarTiposDocumento() {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_documento";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoDocumento td = new TipoDocumento();
                td.setId_tipodocumento(rs.getInt("id_tipodocumento"));
                td.setTipo_documento(rs.getString("tipo_documento"));
                lista.add(td);
            }

        } catch (Exception e) {
            System.out.println("Error al listar tipos de documento: " + e.getMessage());
        }

        return lista;
    }
}

package com.enfermeria;

import com.enfermeria.config.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("🔍 Probando conexión a la base de datos...");

        try (Connection conn = ConexionBD.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("🎉 Conexión exitosa a la base de datos!");
            } else {
                System.out.println("⚠️ No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar:");
            e.printStackTrace();
        }
    }
}

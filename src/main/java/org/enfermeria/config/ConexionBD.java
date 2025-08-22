package com.enfermeria.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConexionBD {
    private static final String PROPS_FILE = "db.properties";
    private static String url;
    private static String user;
    private static String pass;

    static {
        try (InputStream in = ConexionBD.class.getClassLoader().getResourceAsStream(PROPS_FILE)) {
            if (in == null) {
                throw new IllegalStateException("No se encontró " + PROPS_FILE + " en resources.");
            }
            Properties p = new Properties();
            p.load(in);
            url  = p.getProperty("db.url");
            user = p.getProperty("db.user");
            pass = p.getProperty("db.password");
            System.out.println("⚙️ Configuración DB cargada correctamente");
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error cargando " + PROPS_FILE + ": " + e.getMessage());
        }
    }

    private ConexionBD() {}

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pass);
        System.out.println("✅ Conexión establecida con MySQL");
        return conn;
    }
}

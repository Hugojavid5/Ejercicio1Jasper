package org.hugo.dein.jasperejercicio1.BBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase maneja la conexión con la base de datos, proporcionando métodos
 * para obtener y cerrar la conexión, así como cargar las propiedades de conexión
 * desde un archivo de configuración.
 */
public class ConexionBBDD {

    private static Connection connection;

    /**
     * Constructor que establece la conexión con la base de datos utilizando las
     * propiedades cargadas desde un archivo de configuración.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public ConexionBBDD() throws SQLException {
        Properties connConfig = loadProperties();
        String url = connConfig.getProperty("dburl");
        connection = DriverManager.getConnection(url, connConfig);
        connection.setAutoCommit(true);
    }

    /**
     * Devuelve la conexión actual con la base de datos.
     *
     * @return La conexión activa.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Cierra la conexión con la base de datos si está abierta.
     *
     * @return La conexión, que será nula si ha sido cerrada correctamente.
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    public Connection closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
        return connection;
    }

    /**
     * Carga las propiedades de configuración de la base de datos desde un archivo
     * llamado "configuration.properties".
     *
     * @return Un objeto Properties con las configuraciones de conexión.
     * @throws RuntimeException Si ocurre un error al leer el archivo de propiedades.
     */
    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("configuration.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades", e);
        }
    }
}

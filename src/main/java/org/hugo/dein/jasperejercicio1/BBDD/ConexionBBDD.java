package org.hugo.dein.jasperejercicio1.BBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
     * Constructor que establece la conexión con la base de datos utilizando
     * las propiedades cargadas desde el archivo de configuración.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión con la base de datos.
     */
    public ConexionBBDD() throws SQLException {
        Properties connConfig = cargarProperties();
        String url = connConfig.getProperty("dburl");
        connection = DriverManager.getConnection(url, connConfig);
        connection.setAutoCommit(true);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        connection.setAutoCommit(true);
    }

    /**
     * Devuelve la conexión activa con la base de datos.
     *
     * @return La conexión activa.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Cierra la conexión con la base de datos.
     *
     * @return La conexión cerrada.
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    public Connection CloseConexion() throws SQLException {
        connection.close();
        return connection;
    }

    /**
     * Carga las propiedades de conexión desde un archivo de configuración.
     *
     * @return Un objeto Properties con las propiedades de configuración.
     */
    public static Properties cargarProperties() {
        try (FileInputStream fs = new FileInputStream("configuration.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

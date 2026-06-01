package CONEXION;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    //Constantes privadas
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_pedidos_compra";
    private static final String USUARIO = "tu_user";
    private static final String PASSWORD = "tu_password";

    //Abrir canal de comunicacion con la base de datos (connection)
    public static Connection getConexion() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1045:
                    System.out.println("Error de autenticación: Verifique el usuario y la contraseña.");
                    break;
                case 1044:
                    System.out.println("Error de permisos: El usuario no tiene acceso a la base de datos.");
                    break;
                default:
                    System.out.println("Error de SQL: " + e.getMessage());
            }
            throw e;
        }
    }
}
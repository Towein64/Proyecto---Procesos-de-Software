package coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//metodo que hace conexion sql;
public class Conexion { //sdsdsd
    //conexion local

    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:sqlite:C:/Users/User/Documents/NetBeansProjects/Proyecto_Procesos/src/coneccion/bd_1.db");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local" + e);
        }
        return null;
    }
}

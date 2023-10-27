package controlador;

import modelo.Usuario;
import coneccion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ControladorUsuario {

    // Método para iniciar sesión
    public boolean loginUsuario(String usuario, String password) {
        boolean respuesta = false;

        try (Connection cn = Conexion.conectar()) {
            String sql = "SELECT * FROM empleados WHERE usuario = ? AND password = ?";
            try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario); // Establece el valor para el primer parámetro
                preparedStatement.setString(2, password); // Establece el valor para el segundo parámetro

                ResultSet resultado = preparedStatement.executeQuery();

                if (resultado.next()) {
                    respuesta = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
        }

        return respuesta;
    }

    public boolean verificarUsuario(String usuario) {
        boolean usuarioExiste = false;

        try (Connection cn = Conexion.conectar()) {
            String sql = "SELECT COUNT(*) FROM empleados WHERE usuario = ?";
            try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario);

                ResultSet resultado = preparedStatement.executeQuery();

                if (resultado.next()) {
                    int cantidad = resultado.getInt(1);
                    if (cantidad > 0) {
                        usuarioExiste = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar usuario: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al verificar usuario");
        }

        return usuarioExiste;
    }

    public boolean agregarUsuario(String usuario, String password) {
        if (verificarUsuario(usuario)) {
            // El usuario ya existe, muestra un mensaje y no realices la inserción.
            System.out.println("El usuario ya está registrado.");
            return false;
        }

        boolean respuesta = false;

        try (Connection cn = Conexion.conectar()) {
            String sql = "INSERT INTO empleados (usuario, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, password);

                // Ejecutar la inserción
                int filasInsertadas = preparedStatement.executeUpdate();

                if (filasInsertadas > 0) {
                    respuesta = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar usuario");
        }

        return respuesta;
    }

}

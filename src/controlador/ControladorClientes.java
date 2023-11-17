package controlador;
import modelo.Cliente;
import coneccion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControladorClientes {

    // Método para agregar un nuevo cliente a la base de datos
    public boolean agregarCliente(Cliente cliente) {
        // Declara la conexión y la sentencia SQL
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Obtén una conexión a la base de datos
            conn = Conexion.conectar(); // Debes implementar la clase Conexion con el método obtenerConexion()

            // Define la sentencia SQL para la inserción
            String sql = "INSERT INTO clientes (nombre, apellido, correo) VALUES (?, ?, ?)";

            // Prepara la sentencia SQL con los valores del cliente
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCorreo());

            // Ejecuta la inserción
            int filasAfectadas = stmt.executeUpdate();

            // Comprueba si la inserción fue exitosa
            if (filasAfectadas > 0) {
                return true; // La inserción fue exitosa
            } else {
                return false; // La inserción falló
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // La inserción falló debido a un error
        } finally {
            // Cierra la conexión y la sentencia
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean actualizarCliente(Cliente cliente) {
    Connection conn = null;
    PreparedStatement stmt = null;
    
    try {
        conn = Conexion.conectar(); // Debes implementar la clase Conexion con el método obtenerConexion()

        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ? WHERE id = ?"; // Supongo que tienes un campo "id" para identificar al cliente que deseas actualizar.

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, cliente.getNombre());
        stmt.setString(2, cliente.getApellido());
        stmt.setString(3, cliente.getCorreo());
        stmt.setInt(4, cliente.getId()); // Asegúrate de tener un método getId() en tu clase Cliente o usar el campo adecuado.

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            return true; // La actualización fue exitosa
        } else {
            return false; // La actualización falló
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // La actualización falló debido a un error
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public boolean borrarCliente(int idCliente) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = Conexion.conectar(); // Debes implementar la clase Conexion con el método obtenerConexion()

        String sql = "DELETE FROM clientes WHERE id = ?"; // Supongo que tienes un campo "id" para identificar al cliente que deseas borrar.

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCliente);

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            return true; // El cliente fue eliminado exitosamente
        } else {
            return false; // No se encontró un cliente con el ID especificado
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // La eliminación falló debido a un error
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


}

package controlador;
import modelo.Libro;
import coneccion.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Blob;
import vista.AgregarLibros;
import java.sql.ResultSet;

public class ControladorLibros {
    

    // Método para agregar un nuevo libro a la base de datos
    public boolean agregarLibro(Libro libro) throws FileNotFoundException {
        Connection conn = null;
    PreparedStatement stmt = null;
    FileInputStream fis = null;

    try {
        conn = Conexion.conectar(); // Debes implementar la clase Conexion con el método obtenerConexion()

        // Verifica si el libro ya existe en la base de datos
        if (existeLibro(libro)) {
            // Si existe, actualiza el stock en lugar de insertar un nuevo registro
            String updateSql = "UPDATE libros SET stock = stock + 1 WHERE nombre = ? AND autor = ?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getAutor());
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                return true; // Stock actualizado exitosamente
            } else {
                return false; // Fallo al actualizar el stock
            }
        } else {
            // Si no existe, realiza la inserción
            String insertSql = "INSERT INTO libros (nombre, autor, precio, stock) VALUES (?, ?, ?, 1)";
            stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getAutor());
            stmt.setDouble(3, libro.getPrecio());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                return true; // Inserción exitosa
            } else {
                return false; // Fallo al insertar
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
    public boolean existeLibro(Libro libro) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Conexion.conectar(); // Debes implementar la clase Conexion con el método obtenerConexion()

        // Define la sentencia SQL para verificar si el libro existe
        String sql = "SELECT COUNT(*) AS count FROM libros WHERE nombre = ? AND autor = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, libro.getNombre());
        stmt.setString(2, libro.getAutor());

        rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0; // Si count es mayor que 0, el libro existe
        }

        return false;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
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

    public boolean actualizarLibro(Libro libro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.conectar(); // Debes implementar la clase Conexion con el método obtenerConexion()

            // Define la sentencia SQL para la actualización, asumiendo que 'imagen' es un campo BLOB
            String sql = "UPDATE libros SET nombre = ?, autor = ?, precio = ?, stock = ?, imagen = ? WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getAutor());
            stmt.setDouble(3, libro.getPrecio());
            stmt.setInt(4, libro.getStock());

            // Para el campo BLOB (imagen), debes establecerlo de acuerdo a tus necesidades
            // Puedes utilizar stmt.setBlob(5, libro.getImagen());

            stmt.setInt(6, libro.getId()); // Asumiendo que 'id' es la clave primaria que identifica el libro a actualizar

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
    public boolean eliminarLibro(int idLibro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.conectar(); // Debes implementar la clase Conexion con el método obtenerConexion()

            // Define la sentencia SQL para la eliminación, asumiendo que 'id' es la clave primaria que identifica el libro
            String sql = "DELETE FROM libros WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLibro); // El ID del libro que deseas eliminar

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                return true; // La eliminación fue exitosa
            } else {
                return false; // No se encontró un libro con el ID especificado
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

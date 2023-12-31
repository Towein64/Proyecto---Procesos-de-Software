/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import coneccion.Conexion;
import controlador.ControladorUsuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JTable;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author User
 */
public class GestionUsuarios extends javax.swing.JInternalFrame {

    private int idUsuario;

    /**
     * Creates new form inter2
     */
    public GestionUsuarios() {
        initComponents();
        this.CargarTablaUsuarios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        descrip = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Usuarios");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scroll.setViewportView(tablaUsuarios);

        jPanel1.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 140));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 260, 160));

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 100, 30));

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 100, 30));

        descrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripActionPerformed(evt);
            }
        });
        getContentPane().add(descrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 120, 30));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 120, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if (!descrip.getText().isEmpty() ) {
       ControladorUsuario controlusuario = new ControladorUsuario();
       String nuevoUsuario = descrip.getText().trim(); // Nuevo nombre de usuario
       String password = jTextField2.getText().trim(); // Nueva contraseña

       int id = obtenerIdDeLaTabla();

       if (id != -1) {
           if (controlusuario.actualizarUsuario(id, nuevoUsuario)) {
               if (controlusuario.actualizarPassword(nuevoUsuario, password)) {
                   JOptionPane.showMessageDialog(null, "Usuario Actualizado");
                   descrip.setText("");
                   jTextField2.setText("");
                   this.CargarTablaUsuarios();
               } else {
                   JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña");
               }
           } else {
               JOptionPane.showMessageDialog(null, "Error al actualizar el nombre de usuario");
           }
       } else {
           JOptionPane.showMessageDialog(null, "Seleccione un usuario de la tabla");
       }
   } else {
       JOptionPane.showMessageDialog(null, "Seleccione un usuario");
   }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void descripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descripActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  if (!descrip.getText().isEmpty()) {
       ControladorUsuario controlusuario = new ControladorUsuario();
       String usuario = descrip.getText().trim();
       String password = jTextField2.getText().trim(); // Obtener la contraseña del campo jTextField2

       if (controlusuario.eliminarUsuario(usuario)) {
           JOptionPane.showMessageDialog(null, "Usuario Actualizado");
           descrip.setText(""); 
           this.CargarTablaUsuarios();
       } else {
           JOptionPane.showMessageDialog(null, "Error al actualizar el usuario");
       }

   } else {
       JOptionPane.showMessageDialog(null, "Seleccione un usuario");
   }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField descrip;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JScrollPane scroll;
    public static javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables

    //metodo para mostrar los usuarios en la tabla;
    private void CargarTablaUsuarios() {

        DefaultTableModel modelo = new DefaultTableModel();

        try (Connection con = Conexion.conectar()) {
            if (con != null) {
                String sql = "SELECT * FROM empleados";
                try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

                    modelo.addColumn("ID");
                    modelo.addColumn("Usuario");
                    modelo.addColumn("Password");
                    while (rs.next()) {
                        Object[] fila = new Object[3];
                        for (int i = 0; i < 3; i++) {
                            fila[i] = rs.getObject(i + 1);
                        }

                        modelo.addRow(fila);
                    }

                    tablaUsuarios.setModel(modelo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de usuarios: " + e);
        }

        tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaUsuarios.rowAtPoint(e.getPoint());
                int columna = 0; // Suponemos que la primera columna contiene el ID del usuario.

                if (fila > -1) {
                    int idUsuario = (int) modelo.getValueAt(fila, columna);
                    EnviarDatosUsuariosSeleccionados(idUsuario);
                }
            }
        });

    }

    private void EnviarDatosUsuariosSeleccionados(int idUsuario) {
        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT * FROM empleados WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idUsuario); // Establece el valor del parámetro en la consulta

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                descrip.setText(rs.getString("usuario"));
                jTextField2.setText(rs.getString("password"));

            }

            rs.close(); 
            pst.close();
            con.close();
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
    }
    
    private int obtenerIdDeLaTabla() {
    int filaSeleccionada = tablaUsuarios.getSelectedRow(); // Obtén la fila seleccionada
    if (filaSeleccionada != -1) {
        DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
        int id = (int) modelo.getValueAt(filaSeleccionada, 0);
        return id;
    }
    return -1; 
}

}

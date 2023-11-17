package modelo;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String usuario;
    private String password;
   

    //cosntructor
    public Usuario() {
        this.idUsuario = 0;
        this.nombre = "";
       
        this.usuario = "";
        this.password = "";
        
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }





    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
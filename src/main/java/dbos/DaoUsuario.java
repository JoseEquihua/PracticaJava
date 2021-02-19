/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbos;


import dtos.DtoUsuario;
import interfaces.IUsuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DaoUsuario implements IUsuario {

    static String URL = "jdbc:mysql://localhost:3306/web?useSSL=false";
    static String USER = "root";
    static String PSW = "1234";
    
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    String consulta;
    
    @Override
    public List<DtoUsuario> read(String correo) throws Exception {        
        List<DtoUsuario> datos = new ArrayList<>();
        consulta = "SELECT * FROM usuario where correo like ? ";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PSW);
        pst = conn.prepareStatement(consulta);    
        pst.setString(1, correo);
        rs = pst.executeQuery();
        while ( rs.next() ){
            DtoUsuario user = new DtoUsuario();
            user.setCorreo(rs.getString("correo"));
            user.setNombre(rs.getString("nombre"));
            user.setApp(rs.getString("app"));
            user.setApm(rs.getString("apm"));
            user.setContrasena(rs.getString("contrasena"));
            datos.add(user);            
        }
        conn.close();
        return datos;
    }
    
    @Override
    public List<DtoUsuario> read(String correo, String password) throws Exception {
        List<DtoUsuario> datos = new ArrayList<>();
        consulta = "SELECT * FROM usuario where correo like ?  AND contrasena like ?";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PSW);
        pst = conn.prepareStatement(consulta);    
        pst.setString(1, correo);
        pst.setString(2, password);
        rs = pst.executeQuery();
        while ( rs.next() ){
            DtoUsuario user = new DtoUsuario();
            user.setCorreo(rs.getString("correo"));
            user.setNombre(rs.getString("nombre"));
            user.setApp(rs.getString("app"));
            user.setApm(rs.getString("apm"));
            user.setContrasena(rs.getString("contrasena"));
            datos.add(user);            
        }
        conn.close();
        return datos;
    }
    
    
    @Override
    public boolean create(DtoUsuario user) throws Exception {
        boolean res = false;
        consulta = "INSERT INTO usuario(correo, nombre, app, apm, contrasena) VALUES (?, ?, ?, ?, ?)";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PSW);
        pst = conn.prepareStatement(consulta); 
        pst.setString(1, user.getCorreo());
        pst.setString(2, user.getNombre());
        pst.setString(3, user.getApp());
        pst.setString(4, user.getApm());
        pst.setString(5, user.getContrasena());
        
        int r = pst.executeUpdate();
        if(r > 0){
            res = true;
        }
        
        return res;
    } 
}

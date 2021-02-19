/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbos;

import dtos.DtoUsuario;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author PC
 */
public class DaoUsuarioTest {
    
    public DaoUsuarioTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
   
    /**
     * Test of read method, of class DaoUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testRead_String() throws Exception {
        System.out.println("read");
        String correo = "joseequihua@hotmail.com";
        DaoUsuario instance = new DaoUsuario();
        String expResult = "joseequihua@hotmail.com";
        List<DtoUsuario> result = instance.read(correo);
       // System.out.println(result.get(0).getCorreo());
        assertEquals(expResult, result.get(0).getCorreo());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of read method, of class DaoUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testRead_String_String() throws Exception {
        System.out.println("read");
        String correo = "joseequihua@hotmail.com";
        String password = "1234";
        DaoUsuario instance = new DaoUsuario();
        String expResultOne = "joseequihua@hotmail.com";
        String expResultTwo = "1234";
        List<DtoUsuario> result = instance.read(correo, password);
        System.out.println(result.get(0).getContrasena());
        assertEquals(expResultOne, result.get(0).getCorreo());
        assertEquals(expResultTwo, result.get(0).getContrasena());
        // TODO review the generated test code and remove the default call to fail.        
    }

    /**
     * Test of create method, of class DaoUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testCreate() throws Exception {
        System.out.println("create");
        DtoUsuario user = new DtoUsuario();
        user.setCorreo("test@gmail.com");
        user.setNombre("test");
        user.setApp("Ap paterno");
        user.setApm("Ap materno");
        user.setApm("contrasena");
        DaoUsuario instance = new DaoUsuario();
        boolean expResult = true;
        boolean result = instance.create(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbos.DaoUsuario;
import dtos.DtoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(name = "RecupPSW", urlPatterns = {"/RecupPSW"})
public class RecupPSW extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            DaoUsuario dao = new DaoUsuario(); 
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            String correo = request.getParameter("email");            
            List<DtoUsuario> datos = dao.read(correo);
            String psw="";
            if(datos.size() > 0){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet RecupPSW</title>");            
                out.println("</head>");
                out.println("<body>");
                for(DtoUsuario dto : datos){
                    out.println("<h1>Correo enviado</h1>");
                    psw = dto.getContrasena();
                }
                out.println("</body>");
                out.println("</html>"); 
                
                String de = "softwareconsultingservice69@gmail.com";                 
                String clave = "SCS123456.";
                String para= correo;
                String host = "smtp.gmail.com";




                Properties prop = System.getProperties();

                prop.put("mail.smtp.starttls.enable","true");
                prop.put("mail.smtp.host", host);
                prop.put("mail.smtp.user",de);
                prop.put("mail.smtp.password", clave);
                prop.put("mail.smtp.port",587);
                prop.put("mail.smtp.auth","true");

                prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

                Session sesion = Session.getDefaultInstance(prop,null);

                MimeMessage message = new MimeMessage(sesion);

                message.setFrom(new InternetAddress(de));

                message.setRecipient(Message.RecipientType.TO, new 
                InternetAddress(para));

                message.setSubject("Recuperación de contraseña");
                message.setText("La contraseña para el usuario: " + correo + "\nContraseña: " + psw);

                Transport transport = sesion.getTransport("smtp");

                transport.connect(host,de,clave);

                transport.sendMessage(message, message.getAllRecipients());

                transport.close();
                
            } else {
                
            }                    
        } catch (Exception ex) {
            Logger.getLogger(RecupPSW.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

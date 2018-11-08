/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yangd
 */
@WebServlet(name = "list", urlPatterns = {"/list"})
public class list extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         //Load the driver   
         String driver = "org.apache.derby.jdbc.ClientDriver";
         try {
         Class.forName(driver);
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(subTraffic.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Start Connection
        String connectionURL = "jdbc:derby://localhost:1527/DongYang";
        try {
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            System.out.println("Connect successfully ! ");
            
            //Create Statements to select all information
                Statement st = conn.createStatement();
                String selectq = "SELECT * FROM reports ORDER BY rtime DESC";
                
                ResultSet rs = st.executeQuery(selectq);
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(rs.getString("road"));
                    out.println("</td>");
                    out.println("<td>");
                    out.println(rs.getString("situ"));
                    out.println("</td>");
                    out.println("<td>");
                    out.println(rs.getString("rtime"));
                    out.println("</td>");
                    out.println("<td>");
                    out.println(rs.getString("uname"));
                    out.println("</td>");
                    out.println("</tr>");
                }
                
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(subTraffic.class.getName()).log(Level.SEVERE, null, ex);
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

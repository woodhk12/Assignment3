/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yangd
 */
@WebServlet(name = "subTraffic", urlPatterns = {"/subTraffic"})
public class subTraffic extends HttpServlet {
   

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
        HttpSession session=request.getSession(true);
        userBean uname = (userBean) session.getAttribute("uname");
       
        //Get inputs from request
        String name = uname.getName();
        String road = request.getParameter("road");
        String situ = request.getParameter("situation");
        
        //If any of these are empty, remind user to finish input
        if(name.isEmpty() || road.isEmpty() || situ.isEmpty()){
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/invalidInput.jsp");
             rd.forward(request, response);
        }
        
        //Proceed to DB connection
        else
        {
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
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                //Create Statements to insert information
                Statement st = conn.createStatement();
                String insertq = "INSERT INTO reports (uname,road,situ,rtime) VALUES ('" + name + "','" + road + "','" + situ + "','"+ timestamp + "')";
                System.out.println(insertq);
                
                st.execute(insertq);
                st.close();
                conn.close();
                
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Successful.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(subTraffic.class.getName()).log(Level.SEVERE, null, ex);
            }  
           
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

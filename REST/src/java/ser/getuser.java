/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lifeuniverse
 */
public class getuser extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet getuser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getuser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        HashMap<String, String> postReq = General.parseBodyRequest(request.getReader());
        String clientOrigin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        try (PrintWriter out = response.getWriter()) {
            if(postReq.containsKey("token")){
                String token = postReq.get("token");
                HashMap<String, Object> user = General.DBX.getUser(token);

                out.println("{"
                        + "\"full_name\": \"" + user.get("full_name") + "\", " 
                        + "\"username\": \"" + user.get("username") + "\", " 
                        + "\"email\": \"" + user.get("email") + "\", " 
                        + "\"address\": \"" + user.get("address") + "\", " 
                        + "\"postal_code\": \"" + user.get("postal_code") + "\", " 
                        + "\"phone_number\": \"" + user.get("phone_number") + "\"" 
                        + "}");
            }
            
            else if(postReq.containsKey("username")){
                String un = postReq.get("username");
                HashMap<String, Object> user = General.DBX.getUserByUsername(un);

                out.println("{"
                        + "\"full_name\": \"" + user.get("full_name") + "\", " 
                        + "\"username\": \"" + user.get("username") + "\", " 
                        + "\"email\": \"" + user.get("email") + "\", " 
                        + "\"address\": \"" + user.get("address") + "\", " 
                        + "\"postal_code\": \"" + user.get("postal_code") + "\", " 
                        + "\"phone_number\": \"" + user.get("phone_number") + "\"" 
                        + "}");
            }
        }catch(Exception e){
            
        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lifeuniverse
 */
public class register extends HttpServlet {

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
        //processRequest(request, response);
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
        //processRequest(request, response);
        HashMap<String, String> postReq = General.parseBodyRequest(request.getReader());
        String clientOrigin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Vector<String> dataInput = new Vector<String>();
            if(!postReq.containsKey("full_name")){
                out.println("{\"error\": true, \"msg\": \"parameter full_name tidak ditemukan\"}");
                return;
            }
            dataInput.add(postReq.get("full_name"));
            
            if(!postReq.containsKey("username")){
                out.println("{\"error\": true, \"msg\": \"parameter username tidak ditemukan\"}");
                return;
            }
            dataInput.add(postReq.get("username"));
            
            if(!postReq.containsKey("email")){
                out.println("{\"error\": true, \"msg\": \"parameter email tidak ditemukan\"}");
                return;
            }
            dataInput.add(postReq.get("email"));
            
            if(!postReq.containsKey("password")){
                out.println("{\"error\": true, \"msg\": \"parameter password tidak ditemukan\"}");
                return;
            }
            dataInput.add(postReq.get("password"));
            
            if(!postReq.containsKey("address")){
                out.println("{\"error\": true, \"msg\": \"parameter address tidak ditemukan\"}");
                return;
            }
            dataInput.add(postReq.get("address"));
            
            if(!postReq.containsKey("postal_code")){
                out.println("{\"error\": true, \"msg\": \"parameter postal_code tidak ditemukan\"}");
                return;
            }
            dataInput.add(postReq.get("postal_code"));
            
            if(!postReq.containsKey("phone_number")){
                out.println("{\"error\": true, \"msg\": \"parameter phone_number tidak ditemukan\"}");
                return;
            }
            dataInput.add(postReq.get("phone_number"));
            dataInput.add(null);
            
            String query = "INSERT INTO user (full_name, "
                    + "username, email, password, address, postal_code,"
                    + " phone_number, token) VALUES (" 
                    + General.DBX.serializeVector(dataInput) + ");";
            General.DBX.queryInsertUpdateDelete(query);
            out.println("{\"error\": false}");
        } catch (Exception e) {
            e.printStackTrace();
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

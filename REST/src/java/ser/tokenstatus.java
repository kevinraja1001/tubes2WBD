/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser;

import java.io.BufferedReader;
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
public class tokenstatus extends HttpServlet {

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
            
        Vector<String> vsi = new Vector<String>();
        BufferedReader s = request.getReader();
        String line = null;
        StringBuffer sb = new StringBuffer();
        try {
            while ((line = s.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            /*report an error*/ 
        }
        
        response.setContentType("text/html;charset=UTF-8");
        String clientOrigin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        try (PrintWriter out = response.getWriter()) {
            String urlEncoded = sb.toString();
            String[] fields = urlEncoded.split("=");
            
            if(!fields[0].equals("token")){
                out.println("Error -99: " + fields[0]);
                return;
            }
            
            String token = fields[1]; 
            
            if (token.equals("NULL") || token.isEmpty()) {
                out.println("Error -98: NULL || empty");
                return;
            }

            out.println(String.valueOf(General.TokenMaster.tokenStatus(String.valueOf(token))));
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

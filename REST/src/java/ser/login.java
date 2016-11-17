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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lifeuniverse
 */
public class login extends HttpServlet {

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
            String un = postReq.get("username");
            String pw = postReq.get("password");
            if (un.length() != 0 || pw.length() != 0) {
                response.setContentType("application/json");
                Vector<HashMap<String, Object>> retval
                        = General.DBX.query("SELECT * FROM user WHERE "
                                + "username=\"" + un + "\" AND password=\""
                                + pw + "\";");
                if (retval.size() == 1) {
                    Vector<String> vs = General.TokenMaster.createTokenExpiryTime(un, pw);
                    out.println("{\"error\": false, \"token\": \""
                            + vs.get(0) + "\", \"expiry_time\": \""
                            + vs.get(1) + "\"}");
                    return;
                }
                out.println("{\"error\": true}");
            }
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
    }// </editor-fold>|-|+\
}

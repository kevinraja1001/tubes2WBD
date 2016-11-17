/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "SessionTrack", urlPatterns = {"/SessionTrack"})
public class SessionTrack extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // Create a session object if it is already not  created.
        HttpSession session = request.getSession(true);
        // Get session creation time.
        Date createTime = new Date(session.getCreationTime());
        // Get last access time of this web page.
        Date lastAccessTime
                = new Date(session.getLastAccessedTime());
        String token = request.getParameter("token");
        if (token==null){
            out.print(0);
            return;
        }

        String title = "Welcome Back to my website";
        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("ABCD");

        // Check if this is new comer on your web page.
        
        int stat=1;
        stat = validasi(token);
        if (session.isNew()) {
            
            out.print(stat);
        } else {
            out.print(stat);
        }

        // Set response content type
        response.setContentType("text/html");

        String docType
                = "<!doctype html public \"-//w3c//dtd html 4.0 "
                + "transitional//en\">\n";
        
    }

    public static int validasi(String token) throws MalformedURLException, IOException {
        String webPage;
        try {
//            HttpPost httppost = new HttpPost("http://192.168.1.103:9077/REST/tokenstatus");
            URL url = new URL("http://192.168.1.104:9077/REST/tokenstatus");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            Map<String, Object> params = new LinkedHashMap<>();
            params.put("token", token);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            huc.setRequestMethod("POST");
            huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            huc.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            huc.setDoOutput(true);
            huc.getOutputStream().write(postDataBytes);
            huc.connect();

            InputStream is = huc.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = null;
            webPage = "";
            while ((data = reader.readLine()) != null) {
                webPage += data + "";
            }

        } finally {
            
        }
        return Integer.parseInt(webPage);
    }

}

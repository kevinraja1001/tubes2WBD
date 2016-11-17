/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/upload"})
public class upload extends HttpServlet {

    public static HashMap<String, Object> parseBodyRequest(BufferedReader reader) {

        Vector<String> vsi = new Vector<String>();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                vsi.add(line);
            }
        } catch (Exception e) {
            /*report an error*/
        }

        HashMap<String, Object> ret = new HashMap<String, Object>();
        int i = 0;
        for (String s : vsi) {
            if (s.length() == 0) {
                Pattern pattern = Pattern.compile("\"(.*?)\"");
                Matcher matcher = pattern.matcher(vsi.get(i - 1));
                if(matcher.find())
                    ret.put(matcher.group(1), vsi.get(i + 1));
            }
            i++;
        }

        return ret;
    }

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
        HashMap<String, Object> hasil = parseBodyRequest(request.getReader());
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            File file;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            
            //file = (File) hasil.get("file-upload");
            //String coba = (String) hasil.get("name");
            
            
            ServletContext context = request.getSession().getServletContext();;
            String filePath = request.getServletContext().getRealPath("") + context.getInitParameter("file-upload");

            // Verify the content type
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("c:\\temp"));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax(maxFileSize);
                try {
                    // Parse the request to get file items.
                    List fileItems = upload.parseRequest(request);

                    // Process the uploaded file items
                    Iterator i = fileItems.iterator();
                    while (i.hasNext()) {
                        FileItem fi = (FileItem) i.next();
                        if (!fi.isFormField()) {
                            // Get the uploaded file parameters
                            String fieldName = fi.getFieldName();
                            String fileName = fi.getName();
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();
                            // Write the file
                            if (fileName.lastIndexOf("\\") >= 0) {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\")));
                            } else {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\") + 1));
                            }
                        }

                    }
                    //out.println(coba);
                    //String up = SOAPClientSAAJ.getStringFromSOAPRespond("113","add_produk", "name",request.getParameter("name"),"desc",request.getParameter("desc"),"price",request.getParameter("price"),"url_pict",filePath,"penjual","nugsky",null,null,null,null,null,null);
                    out.println("aaa");
                } catch (Exception ex) {
                    out.println(ex);
                }
            } else {
                out.println("<p>Tidak ada file yang dipilih</p>");
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

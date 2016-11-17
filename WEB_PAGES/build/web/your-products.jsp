<%-- 
    Document   : your-products
    Created on : Nov 11, 2016, 12:32:20 AM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="client.SOAPClientSAAJ" %>
<%@page import="java.io.IOException" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Your Products</title>
        <link rel="stylesheet" type="text/css" href="css/general.css">
        <link rel="stylesheet" type="text/css" href="css/your-products.css">
    </head>
    <body>
        <div id="container">
            <div id="logo">
                <span class="merah teks">Sale</span><span class="biru teks">Project</"span>
            </div>

            <div id="main-page">
                <div id="name-logout">
                    <div>Hi, User Full Name!</div>
                    <a href="../misc/logout.php" class="merah">logout</a>
                </div>
                <div id="navigation">
                    <ul>
                        <li><a href="catalog.jsp">Catalog</a></li>
                        <li class="nav-selected"><a href="your-products.jsp">Your Products</a></li>
                        <li><a href="add-product.jsp">Add Product</a></li>
                        <li><a href="sales.jsp">Sales</a></li>
                        <li id="end"><a href="purchases.jsp">Purchases</a></li>
                    </ul>
                </div>
                <div id="section-your_products">
                    <div id="teks-pembuka">
                        What are you going to sell today?
                    </div>
                    <hr />
                    <div class="item">
<%  try{
        String[][] katalog = SOAPClientSAAJ.getArrayFromSOAPRespond("get_all_produk", null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
        for(int i=0; i<katalog.length; i++){

            out.println("            <div class=\"item\">");
            out.println("                <div class=\"owner\">Owner full_name</div>");
            String month = new String();
            if (katalog[i][12].substring(5,7).equalsIgnoreCase("01")){
                month = "January";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("02")){
                month = "February";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("03")){
                month = "March";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("04")){
                month = "April";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("05")){
                month = "May";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("06")){
                month = "June";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("07")){
                month = "July";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("08")){
                month = "August";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("09")){
                month = "September";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("10")){
                month = "October";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("11")){
                month = "November";
            }
            else if (katalog[i][12].substring(5,7).equalsIgnoreCase("12")){
                month = "December";
            }
            String date = new String(katalog[i][7].substring(8,10));
            String year = new String(katalog[i][7].substring(0,4));
            String hour = new String(katalog[i][7].substring(11,13));
            String minute = new String(katalog[i][7].substring(14,16));
            if(katalog[i][5].equalsIgnoreCase("nugsky")){
                out.println("            <div class=\"date-added1\"><b>Dayname, "+date+" "+month+" "+year+"</b></div>");
                out.println("            <div class=\"date-added\">at "+hour+":"+minute+"</div>");
                out.println("            <hr />");
                out.println("            <table class=\"item-dalam\">");
                out.println("                <tr>");
                out.println("                    <td class=\"gambar baris-item\">");
                out.println("                        <img src=\""+katalog[i][4]+"\">");
                out.println("                    </td>");
                out.println("                    <td class=\"detail baris-item\">");
                out.println("                        <div class=\"nama\" id=\"nama_"+katalog[i][0]+"\">"+katalog[i][1]+"</div>");
                out.println("                        <div class=\"harga\" id=\"harga_"+katalog[i][0]+"\">IDR "+katalog[i][3]+"</div>");
                out.println("                        <div class=\"deskripsi\" id=\"desc_"+katalog[i][0]+"\">"+katalog[i][2]+"</div>");
                out.println("                    </td>");
                out.println("                    <td class=\"edit-delete baris-item\">");
                out.println("           <div class=\"n\">"+katalog[i][6]+"</div>");
                out.println("                       <div class=\"n npurchases\">p purchases</div>");
                out.println("                        <span class=\"c_edit label kuning\">EDIT</span>");
                out.println("                        <span class=\"c_delete label merahtua\">DELETE</span>");
                out.println("                    </td>");
                out.println("                </tr>");
                out.println("            </table>");
            }
        }        
    }
    catch (Exception e){
        System.err.println("Error occurred while sending SOAP Request to Server");
        e.printStackTrace();
    }
%>
                        <hr />
                    </div>
                </div>
            </div>
        </div>
        <script>
            var id_pengguna = '.$USER_DATA['id'].';
            function notif_delete(id){');
                    var idhtml = "nama_".concat(id); ');
                    idhtml = idhtml.concat("_"); ');
                    idhtml = idhtml.concat(id_pengguna); ');
                    var x = document.getElementById(idhtml).innerHTML; ');
                    var y = confirm(("Anda yakin ingin menghapus ".concat(x)).concat("?")); ');
                    if (y == true) {');
//f_push_html('			alert("yes")');
                    var link = "../misc/do-delete.php?id=".concat(id_pengguna).concat("&idprod=").concat(id)');
                    window.location.href = link; ');
            }');
            }');
        </script>
    </body>
</html>

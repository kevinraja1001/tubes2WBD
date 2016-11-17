<%-- 
    Document   : sales
    Created on : Nov 11, 2016, 12:25:24 AM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="client.SOAPClientSAAJ" %>
<%@page import="java.io.IOException" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sales</title>
        <link rel="stylesheet" type="text/css" href="css/general.css">
        <link rel="stylesheet" type="text/css" href="css/sales.css">
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
                        <li><a href="your-products.jsp">Your Products</a></li>
                        <li><a href="add-product.jsp">Add Product</a></li>
                        <li class="nav-selected"><a href="sales.jsp">Sales</a></li>
                        <li id="end"><a href="purchases.jsp">Purchases</a></li>
                    </ul>
                </div>
                <div id="teks-pembuka">
                    Here are your sales
                </div>
                <hr>
<%  try{
        String[][] sales = SOAPClientSAAJ.getArrayFromSOAPRespond("113","get_sales_purchase", "x", "nama user", "mode", "sales", null,null,null,null,null,null,null,null,null,null,null,null);
        for(int i=1; i < sales.length; i++){
            String month = new String();
            if (sales[i][12].substring(5,7).equalsIgnoreCase("01")){
                month = "January";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("02")){
                month = "February";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("03")){
                month = "March";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("04")){
                month = "April";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("05")){
                month = "May";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("06")){
                month = "June";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("07")){
                month = "July";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("08")){
                month = "August";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("09")){
                month = "September";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("10")){
                month = "October";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("11")){
                month = "November";
            }
            else if (sales[i][12].substring(5,7).equalsIgnoreCase("12")){
                month = "December";
            }
            String date = new String(sales[i][12].substring(8,10));
            String year = new String(sales[i][12].substring(0, 4));
            String hour = new String(sales[i][12].substring(11,13));
            String minute = new String(sales[i][12].substring(14,16));

            out.println("        <div class=\"item\">");
            out.println("            <br>");
            out.println("            <div class=\"date-added1\"><b>Dayname, "+date+" "+month+" "+year+"</b></div>");
            out.println("            <div class=\"date-added\">at "+hour+":"+minute+"</div>");
            out.println("            <hr />");
            out.println("            <table>");
            out.println("                <tr>");
            out.println("                    <td class=\"gambar baris-item\">");
            out.println("                        <img src=\"'url '\">");
            out.println("                    </td>");
            out.println("                    <td class=\"detail baris-item\">");
            out.println("                        <div class=\"nama\">Nama Produk</div>");
            out.println("                        <div class=\"hargatotal\">IDR XXXXXXXX</div>");
            out.println("                        <div class=\"banyak\">NN</div>");
            out.println("                        <div class=\"hargasatuan\">@IDR YYYYYY</div>");
            out.println("                        <br>");
            out.println("                        <div class=\"pembeli\">bought by <b>Pembeli</b></div>");
            out.println("                    </td>");
            out.println("                    <td class=\"delivery baris-item\">");
            out.println("                        <div class=\"consignee\"> Delivery to <b>"+sales[i][1]+"</b></div>");
            out.println("                        <div class=\"address\">"+sales[i][2]+"</div>");
            out.println("                        <div class=\"kodepos\">"+sales[i][3]+"</div>");
            out.println("                        <div class=\"telepon\">"+sales[i][4]+"</div>");
            out.println("                    </td>");
            out.println("                </tr>");
            out.println("            </table>");
            out.println("            <br>");
            out.println("        </div>");
            out.println("    </div>");
        }
    }
    catch (Exception e){
        System.err.println("Error occurred while sending SOAP Request to Server");
        e.printStackTrace();
    }
%>                                        
        </div>
    </body>
</html>

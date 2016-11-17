<%-- 
    Document   : purchases
    Created on : Nov 10, 2016, 11:52:12 PM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="client.SOAPClientSAAJ" %>
<%@page import="java.io.IOException" %>

<%  try{
        String[][] init = SOAPClientSAAJ.getArrayFromSOAPRespond(null,"init", null,null,null,null, null,null,null,null,null,null,null,null,null,null,null,null);
    }
    catch (Exception e){
        System.err.println("Error occurred while sending SOAP Request to Server");
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Purchases</title>
        <link rel="stylesheet" type="text/css" href="css/general.css">
        <link rel="stylesheet" type="text/css" href="css/purchase.css">
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
                        <li><a href="sales.jsp">Sales</a></li>
                        <li id="end" class="nav-selected"><a href="purchases.jsp">Purchases</a></li>
                    </ul>
                </div>
                <div id="teks-pembuka">
                    Here are your purchases
                </div>
                <hr />
                <br>
<%  try{
        String[][] purchases = SOAPClientSAAJ.getArrayFromSOAPRespond("113","get_sales_purchase", "x", "nugsky", "mode", "purchases", null,null,null,null,null,null,null,null,null,null,null,null);
        for(int i=1; i < purchases.length; i++){
            String month = new String();
            if (purchases[i][12].substring(5,7).equalsIgnoreCase("01")){
                month = "January";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("02")){
                month = "February";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("03")){
                month = "March";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("04")){
                month = "April";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("05")){
                month = "May";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("06")){
                month = "June";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("07")){
                month = "July";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("08")){
                month = "August";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("09")){
                month = "September";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("10")){
                month = "October";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("11")){
                month = "November";
            }
            else if (purchases[i][12].substring(5,7).equalsIgnoreCase("12")){
                month = "December";
            }
            String date = new String(purchases[i][12].substring(8,10));
            String year = new String(purchases[i][12].substring(0, 4));
            String hour = new String(purchases[i][12].substring(11,13));
            String minute = new String(purchases[i][12].substring(14,16));
            out.println("        <div class=\"item\">");
            out.println("            <div class=\"owner\">"+date+" "+month+" "+year+"</div>");
            out.println("            <div class=\"date-added\">at "+hour+":"+minute+"</div>");
            out.println("            <hr />");
            out.println("            <div class=\"baris-item\">");
            out.println("                <div class=\"gambar baris-item\">");
            out.println("                    <img src=\""+purchases[i][7]+"\">");
            out.println("                </div>");
            out.println("                <div class=\"detail baris-item\">");
            out.println("                    <div class=\"nama\">"+purchases[i][6]+"</div>");
            out.println("                    <div class=\"harga\">IDR "+Integer.parseInt(purchases[i][8]) * Integer.parseInt(purchases[i][9])+"</div>");
            out.println("                    <div class=\"harga\">"+purchases[i][9]+" pcs</div>");
            out.println("                    <div class=\"harga\">@IDR "+purchases[i][8]+"</div>");
            out.println("                            <div class=\"harga\"><br/></div>");
            out.println("                    <div class=\"deskripsi\">bought from <b>"+purchases[i][10]+"</b></div>");
            out.println("                </div>");
            out.println("            </div>");
            out.println("            <div class=\"like-buy baris-item\">");
            out.println("                <div class\"deskripsi\">Delivery to <b>"+purchases[i][1]+"</b></div>");
            out.println("                <div class=\"deskripsi\">"+purchases[i][2]+"</div>");
            out.println("                <div class=\"deskripsi\">"+purchases[i][3]+"</div>");
            out.println("                <div class=\"deskripsi\">"+purchases[i][4]+"</div>");
            out.println("            </div>");
            out.println("        </div>");
        }
    }
    catch (Exception e){
        System.err.println("Error occurred while sending SOAP Request to Server");
        e.printStackTrace();
    }
%>
            </div>
        </div>
    </body>
</html>
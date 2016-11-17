<%-- 
    Document   : catalog
    Created on : Nov 10, 2016, 11:28:04 PM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="client.SOAPClientSAAJ" %>
<%@page import="java.io.IOException" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Catalog</title>
        <link rel="stylesheet" type="text/css" href="css/general.css">
        <link rel="stylesheet" type="text/css" href="css/catalog.css">
        <link rel="stylesheet" type="text/css" href="css/add product.css">
    </head>
    <body>
<%  String[][] init = SOAPClientSAAJ.getArrayFromSOAPRespond(null,"init", null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);%>
        <div id="container">
            <div id="logo">
                <span class="merah teks">Sale</span><span class="biru teks">Project</"span>
            </div>

            
                <div id="name-logout">
                    <div>Hi, User Full Name!</div>
                    <a href="../misc/logout.php" class="merah">logout</a>
                </div>
                <div id="navigation">
                    <ul>
                        <li class="nav-selected"><a href="catalog.jsp">Catalog</a></li>
                        <li><a href="your-products.jsp">Your Products</a></li>
                        <li><a href="add-product.jsp">Add Product</a></li>
                        <li><a href="sales.jsp">Sales</a></li>
                        <li id="end"><a href="purchases.jsp">Purchases</a></li>
                    </ul>
                </div>
                
                    <div id="teks-pembuka">
                        What are you going to buy today?
                    </div>
                    <hr />
                    <form action="' . htmlspecialchars($_SERVER["PHP_SELF"]) . '" method="GET" id="search-area">
                        <input id="search-input" type="text" name="keyword" placeholder="Search catalog ..." />
                        <input type="text" name="id"  hidden />
                        <input type="submit" value="GO" id="submit-search"></input>
                        <div>
                            by
                            <input type="radio" name="typex" value="product" ' . ($is_search ? ($_GET['typex'] == 'product' ? 'checked' : '') : 'checked') . '> Product
                            <input type="radio" name="typex" value="store" ' . ($is_search ? ($_GET['typex'] == 'store' ? 'checked' : '') : '') . '> Store
                        </div>
                    </form>

                    <div id="teks-pencarian">
                       <b>keyword</b> by <b>typex</b>
                        <hr />
                    </div>
<%  try{
        String[][] katalog = SOAPClientSAAJ.getArrayFromSOAPRespond(null,"get_all_produk", null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
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
            String date = new String(katalog[i][7].substring(8,10));
            String year = new String(katalog[i][7].substring(0,4));
            out.println("                <div class=\"date-added\">added this on Day, "+month+" "+date+", "+year+" </div>");
            out.println("                <hr />");
            out.println("                <div class=\"item-dalam\">");
            out.println("                    <div class=\"gambar baris-item\">");
            out.println("                        <img src=\"+katalog[i][4]+\">");
            out.println("                    </div>");
            out.println("                    <div class=\"detail baris-item\">");
            out.println("                    <div class=\"nama\" id=\"nama_"+katalog[i][0]+"\">Nama Produk</div>");
            out.println("                        <div class=\"harga\" id=\"harga_"+katalog[i][0]+"\">IDR "+katalog[i][3]+"</div>");
            out.println("                        <div class=\"deskripsi\">"+katalog[i][2]+"</div>");
            out.println("                    </div>");
            out.println("                    <div class=\"like-buy baris-item\">");
            out.println("                        <div class=\"n\" id=\"nlikes_"+katalog[i][0]+">"+katalog[i][6]+"</div>");
            out.println("                        <div class=\"n npurchases\">Y purchases</div>");
            out.println("                        <span class=\"c_likes label merah\">LIKE</span>");
            out.println("                  	<span class=\"c_purchases label hijau\" id=\"buy\" href=\"catalog.jsp\">BUY</span>");
            out.println("                    </div>");
            out.println("                </div>");
            out.println("                <hr />");
            out.println("            </div>");
        }
    }
    catch (Exception e){
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
    }
%>
                
                <div id="section-confirm">
                    <div id="teks-pembuka">
                        Please confirm your purchase
                    </div>
                    <hr />
                    <form action="../misc/do-buy.php" method="post" id="add-product">
                        <div id="confirm-detail-produk">
                            <table>
                                <tr>
                                    <td>Product</td>
                                    <td>:</td>
                                    <td><span id="confirm_nama_produk"></span></td>
                                </tr>
                                <tr>
                                    <td>Price</td>
                                    <td>:</td>
                                    <td><span id="confirm_harga"></span></td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td>:</td>
                                    <td><input type="text" name="qty" id="input_quantity" value="1" /> pcs</td>
                                </tr>
                                <tr>
                                    <td>Total Price</td>
                                    <td>:</td>
                                    <td><span id="confirm_harga_calculated"></span></td>
                                </tr>
                                <tr>
                                    <td>Deliver to</td>
                                    <td>:</td>
                                    <td></td>
                                </tr>
                            </table>
                        </div>

                        <div class="data-input">
                            <div>
                                Consignee
                            </div>
                            <input type="text" name="consignee" required>
                        </div>
                        <div class="data-input">
                            <div>
                                Full Address
                            </div>
                            <textarea rows="3" name="address"></textarea>
                        </div>
                        <div class="data-input">
                            <div>
                                Postal Code
                            </div>
                            <input type="number" name="postal_code" required>
                        </div>
                        <div class="data-input">
                            <div>
                                Phone Number
                            </div>
                            <input type="number" name="phone" required>
                        </div>
                        <div class="data-input">
                            <div>
                                12 Digits Credit Card Number
                            </div>
                            <input type="text" name="CCN" required>
                        </div>
                        <div class="data-input">
                            <div>
                                3 Digits Card Verification Value
                            </div>
                            <input type="text" name="CCVV" required>
                        </div>
                        <input type="number" id="confirm_id_product" name="id" value="" hidden />
                        <input type="text" name="post_id" value="' . $USER_DATA["id"] . '" hidden />
                               <div class="form-submit">
                            <input type="submit" value="CONFIRM" />
                            <button type="button" id="confirm_cancel">CANCEL</button>
                        </div>
                    </form>
                    <hr />
                </div>
            
        </div>
        <script>
            $(document).ready(function(){
                $("#section-confirm").hide();
            });
            $(".c_purchases label hijau").click(function(event){
                event.preventDefault();
                $("#section-confirm").show();
            });
        </script>
        <script type="text/javascript" src="../js/ajax-like.js"></script>
        <script type="text/javascript" src="../js/catalog.js"></script>
    </body>
</html>

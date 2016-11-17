<%-- 
    Document   : buy
    Created on : Nov 10, 2016, 11:23:10 PM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>buy</title>
        <link rel="stylesheet" type="text/css" href="css/general.css">
        <link rel="stylesheet" type="text/css" href="css/catalog.css">
        <link rel="stylesheet" type="text/css" href="css/add product.css">
        <link rel="stylesheet" type="text/css" href="css/buy.css">
    </head>

    <body>
        <input type="number" id="h_price" class="hidden" value="'.$price.'"></input>
        <div id="container">
            <div id="logo">
                <span class="merah teks">Sale</span><span class="biru teks">Project</"span>
            </div>

            <div id="main-page">
                <div id="name-logout">
<%  out.println("                  <div>Hi, "+(String)session.getAttribute("fn")+"!</div>");%>
                    <a href="logout.jsp" class="merah">logout</a>
                </div>
                <div id="navigation">
                    <ul>
                        <li><a href="catalog.jsp">Catalog</a></li>
                        <li><a href="your-products.jsp">Your Products</a></li>
                        <li><a href="add-product.jsp">Add Product</a></li>
                        <li><a href="sales.jsp">Sales</a></li>
                        <li id="end"><a href="purchases.jsp">Purchases</a></li>
                    </ul>
                </div>
                <div id="teks-pembuka">
                    Please confirm your purchase
                </div>
                <hr />
                <form method="post" id="buy" action="../misc/do-buy.php">
                    <pre>Product		: Nama Produk</pre>
                    <pre>Price		: IDR XXXXXXX</pre>
                    <pre>Quantity		: <input type="number" name="qty" id="qty" class="pcs" onkeyup="hitung_total()" onchange="hitung_total()"> pcs</pre>
                    <input type="number" class="hidden" name="id" value="'.$row['id'].'"</input>
                    <pre>Total Price	: IDR <a id="total">'YYYYYYYY</a></pre>
                    <pre>Delivery to	: </pre>
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
                        <textarea rows="2" name="address" form="buy"></textarea>
                    </div>
                    <div class="data-input">
                        <div>
                            Postal Code
                        </div>
                        <input type="text" name="postal_code" required>
                    </div>
                    <div class="data-input">
                        <div>
                            Phone Number
                        </div>
                        <input type="text" name="phone" required>
                    </div>
                    <div class="data-input">
                        <div>
                            12 Digits Credit Card Number
                        </div>
                        <input type="text" name="CCN" required>
                    </div>
                    <div class="data-input">
                        <div>
                            3 Digits Credit Card Verification Value
                        </div>
                        <input type="text" name="CCVV" required>
                    </div>
                    <div class="form-submit">
                        <input type="submit" value="CONFIRM">
                        <a href="catalog.html"><button type="button" a href="catalog.html">CANCEL</button></a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
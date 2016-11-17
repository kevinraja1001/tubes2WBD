<%-- 
    Document   : add-product
    Created on : Nov 10, 2016, 11:04:08 PM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Product</title>
        <link rel="stylesheet" type="text/css" href="css/general.css">
        <link rel="stylesheet" type="text/css" href="css/catalog.css">
        <link rel="stylesheet" type="text/css" href="css/add product.css">
    </head>
    <body>
        <div id="container">
            <div id="logo">
                <span class="merah teks">Sale</span><span class="biru teks">Project</span>
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
                        <li class="nav-selected"><a href="add-product.jsp">Add Product</a></li>
                        <li><a href="sales.jsp">Sales</a></li>
                        <li id="end"><a href="purchases.jsp">Purchases</a></li>
                    </ul>
                </div>
                <div id="teks-pembuka">
                    Please add your product here
                </div>
                <hr />
                <form method="POST" enctype="multipart/form-data" id="formap" action="upload">
                    <div class="data-input">
                        <div>
                            Name
                        </div>
                        <input type="text" id="name" name="name">
                    </div>
                    <div class="data-input">
                        <div>
                            Description
                        </div>
                        <input type="text" id="desc" name="desc">
                    </div>
                    <div class="data-input">
                        <div>
                            Price (IDR)
                        </div>
                        <input name="price" id="price" onchange="check_number()">
                    </div>
                    <div class="data-input">
                        <div>
                            Photo
                        </div>
                        <input type="file" id="photo" name="photo" accept="image/*">
                    </div>
                    <div class="form-submit">
                        <input type="submit" value="ADD">
                        <a href="catalog.html?id='.$USER_DATA['id'].'"><button type="button" a href="catalog.html">CANCEL</button></a>
                    </div>
                </form>
            </div>
            <script type="text/javascript" src="../js/ajax-like.js"></script>
            <script type="text/javascript" src="../js/add-product.js"></script>
    </body>
</html>

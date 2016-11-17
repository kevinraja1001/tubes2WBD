<%-- 
    Document   : edit-product
    Created on : Nov 10, 2016, 11:36:21 PM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Product</title>
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
                  <div>Hi, <%=session.getAttribute("fn")%>!</div>
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
                    Please update your product here
                </div>
                <hr />
                <form method="post" enctype="multipart/form-data" id="edit-product" action="../misc/do-edit-product.php?id='.$USER_DATA['id'].'">
                    <div class="data-input">
                        <div>
                            Name
                        </div>
                        <input type="text" name="name" value="'.$value['name'].'" required>
                    </div>
                    <div class="data-input">
                        <div>
                            Description (max 200 chars)
                        </div>
                        <textarea rows="3" name="description" form="edit-product">Ini adalah deskripsi</textarea>
                    </div>
                    <div class="data-input">
                        <div>
                            Price (IDR)
                        </div>
                        <input type="number" name="price" value="'.$value['price'].'" required>
                    </div>
                    <div class="data-input">
                        <div>
                            Photo
                        </div>
                        <input type="file" name="photo" id="photo" src="accept="image/*" disabled>
                               <label for="photo">img/photo.jpg</label>
                    </div>
                    <input type="hidden" name="idprod" value=' . $idprod . '>
                    <div class="form-submit">
                        <input type="submit" value="UPDATE">
                        <a href="your-products.php?id=' . $USER_DATA['id'] .'"><button type="button" a href="your-products.php?id='.$USER_DATA['id'].'">CANCEL</button></a>
                    </div>
                </form>
                }
            </div>
        </div>
        <script type="text/javascript" src="../js/edit-product.js"></script>
    </body>
</html>

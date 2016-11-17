
<%-- 
    Document   : delete
    Created on : Nov 10, 2016, 11:29:54 PM
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
                <span class="merah teks">Sale</span><span class="biru teks">Project</"span>
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
                    Are you sure to delete this product
                </div>
                <hr />
                foreach ($data as $key => $value){
                <form action="../misc/do-delete.php" method="post" id="delete-product">
                    <div id="confirm-detail-produk">
                        <table>
                            <tr>
                                <td>Product</td>
                                <td>:</td>
                                <td>Nama Produk</td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td>:</td>
                                <td>XXXXXX</span></td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td>:</td>
                                <td>Ini adalah deskripsi</span></td>
                            </tr>
                        </table>
                    </div>

                    <div class="data-input">
                        <input type="hidden" name="id" value='.$idprod.'>
                    </div>
                    <div class="form-submit">
                        <input type="submit" value="DELETE">
                        <button type="button" href="your-products.php?id='.$USER_DATA['id'].'">CANCEL</button>
                    </div>
                </form>
                }
                <hr />
            </div>
        </div>
    </body>
</html>

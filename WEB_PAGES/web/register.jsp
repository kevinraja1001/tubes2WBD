<%-- 
    Document   : register
    Created on : Nov 11, 2016, 12:16:46 AM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="css/general.css" />
	<link rel="stylesheet" type="text/css" href="css/login.css" />
    </head>
    <body>
	<div id="container">
            <div id="logo">
                <span class="merah teks">Sale</span><span class="biru teks">Project</"span>
            </div>
            <div id="main-page">
                <div id="judul-utama">
                    Please register
                </div>
                <hr id="garis-pembatas" />
                <form method="post" action="../misc/do-register.php" id="formr">
                    <div class="data-input">
                        <div>
                            Full Name
                        </div>
                        <input type="text" name="full_name" id="full_name" />
                    </div>
                    <div class="data-input">
                        <div>
                            Username
                            <span id="username-tidak-tersedia" class="merah">*username sudah digunakan</span>
                        </div>
                        <input type="text" name="username" id="username" />
                    </div>
                    <div class="data-input">
                        <div>
                            Email
                            <span id="email-sudah-ada" class="merah">*email sudah digunakan</span>
                        </div>
                        <input type="text" name="email" id="email" />
                    </div>
                    <div class="data-input">
                        <div>
                            Password
                        </div>
                        <input type="password" name="password" id="password" />
                    </div>
                    <div class="data-input">
                        <div>
                            Confirm Password
                        </div>
                        <input type="password" name="confirm_password" id="confirm_password" />
                    </div>
                    <div class="data-input">
                        <div>
                            Full Address
                        </div>
                        <input type="text" name="full_address" id="full_address" />
                    </div>
                    <div class="data-input">
                        <div>
                            Postal Code
                        </div>
                        <input type="text" name="postal_code" id="postal_code" />
                    </div>
                    <div class="data-input">
                        <div>
                            Phone Number
                        </div>
                        <input type="text" name="phone_number" id="phone_number" />
                    </div>
                    <div class="form-submit">
                        <input type="submit" value="REGISTER">
                    </div>
                </form>
                <div id="register"><b>Already registered? Login <a href="login.jsp">here</a></b></div>
            </div>
	</div>
        <script>
            $(document).ready(function () {
                $("#username-tidak-tersedia").hide();
                $("#email-sudah-ada").hide();
            });
        </script>
    </body>
</html>

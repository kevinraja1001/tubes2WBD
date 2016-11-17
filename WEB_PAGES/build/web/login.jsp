<%-- 
    Document   : login
    Created on : Nov 10, 2016, 11:46:40 PM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
                    Please login
                </div>
                <%//if(isset($_GET['status')
                    //if($_GET['status'] == '0')%>
                <span class="pesan-kesalahan-login">Username atau password salah!</span>

                <hr id="garis-pembatas" />
                    <div class="data-input">
                        <div>
                            Email or Username
                        </div>
                        <input id="username" type="text" name="email_username"/>
                    </div>
                    <div class="data-input">
                        <div>
                            Password
                        </div>
                        <input id="password" type="password" name="password" />
                    </div>
                    <div class="form-submit">
                        <input id="submit" type="submit" value="LOGIN">
                    </div>
                <div id="register"><b>Don't have an account yet? Register <a href="register.jsp">here</a></b></div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $(".pesan-kesalahan-login").hide();
            });
            $("#submit").click(function(event){
                
                var u = $("#username").val();
                var p = $("#password").val();
                var f = new FormData();
                f.append('username', u);
                f.append('password', p);
                console.log(f);
                $.ajax({
                    url : "http://192.168.43.168:9077/rest/login",
                    type: "POST",
                    data : f,
                    processData: false,
                    contentType: false,
                    success:function(data, textStatus, jqXHR){
                        console.log(data);
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        //if fails     
                    }
                });
                alert(u + p);
                event.preventDefault();
            });
            
        </script>
    </body>
</html>

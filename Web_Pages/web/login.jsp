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
                $.post("http://localhost:15376/REST/login",
                    {
                        'username': u,
                        'password': p
                    },
                    function(data, status){
                        console.log(data);
                        if(data.error === false){
                            var d = data;
                            var d2 = data;
                            $.post("http://localhost:15376/REST/getuser",{
                                'username'  : u
                            },
                            function(data){
                                console.log(data);
                                d2 = data;
                            });                            
                            $(location).attr('href', 'do-login.jsp?un='+u+'&t='+d.token+'&et='+d.expiry_time+'&fn='+d2.full_name);
                        }
                        else{
                            $(".pesan-kesalahan-login").show();
                        }
                    }
                );
                event.preventDefault();
            });
            
        </script>
    </body>
</html>

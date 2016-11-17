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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/general.css" />
	<link rel="stylesheet" type="text/css" href="css/login.css" />
    </head>
    <body>
	
            <div id="logo">
                <span class="merah teks">Sale</span><span class="biru teks">Project</"span>
            </div>
            
                <div id="judul-utama">
                    Please register
                </div>
                <hr id="garis-pembatas" />
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
                <div id="register"><b>Already registered? Login <a href="login.jsp">here</a></b></div>
            
	
        <script>
            $(document).ready(function () {
                $("#username-tidak-tersedia").hide();
                $("#email-sudah-ada").hide();
            });
            $("#submit").click(function(event){
                event.preventDefault();
                var fn = $("#full_name").val();
                var u = $("#username").val();
                var e = $("#email").val();
                var pw = $("#password").val();
                var cpw = $("#confirm_password").val();
                var fa = $("#full_address").val();
                var pc = $("#postal_code").val();
                var pn = $("#phone_number").val();
                if(isEmpty(fn)){
                    alert("Full Name tidak boleh kosong");
                }
                else{
                    if(u === "" || u === null || u === undefined){
                        alert("username tidak boleh kosong");
                    }
                    else{
                        if(e === "" || e === null || e === undefined){
                            alert("email tidak boleh kosong");
                        }
                        else{
                            if(pw === "" || pw === null || pw === undefined){
                                alert("Password tidak boleh kosong");
                            }
                            else{
                                if(cpw === "" || cpw === null || cpw === undefined){
                                    alert("Konfirmasi password Anda");
                                }
                                else{
                                    if(fa === "" || fa === null || fa === undefined){
                                        alert("Alamat tidak boleh kosong");
                                    }
                                    else{
                                        if(pc === "" || pc === null || pc === undefined){
                                            alert("Kode Pos tidak boleh kosong");
                                        }
                                        else{
                                            if(pn === "" || pn === null || pn === undefined){
                                                alert("Nomor Telepon tidak boleh kosong");
                                            }
                                            else{
                                                if(cpw !== pw){
                                                    alert("Password dan konfirmasinya tidak sama");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                $.post("http://192.168.43.168:9077/REST/register",
                {
                    'full_name'     : fn,
                    'username'      : u,
                    'email'         : e,
                    'password'      : pw,
                    'full_address'  : fa,
                    'postal_code'   : pc,
                    'phone_number'  : pn
                },
                function(data, status){
                    console.log(data);
                    if(data.error === false){
                        $(location).attr('href', 'catalog.jsp');
                    }
                    else{
                        alert(data.msg);
                    }
                });
            });
        </script>
    </body>
</html>

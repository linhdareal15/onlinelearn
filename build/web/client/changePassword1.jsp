<!DOCTYPE html>
<!-- Created By CodingLab - www.codinglabweb.com -->
<html lang="en" dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- <title>Login Form | CodingLab</title> -->
        <link rel="stylesheet" href="client/css/changePass.css">
        <title>Reset Password</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
        <style>
            .eye {
                position: relative;
            }
            .eye img{
                position: absolute;
                width: 20px;
                height: 20px;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
                cursor: pointer;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <div class="wrapper">
                <div class="title"><span>Reset Password</span></div>
                <form action="resetpassword" method="POST">
                    <input type="hidden" name="email" value="${requestScope.email}">
                    <div class="row eye">
                        <i class="fas fa-lock"></i>
                        <input id="pass1" name="newpassword" type="password" placeholder="Enter new password" required>
                        <img onclick="myfunction1()" src="client/img/eye.png" style="display: inline-block">
                    </div>
                    <div class="row eye">
                        <i class="fas fa-lock"></i>
                        <input id="pass2" name="repassword" type="password" placeholder="Confirm your password" required>
                        <img onclick="myfunction2()" src="client/img/eye.png" style="display: inline-block">

                    </div>
                    <p class="alert alert-danger" style="color: #cc0033;">${message1}</p>
                    <div class="row button">
                        <input onclick="send()" type="submit" value="Save">
                    </div>
                    <div class="signup-link">Not a member? <a href="../register">Register now</a></div>
                </form>
            </div>
        </div>
        <script type="text/javascript">
            
            var x1 = true;
            function myfunction1() {
                if (x1) {
                    document.getElementById('pass1').type = "text";
                    x1 = false;
                } else {
                    document.getElementById('pass1').type = "password";
                    x1=true;
                }

            }
            var x2 = true;
            function myfunction2() {
                if (x2) {
                    document.getElementById('pass2').type = "text";
                    x2 = false;
                } else {
                    document.getElementById('pass2').type = "password";
                    x2=true;
                }

            }
           let patternPassWord = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

            function send() {
                var arr = document.getElementsByTagName('input');
                var newpass = arr[0].value;
                var repass = arr[1].value;
                if (!newpass.match(patternPassWord)) {
                    return;
                }
                else if (!newpass.equals(repass)) {
                    return;
                }
                else{
                    alert("Reset Password Successfull")
                }
            }
        </script>            
    </body>

</html>

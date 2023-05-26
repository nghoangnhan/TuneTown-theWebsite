<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log In</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <!-- <link rel="stylesheet" href="./assets/css/reset.css"> -->
    <link rel="stylesheet" href="./assets/css/login.css">
    <link rel="stylesheet" href="./assets/font/font.css">
     <!-- link icon fa tren w3school -->
    <link
     rel="stylesheet"
     href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <!-- Font chu -->
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,500;0,700;1,500;1,700&display=swap"
        rel="stylesheet"
    />
    <link
        href="https://fonts.googleapis.com/css2?family=Tilt+Warp&display=swap"
        rel="stylesheet"
    />
</head>
<body>
    <section>
        <!--Bắt Đầu Phần Nội Dung-->
        <div class="content">
            <div class="logo">
                <a href="home.jsp"><img src="./assets/img/logo/logo.png" alt=""></a>
            </div>
            <div class="form">             
                <form class="login_form" action="login" method="post" name="form">
                    <div class="input-wrapper">
                        <div class="font ">Email</div>
                        <input autocomplete="off" type="text" class="input" name="email" required placeholder="Please enter your Email">
                        <div class="font font2 ">Password</div>
                        <input autocomplete="off" type="password" name="password" required placeholder="Please enter your Password">
                    </div>

                    <div class="input-wrapper" id="error">${message}</div>

                    <div class="remember-sign-in">
                        <label><input type="checkbox" name="" > Remember Sign In</label>
                    </div>
                    <div class="input-form">
                        <input type="submit" value="Log in">
                    </div>
                    <div class="input-form">
                        <p class="input-form-register">Do not have an account? <a href="view/register.jsp">Register</a></p>
                    </div>
                    <div class="input-form">
                        <p>Do not remember password? <a href="view/forgetPassword.jsp">Forget Password</a></p>
                    </div>
                </form>
            </div>
        </div>
        <!--Kết Thúc Phần Nội Dung-->
    </section>
    <script src="./assets/js/login.js"></script>

</body>
</html>
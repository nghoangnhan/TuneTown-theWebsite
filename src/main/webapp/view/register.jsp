<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
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
    <link rel="stylesheet" href="../font.css" />
    <link rel="stylesheet" href="../assets/css/register.css" />
    <link rel="stylesheet" href="../assets/css/reset.css" />
    <title>Register</title>
    <link
      rel="apple-touch-icon"
      sizes="180x180"
      href="../assets/img/favicon/apple-touch-icon.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="32x32"
      href="../assets/img/favicon/favicon-32x32.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="16x16"
      href="../assets/img/favicon/favicon-16x16.png"
    />
    <link rel="manifest" href="../assets/img/favicon/site.webmanifest" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  </head>

  <body>
    <section>
      <div class="content">
        <!--Bắt Đầu Phần Nội Dung-->
        <div class="content-form">
          <div class="logo">
            <a href="home.html"
              ><img src="../assets/img/logo/logo.png" alt=""
            /></a>
          </div>
          <div class="form">
            <form
              class="login_form"
              action="register"
              method="post"
              name="form"
            >
              <div class="input-wrapper">
                <div class="font">Username</div>
                <input
                  autocomplete="off"
                  type="text"
                  class="input"
                  name="username"
                  id="username"
                  placeholder="Please enter your Username"
                />
                <div id="error1">Username existed!</div>
                <div class="font font2">Email</div>
                <input
                  autocomplete="off"
                  type="text"
                  class="input"
                  name="email"
                  id="email"
                  placeholder="Please enter your Email"
                />
                <div id="error2">Wrong email format!</div>
                <div class="font font2">Password</div>
                <input
                  id="password"
                  type="password"
                  name="password"
                  placeholder="Please enter your password"
                />
                <div class="font font2">Re-enter your Password</div>
                <input
                  id="confirmPassword"
                  type="password"
                  name="rePassword"
                  placeholder="Please re-enter your password"
                />
                <div id="error3">Password does not match!</div>
              </div>
              <div class="input-form">
                <input type="submit" value="Sign up" />
              </div>
              <div id="message">${message}</div>
              <div class="input-form">
                <p class="input-form-register">
                  Have an account?
                  <a href="/">Login</a>
                </p>
              </div>
              <div class="input-form">
                <p>
                  Do not remember password?
                  <a href="/view/forgetPassword.jsp">Forgot Password</a>
                </p>
              </div>
            </form>
          </div>
        </div>
        <!--Kết Thúc Phần Nội Dung-->
        <!--Bắt Đầu Phần Hình Ảnh-->
        <div class="img-bg">
          <img
            src="https://images.unsplash.com/photo-1508700115892-45ecd05ae2ad?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80"
            alt="Hình Ảnh Minh Họa"
          />
        </div>
        <!--Kết Thúc Phần Hình Ảnh-->
      </div>
    </section>
  </body>
  <script src="../assets/js/register.js"></script>
</html>

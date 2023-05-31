<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" href="../assets/css/reset.css" />
    <link rel="stylesheet" href="../assets/css/forgetPassword.css" />
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
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <title>Forgot Password</title>
</head>
<body>
<div class="content">
    <div class="content-form">
        <div class="logo">
            <a href="home.html"
            ><img src="../assets/img/logo/logo.png" alt=""
            /></a>
        </div>
        <div>
            <h1 class="title-form">Forget Password</h1>
        </div>
        <form id="otpForm" action="OTP" method="post">
            <div class="form-otp">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">Email</span>
                    </div>
                    <div class="input-box">
                        <input
                                id="input-email"
                                type="text"
                                placeholder="Enter your email"
                                name="email"
                                value="${email}"
                                required
                        />
                    </div>

                    <div class="notifi-wrapper" id="notifi-otp">
                        <h3 class="notifi otp">${message}</h3>
                    </div>
                    <div class="button">
                        <input type="submit" value="Get OTP" />
                    </div>
                </div>
            </div>
        </form>
        <form id="passwordForm" method="post" action="forgetPassword">
            <div class="form-password">
                <div class="input-box">
                    <input type="hidden" id="hidden-email" name="email" value="${email}" />
                </div>
                <div class="input-box">
                    <span class="details">OTP code</span>
                </div>
                <div class="input-box">
                    <input
                            id="input-OTP"
                            type="text"
                            placeholder="Enter your OTP code"
                            name="OTPcode"
                    />
                </div>

                <div class="notifi-wrapper" id="notifi-pass">
                    <h3 class="notifi pass">
                        ${message1}
                        <span id="pass-show">${message2}</span>
                    </h3>
                </div>
                <div class="button">
                    <input type="submit" value="Get Password" />
                </div>
            </div>
        </form>
        <div class="input-form">
            <p>Return To Login <a href="/">Return</a></p>
        </div>
    </div>
</div>
</body>
</html>

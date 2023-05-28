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
  <title>Change Password</title>
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
      <h1 class="title-form">Change Password</h1>
    </div>
    <form id="changePasswordForm" method="post" action="changePassword">
      <div class="form-password">
        <div class="input-box">
          <input
                  id="old-password"
                  type="text"
                  placeholder="Enter your old password"
                  name="oldPassword"
          />
        </div>
        <div class="input-box">
          <input
                  id="newPassword"
                  type="password"
                  placeholder="Enter your new password"
                  name="newPassword"
          />
        </div>
        <div class="input-box">
          <input
                  id="reNewPassword"
                  type="password"
                  placeholder="Re-Enter your new password"
                  name="reNewPassword"
          />
        </div>
        <div id="error">New password does not match!</div>
        <div class="notifi-wrapper" id="notifi-pass">
          <h3 class="notifi pass">
            <span id="pass-show">${message}</span>
          </h3>
        </div>
        <div class="button">
          <input id="bt-changePassword" type="submit" value="Change Password" />
        </div>
      </div>
    </form>
    <div class="input-form">
      <p>Return To Homepage <a href="/TuneTown_theWebsite_war_exploded/loadSong">Return</a></p>
    </div>
  </div>
</div>
</body>
<script src="../assets/js/changePassword.js"></script>
</html>

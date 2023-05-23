<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./assets/css/reset.css" />
    <link rel="stylesheet" href="./assets/css/UpSong.css" />
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
    <title>Upload Song</title>
  </head>
  <body> 
    <!-- SLIDER  -->
    <div class="wrap-slider">
      <div class="slider">
        <!-- <img src="./assets/img/slider/2dia.jpg" alt="slider"> -->
      </div>
    </div>
    <!-- END SLIDER  -->
      <!-- FORM  -->
    <div class="content-form">
        <!-- title form  -->
        <div class="wrap-header-form">
          <h1 class="header-form">Upload Song</h1>
        </div>
      <div class="wrap-form">
        <form action="upload" method="post" enctype="multipart/form-data">
          <h1 class="input_title">Song Name</h1>
          <input class ="input-song-name" name="songName" type="text" placeholder="Your song name" required/>

          <h1 class="input_title">Song Image</h1>
          <div class="input-file-container">  
            <input class="input-btn" name="songImage" type="file" placeholder="Input PNG,JPG,..." accept="image/png, image/gif, image/jpeg" onclick="uploadImage()" required/>
          </div>

          <div class="input_title">Song</div>
            <div class="input-file-container">   
                       
              <input class="input-btn" name="songData" type="file" placeholder="Input mp3,mp4..." accept=".mp4,.mp3,audio/*" onclick="uploadImage()" required/>
            </div>
          <div class="submit-btn">
            <input type="submit"  />
          </div>
      </div>
      </form>
    </div>
    <!-- end title form  -->
<!--    Firebase config-->
    <script src="https://www.gstatic.com/firebasejs/9.20.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/9.20.0/firebase-storage.js"></script>
    <script src="./assets/js/firebase.js></script>

  </body>
</html>
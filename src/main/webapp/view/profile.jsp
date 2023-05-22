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
  <link rel="stylesheet" href="./assets/css/reset.css" />
  <link rel="stylesheet" href="./assets/css/profile.css" />
  <link
          rel="apple-touch-icon"
          sizes="180x180"
          href="./assets/img/favicon/apple-touch-icon.png"
  />
  <link
          rel="icon"
          type="image/png"
          sizes="32x32"
          href="./assets/img/favicon/favicon-32x32.png"
  />
  <link
          rel="icon"
          type="image/png"
          sizes="16x16"
          href="./assets/img/favicon/favicon-16x16.png"
  />
  <link rel="manifest" href="./assets/img/favicon/site.webmanifest" />
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
  />
  <title>Profile</title>
</head>
<body>
<div class="full-body">
  <!-- MUSIC INTERACT  -->
  <div class="wrap-music-nav">
    <!-- THANH BÊN PLAYLIST  -->
    <div class="music-nav">
      <div class="list-tool">
        <div class="wrap-nav-item">
          <a href="loadSong">
            <div class="nav-item home is-active">
              <i class="fa fa-home"></i>Home
            </div>
          </a>

          <div class="nav-item-profile">
            <div class="wrap-option-profile">
              <img
                      id="avatar-profile"
                      src="${avatar}"
                      alt="avatar"
              />
              <ul id="option-profile">
                <li><a href="">Profile</a></li>
                <li><a href="logOut">Logout</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- END THANH BÊN PLAYLIST  -->

  <!-- WRAP FORM  -->
  <form action="modifyProfile" method="post" enctype="multipart/form-data">
    <div class="updateInfor-form">
      <div class="wrapper">
        <div class="title">Update Informations</div>
        <div class="form">
          <div class="inputfield">
            <label>Username</label>
            <input value="${user.getUserName()}" name="username" type="text" class="input" />
          </div>
          <div class="inputfield">
            <label>Password</label>
            <input value="${user.getUserPassword()}" name="password" type="password" class="input" />
          </div>
          <div class="inputfield">
            <label>Birthday</label>
            <input value="${user.getBirthDate()}" name="birthdate" type="date" class="input" />
          </div>
          <div class="inputfield">
            <label>Gender</label>
            <div class="custom_select" name="gender">
              <select>
                <option value="0">Male</option>
                <option value="1">Female</option>
              </select>
            </div>
          </div>
          <div class="inputfield">
            <label>Email Address</label>
            <input value="${user.getEmail()}" name="email" type="text" class="input" />
          </div>
          <div class="inputfield">
            <label>Bio</label>
            <input value="${user.getUserBio()}" name="userBio" class="textarea" />
          </div>
          <div class="input-file-container">
            <label>Avatar</label>
            <input class="input-btn" name="userAvatar" type="file" placeholder="Input PNG,JPG,..." accept="image/png, image/gif, image/jpeg" onclick="uploadImage()" required/>
          </div>
          <div class="inputfield">
            <input type="submit" value="Update" class="btn" />
          </div>
        </div>
      </div>
    </div>
  </form>
  <!-- END FORM  -->

  <!-- HISTORY -->
  <!-- VÙNG FEED NHẠC  -->
  <div class="music-history">
    <!-- Song Popular -->
    <div class="title-song">History<i class="fa fa-history"></i></div>
    <div class="wrap-song-item">
      <div class="song-item nohover">
        <div class="song-ranking-nohover">#</div>
        <div class="song-info">
          <div class="song-info-title">Title</div>
        </div>
        <div class="song-genre-nohover">Genre</div>
        <div class="song-duration-nohover">Duration</div>
      </div>

      <div class="song-item">
        <div class="song-ranking">1</div>
        <div class="song-img">
          <img src="./assets/img/CoverArt/starboy.jpg" alt="" />
        </div>
        <div class="song-info">
          <div class="song-info-title">Starboy</div>
          <div class="song-info-author">The Weekend</div>
        </div>
        <div class="song-genre">Pop</div>
        <div class="song-duration">3:48</div>
      </div>

      <div class="song-item">
        <div class="song-ranking">2</div>
        <div class="song-img">
          <img src="./assets/img/CoverArt/starboy.jpg" alt="" />
        </div>
        <div class="song-info">
          <div class="song-info-title">Starboy</div>
          <div class="song-info-author">The Weekend</div>
        </div>
        <div class="song-genre">Pop</div>
        <div class="song-duration">3:48</div>
      </div>

      <div class="song-item">
        <div class="song-ranking">3</div>
        <div class="song-img">
          <img src="./assets/img/CoverArt/starboy.jpg" alt="" />
        </div>
        <div class="song-info">
          <div class="song-info-title">Starboy</div>
          <div class="song-info-author">The Weekend</div>
        </div>
        <div class="song-genre">Pop</div>
        <div class="song-duration">3:48</div>
      </div>

      <div class="song-item">
        <div class="song-ranking">4</div>
        <div class="song-img">
          <img src="./assets/img/CoverArt/starboy.jpg" alt="" />
        </div>
        <div class="song-info">
          <div class="song-info-title">Starboy</div>
          <div class="song-info-author">The Weekend</div>
        </div>
        <div class="song-genre">Pop</div>
        <div class="song-duration">3:48</div>
      </div>

      <div class="song-item">
        <div class="song-ranking">5</div>
        <div class="song-img">
          <img src="./assets/img/CoverArt/starboy.jpg" alt="" />
        </div>
        <div class="song-info">
          <div class="song-info-title">Starboy</div>
          <div class="song-info-author">The Weekend</div>
        </div>
        <div class="song-genre">Pop</div>
        <div class="song-duration">3:48</div>
      </div>

      <div class="song-item">
        <div class="song-ranking">6</div>
        <div class="song-img">
          <img src="./assets/img/CoverArt/starboy.jpg" alt="" />
        </div>
        <div class="song-info">
          <div class="song-info-title">Starboy</div>
          <div class="song-info-author">The Weekend</div>
        </div>
        <div class="song-genre">Pop</div>
        <div class="song-duration">3:48</div>
      </div>
    </div>
    <!-- End Song Popular -->
  </div>
  <!-- END HISTORY -->

  <!-- MUSIC GENRE  -->
  <div class="wrap-music-genre">
    <div class="title-song">
      Music genre<i class="fa fa-headphones"></i>
    </div>
    <div class="choose-music-genre">
      <div class="music-genre">
        <input type="checkbox" id="popgenre" name="" />
        <label for="popgenre"><span></span>Pop</label>

        <input type="checkbox" id="bolerogenre" name="" />
        <label for="bolerogenre"><span></span>Bolero</label>

        <input type="checkbox" id="edmgenre" name="" />
        <label for="edmgenre"><span></span>EDM</label>

        <input type="checkbox" id="rockrollgenre" name="" />
        <label for="rockrollgenre"><span></span>Rock&Roll</label>

        <input type="checkbox" id="jazzgenre" name="" />
        <label for="jazzgenre"><span></span>Jazz</label>

        <input type="checkbox" id="countrygenre" name="" />
        <label for="countrygenre"><span></span>Country</label>

        <input type="checkbox" id="discogenre" name="" />
        <label for="discogenre"><span></span>Disco</label>

        <input type="checkbox" id="rapgenre" name="" />
        <label for="rapgenre"><span></span>Rap</label>

        <input type="checkbox" id="vpopgenre" name="" />
        <label for="vpopgenre"><span></span>V-Pop</label>

        <input type="checkbox" id="kpopgenre" name="" />
        <label for="kpopgenre"><span></span>K-Pop</label>

        <input type="checkbox" id="othergenre" name="" />
        <label for="othergenre"><span></span>Other</label>
      </div>
    </div>
  </div>
  <!-- END MUSIC GENRE  -->
</div>
</body>
<script src="./assets/js/home.js"></script>
</html>

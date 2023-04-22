<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <link rel="stylesheet" href="./assets/css/home.css" />
  <link rel="stylesheet" href="./assets/css/reset.css" />
  <title>TuneTown</title>
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
</head>
<body>
<div class="full-body">
  <!-- MUSIC INTERACT  -->
  <div class="wrap-music-nav">
    <!-- THANH BÊN PLAYLIST  -->
    <div class="music-nav">
      <div class="list-tool">
        <div class="wrap-nav-item">
          <div class="nav-item home is-active">
            <i class="fa fa-home"></i>Home
          </div>


          <div class="nav-item text">
            <input name="search-bar" type="text" placeholder="Search" />
            <i class="fa fa-search"></i>
          </div>

          <div class="nav-item-profile">
            <div class="wrap-option-profile">
              <img
                      id="avatar-profile"
                      src="./assets/img/Ava/TT3.jpg"
                      alt="avatar"
              />
              <ul id="option-profile">
                <li><a href="loadProfile" methods="POST">Profile</a></li>
                <li><a href="">Setting</a></li>
                <li><a href="">Logout</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- END THANH BÊN PLAYLIST  -->
  </div>

  <div class="content">
    <!-- VÙNG FEED NHẠC  -->
    <div class="music-feed">
      <!-- Playlist  -->
      <div class="title-playlist">Recommend Playlist</div>
      <div class="wrap-playlist-item">
        <div class="playlist-item">
          <img src="./assets/img/CoverArt/mosaic.jpg" alt="" />
          <div class="playlist-title">Playlist #1</div>
          <div class="playlist-artist">Mosiac</div>
          <div class="iconplay">
            <i class="fa fa-play-circle"></i>
          </div>
        </div>
        <div class="playlist-item">
          <img src="./assets/img/CoverArt/dontoliver.jpg" alt="" />
          <div class="playlist-title">Playlist #2</div>
          <div class="playlist-artist">Don Toliver</div>
          <div class="iconplay">
            <i class="fa fa-play-circle"></i>
          </div>
        </div>
        <div class="playlist-item">
          <img src="./assets/img/CoverArt/lilpump.jpg" alt="" />
          <div class="playlist-title">Playlist #3</div>
          <div class="playlist-artist">Lil Pump</div>
          <div class="iconplay">
            <i class="fa fa-play-circle"></i>
          </div>
        </div>
        <div class="playlist-item">
          <img src="./assets/img/CoverArt/realliife.jpg" alt="" />
          <div class="playlist-title">Playlist #4</div>
          <div class="playlist-artist">Real Lift</div>
          <div class="iconplay">
            <i class="fa fa-play-circle"></i>
          </div>
        </div>
        <div class="playlist-item">
          <img src="./assets/img/CoverArt/sand.jpg" alt="" />
          <div class="playlist-title">Playlist #5</div>
          <div class="playlist-artist">San</div>
          <div class="iconplay">
            <i class="fa fa-play-circle"></i>
          </div>
        </div>
        <div class="playlist-item">
          <img src="./assets/img/CoverArt/william.jpg" alt="" />
          <div class="playlist-title">Playlist #6</div>
          <div class="playlist-artist">William</div>
          <div class="iconplay">
            <i class="fa fa-play-circle"></i>
          </div>
        </div>
      </div>
      <!-- End Playlist  -->
      <!-- Song Popular -->
      <div class="title-song">
        Popular Song <i class="fa fa-line-chart"></i>
      </div>
      <div class="wrap-song-item">
        <div class="song-item nohover">
          <div class="song-ranking-nohover">#</div>
          <div class="song-info">
            <div class="song-info-title">Title</div>
          </div>
          <div class="song-genre-nohover">Genre</div>
          <div class="song-view-nohover">Listens</div>
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
          <div class="song-view">1,234,567</div>
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
          <div class="song-view">1,234,567</div>
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
          <div class="song-view">1,234,567</div>
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
          <div class="song-view">1,234,567</div>
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
          <div class="song-view">1,234,567</div>
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
          <div class="song-view">1,234,567</div>
        </div>
      </div>
      <!-- End Song Popular -->

      <!-- Song Item  -->
      <div class="title-song">Recommend Song</div>
      <div class="wrap-song-item">
        <div class="song-item nohover">
          <div class="song-info">
            <div class="song-info-title">Title</div>
          </div>
          <div class="song-genre-nohover">Genre</div>
          <div class="song-view-nohover">Listens</div>
        </div>

          <c:forEach items="${listSong}" var="song" varStatus="status">
            <c:if test="${status.index < 10}">
              <div class="song-item" id="song-item-${song.getSongId()}" >
                <div class="song-img">
                  <img id="${song.getSongId()}" src="data:image/jpg;base64,${song.getSongPoster()}" alt="" onclick="moveToControlBar(this)"/>
                  <div id="song-data-${song.getSongId()}" hidden="hidden">${song.getSongData()}</div>
                </div>
                <div class="song-info">
                  <div id ="song-title-${song.getSongId()}" class="song-info-title">${song.getSongName()}</div>
                  <div class="song-info-author">
                    <c:forEach items="${song.getArtistList()}" var="o">
                      ${o.getUserName()}
                    </c:forEach>
                  </div>
                </div>
                <div class="song-genre">Pop</div>
                <div class="song-view">1,000,000</div>
              </div>
            </c:if>
          </c:forEach>

        <!-- End Song Item  -->
        </div>

      </div>
      <!-- End Song Item  -->
    </div>
    <!-- END VÙNG FEED NHẠC -->
    <div class="music-playlist">
      <div class="my-library">
        <i class="fa fa-bookmark"></i>
        <div>Library</div>
      </div>
      <div class="myplaylist-title">My Playlist</div>
      <div class="wrap-my-playlist">
        <div class="my-playlist-item">My Playlist #1</div>
        <div class="my-playlist-item">My Playlist #2</div>
        <div class="my-playlist-item">My Playlist #3</div>
        <div class="my-playlist-item">My Playlist #4</div>
        <div class="my-playlist-item">My Playlist #5</div>
        <div class="my-playlist-item">My Playlist #6</div>
        <div class="my-playlist-item">My Playlist #7</div>
        <div class="my-playlist-item">My Playlist #8</div>
      </div>
    </div>
  </div>
  <!-- END MUSIC INTERACT  -->

  <!-- THANH BAR CONTROL NHẠC  -->
  <div class="music-control-bar">
    <div class="wrap-songing-item">
      <div class="songing-image">
        <img id="songing-image" src="./assets/img/CoverArt/starboy.jpg" alt="" />
      </div>
      <div class="songing-info">
        <div id ="songing-info-title" class="songing-info-title">Starboy</div>
        <div class="songing-info-author">The Weekend</div>
      </div>

      <div class="songing-control">
        <div class="control-btn">
          <i class="fa fa-step-backward fa-2x"></i>
          <i id="fa-pause-circle" class="fa fa-pause-circle fa-2x" onclick="playAudio()">
            <audio id="audio" controls hidden="hidden" src = "" type="audio/mpeg"> </audio>
          </i>
            <i id="fa-play-circle" class="fa fa-play-circle active" onclick="playAudio()"></i>
          <i class="fa fa-step-forward fa-2x"></i>
        </div>
        <div class="control-timeline">
          <div id="start-timeline"><h4>0:00</h4></div>
          <input
                  placeholder="."
                  type="range"
                  min="1"
                  max="100"
                  value="1"
                  class="slider-timeline"
                  id="slider-timeline"
          />
          <div class="seekbar"></div>
          <div id="end-timeline"><h4 id="end-duration" >3:40</h4></div>
        </div>
      </div>
      <div class="songing-volume">
        <i class="fa fa-volume-down"></i>
        <input
                placeholder="."
                type="range"
                min="1"
                max="100"
                value="1"
                class="slider-volume"
                id="slider-volume"
        />
        <i class="fa fa-volume-up"></i>
      </div>
    </div>
  </div>
  <!-- END THANH BAR CONTROL NHẠC  -->
</div>
</body>
<footer></footer>
<script src="./assets/js/home.js"></script>
</html>

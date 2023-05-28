<%@ page import="vn.hcmute.tunetown.DAO.UserDAO" %>
<%@ page import="vn.hcmute.tunetown.model.User" %>
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
  <link rel="stylesheet" href="./assets/css/profile.css" />
  <link rel="stylesheet" href="./assets/css/UpSong.css" />
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
            <i class="fa fa-home" id="bt-home" onclick="handleBackHomeClick()"> Home</i>
          </div>
          <div class="nav-item upload">
            <i class="fa fa-star" id="bt-upload" onclick="upSong()"> Upload Product</i>
          </div>


            <!-- THANH SEARCH  -->
            <div class="nav-item text">
              <div class="search-container">
                <input
                id="search-content"
                name="search-bar"
                type="text"
                placeholder="Search"
                oninput="findSongs(this.value)"
                />
                <div id="search-results" class="dropdown"></div>
              </div>
              <i class="fa fa-search"></i>
            </div>

          <div class="nav-item info">
            <div class="info">Hello ${username}</div>
          </div>

          <div class="nav-item-profile">
            <div class="wrap-option-profile">
              <img
                      id="avatar-profile"
                      src="${avatar}"
                      alt="avatar"
              />
              <ul id="option-profile">
                <li><a id="bt-profile" onclick="loadUser()" methods="get">Profile</a></li>
                <li><a href="logOut">Logout</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- END THANH BÊN PLAYLIST  -->
  </div>

  <div class="content" id="content">

    <div id="user-feed" class="user-feed">
      <input type="hidden" value="${userId}" name="userId" id="user-id">

    </div>

    <div id="upload-feed" class="upload-feed">
<%--      <input type="hidden" value="${userId}" name="userId" id="user-id">--%>

    </div>


    <!-- VÙNG FEED NHẠC  -->
    <div class="music-feed active" id="music-feed">
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
      <div class="wrap-song-item" id="home-song">
        <div class="song-item nohover">
          <div class="song-info">
            <div class="song-info-title">Title</div>
          </div>
          <div class="song-genre-nohover">Genre</div>
          <div class="song-view-nohover">Listens</div>
        </div>

        <c:forEach items="${listSong}" var="song" varStatus="status">
          <c:if test="${status.index < 100}">
            <div class="song-item" id="song-item-${song.getSongId()}">
              <div class="song-img">
                <img id="${song.getSongId()}" src="./assets/img/CoverArt/starboy.jpg" alt="" onclick="playSong(this)" />
                <div id="song-data-${song.getSongId()}" hidden="hidden">${song.getSongData()}</div>
              </div>
              <div class="song-info" id="info-song">
                <div id="song-title-${song.getSongId()}" class="song-info-title">${song.getSongName()}</div>
                <div class="song-info-author" id="song-info-author-${song.getSongId()}">${song.getArtists().getUserName()}</div>
              </div>
              <div class="song-genre">${song.getGenre().getGenreName()}</div>
              <div class="song-view">${song.getAmountOfListens()}</div>
            </div>
          </c:if>
        </c:forEach>
      </div>

    </div>
    <!-- End Song Item  -->

    <!-- TAB PLAYLIST  -->
    <div id="playlist-feed" class="playlist-feed">
      <div class="wrap-btn">
        <div class="wrap-button-back-home">
          <button id="btn-back-home" class="btn-back-home">
            <i class="fa fa-chevron-circle-left"></i>Back
          </button>
        </div>

        <div class="wrap-button-delete">
          <button id="btn-delete" class="btn-delete">Delete</button>
        </div>

        <div class="wrap-button-setPersonal">
          <button id="btn-setPersonal" class="btn-setPersonal" onclick="setPersonalPlaylist()">To personal</button>
        </div>

      </div>
      <%--      Notification Cofirm--%>
      <div id="delete-modal" class="delete-modal">
        <div class="delete-modal-content">
          <h3>Confirm Delete</h3>
          <p>Are you sure you want to delete?</p>
          <div class="modal-buttons">
            <button id="confirm-delete-btn">Delete</button>
            <button id="cancel-delete-btn">Cancel</button>
          </div>
        </div>
      </div>
      <div class="title-playlist-feed" id="title-playlist-feed" name="playlistName" ></div>
      <div class="wrap-song-item">
        <div class="song-item nohover">
          <div class="song-ranking-nohover">#</div>
          <div class="song-info">
            <div class="song-info-title">Title</div>
          </div>
          <div class="song-genre-nohover">Genre</div>
          <div class="song-view-nohover">Views</div>
        </div>

        <div class="list-song-item" id="playlist-songs">
<%--          songs in here--%>


        </div>
      </div>
    </div>
    <!-- END TAB PLAYLIST  -->

    <div class="music-playlist">
      <div class="my-library">
        <i class="fa fa-bookmark"></i>
        <div>Library</div>
      </div>
      <div class="wrap-create-playlist">
          <button id="btn-create-playlist" onclick="createPlaylist()" >
            <i class="fa fa-plus-square">Create Playlist</i>
          </button>
        <button id="btn-suggest-playlist" onclick="suggestPlaylist()" >
          <i class="fa fa-plus-square">Suggest Playlist</i>
        </button>
      </div>

      <div class="myplaylist-title">My Playlist</div>
      <div class="wrap-my-playlist" id = "my-playlist">

        <c:forEach items="${listPlaylist}" var = "playlist">

          <div class="my-playlist-item"
               id="playlist-id-${playlist.getPlaylistId()}" onclick="loadPlaylistSongs(this)">
              ${playlist.getPlaylistName()}
          </div>

        </c:forEach>

      </div>
      </div>
    </div>
    </div>
    <!-- END VÙNG FEED NHẠC -->

  </div>
  <!-- END MUSIC INTERACT  -->

  <!-- THANH BAR CONTROL NHẠC  -->
  <div class="music-control-bar">
    <div class="wrap-songing-item">
      <div class="songing-image">
        <img id="songing-image" src="./assets/img/CoverArt/starboy.jpg" alt="" />
      </div>
      <div class="songing-info">
        <div id ="songing-info-title" class="songing-info-title">None</div>
        <div id="songing-info-author" class="songing-info-author">None</div>
      </div>

      <div class="songing-control">
        <div class="control-btn">
            <i id="fa-random" class="fa fa-random"></i>
          <i id="fa-backward" class="fa fa-step-backward fa-2x" onclick="backward()"></i>
          <i id="fa-pause-circle" class="fa fa-pause-circle fa-2x" onclick="playAudio()">
            <audio id="audio" controls hidden="hidden" src = "" type="audio/mpeg"> </audio>
          </i>
            <i id="fa-play-circle" class="fa fa-play-circle active" onclick="playAudio()"></i>
          <i id="fa-forward" class="fa fa-step-forward fa-2x" onclick="forward()"></i>
            <i id="fa-retweet" class="fa fa-retweet"></i>
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
                value="100"
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
<script src="./assets/js/controller.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</html>

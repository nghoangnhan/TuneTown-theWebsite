const navItems = document.querySelectorAll(".nav-item");
const navItem = document.querySelector(".nav-item");
[...navItems].forEach((item) => item.addEventListener("click", handleTabclick));
function handleTabclick(event) {
  [...navItems].forEach((item) => item.classList.remove("is-active"));
  event.target.classList.add("is-active");
  console.log(event.target);
}

const ava = document.getElementById("avatar-profile");
const optionPro = document.getElementById("option-profile");
ava.addEventListener("click", clickAva);
function clickAva(event) {
  console.log("click");
  optionPro.classList.toggle("active");
}

const playbtn = document.getElementById("fa-play-circle");
const stopbtn = document.getElementById("fa-pause-circle");
playbtn.addEventListener("click", PlayStopMusic);
stopbtn.addEventListener("click", PlayStopMusic);
console.log(playbtn.className == "fa-play-circle");

function PlayStopMusic() {
  playbtn.classList.toggle("active");
  stopbtn.classList.toggle("active");
}

const audio = document.getElementById("audio");
isPlayed = false;
function playAudio() {
  if (isPlayed == false)
  {
    audio.play();
    isPlayed = true;
  }
  else {
    audio.pause();
    isPlayed = false;
  }
}
const songImage = document.getElementById("songing-image");

let songInfoTitle = document.getElementById("songing-info-title");


const repeatbtn = document.getElementById("fa-retweet");
const randombtn = document.getElementById("fa-random");

function ActiveRandom(event) {
  event.target.classList.toggle("active");
  repeatbtn.classList.remove("active")
}
function ActiveRepeat(event) {
  event.target.classList.toggle("active");
  randombtn.classList.remove("active")
}
function moveToControlBar(get) {
  let id = get.getAttribute('id');
  const data = document.getElementById("song-data-" + id);
  const image = get.getAttribute('src');
  audio.src = deleteLetter(data.innerHTML, "amp;");

  songImage.setAttribute('src', image);

  // Load song's information
  const songTitle = document.getElementById("song-title-" + id).innerHTML;
  songInfoTitle.innerHTML = songTitle;
  audio.load();

  // load Duration onto Control Bar
  audio.addEventListener("loadedmetadata", function() {
    const currentAudio = document.getElementById("audio");
    var duration = currentAudio.duration;
    var currentTime = currentAudio.currentTime;


    // var start = document.getElementById("start-timeline");
    // minute_float = parseFloat(currentTime / 60);
    // minute_int = parseInt(currentTime / 60);
    // second = parseInt((minute_float - minute_int) * 60);
    // start.innerHTML = minute_int + ":" + second;

    var end = document.getElementById("end-duration");
    minute_float = parseFloat(duration / 60);
    minute_int = parseInt(duration / 60);
    second = parseInt((minute_float - minute_int) * 60);
    end.innerHTML = minute_int + ":" + second;
    console.log(end.innerHTML);
    console.log(duration);

    // play the Audio link
    playAudio();
    playbtn.classList.toggle("active");
    stopbtn.classList.toggle("active");
    // handle seekbar
    const slider = document.getElementById('slider-timeline');


    // update slider position based on audio time
    function updateSlider() {
      const duration = currentAudio.duration;
      const currentTime = currentAudio.currentTime;
      const progress = (currentTime / duration) * 100;
      slider.value = progress;

      var start = document.getElementById("start-timeline");
      minute_float = parseFloat(currentTime / 60);
      minute_int = parseInt(currentTime / 60);
      second = parseInt((minute_float - minute_int) * 60);
      start.innerHTML = minute_int + ":" + second.toString().padStart(2, "0");
    }

    // update audio time based on slider position
    function updateAudioTime() {
      const duration = currentAudio.duration;
      const seekTime = (slider.value / 100) * duration;
      currentAudio.currentTime = seekTime;
    }

    // add event listeners
    slider.addEventListener('input', updateAudioTime);
    currentAudio.addEventListener('timeupdate', updateSlider);


    // handle button backward, forward
    const bt_backward = document.getElementById('fa-backward');
    const bt_forward = document.getElementById('fa-forward');

    function playPreviousSong() {
      const currentSong = document.getElementById("song-item-"+id);

      if (currentSong !== null) {
        const previousSong = currentSong.previousElementSibling;

        if (previousSong !== null) {
          currentSong.classList.remove("active");
          previousSong.classList.add("active");
          moveToControlBar(previousSong.querySelector("img"));
        } else {
          // Handle case where there is no previous element sibling
          console.log("No previous song");
        }
      } else {
        // Handle case where there is no active song
        console.log("No active song");
      }
    }
    function playNextSong() {
      const currentSong = document.getElementById("song-item-"+id);

      if (currentSong !== null) {
        const nextSong = currentSong.nextElementSibling;

        if (nextSong !== null) {
          currentSong.classList.remove("active");
          nextSong.classList.add("active");
          moveToControlBar(nextSong.querySelector("img"));
        } else {
          // Handle case where there is no previous element sibling
          console.log("No next song");
        }
      } else {
        // Handle case where there is no active song
        console.log("No active song");
      }
    }

    bt_backward.addEventListener("click", playPreviousSong);
    bt_forward.addEventListener("click", playNextSong);

    // Handle Volume
    const sliderVolume = document.getElementById('slider-volume');

    function setVolume() {
      audio.volume = sliderVolume.value / 100;
    }

    sliderVolume.addEventListener('input', setVolume);


    repeatbtn.addEventListener("click", ActiveRepeat);
    randombtn.addEventListener("click", ActiveRandom);

    // Play random song in homepage
    function playRandomSong() {
      const currentSong = document.getElementById("song-item-"+id);
      // songList.forEach(songItem => {
      if (currentSong !== null) {
        const randomIndex = Math.floor(Math.random() * 10);
        const randomSong = document.getElementById("song-item-"+randomIndex);
        console.log(randomSong);

        if (randomSong !== null && randomSong !== currentSong) {
          currentSong.classList.remove("active");
          randomSong.classList.add("active");
          moveToControlBar(randomSong.querySelector("img"));
        } else {
          // Handle case where there is no previous element sibling
          console.log("No next song");
        }
      } else {
        // Handle case where there is no active song
        console.log("No active song");
      }
      // });
    }

    audio.addEventListener("ended", function() {
      if (repeatbtn.classList.contains("active")) {
        // If repeat button is active, replay the current song
        audio.currentTime = 0;
        audio.play();
      } else if (randombtn.classList.contains("active")) {
        // If random button is active, play a random song
        playRandomSong();
      } else {
        // Otherwise, play the next song in the queue
        playNextSong();
      }
    });
  });
}



function deleteLetter(str, letter) {
  return str.replace(new RegExp(letter, "g"), "");
}

// CONTENT FEED CHANGE EVENT
var currentPlaylistId;
function loadPlaylistSongs(get) {
  let playlistId = get.getAttribute("id");
  const btnBackHome = document.getElementById("btn-back-home");
  const btnPlaylist = document.getElementById(playlistId);
  const musicfeed = document.getElementById("music-feed");
  const playlistfeed = document.getElementById("playlist-feed");
  btnBackHome.addEventListener("click", showMusicFeed(musicfeed, playlistfeed));
  btnPlaylist.addEventListener("click", showPlaylistFeed(musicfeed, playlistfeed));

  playlistId = deleteLetter(playlistId, "playlist-id-");
  console.log(playlistId);
  currentPlaylistId = playlistId;
  getPlaylistSongs();
}

function showMusicFeed(musicfeed, playlistfeed) {
  musicfeed.classList.add("active");
  playlistfeed.classList.remove("active");
}
function showPlaylistFeed(musicfeed, playlistfeed) {
  musicfeed.classList.remove("active");
  playlistfeed.classList.add("active");
}

function getPlaylistSongs() {
  console.log(currentPlaylistId);
    $.ajax({
        url: "/TuneTown_war_exploded/loadPlaylistSongs",
        type: "post",
        dataType: "json",
        data: {
            playlistId : currentPlaylistId
        },
        success: function (data) {

            var listSong = JSON.parse(JSON.stringify(data));

            console.log(listSong);

            var listSongHTML = document.getElementById("playlist-songs");
            listSongHTML.innerHTML = ``;

            listSong.forEach((song) => {

              var listArtists = song.songArtists;
              let artists = "";
              listArtists.forEach((artist) => {
                artists += artist.userName;
              });

              listSongHTML.innerHTML += `
                        <div class="song-item playlisted">
                          <div class="song-ranking">1</div>
                          <div class="song-img">
                            <img src="${song.songPoster}" alt="" />
                          </div>
                          <div class="song-info">
                            <div class="song-info-title">${song.songName}</div>
                            <div class="song-info-author">${artists}</div>
                          </div>
                          <div class="song-genre">Pop</div>
                          <div class="song-view">1,234,567</div>
                        </div>
              `
            });

        },
        error: function (xhr){
            console.log("Error" + xhr.responseText);
        }

    });
}

// // SHOW EDIT OPTION PLAYLIST
// const deleteCheckboxes = document.getElementsByClassName(".delete-checkbox");
// console.log(deleteCheckboxes)
// const editButton = document.getElementById("btn-edit");
// const deleteButton = document.getElementById("btn-delete");
// const cancelButton = document.getElementById("btn-cancel");
// editButton.addEventListener(
//     "click",
//     (showEditOption = () => {
//       deleteButton.classList.toggle("active");
//       cancelButton.classList.toggle("active");
//       [...deleteCheckboxes].forEach((item) => item.classList.toggle("active"));
//     })
// );
// deleteButton.addEventListener(
//     "click",
//     (deleteOption = () => {
//       const checkboxes = document.querySelectorAll(".delete-checkbox:checked");
//       checkboxes.forEach((checkbox) => {
//         const songItem = checkbox.closest(".song-item");
//         songItem.remove();
//       });
//     })
// );
// cancelButton.addEventListener(
//     "click",
//     (removeOption = () => {
//       deleteButton.classList.remove("active");
//       cancelButton.classList.remove("active");
//       [...deleteCheckboxes].forEach((item) => item.classList.remove("active"));
//     })
// );

// RIGHT CLICK SONG ITEM
// Show contextMenu
const songItems = document.querySelectorAll(".song-item");
let activeContextMenu = null;
songItems.forEach((songItem) => {
  songItem.addEventListener("contextmenu", showContextMenu);
});

let playlists;




function showContextMenu(event) {
  event.preventDefault(); // Ngăn chặn hiển thị menu chuột phải mặc định của trình duyệt

  const songItem = event.target.closest(".song-item");
  const songItems = document.querySelectorAll(".song-item");
  songItem.classList.add("active");

  // Xóa context menu cũ nếu nó tồn tại
  if (activeContextMenu) {
    activeContextMenu.remove();
  }
  if (songItem.classList.contains("active")) {
    songItems.forEach((item) => item.classList.remove("active"));
  }

  let playlistDropdownHTML = ``;
  loadPLaylists();
  console.log(playlists);
  playlists.forEach((playlist) => {
    playlistDropdownHTML += `
           <li class="my-playlist" id="my-playlist-${playlist.playlistId}">${playlist.playlistName}</li>
      `;
  });


  const contextMenu = document.createElement("div");
  contextMenu.className = "context-menu";
  contextMenu.innerHTML = `
    <ul>
      <li id="context-menu-option">
        <ul class="add-to-playlist">Add to Playlist<i class="fa fa-chevron-right"></i>
            <ul class="playlist-dropdown" id="playlist-dropdown-id">   
                ${playlistDropdownHTML}       
            </ul>
        </ul>
      </li>   
      <li>Share</li>
      <li>Copy Link</li>
      
    </ul>
  `;





  const songItemRect = songItem.getBoundingClientRect();
  contextMenu.style.top = `${event.clientY - songItemRect.top}px`;
  contextMenu.style.left = `${event.clientX - songItemRect.left}px`;
  songItem.appendChild(contextMenu);
  activeContextMenu = contextMenu; // Cập nhật context menu đang hiển thị
  songItem.classList.add("active");
  document.addEventListener("click", function hideContextMenu() {
    if (activeContextMenu === contextMenu) {
      activeContextMenu = null; // Gán giá trị null nếu context menu được đóng bằng cách click vào một playlist item
      songItem.classList.remove("active");
    }
    document.removeEventListener("click", hideContextMenu);
    // songItem.classList.remove("active");
    contextMenu.remove();
  });

  // SHOW PLAYLIST OPTION
  const addToPlaylistItem = contextMenu.querySelector(".add-to-playlist");
  const playlistDropdown = document.querySelector(".playlist-dropdown");
  const playlistItems = document.querySelectorAll(".my-playlist");
  let isDropdownShown = false; // Biến để kiểm tra xem dropdown đã được hiển thị chưa

  addToPlaylistItem.addEventListener("mouseenter", showPlaylistDropdown);
  // addToPlaylistItem.addEventListener("mouseout", closePlaylistDropdown);
  // playlistDropdown.addEventListener("mouseover", showPlaylistDropdown);
  playlistDropdown.addEventListener("mouseout", closePlaylistDropdown);

  playlistItems.forEach((playlistItem) => {
    playlistItem.addEventListener("click", closePlaylistDropdown);
  });

  function closePlaylistDropdown(event) {
    if (
        !addToPlaylistItem.contains(event.relatedTarget) &&
        !playlistDropdown.contains(event.relatedTarget)
    ) {
      playlistDropdown.style.display = "none";
    }
  }
  function showPlaylistDropdown() {
    console.log("Show playlist dropdown");
    playlistDropdown.style.display = "block";

    isDropdownShown = true; // Đặt biến isDropdownShown thành true khi dropdown được hiển thị
    if (isDropdownShown) songItem.classList.add("active");
  }


  // rightClickedSongId = deleteLetter(rightClickedSongId, "song-item-");
  // console.log(rightClickedSongId);

  playlistItems.forEach((playlistItem) => {
    // playlistItem.addEventListener("click", addSongToPlaylist(rightClickedSongId));
    playlistItem.addEventListener("click",getPlaylistId=(e)=>{
      const playlistID =e.target.getAttribute("id");
      let rightClickedSongId = event.target.getAttribute("id");
      rightClickedSongId = deleteLetter(rightClickedSongId, "song-item-");
      console.log("rightclickedSongId:" + rightClickedSongId);
      addSongToPlaylist(rightClickedSongId, playlistID);
    })
  });

}

function loadPLaylists(){
  $.ajax({
    url: "/TuneTown_war_exploded/loadPlaylists",
    type: "post",
    dataType: "json",
    success: function (data) {

      //console.log(data);
      var jsonData = JSON.parse(JSON.stringify(data));

      playlists = jsonData;

    },
    error: function (xhr){
      console.log("Error: " + xhr.responseText);
    }
  });
}

function addSongToPlaylist(rightClickedSongId, playlistId) {

  playlistId = deleteLetter(playlistId, "my-playlist-");

  $.ajax({
    url: "/TuneTown_war_exploded/addSongToPlaylist",
    type: "post",
    data: {
      songId : rightClickedSongId,
      playlistId: playlistId
    },
    dataType: "json",
    success: function () {
      console.log("Add successfully");
    },
    error: function (xhr){
      console.log("Error: " + xhr.responseText);
    }
  });
}


//DELETE SONG ITEM TRONG PLAYLIST
// Show contextMenu Trong Playlist
const songItemsPlaylist = document.querySelectorAll(".song-item.playlisted");
console.log(songItemsPlaylist);
songItemsPlaylist.forEach((songItemPlaylist) => {
  songItemPlaylist.addEventListener("contextmenu", showContextMenuPlaylist = (event) => {
    event.preventDefault(); // Ngăn chặn hiển thị menu chuột phải mặc định của trình duyệt
    const songItem = event.target.closest(".song-item");
    const songItems = document.querySelectorAll(".song-item");
    songItem.classList.add("active");

    // Xóa context menu cũ nếu nó tồn tại
    if (activeContextMenu) {
      activeContextMenu.remove();
    }
    if (songItem.classList.contains("active")) {
      songItems.forEach((item) => item.classList.remove("active"));
    }
    // Tạo context menu mới
    const contextMenu = document.createElement("div");
    contextMenu.className = "context-menu";
    contextMenu.innerHTML = `
        <ul>  
          <li>Share</li>
          <li>Copy Link</li>
          <li class="remove-SongItem-playlist">Remove from Playlist</li>
        </ul>
      `;
    // Lấy vị trí hiển thị của context menu
    const songItemRect = songItem.getBoundingClientRect();
    contextMenu.style.top = `${event.clientY - songItemRect.top}px`;
    contextMenu.style.left = `${event.clientX - songItemRect.left}px`;

    // Thêm contextmenu vào SongItem
    songItem.appendChild(contextMenu);
    activeContextMenu = contextMenu; // Cập nhật trạng thái context menu đang hiển thị
    songItem.classList.add("active");
    document.addEventListener("click", function hideContextMenu() {
      // Đóng context menu khi click vào bất kỳ đâu trên trang
      if (activeContextMenu === contextMenu) {
        activeContextMenu = null; // Gán giá trị null nếu context menu được đóng bằng cách click vào một playlist item
        songItem.classList.remove("active");
      }
      document.removeEventListener("click", hideContextMenu);
      // songItem.classList.remove("active");
      contextMenu.remove();
    });

    // REMOVE SONG ITEM FROM PLAYLIST
    const removeSongItemPlaylist = contextMenu.querySelector(
        ".remove-SongItem-playlist"
    );
    removeSongItemPlaylist.addEventListener("click", removeSongItemPlaylistFunc);

    function removeSongItemPlaylistFunc() {
      songItem.remove();
    }
  });
});

// // Find Songs
function findSongs(searchQuery) {
  var listSongHTML = document.getElementById("home-song");
  var songItems = listSongHTML.getElementsByClassName("song-item");

  var dropdown = document.getElementById("search-results");
  dropdown.innerHTML = ""; // Clear previous results

  for (var i = 0; i < songItems.length; i++) {
    var songTitleElement = songItems[i].querySelector(".song-info-title");
    var songTitle = songTitleElement.textContent.toLowerCase();

    if (songTitle.includes(searchQuery.toLowerCase())) {
      var songItemClone = songItems[i].cloneNode(true);// Resize each item within the dropdown
      // Resize the image within the cloned song item
      var songImg = songItemClone.querySelector(".song-img img");
      songImg.style.width = "50px"; // Adjust the desired width
      songImg.style.height = "50px"; // Adjust the desired height

      songItemClone.addEventListener("click", function () {
        var searchContent = document.getElementById("search-content");
        searchContent.value = this.querySelector(".song-info-title").textContent; // Set search bar value to song title
        dropdown.innerHTML = ""; // Clear the dropdown after selection
      });
      dropdown.appendChild(songItemClone); // Append the cloned item to the dropdown
    }
  }

  if (dropdown.innerHTML === "") {
    dropdown.style.display = "none"; // Hide the dropdown if no results
  } else {
    dropdown.style.display = "block"; // Show the dropdown with results
  }
}


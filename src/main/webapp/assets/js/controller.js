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

function createPlaylist() {
    $.ajax({
        url: "/createPlaylist",
        type: "get",
        success: function () {
            loadPLaylist();
            // var listPlaylist = document.getElementById("my-playlist");
            // listPlaylist.innerHTML = data; // data: HTML / json
        },
        error: function (xhr){
            console.log("Error: " + xhr.responseText);
        }
    });
}

function loadPLaylist(){
    $.ajax({
        url: "/loadPlaylists",
        type: "post",
        dataType: "json",
        success: function (data) {
            var jsonData = JSON.parse(JSON.stringify(data));
            let myPlaylistHTML = document.getElementById("my-playlist");
            myPlaylistHTML.innerHTML = ``;

            jsonData.forEach((playlist) => {
                myPlaylistHTML.innerHTML += `
                    <div class="my-playlist-item" id="playlist-id-${playlist.playlistId}" onclick="loadPlaylistSongs(this)">
                        ${playlist.playlistName}
                    </div>
                `;
            });
        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });
}

function deleteLetter(str, letter) {
    return str.replace(new RegExp(letter, "g"), "");
}

function suggestPlaylist() {
    $.ajax({
        url: "/suggestPlaylist",
        type: "get",
        success: function () {
            loadPLaylist();
            getPlaylistSongs();
        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });
}

function recommendPlaylist(genreId) {
    $.ajax({
        url: "/recommendPlaylist",
        type: "post",
        data:{
            genreId: genreId
        },
        success: function (data) {
            var listSong = JSON.parse(JSON.stringify(data.slice(1)));
            var listSongHTML = document.getElementById("playlist-songs");
            listSongHTML.innerHTML = ``;
            var titleHTML = document.getElementById("title-playlist-feed");
            titleHTML.innerHTML = ``;
            titleHTML.innerHTML += `
              <div class="title-playlist-feed" id="title-playlist" name="playlistName">${data[0].playlistName}</div>
          `;

            listSong.forEach((song) => {
                listSongHTML.innerHTML += `
                  <div class="song-item playlisted">
                    <div class="song-img">
                      <img id="${song.songId}" src="${song.songPoster}" alt="" onclick="playSong(this)"/>
                      <div id="song-data-${song.songId}" hidden="hidden">${song.songData}</div>
                    </div>
                    <div class="song-info">
                      <div class="song-info-title">${song.songName}</div>
                      <div class="song-info-author">${song.songArtists}</div>
                    </div>
                    <div class="song-genre">${song.songGenre}</div>
                    <div class="song-view">${song.songAmoutOfListens}</div>
                  </div>
                `;
            });
        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });
}


//const playbtn = document.getElementById("fa-play-circle");
function playSong(currentSong) {
    let songId = currentSong.getAttribute("id");

    $.ajax({
        url: "/playSong",
        type: "get",
        dataType: "json",
        data: {
          songId: songId,
        },
        success: function (data) {
            // Cap nhat music player
            var jsonData = JSON.parse(JSON.stringify(data));
            jsonData = jsonData[0];
            updateControlBar(songId, jsonData.songPoster, jsonData.songName, jsonData.songArtist, jsonData.songData);
            audio.play();
            if(!isPlayed) {
                stopbtn.classList.toggle("active");
                playbtn.classList.toggle("active");
            }
            isPlayed = true;
        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });
}


const audioPlay = document.getElementById("audio");
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

function updateControlBar(songId, poster, songName, songArtist, songData) {
    let songImage = document.getElementById("songing-image");
    let songInfoTitle = document.getElementById("songing-info-title");
    let songInfoAuthor = document.getElementById("songing-info-author");


    songImage.src = poster;
    songInfoAuthor.innerHTML = songArtist;
    songInfoTitle.innerHTML = songName;
    audioPlay.src = deleteLetter(songData, "amp;");

    //update listens
    $.ajax({
        url: "/updateListeningHistory",
        type: "get",
        data: {
            songId: songId,
        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });



    audio.addEventListener("loadedmetadata", function() {
        const slider = document.getElementById('slider-timeline');

        const currentAudio = document.getElementById("audio");
        var duration = currentAudio.duration;
        var currentTime = currentAudio.currentTime;

        var end = document.getElementById("end-duration");
        minute_float = parseFloat(duration / 60);
        minute_int = parseInt(duration / 60);
        second = parseInt((minute_float - minute_int) * 60);
        end.innerHTML = minute_int + ":" + second;

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

        slider.addEventListener('input', updateAudioTime);
        currentAudio.addEventListener('timeupdate', updateSlider);

        //VOLUMN
        const sliderVolume = document.getElementById('slider-volume');
        function setVolume() {
            audio.volume = sliderVolume.value / 100;
        }

        sliderVolume.addEventListener('input', setVolume);


        //Play Mode
        repeatbtn.addEventListener("click", ActiveRepeat);
        randombtn.addEventListener("click", ActiveRandom);

        // Play random song in homepage
        function playRandomSong() {
            //servlet function

            //delete all
            const currentSong = document.getElementById("song-item-"+songId);
            // songList.forEach(songItem => {
            if (currentSong !== null) {
                const randomIndex = Math.floor(Math.random() * 10);
                const randomSong = document.getElementById("song-item-"+randomIndex);

                if (randomSong !== null && randomSong !== currentSong) {
                    currentSong.classList.remove("active");
                    randomSong.classList.add("active");
                    playSong(randomSong.querySelector("img"));
                } else {
                    // Handle case where there is no previous element sibling
                    console.log("No next song");
                }
            } else {
                // Handle case where there is no active song
                console.log("No active song");
            }
        }
        //Ended song event
        audio.addEventListener("ended", function() {
            if (repeatbtn.classList.contains("active")) {
                audio.currentTime = 0;
                audio.play();
            } else if (randombtn.classList.contains("active")) {
                playRandomSong();
            } else {
                audio.pause();
                stopbtn.classList.remove("active");
                playbtn.classList.add("active");
                isPlayed = false;
            }
        });
    });

    audioPlay.load();
}

function forward() {
    $.ajax({
        url: "/nextSong",
        type: "get",
        dataType: "json",
        success: function (data) {
            // Cap nhat music player
            var jsonData = JSON.parse(JSON.stringify(data));
            jsonData = jsonData[0];
            updateControlBar(jsonData.songId, jsonData.songPoster, jsonData.songName, jsonData.songArtist, jsonData.songData);
            audio.play();

            if(!isPlayed) {
                stopbtn.classList.toggle("active");
                playbtn.classList.toggle("active");
            }
            isPlayed = true;

        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });
}
function backward() {
    $.ajax({
        url: "/previousSong",
        type: "get",
        dataType: "json",
        success: function (data) {
            // Cap nhat music player
            var jsonData = JSON.parse(JSON.stringify(data));
            jsonData = jsonData[0];
            updateControlBar(jsonData.songId, jsonData.songPoster, jsonData.songName, jsonData.songArtist, jsonData.songData);
            audio.play();

            if(!isPlayed) {
                stopbtn.classList.toggle("active");
                playbtn.classList.toggle("active");
            }
            isPlayed = true;
        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });
}

// RIGHT CLICK SONG ITEM
// Show contextMenu
let activeContextMenu = null;
const songItems = document.querySelectorAll(".song-item");
songItems.forEach((songItem) => {
    songItem.addEventListener("contextmenu", showContextMenu);
});

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
      <li id="download-song">Download</li>
      
    </ul>
  `;
    contextMenu.addEventListener("click", (eventContext) => {
        if (eventContext.target.id === "download-song") {
            // Handle download button click
            let rightClickedSongId = event.target.getAttribute("id");
            rightClickedSongId = deleteLetter(rightClickedSongId, "song-item-");
            downloadSongItemFunc(rightClickedSongId);
        }
    });


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
        playlistDropdown.style.display = "block";

        isDropdownShown = true; // Đặt biến isDropdownShown thành true khi dropdown được hiển thị
        if (isDropdownShown) songItem.classList.add("active");
    }
    playlistItems.forEach((playlistItem) => {
        playlistItem.addEventListener("click",getPlaylistId=(e)=>{
            const playlistID = e.target.getAttribute("id");
            let rightClickedSongId = event.target.getAttribute("id");
            rightClickedSongId = deleteLetter(rightClickedSongId, "song-item-");
            addSongToPlaylist(rightClickedSongId, playlistID);
        })
    });
}

// // SHOW MODAL DELETE CONFIRM
const deleteButton = document.getElementById("btn-delete");
const deleteModal = document.getElementById("delete-modal");
const confirmDeleteBtn = document.getElementById("confirm-delete-btn");
const cancelDeleteBtn = document.getElementById("cancel-delete-btn");

// Show confirm delete modal
deleteButton.addEventListener("click", () => {
    deleteModal.style.display = "flex";
});
// Handle confirm delete
confirmDeleteBtn.addEventListener("click", () => {
    // Perform delete operation here
    // Hide confirm delete modal
    deletePlaylist();
    deleteModal.style.display = "none";
});
// Handle cancel delete
cancelDeleteBtn.addEventListener("click", (event) => {
    event.preventDefault();
    // Hide confirm delete modal
    deleteModal.style.display = "none";
});



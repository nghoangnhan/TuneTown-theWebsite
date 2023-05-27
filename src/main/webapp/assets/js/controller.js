function createPlaylist() {
    $.ajax({
        url: "/TuneTown_theWebsite_war_exploded/createPlaylist",
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
        url: "/TuneTown_theWebsite_war_exploded/loadPlaylists",
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
        url: "/TuneTown_theWebsite_war_exploded/suggestPlaylist",
        type: "get",
        success: function () {
            loadPLaylist();
        },
        error: function (xhr) {
            console.log("Error: " + xhr.responseText);
        }
    });
}
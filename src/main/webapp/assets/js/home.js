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
let songInfoAuthor = document.getElementById("song-info-author");
function moveToControlBar(get) {
  let id = get.getAttribute('id');
  const data = document.getElementById("song-data-" + id);
  const image = get.getAttribute('src');
  audio.src = deleteLetter(data.innerHTML, "amp;");

  songImage.setAttribute('src', image);

  // Load song's information
  const songTitle = document.getElementById("song-title-" + id).innerHTML;
  songInfoTitle.innerHTML = songTitle;

  const songArtists = document.getElementById("song-info-author-" + id).innerHTML;
  console.log(songArtists);
  songInfoAuthor.innerHTML = songArtists;

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
    end.innerHTML = minute_int + ":" + second.toString().padStart(2, "0");
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
    audio.addEventListener("ended", playNextSong);

    // Handle Volume
    const sliderVolume = document.getElementById('slider-volume');

    function setVolume() {
      audio.volume = sliderVolume.value / 100;
    }

    sliderVolume.addEventListener('input', setVolume);

  });
}

function deleteLetter(str, letter) {
  return str.replace(new RegExp(letter, "g"), "");
}

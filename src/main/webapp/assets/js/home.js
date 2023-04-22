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
function moveToControlBar(get) {
  const id = get.getAttribute('id');
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

  


  });
}

function deleteLetter(str, letter) {
  return str.replace(new RegExp(letter, "g"), "");
}
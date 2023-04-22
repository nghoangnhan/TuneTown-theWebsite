import { initializeApp } from "firebase/app";
import { getStorage, ref } from "firebase/storage";

const firebaseConfig = {
    apiKey: "AIzaSyB86RcOkLmIH_VkHBgAG7YAsIrqJUI92QY",
    authDomain: "tunetowntest-e968a.firebaseapp.com",
    projectId: "tunetowntest-e968a",
    storageBucket: "tunetowntest-e968a.appspot.com",
    messagingSenderId: "351615623991",
    appId: "1:351615623991:web:588dcb43bf8b08e55570b2",
    measurementId: "G-0KC1R9PKZQ"
};


const firebase = initializeApp(firebaseConfig);

const storage = getStorage(firebase);


var fileText = document.querySelector(".fileText");
var uploadPercentage = document.querySelector(".uploadPercentage");
var progress =  document.querySelector(".progress");
var percentVal;
var fileItem;
var fileName;
var img = document.querySelector(".img");

function getFile(e){
    fileItem = document.getElementById("fileInput").files[0];
    fileName = fileItem.name;
    fileText.innerHTML = fileName;
}

function uploadImage(fileName, fileContent){

    let storageRef = ref(storage);
    let uploadTask = storageRef.put(fileContent);
    let uploadUrl

    uploadTask.on("state_changed",(snapshot)=>{
        console.log(snapshot);
        percentVal = Math.floor((snapshot.bytesTransferred/snapshot.totalBytes)*100);
        console.log(percentVal);
        uploadPercentage.innerHTML = percentVal+"%";
        progress.style.width=percentVal+"%";
    },(error)=>{
        console.log("Error is ", error);
    },()=>{

        uploadTask.snapshot.ref.getDownloadURL().then((url)=>{
            console.log("URL", url);

            if(url != ""){
                img.setAttribute("src",url);
                img.style.display="block";
            }

        })

    })

}
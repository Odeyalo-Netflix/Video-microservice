console.log("HELLO!")
var element = document.getElementById("video");
const image = document.getElementById('poster');
var videoElement = document.getElementById("player");
var nameElem = document.getElementById("name");
var descElem = document.getElementById("desc");
var typeElem = document.getElementById("type");
var request = new XMLHttpRequest();
request.open("GET", "http://localhost:8084/api/v1/video/4a85344b-db96-4589-8d85-928888b4f3a3");

request.onload = response => {
    const body = JSON.parse(response.srcElement.response);
    const name = body.name;
    const videoType = body.videoType;
    const description = body.description;
    const streamUrl = body.streamUrl;
    const poster = body.poster;
    const year = body.year;
    image.setAttribute("src", poster);
    videoElement.setAttribute("src", streamUrl);
    nameElem.innerText = name;
    descElem.innerText = description;
    typeElem.innerText = videoType;
    element.appendChild(image)
}
request.send(null);
// image.setAttribute("src", )

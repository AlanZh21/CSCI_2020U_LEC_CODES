function requestDataFromServer(url)
{
  fetch(url, {
    method: 'GET', //use a get method
    headers: {
      'Accept': 'application/json' //only accepts this response type
    }
  }).then(response => response.json()) //then just get body of json
    .then(response => console.log(response)) //logs the response
}

(function(){
  let apiUrl = "http://localhost:8080/HelloWorldIO-1.0-SNAPSHOT/api/read/book";
  requestDataFromServer(apiUrl);
})()
function sendDataToServer(data,url){
  fetch(url, {
    mode: "no-cors", //NOT SAFE IN REAL LIFE SCENARIO
    method: 'POST', //use a get method
    headers: {
      'Accept': 'application/json', //only accepts this response type
      'Content-Type': 'text/plain'
    },
    body: data
  }).then(response => console.log(response)) //logs the response
    .catch(error => console.log(error))
}
function onSave(){
  let filename = document.getElementById("filename");
  let filecontent = document.getElementById("file-content");
  let data = {
    "title": filename.value,
    "content": filecontent.value
  }
  let postUrl = "http://localhost:8080/HelloWorldIO-1.0-SNAPSHOT/api/read/save"
  sendDataToServer(JSON.stringify(data), postUrl)
}

let  ws = new WebSocket("ws://localhost:8080/WebSocketsHelloWorld-1.0-SNAPSHOT/ws");

ws.onmessage = function (event) {
    console.log(event.data);
    let message = JSON.parse(event.data);
    document.getElementById("log").value += "[" + timestamp() + "] " + message.message + "\n";
}

document.getElementById("input").addEventListener("keyup", function (event) {
    if (event.key === "Enter") {
        let request = {"type":"chat", "msg":event.target.value};
        ws.send(JSON.stringify(request));
        event.target.value = "";
    }
});

function timestamp() {
    let d = new Date(), minutes = d.getMinutes();
    if (minutes < 10) minutes = '0' + minutes;
    return d.getHours() + ':' + minutes;
}
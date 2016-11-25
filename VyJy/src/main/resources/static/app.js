
function startWebSocket(){
	console.log("deze functie wordt aangeroepen");
	connect();
	sendName("Françoise");
	disconnect();
}


function connect() {
    var socket = new SockJS('/vyjy');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        //Waarnaar luister je?
        stompClient.subscribe('/topic/something', function (greeting) {
            //Wat doe je als een message binnenkomt?
        	//showGreeting(JSON.parse(greeting.body).content);
        	console.log("new message: " + greeting);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName(naam) {
	//Waarheen stuur je?
    stompClient.send("/app/bla", {}, {'name': naam});
}

function showGreeting(message) {
    //$("#greetings").append("<tr><td>" + message + "</td></tr>");
}
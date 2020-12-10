/*var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);

server.listen(8081, function(){
    console.log("Server is now running.");
});

io.on('connection', function(socket){
    console.log("Player Connected!");
    socket.on('disconnect', function(){
        console.log("Player disconnected");
    });
});
*/

const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8081 });

wss.on('connection', function connection(ws) {
  ws.on('message', function incoming(message) {
    console.log('received: %s', message);
  });
});
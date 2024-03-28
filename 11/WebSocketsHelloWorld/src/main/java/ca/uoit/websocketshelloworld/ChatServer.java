package ca.uoit.websocketshelloworld;

import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.*;

import jakarta.ws.rs.PathParam;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value="/ws/{roomID}") //websocket
public class ChatServer {

    private Map<String, String> usernames = new HashMap<String, String>();

    private static Map<String, String> roomList = new HashMap<>();

    @OnOpen //called the first time client connects
    public void open(@PathParam("roomID") String roomID, Session session) throws IOException{
        //getBasicRemote(), sends message,
        //sendText(), text
        session.getBasicRemote().sendText("{\"type\": \"chat\", \"message\":\"(Server): Welcome to the chat room. Please state your username to begin.\"}");
        roomList.put(session.getId(), roomID);
    }

    @OnClose
    public void close(@PathParam("roomID") String roomID, Session session) throws IOException{
        String userId = session.getId();
        if (usernames.containsKey(userId)) {
            String username = usernames.get(userId);
            roomList.remove(session.getId());
            usernames.remove(userId); //removes user
            //broadcast this person left the server
            for (Session peer : session.getOpenSessions()){
                if(roomList.get(peer.getId()).equals(roomID)){
                    peer.getBasicRemote().sendText("{\"type\": \"chat\", \"message\":\"(Server): " + username + " left the chat room.\"}");
                }
            }
        }
    }

    @OnMessage
    //comm = message
    public void handleMessage(@PathParam("roomID") String roomID, String comm, Session session) throws IOException{
        String userID = session.getId(); //
        JSONObject jsonmsg = new JSONObject(comm); //parse string to json
        String type = (String) jsonmsg.get("type");// type = "chat"
        String message = (String) jsonmsg.get("msg");

        // not their first message
        if(usernames.containsKey(userID)){ //checks if userID is already in the server
            String username = usernames.get(userID);
            System.out.println(username);
            for(Session peer: session.getOpenSessions()){
                if(roomList.get(peer.getId()).equals(roomID)){
                    peer.getBasicRemote().sendText("{\"type\": \"chat\", \"message\":\"(" + username + "): " + message+"\"}");
                }
            }
        }else{ //first message is their username
            usernames.put(userID, message); //puts their first message as a username
            session.getBasicRemote().sendText("{\"type\": \"chat\", \"message\":\"(Server): Welcome, " + message + "!\"}");
            //broadcast this person joined the server to the rest
            for(Session peer: session.getOpenSessions()){ //getOpenSessions(), lists all sessions/clients
                if(!peer.getId().equals(userID) && (roomList.get(peer.getId()).equals(roomID))){
                    peer.getBasicRemote().sendText("{\"type\": \"chat\", \"message\":\"(Server): " + message + " joined the chat room.\"}");
                }
            }
        }

    }

}
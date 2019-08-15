package com.WitcherModShop.model;

import java.io.Serializable;

/**
 * required for storing the username and password received from the client
 */
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;
    private String steamID;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {
    }
    public JwtRequest(String username, String password, String steamID) {
        this.setUsername(username);
        this.setPassword(password);
        this.setSteamID(steamID);

    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSteamID() { return steamID; }
    public void setSteamID(String steamID) { this.steamID = steamID; }
}

package View;

import Model.Client;

public class SessionManager {
    private static SessionManager instance;
    private Client currentUser;

    private SessionManager() { }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Client getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }
}
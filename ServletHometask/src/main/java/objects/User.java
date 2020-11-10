package objects;

import java.io.Serializable;

public class User implements Serializable{

    private final String username;

    public User(String nm){
        this.username=nm;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString(){
        return "Username="+this.username;
    }
}

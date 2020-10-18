package Model;

import android.widget.SeekBar;

public class User {
    public String email;
    public String phone;
    public String name;

    public User() {
    }

    public User(String email,String phone,String name) {
        this.name = name;
        this.email= email;
        this.phone= phone;
    }

}

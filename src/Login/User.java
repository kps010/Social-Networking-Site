package Login;

import java.util.*;

public class User {
    public static String name;
    public static String email;
    public static String phno;
    public static String pass;
    public static String gender;
    public static String bio;
    public static Date dob;

    User(String name, String email, String phno, String pass, String gender, String bio, Date dob) {
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.pass = pass;
        this.gender = gender;
        this.bio = bio;
        this.dob = dob;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPass() {
        return pass;
    }

    public static String getPhno() {
        return phno;
    }

    public static String getGender() {
        return gender;
    }

    public static String getBio() {
        return bio;
    }

    public static Date getDob() {
        return dob;
    }
}

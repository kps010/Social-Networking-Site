package DS;

import Login.User;

public class UserLL {
    public static class node {
        String username;
        String password;
        node next;

        public node(String username, String password) {
            this.username = username;
            this.password = password;
            next = null;
        }
    }

    public static node first = null;

    static public void insert(String username, String pass) {
        node n = new node(username, pass);
        if (first == null) {
            first = n;
        } else {
            node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
    }

    static public void deleteAll() {
        while (first != null) {
            first = first.next;
        }
    }

    static public boolean contains(String name) {
        if (first == null) {
            return false;
        } else {
            node temp = first;
            while (temp != null) {
                if (temp.username.equals(name)) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    static public boolean checkUser(String name, String pass) {
        if (first == null) {
            return false;
        } else {
            node temp = first;
            while (temp != null) {
                if (temp.username.equals(name) && temp.password.equals(pass)) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    static public boolean checkOldUser(String name) {
        if (first == null) {
            return false;
        } else {
            node temp = first;
            while (temp != null) {
                if (temp.username.equals(name)) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    static public void display() {
        if (first != null) {
            System.out.println("EMPTY");
        } else {
            node temp = first;
            while (temp.next != null) {
                System.out.println(temp.username + "=>");
            }
            System.out.println(temp.username);
        }
    }
}

package DS;

public class LL {
    public static class node {
        public static String username;
        node next;

        public node(String username) {
            this.username = username;
            next = null;
        }
    }

    public static node first = null;

    static public void insert(String username) {
        node n = new node(username);
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

    public static int getCount() {
        int count = 0;
        if (first == null) {
            return count;
        } else {
            node temp = first;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
        }
        return count;
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
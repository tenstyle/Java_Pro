package Lesson_2.Task_2.client;

public class GetHash {
    public static void main(String[] args) {
        String pass = "pass";
        int myHash = pass.hashCode();
        System.out.println(myHash);
    }
}

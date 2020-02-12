package Models;

public class Main {

    public static void main(String[] args) {

        testTicket();

    }

	//Method to test Ticket object
    public static void testTicket() {
        Ticket t1 = new Ticket(1, new java.util.Date(System.currentTimeMillis()), 1, 1, null);
        Ticket t2 = new Ticket(2, new java.util.Date(System.currentTimeMillis()), 2, 2, null);
        System.out.println(t1);
        System.out.println(t2);
    }
}
import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }
}

class Reservation {
    String customerName;
    Room room;
    boolean paymentDone;

    Reservation(String customerName, Room room, boolean paymentDone) {
        this.customerName = customerName;
        this.room = room;
        this.paymentDone = paymentDone;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedRooms();

        int choice;
        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }

    static void seedRooms() {
        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Standard", 1000));
        rooms.add(new Room(201, "Deluxe", 2000));
        rooms.add(new Room(202, "Deluxe", 2000));
        rooms.add(new Room(301, "Suite", 3500));
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room No: " + room.roomNumber + " | Category: " + room.category + " | Price: ₹" + room.price);
            }
        }
    }

    static void bookRoom() {
        viewAvailableRooms();
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available or invalid room number.");
            return;
        }

        System.out.println("Room Price: ₹" + selectedRoom.price);
        System.out.print("Proceed with payment? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            selectedRoom.isAvailable = false;
            reservations.add(new Reservation(name, selectedRoom, true));
            System.out.println("Booking confirmed for " + name + " in room " + selectedRoom.roomNumber);
        } else {
            System.out.println("Booking cancelled.");
        }
    }

    static void viewReservations() {
        System.out.println("\nCurrent Reservations:");
        for (Reservation res : reservations) {
            System.out.println("Name: " + res.customerName +
                               " | Room: " + res.room.roomNumber +
                               " | Category: " + res.room.category +
                               " | Paid: " + (res.paymentDone ? "Yes" : "No"));
        }
    }
}

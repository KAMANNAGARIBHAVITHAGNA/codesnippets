import java.util.*;

public class HotelManagement {

    static class Room {
        int roomNumber;
        boolean isBooked;
        String guestName;

        Room(int roomNumber) {
            this.roomNumber = roomNumber;
            this.isBooked = false;
            this.guestName = "";
        }

        void bookRoom(String guestName) {
            if (isBooked) {
                System.out.println("Room " + roomNumber + " is already booked.");
            } else {
                this.isBooked = true;
                this.guestName = guestName;
                System.out.println("Room " + roomNumber + " booked successfully for " + guestName + ".");
            }
        }

        void vacateRoom() {
            if (!isBooked) {
                System.out.println("Room " + roomNumber + " is not currently booked.");
            } else {
                this.isBooked = false;
                System.out.println("Room " + roomNumber + " vacated by " + guestName + ".");
                this.guestName = "";
            }
        }

        @Override
        public String toString() {
            return "Room " + roomNumber + (isBooked ? " (Booked by: " + guestName + ")" : " (Available)");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Room> rooms = new ArrayList<>();

        // Initialize rooms
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i));
        }

        boolean running = true;

        while (running) {
            System.out.println("\nHotel Management System");
            System.out.println("1. View all rooms");
            System.out.println("2. Book a room");
            System.out.println("3. Vacate a room");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nRoom Status:");
                    for (Room room : rooms) {
                        System.out.println(room);
                    }
                    break;
                case 2:
                    System.out.print("Enter room number to book (1-10): ");
                    int roomToBook = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (roomToBook < 1 || roomToBook > 10) {
                        System.out.println("Invalid room number.");
                    } else {
                        System.out.print("Enter guest name: ");
                        String guestName = scanner.nextLine();
                        rooms.get(roomToBook - 1).bookRoom(guestName);
                    }
                    break;
                case 3:
                    System.out.print("Enter room number to vacate (1-10): ");
                    int roomToVacate = scanner.nextInt();
                    if (roomToVacate < 1 || roomToVacate > 10) {
                        System.out.println("Invalid room number.");
                    } else {
                        rooms.get(roomToVacate - 1).vacateRoom();
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}

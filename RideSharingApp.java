import java.util.*;

// User Class
class User {
    int userId;
    String name;
    String location;

    public User(int userId, String name, String location) {
        this.userId = userId;
        this.name = name;
        this.location = location;
    }
}

// Driver Class
class Driver {
    int driverId;
    String name;
    String location;
    boolean isAvailable;

    public Driver(int driverId, String name, String location) {
        this.driverId = driverId;
        this.name = name;
        this.location = location;
        this.isAvailable = true;
    }
}

// Ride Class
class Ride {
    User user;
    Driver driver;
    double distance;
    double fare;

    public Ride(User user, Driver driver, double distance) {
        this.user = user;
        this.driver = driver;
        this.distance = distance;
        this.fare = calculateFare(distance);
    }

    private double calculateFare(double distance) {
        double baseFare = 50;
        double perKmRate = 10;
        return baseFare + (distance * perKmRate);
    }

    public void displayRideDetails() {
        System.out.println("\n--- Ride Details ---");
        System.out.println("User: " + user.name);
        System.out.println("Driver: " + driver.name);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Total Fare: ₹" + fare);
    }
}

// Main Application Class
public class RideSharingApp {

    static List<User> users = new ArrayList<>();
    static List<Driver> drivers = new ArrayList<>();

    // Register User
    public static void registerUser(int id, String name, String location) {
        users.add(new User(id, name, location));
        System.out.println("User registered successfully!");
    }

    // Register Driver
    public static void registerDriver(int id, String name, String location) {
        drivers.add(new Driver(id, name, location));
        System.out.println("Driver registered successfully!");
    }

    // Find Available Driver
    public static Driver findAvailableDriver(String location) {
        for (Driver d : drivers) {
            if (d.isAvailable && d.location.equalsIgnoreCase(location)) {
                return d;
            }
        }
        return null;
    }

    // Book Ride
    public static void bookRide(User user, double distance) {
        Driver driver = findAvailableDriver(user.location);

        if (driver != null) {
            driver.isAvailable = false;
            Ride ride = new Ride(user, driver, distance);
            ride.displayRideDetails();
        } else {
            System.out.println("No drivers available in your location.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Sample Data
        registerDriver(1, "Raj", "Delhi");
        registerDriver(2, "Amit", "Mumbai");

        registerUser(1, "Rahul", "Delhi");

        System.out.println("\nEnter distance to travel (in km): ");
        double distance = sc.nextDouble();

        bookRide(users.get(0), distance);

        sc.close();
    }
}
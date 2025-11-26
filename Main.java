import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Einfacher Netzwerkgerätemonitor
public class Main {

    static class Device {
        String hostname;
        String ip;
        String status; // "Online" or "Offline"

        Device(String hostname, String ip) {
            this.hostname = hostname;
            this.ip = ip;
            this.status = "Unknown";
        }

        @Override
        public String toString() {
            return hostname + " | " + ip + " | " + status; // Speichern als eine Zeile
        }

        String toFileString() {
            return hostname + ";" + ip + ";" + status;
        }

        static Device fromFileString(String line) {
            String[] parts = line.split(";");
            Device d = new Device(parts[0], parts[1]); // Hostname + IP laden
            if (parts.length > 2) d.status = parts[2];
            return d;
        }
    }

    static final String FILE_NAME = "devices.txt"; // Speicherdatei
    static List<Device> devices = new ArrayList<>(); // Liste der Geräte

    public static void main(String[] args) {
        loadDevices(); // Beim Start vorhandene Geräte laden
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Network Device Monitor ===");
            System.out.println("1) Show devices");
            System.out.println("2) Add device");
            System.out.println("3) Remove device");
            System.out.println("4) Check status");
            System.out.println("5) Save devices");
            System.out.println("6) Load devices");
            System.out.println("7) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showDevices();
                    break;
                case "2":
                    addDevice(scanner);
                    break;
                case "3":
                    removeDevice(scanner);
                    break;
                case "4":
                    checkStatus();
                    break;
                case "5":
                    saveDevices();
                    break;
                case "6":
                    loadDevices();
                    break;
                case "7":
                    saveDevices(); // Beim Schließen speichern
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void showDevices() {
        if (devices.isEmpty()) {
            System.out.println("No devices in the list."); // Falls die Liste leer ist
            return;
        }
        System.out.println("\nDevices:");
        for (int i = 0; i < devices.size(); i++) {
            System.out.println((i + 1) + ") " + devices.get(i));
        }
    }

    static void addDevice(Scanner scanner) {
        System.out.print("Enter hostname: ");
        String hostname = scanner.nextLine().trim();
        System.out.print("Enter IP address: ");
        String ip = scanner.nextLine().trim();

        devices.add(new Device(hostname, ip)); // Neues Gerät in die Liste packen
        System.out.println("Device added!");
    }

    static void removeDevice(Scanner scanner) {
        showDevices();
        if (devices.isEmpty()) return;
        System.out.print("Enter device number to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < devices.size()) {
                Device removed = devices.remove(index); // Gerät löschen
                System.out.println("Removed: " + removed.hostname);
            } else {
                System.out.println("Invalid number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    static void checkStatus() {
        // Simulierter Ping-Test (Zufall)
        if (devices.isEmpty()) {
            System.out.println("No devices to check.");
            return;
        }
        for (Device d : devices) {
            d.status = Math.random() > 0.3 ? "Online" : "Offline";
        }
        System.out.println("Status checked!");
    }

    static void saveDevices() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Device d : devices) {
                pw.println(d.toFileString()); // In Datei schreiben
            }
            System.out.println("Devices saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving devices: " + e.getMessage());
        }
    }

    static void loadDevices() {
        devices.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                devices.add(Device.fromFileString(line)); // Aus Datei wieder aufbauen
            }
            System.out.println("Devices loaded from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading devices: " + e.getMessage());
        }
    }
}
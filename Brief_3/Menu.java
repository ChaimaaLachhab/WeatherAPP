import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        ConnectionDB connecter = new ConnectionDB();
        Scanner scannerr = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Create City");
            System.out.println("2. Read City");
            System.out.println("3. Update City");
            System.out.println("4. Delete City");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");
            choice = scannerr.nextInt();

            try (Connection connect = connecter.getConnection()) {
                switch (choice) {
                    case 1:
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter the information for the new city:");
                        System.out.print("ID: ");
                        int cityId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Name: ");
                        String cityName = scanner.nextLine();
                        System.out.print("Current Temperature: ");
                        double currentTemperature = scanner.nextDouble();
                        System.out.print("Current Humidity: ");
                        double currentHumidity = scanner.nextDouble();
                        System.out.print("Current WindSpeed: ");
                        double currentWindSpeed = scanner.nextDouble();
                        City.createCity(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed), connect);

                        break;
                    case 2:
                        System.out.println(City.readCity(connect));
                        break;
                    case 3:
                        Scanner in = new Scanner(System.in);
                        System.out.println("Enter ID for the city to be updated:");
                        int upCityId = in.nextInt();
                        in.nextLine();
                        System.out.print("New Name: ");
                        String upCityName = in.nextLine();
                        System.out.print("New Current Temperature: ");
                        double upCurrentTemperature = in.nextDouble();
                        System.out.print("New Current Humidity: ");
                        double upCurrentHumidity = in.nextDouble();
                        System.out.print("New Current WindSpeed: ");
                        double upCurrentWindSpeed = in.nextDouble();

                        City.updateCity(new City(upCityId, upCityName, upCurrentTemperature, upCurrentHumidity, upCurrentWindSpeed), connect);
                        break;
                    case 4:
                        Scanner put = new Scanner(System.in);
                        System.out.print("Enter the ID of the city to be deleted: ");
                        int deleteCityId = put.nextInt();
                        City.deleteCity(deleteCityId, connect);

                        break;
                    case 5:
                        System.out.println("Exiting Program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (choice != 5);
        scannerr.close();
    }
}

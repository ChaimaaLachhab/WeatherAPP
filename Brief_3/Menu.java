import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        try (Connection connect = ConnectionDB.getConnection()) {
            do {
                System.out.println();
                System.out.println("....................CITY....................................CITY_HISTORY................");
                System.out.println("           1. Create City                  .           5. Create CityHistory            ");
                System.out.println("           2. Read City                    .           6. Read CityHistory              ");
                System.out.println("           3. Update City                  .           7. Update CityHistory            ");
                System.out.println("           4. Delete City                  .           8. Delete CityHistory            ");
                System.out.println("........................................................................................");
                System.out.println();
                System.out.println("                                       9. Exit                                          ");
                System.out.println();
                System.out.print("..............Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        //Create City
                        Scanner A = new Scanner(System.in);
                        System.out.println("Enter the information for the new city:");
                        System.out.print("ID: ");
                        int cityId = A.nextInt();
                        A.nextLine();
                        System.out.print("Name: ");
                        String cityName = A.nextLine();
                        System.out.print("Current Temperature: ");
                        double currentTemperature = A.nextDouble();
                        System.out.print("Current Humidity: ");
                        double currentHumidity = A.nextDouble();
                        System.out.print("Current WindSpeed: ");
                        double currentWindSpeed = A.nextDouble();
                        City city = new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed);
                        city.createCity(connect);
                        break;

                    case 2:
                        //Read City
                        System.out.println("\n.....................La liste des villes....................\n");
                        List<City> cities = City.readCity(connect);
                        cities.forEach(System.out::println);
                        break;

                    case 3:
                        //Update City
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

                        City city1 = new City(upCityId, upCityName, upCurrentTemperature, upCurrentHumidity, upCurrentWindSpeed);
                        city1.updateCity(connect);
                        break;

                    case 4:
                        //Delete City
                        Scanner put = new Scanner(System.in);
                        System.out.print("Enter the ID of the city to be deleted: ");
                        int deleteCityId = put.nextInt();
                        City city2 = new City(deleteCityId, null, 0, 0, 0);
                        city2.deleteCity(connect);
                        break;

                    case 5:
                        // Create CityHistory
                        Scanner input = new Scanner(System.in);
                        System.out.println("Enter the information for the new city:");
                        System.out.print("Historical Data Id: ");
                        int historicalDataId = input.nextInt();
                        input.nextLine();
                        System.out.print("City Id: ");
                        int cityIdh = input.nextInt();
                        input.nextLine();
                        System.out.print("Even Date (YYYY-MM-JJ) :");
                        String x = new Scanner(System.in).next();
                        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate evenDate = LocalDate.parse(x, date);
                        System.out.print("Temperature: ");
                        double temperature = input.nextDouble();

                        CityHistory cityHistory = new CityHistory(historicalDataId, cityIdh, evenDate, temperature);
                        cityHistory.createCityHistory(connect);
                        break;

                    case 6:
                        //Read CityHistory
                        Scanner scanne = new Scanner(System.in);
                        System.out.print("Enter the City Id to see the history: ");
                        int cityIdHistory = scanne.nextInt();
                        System.out.println("\n.................La liste des Historiques...................");
                        List<CityHistory> citiesHistory = CityHistory.readCityHistory(cityIdHistory, connect);
                        citiesHistory.forEach(x1 -> System.out.println(x1));
                        break;

                    case 7:
                        //Update CityHistory
                        Scanner inn = new Scanner(System.in);
                        System.out.println("Enter ID for the city history to be updated:");
                        int historicalDataIdh = inn.nextInt();
                        inn.nextLine();
                        System.out.print("New Even Date (YYYY-MM-JJ) :");
                        String xx = new Scanner(System.in).next();
                        DateTimeFormatter date2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate newEvenDate = LocalDate.parse(xx, date2);
                        System.out.print("New Temperature: ");
                        double newTemperature = inn.nextDouble();
                        CityHistory cityHistory2 = new CityHistory(historicalDataIdh, newEvenDate, newTemperature);
                        cityHistory2.updateCityHistory(connect);
                        break;

                    case 8:
                        //Delete CityHistory
                        Scanner putt = new Scanner(System.in);
                        System.out.print("Enter the ID of the city history to be deleted: ");
                        int deleteHistoricalDataId = putt.nextInt();
                        CityHistory cityHistory3 = new CityHistory(deleteHistoricalDataId, 0, null, 0);
                        cityHistory3.deleteCityHistory(connect);
                        break;

                    case 9:
                        //Exiting
                        System.out.println("Exiting Program!!!!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid number.");
                }
            } while (choice != 9);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
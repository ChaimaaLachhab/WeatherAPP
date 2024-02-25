import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private LocalDate evenDate;
    private double temperature;

    private City city;

    public CityHistory(int historicalDataId, LocalDate evenDate, double temperature ){
        this.historicalDataId=historicalDataId;
        this.evenDate=evenDate;
        this.temperature=temperature;
    }

    public CityHistory(int historicalDataId, int cityId, LocalDate evenDate, double temperature ){
        this.historicalDataId=historicalDataId;
        this.cityId=cityId;
        this.evenDate=evenDate;
        this.temperature=temperature;
    }

    public CityHistory(City city,int historicalDataId, LocalDate evenDate, double temperature ){
        this.city= city;
        this.historicalDataId=historicalDataId;
        this.evenDate=evenDate;
        this.temperature=temperature;
    }

    public void createCityHistory(Connection connection) throws SQLException {
        String sql = "INSERT INTO CityHistory (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, this.historicalDataId);
            statement.setInt(2, this.cityId);
            statement.setDate(3, Date.valueOf(this.evenDate));
            statement.setDouble(4, this.temperature);
            statement.executeUpdate();
        }
        System.out.println("CityHistory created successfully");
    }

    public static List<CityHistory> readCityHistory(int cityId, Connection connection) throws SQLException {
        List<CityHistory> citiesHistory = new ArrayList<>();
        String sql = "SELECT CityHistory.historicalDataId, CityHistory.cityId, CityHistory.eventDate, CityHistory.temperature, City.cityName" +
                "FROM CityHistory" +
                "JOIN City c ON CityHistory.cityId = City.cityId" +
                "WHERE CityHistory.cityId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int historicalDataId = resultSet.getInt("historicalDataId");
                     cityId = resultSet.getInt("cityId");
                    LocalDate evenDate = resultSet.getDate("eventDate").toLocalDate();
                    double temperature = resultSet.getDouble("temperature");
                    String cityName = resultSet.getString("cityName");
                    City city = new City(cityId, cityName, 0, 0, 0);
                    citiesHistory.add(new CityHistory(city, historicalDataId, evenDate, temperature));
                }
            }
        }
        return citiesHistory;
    }


    public void updateCityHistory(Connection connection) throws SQLException {
        String sql = "UPDATE CityHistory SET eventDate = ?, temperature = ? WHERE historicalDataId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(this.evenDate));
            statement.setDouble(2, this.temperature);
            statement.setInt(3, this.historicalDataId);
            statement.executeUpdate();
        }
        System.out.println("CityHistory updated successfully");
    }


    public void deleteCityHistory(Connection connection) throws SQLException {
        String sql = "DELETE FROM CityHistory WHERE historicalDataId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, this.historicalDataId);
            statement.executeUpdate();
        }
        System.out.println("CityHistory deleted successfully!");
    }

    public static CityHistory searchCityHistory(int historicalDataId, Connection connection)throws SQLException{
        String sql ="SELECT * FROM CityHistory WHERE historicalDataId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, historicalDataId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CityHistory(resultSet.getInt("historicalDataId"),
                            resultSet.getInt("cityId"),
                            resultSet.getDate("eventDate").toLocalDate(),
                            resultSet.getDouble("temperature"));
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return  "\nCityId = " + city.getCityId() +
                "  CityName = " + city.getCityName() +
                " | HistoricalDataId = " + historicalDataId +
                "\n                         | EvenDate = " + evenDate +
                "\n                         | Temperature = " + temperature +" °C\n";
    }

}

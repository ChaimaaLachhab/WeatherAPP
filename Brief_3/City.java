import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class City {

    private int cityId;
    private String cityName;
    private double currentTemperature;
    private double currentHumidity;
    private double currentWindSpeed;


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public City(int cityId, String cityName, double currentTemperature, double currentHumidity, double currentWindSpeed){
        this.cityId=cityId;
        this.cityName=cityName;
        this.currentTemperature=currentTemperature;
        this.currentHumidity=currentHumidity;
        this.currentWindSpeed=currentWindSpeed;
    }

    public void createCity(Connection connection) throws SQLException {
        String sql = "INSERT INTO City (cityID, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, this.cityId);
            statement.setString(2, this.cityName);
            statement.setDouble(3, this.currentTemperature);
            statement.setDouble(4, this.currentHumidity);
            statement.setDouble(5, this.currentWindSpeed);
            statement.executeUpdate();
        }
        System.out.println("City created successfully");
    }

    public static List<City> readCity(Connection connection) throws SQLException {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int cityId = resultSet.getInt("cityId");
                String cityName = resultSet.getString("cityName");
                double currentTemperature = resultSet.getDouble("currentTemperature");
                double currentHumidity = resultSet.getDouble("currentHumidity");
                double currentWindSpeed = resultSet.getDouble("currentWindSpeed");
                cities.add(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
            }
        }
        return cities;
    }

    public void updateCity(Connection connection) throws SQLException {
        String sql = "UPDATE City SET cityName = ?, currentTemperature = ?, currentHumidity = ?, currentWindSpeed = ? WHERE cityId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.cityName);
            statement.setDouble(2, this.currentTemperature);
            statement.setDouble(3, this.currentHumidity);
            statement.setDouble(4, this.currentWindSpeed);
            statement.setInt(5, this.cityId);
            statement.executeUpdate();
        }
        System.out.println("City updated successfully");
    }


    public void deleteCity(Connection connection) throws SQLException {
        String sqlDeleteCityHistory = "DELETE FROM CityHistory WHERE cityId = ?";
        String sqlDeleteCity = "DELETE FROM City WHERE cityId = ?";
        try (PreparedStatement statementDeleteCityHistory = connection.prepareStatement(sqlDeleteCityHistory);
             PreparedStatement statementDeleteCity = connection.prepareStatement(sqlDeleteCity)) {
            statementDeleteCityHistory.setInt(1, this.cityId);
            statementDeleteCity.setInt(1, this.cityId);
            statementDeleteCityHistory.executeUpdate();
            statementDeleteCity.executeUpdate();
        }
        System.out.println("City deleted successfully");
    }


    public static City searchCity(int cityId, Connection connection) throws SQLException {
        String sql ="SELECT * FROM City WHERE cityId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new City(resultSet.getInt("cityId"),
                            resultSet.getString("cityName"),
                            resultSet.getDouble("currentTemperature"),
                            resultSet.getDouble("currentHumidity"),
                            resultSet.getDouble("currentWindSpeed"));
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return  "City Id=" + cityId +
                " | City Name=" + cityName +
                "\n         | Current Temperature=" + currentTemperature +" °C"+
                "\n         | Current Humidity=" + currentHumidity +" %"+
                "\n         | Current WindSpeed=" + currentWindSpeed +" Km/h\n";
    }


}
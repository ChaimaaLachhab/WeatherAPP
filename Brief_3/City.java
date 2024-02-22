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

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(double currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public double getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCurrentWindSpeed(double currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    public City(){}
    public City(int cityId, String cityName, double currentTemperature, double currentHumidity, double currentWindSpeed){
        this.cityId=cityId;
        this.cityName=cityName;
        this.currentTemperature=currentTemperature;
        this.currentHumidity=currentHumidity;
        this.currentWindSpeed=currentWindSpeed;
    }

    public static void createCity(City city, Connection connection) throws SQLException {
        String sql = "INSERT INTO City (cityID, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, city.getCityId());
        statement.setString(2, city.getCityName());
        statement.setDouble(3, city.getCurrentTemperature());
        statement.setDouble(4, city.getCurrentHumidity());
        statement.setDouble(5, city.getCurrentWindSpeed());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City updated successfully!");
    }

    public static List<City> readCity(Connection connection) throws SQLException{
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int cityId = resultSet.getInt("cityId");
            String cityName = resultSet.getString("cityName");
            double ct = Double.parseDouble(resultSet.getString("currentTemperature"));
            double ch = Double.parseDouble(resultSet.getString("currentHumidity"));
            double cw = Double.parseDouble(resultSet.getString("currentWindSpeed"));
            cities.add(new City(cityId, cityName, ct, ch, cw));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cities;
    }

    public static void updateCity(City city, Connection connection) throws SQLException {
        String sql = "UPDATE City SET cityName = ?, currentTemperature = ?, currentHumidity = ?, currentWindSpeed = ? WHERE cityId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, city.getCityName());
        statement.setDouble(2, city.getCurrentTemperature());
        statement.setDouble(3, city.getCurrentHumidity());
        statement.setDouble(4, city.getCurrentWindSpeed());
        statement.setInt(5, city.getCityId());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City updated successfully!");
    }

    public static void deleteCity(int cityId, Connection connection) throws SQLException {
        String sql = "DELETE FROM City WHERE cityId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City deleted successfully!");
    }

    public static void search(int cityId_s,Connection connection)throws SQLException{
        String sql ="select * from City where cityId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityId_s);
        ResultSet resultSet= statement.executeQuery();
        if(resultSet.next()){
            System.out.println(resultSet.getInt("cityId"));
        }
    }

    @Override
    public String toString() {
        return "CityT{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", currentTemperature=" + currentTemperature +
                ", currentHumidity=" + currentHumidity +
                ", currentWindSpeed=" + currentWindSpeed +
                '}';
    }
}



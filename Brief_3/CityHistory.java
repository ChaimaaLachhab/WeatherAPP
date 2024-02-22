import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private LocalDate evenDate;
    private double temperature;

    public int getHistoricalDataId() {
        return historicalDataId;
    }

    public void setHistoricalDataId(int historicalDataId) {
        this.historicalDataId = historicalDataId;
    }

    public int getCityId() {return cityId;}

    public void setCityId(int cityId) {this.cityId = cityId;}

    public LocalDate getEvenDate() {return evenDate;}

    public void setEvenDate(LocalDate evenDate) {this.evenDate = evenDate;}

    public double getTemperature() {return temperature;}

    public void setTemperature(double temperature) {this.temperature = temperature;}

    public CityHistory(int historicalDataId, int cityId, LocalDate evenDate, double temperature ){
        this.historicalDataId=historicalDataId;
        this.cityId=cityId;
        this.evenDate=evenDate;
        this.temperature=temperature;
    }

    public static void createCityHistory(CityHistory city, Connection connection) throws SQLException {
        String sql = "INSERT INTO CityHistory (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, city.getHistoricalDataId());
        statement.setInt(2, city.getCityId());
        statement.setDate(3, Date.valueOf(city.getEvenDate()));
        statement.setDouble(4, city.getTemperature());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City updated successfully!");
    }


    public static List<CityHistory> readCityHistory(int cityId_h ,Connection connection) throws SQLException {
        List<CityHistory> citiesHistory = new ArrayList<>();
        String sql = "SELECT * FROM City, CityHistory WHERE City.cityId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,cityId_h);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int historicalDataId = resultSet.getInt("historicalDataId");
            int cityId = resultSet.getInt("cityId");
            LocalDate evenDate = resultSet.getDate("evenDate").toLocalDate();
            double temperature = resultSet.getDouble("temperature");
            citiesHistory.add(new CityHistory(historicalDataId, cityId, evenDate, temperature));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return citiesHistory;
    }


    public static void updateCityHistory(CityHistory cityHostory, Connection connection) throws SQLException {
        String sql = "UPDATE City SET cityName = ?, currentTemperature = ?, currentHumidity = ?, currentWindSpeed = ? WHERE cityId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

    }

    public void deleteHistory(){

    }


    @Override
    public String toString() {
        return "CityHistoryT{" +
                "historicalDataId=" + historicalDataId +
                ", cityId=" + cityId +
                ", evenDate=" + evenDate +
                ", temperature=" + temperature +
                '}';
    }
}

package dao;

import models.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class DAO {
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:B:\\ME\\SQLite\\CurrencyExchanger.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private Currency createNewCurrency(ResultSet resultSet) {
        try {
            return new Currency(
                    resultSet.getInt("id"),
                    resultSet.getString("code"),
                    resultSet.getString("fullname"),
                    resultSet.getString("sign"));
        } catch (SQLException e) {
            return null;
        }
    }
    public List<Currency> selectAllCurrency() {

        List<Currency> currencies = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from Currencies")) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String fullName = rs.getString("FullName");
                String sign = rs.getString("sign");
                currencies.add(new Currency(id, code, fullName, sign));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currencies;
    }

    public void insertCurrency(Currency currency){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Currencies " +
                     " (code, fullName, sign) VALUES  (?, ?, ?);")) {
            preparedStatement.setString(1, currency.getCode());
            preparedStatement.setString(2, currency.getFullName());
            preparedStatement.setString(3, currency.getSign());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Currency selectCurrency(String code) {
        Currency currency = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("select * from Currencies where code = '" + code + "'")) {
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                currency = createNewCurrency(rs);
            }
            return currency;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package dao;

import models.Currency;
import models.ExchangeRate;

import java.math.BigDecimal;
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
                    resultSet.getString("fullName"),
                    resultSet.getString("sign"));
        } catch (SQLException e) {
            return null;
        }
    }
    private ExchangeRate createNewExchangeRate(ResultSet resultSet) {
        try {
            return new ExchangeRate(
                    resultSet.getInt("id"),
                    findCurrencyById(resultSet.getInt("BaseCurrencyId")),
                    findCurrencyById(resultSet.getInt("TargetCurrencyId")),
                    resultSet.getDouble("rate"));
        } catch (SQLException e) {
            return null;
        }
    }
    public List<Currency> findAllCurrency() {

        List<Currency> currencies = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from Currencies")) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                currencies.add(createNewCurrency(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currencies;
    }

    public void insertCurrency(Currency currency){
        try (Connection connection = getConnection( );
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Currencies " +
                     " (code, fullName, sign) VALUES  (?, ?, ?);")) {
            preparedStatement.setString(1, currency.getCode());
            preparedStatement.setString(2, currency.getFullName());
            preparedStatement.setString(3, currency.getSign());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Currency findCurrencyByCode(String code) {
        Currency currency = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM Currencies WHERE code = '" + code + "'")) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                currency = createNewCurrency(rs);
            }
            return currency;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Currency findCurrencyById(int id) {

        Currency currency = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM Currencies WHERE id= " + id)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                currency = createNewCurrency(rs);
            }
            return currency;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ExchangeRate findExchangeRateByCodes(String baseCurrencyCode, String targetCurrencyCode) {
        ExchangeRate exchangeRate = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM ExchangeRates WHERE " +
                             "basecurrencyid=? AND targetcurrencyid=?")) {
            preparedStatement.setInt(1, findCurrencyByCode(baseCurrencyCode).getId());
            preparedStatement.setInt(2, findCurrencyByCode(targetCurrencyCode).getId());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                exchangeRate = createNewExchangeRate(resultSet);
            }
            return exchangeRate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ExchangeRate> findAllExchangeRate() {

        List<ExchangeRate> exchangeRates = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ExchangeRates")) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                exchangeRates.add(createNewExchangeRate(rs));
            }
            return exchangeRates;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.microgram.project.service;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbService {
    private Connection conn;

    public DbService() {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/microgram?user=postgres&password=123maks";
        return DriverManager.getConnection(url);
    }

    private void init() throws SQLException {
        conn = getNewConnection();
    }

    private void close() throws SQLException {
        conn.close();
    }

    public String openConnection() {
        try {
            init();
            return "Connection to the database was successful";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private void executeUpdate(String query) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
    }

    private void createCustomerTable() throws SQLException {
        String customerTableQuery = "create table customers " +
                "(id integer primary key, " +
                "name text, " +
                "age integer)";
        String customerEntryQuery = "insert into customers " +
                "values (73, 'Brian', 33)";
        executeUpdate(customerTableQuery);
        executeUpdate(customerEntryQuery);
    }

    public String shouldCreateTable() {
        try {
            createCustomerTable();
            conn.createStatement().execute("select * from customers");
            return "Everything went as it should've";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String shouldSelectData() {
        try {
            createCustomerTable();
            String query = "select * from customers where name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "Brian");

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                int age = resultSet.getInt("age");

                return String.format("Age %s", age);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void shouldInsertInResultSet() throws SQLException {
        Statement statement = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );

        ResultSet resultSet = statement.executeQuery("select * from customers");
        resultSet.moveToInsertRow();
        resultSet.updateLong("id", 3L);
        resultSet.updateString("name", "John");
        resultSet.updateInt("age", 18);
        resultSet.insertRow();
        resultSet.moveToCurrentRow();
    }

    public String getMetaData() {
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getTables("postgres", "public", "%", null);
            List<String> tables = new ArrayList<>();
            while (resultSet.next()) {
                tables.add(resultSet.getString(2) + "." + resultSet.getString(3));
            }
            if (tables.contains("public.customers")) {
                return "Everything went as it should've";
            } else {
                throw new SQLException("Something went wrong...");
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}

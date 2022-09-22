package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.User;

import java.sql.*;

public class UserRepository {
    private static final String INSERT_USER_QUERY = "insert into users (username, national_code, birthday, password) " +
            "values (?,?,?,?)";
    private static final String SELECT_USER_BY_USERNAME_QUERY = "select * from users where username = ?";
    private static final String SELECT_USER_BY_NATIONAL_CODE_QUERY = "select * from users where national_code = ?";
    private static final String UPDATE_USER_PASSWORD_QUERY = "update users set password = ? where password = ?";

    public static void createUser(User user){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getNationalCode());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getNationalCode());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
    public static User findUserByUsername(String userName){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_QUERY);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String nationalCode = resultSet.getString(3);
                Date birthday = resultSet.getDate(4);
                String password = resultSet.getString(5);
                return new User(id, username, nationalCode, birthday, password);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }
    public static User findByNationalCode(String national_code){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NATIONAL_CODE_QUERY);
            preparedStatement.setString(1, national_code);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String nationalCode = resultSet.getString(3);
                Date birthday = resultSet.getDate(4);
                return new User(id, username, nationalCode, birthday);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }
    public static void updateUserPassword(String oldPassword, String newPassword){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_QUERY);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, oldPassword);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}

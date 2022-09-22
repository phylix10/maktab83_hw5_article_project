package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Article;
import com.alireza.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private static final String SELECT_PUBLISHED_ARTICLE_QUERY = "select * from article a join users u on u.id = a.user_id " +
            "where is_published = true";
    private static final String SELECT_MY_ARTICLE_QUERY = "select * from article a join users u on u.id = a.user_id " +
            "where u.username = ?";
    private static final String SELECT_ARTICLE_BY_TITLE_QUERY = "select * from article a join users u on u.id = a.user_id " +
            "where a.title = ?";
    private static final String UPDATE_ARTICLE_QUERY = "UPDATE article AS a " +
            "SET title = ?, brief = ?, content = ?, create_date = ? " +
            "FROM users AS u " +
            "WHERE u.username = ? and a.id = ?";
    private static final String UPDATE_ARTICLE_PUBLISH_QUERY = "UPDATE article AS a " +
            "SET is_published = ?" +
            "FROM users AS u " +
            "WHERE u.username = ? and a.title = ?";
    private static final String INSERT_ARTICLE_QUERY = "insert into article(title, brief, content, create_date, user_id) " +
            "values(?, ?, ?, ?, ?)";

    public List<Article> showAllPublishedArticle(){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHED_ARTICLE_QUERY);

            return getArticles(connection, preparedStatement);

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    private List<Article> getArticles(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Article> articleList = new ArrayList<>();
        while (resultSet.next()) {
            Article article = new Article();
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setBrief(resultSet.getString("brief"));
            article.setContent(resultSet.getString("content"));
            article.setCreateDate(resultSet.getDate("create_date"));
            article.setPublished(resultSet.getBoolean("is_published"));
            article.setUserId(
                    new User(resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("national_code"),
                            resultSet.getDate("birthday"),
                            resultSet.getString("password")));
            articleList.add(article);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return articleList;
    }

    public List<Article> showMyArticle(String username){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MY_ARTICLE_QUERY);
            preparedStatement.setString(1, username);

            return getArticles(connection, preparedStatement);

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public String showDetailArticle(String title){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_TITLE_QUERY);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setBrief(resultSet.getString("brief"));
                article.setContent(resultSet.getString("content"));
                article.setCreateDate(resultSet.getDate("create_date"));
                article.setPublished(resultSet.getBoolean("is_published"));
                article.setUserId(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("national_code"),
                        resultSet.getDate("birthday"),
                        resultSet.getString("password")));
                return article.show();
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

    public static void updateArticle(String username, int id, Article article) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTICLE_QUERY);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getBrief());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.setDate(4, article.getCreateDate());
            preparedStatement.setString(5, username);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void updatePublishedArticle(String username, String title, Article article) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTICLE_PUBLISH_QUERY);
            preparedStatement.setBoolean(1, article.getPublished());
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, title);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void createArticle(int id, Article article){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE_QUERY);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getBrief());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.setDate(4, article.getCreateDate());
            preparedStatement.setInt(5, id);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}

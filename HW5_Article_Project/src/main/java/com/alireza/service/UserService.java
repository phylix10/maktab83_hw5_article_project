package com.alireza.service;


import com.alireza.model.Article;
import com.alireza.model.User;
import com.alireza.repository.UserRepository;

import java.sql.Date;

public class UserService {
    private User loggedInUser = null;
    ArticleService articleService = new ArticleService();

    public void register(String username, String nationalCode, String birthday) {
        if (UserRepository.findUserByUsername(username) == null) {
            if (UserRepository.findByNationalCode(nationalCode) == null) {
                User user = new User(username, nationalCode, Date.valueOf(birthday));
                UserRepository.createUser(user);
                System.out.println("Registration was successful");
            } else {
                System.out.println("national code has already been registered, please log in");
            }
        } else {
            System.out.println("username has already been registered, please log in");
        }
    }

    public boolean login(String username, String password) {
        User user = UserRepository.findUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
            else {
                return false;
            }
        } else {
            System.out.println("User with this username and password was not found. Please register");
            return false;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {
        UserRepository.updateUserPassword(oldPassword, newPassword, loggedInUser.getUsername());
        System.out.println("Password changed successfully");
    }

    public void showMyArticles(){
        articleService.showMyArticle(loggedInUser.getUsername());
    }

    public void editMyArticle(int id, Article article){
        articleService.editMyArticle(loggedInUser.getUsername(), id, article);
        System.out.println("Your article has been successfully changed");
    }

    public void addNewArticle(Article article){
        articleService.addNewArticle(loggedInUser.getId(), article);
        System.out.println("An article has been successfully added");
    }

    public void editMyPublishedArticle(String title, Article article){
        articleService.editPublishedArticle(loggedInUser.getUsername(),title,article);
        System.out.println("The publication status of the article has been successfully changed");
    }
}
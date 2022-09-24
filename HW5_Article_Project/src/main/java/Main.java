import com.alireza.model.Article;
import com.alireza.service.ArticleService;
import com.alireza.service.UserService;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserService userService = new UserService();
        ArticleService articleService = new ArticleService();
        boolean flag = true;

        while (flag) {
            printMainMenu();

            System.out.print("Please enter the desired operation number: ");
            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    login(userService, articleService);
                    break;
                case 2:
                    register(userService);
                    break;
                case 3:
                    showPublishedArticle(articleService);
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }
    }

    public static void printMainMenu() {
        System.out.println(
                "――――― Welcome To The Article Project ――――――\n " +
                        "✎ 1. Login\n " +
                        "✎ 2. Register\n " +
                        "✎ 3. View articles\n " +
                        "✎ 4. Exit Menu");
    }

    public static void printLoginMenu() {
        System.out.println(
                "――――― Login was successful ――――――\n " +
                        "✎ 1. Change password\n " +
                        "✎ 2. View your articles\n " +
                        "✎ 3. Edit your articles (Publishing or not publishing)\n " +
                        "✎ 4. Add new article\n " +
                        "✎ 5. Exit");
    }

    public static void printEditArticleMenu() {
        System.out.println(
                "――――― You are in the edit menu of your article ――――――\n " +
                        "✎ 1. Change article information\n " +
                        "✎ 2. Publishing or not publishing the article\n " +
                        "✎ 3. Exit");
    }


    public static void changePassword(UserService userService) {
        System.out.print("Enter your old password: ");
        String oldPassword = scanner.next();

        System.out.print("Enter your new password: ");
        String newPassword = scanner.next();

        userService.changePassword(oldPassword, newPassword);
    }

    public static void viewYourArticles(UserService userService, ArticleService articleService) {
        userService.showMyArticles();

        System.out.print("Please enter the title of each article to view its details: ");
        String title = scanner.next();
        articleService.showDetailArticle(title);
    }

    public static void changeArticleInformation(UserService userService) {
        System.out.println("Enter the ID of the article you want to edit: ");
        scanner.nextLine();
        int id1 = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the title: ");
        String title1 = scanner.nextLine();

        System.out.println("Enter the brief: ");
        String brief1 = scanner.nextLine();

        System.out.println("Enter the content: ");
        String content1 = scanner.nextLine();

        System.out.println("Enter the createDate (year-month-day): ");
        String createDate1 = scanner.nextLine();

        Article article1 = new Article(title1, brief1, content1, Date.valueOf(createDate1));

        userService.editMyArticle(id1, article1);
    }

    public static void editPublishingArticle(UserService userService) {
        System.out.print("Enter the title of the article you want to publish: ");
        String title2 = scanner.next();

        System.out.print("To publish or not to publish (true/false): ");
        String isPublished = scanner.next();

        Article article2 = new Article(title2);

        userService.editMyPublishedArticle(article2, Boolean.parseBoolean(isPublished));
    }

    public static void addArticle(UserService userService) {
        System.out.print("Enter the title: ");
        String title3 = scanner.next();

        System.out.print("Enter the brief: ");
        String brief3 = scanner.next();

        System.out.print("Enter the content: ");
        String content3 = scanner.next();

        System.out.print("Enter the createDate (year-month-day): ");
        String createDate3 = scanner.next();

        Article article3 = new Article(title3, brief3, content3, Date.valueOf(createDate3));

        userService.addNewArticle(article3);
    }

    public static void register(UserService userService) {
        System.out.print("Enter your username: ");
        String username1 = scanner.next();

        System.out.print("Enter your national code (10 digits): ");
        String nationalCode1 = scanner.next();

        System.out.print("Enter your date of birth into the lunar date (year-month-day): ");
        String birthDay1 = scanner.next();

        userService.register(username1, nationalCode1, birthDay1);
    }

    public static void showPublishedArticle(ArticleService articleService) {
        articleService.showAllPublishedArticle();

        System.out.print("Please enter the title of each article to view its details: ");
        String title4 = scanner.next();

        articleService.showDetailArticle(title4);
    }

    public static void editingMenu(UserService userService) {
        boolean flag = true;

        while (flag) {
            printEditArticleMenu();
            System.out.print("Please enter the title of each article to view its details: ");
            int input3 = scanner.nextInt();

            switch (input3) {
                case 1:
                    changeArticleInformation(userService);
                    break;
                case 2:
                    editPublishingArticle(userService);
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }
    }

    public static void login(UserService userService, ArticleService articleService) {
        boolean flag = true;
        System.out.print("Enter your username: ");
        String username = scanner.next();

        System.out.print("Enter your password (If you are logging in for the first time, your password is your national code): ");
        String password = scanner.next();

        if (userService.login(username, password)) {
            while (flag) {
                printLoginMenu();

                System.out.print("Please enter the desired operation number: ");
                int input2 = scanner.nextInt();

                switch (input2) {
                    case 1:
                        changePassword(userService);
                        break;
                    case 2:
                        viewYourArticles(userService, articleService);
                        break;
                    case 3:
                        editingMenu(userService);
                        break;
                    case 4:
                        addArticle(userService);
                        break;
                    case 5:
                        flag = false;
                        break;
                }
            }
        } else {
            System.out.println("User with this username and password was not found. Please register");
        }
    }
}

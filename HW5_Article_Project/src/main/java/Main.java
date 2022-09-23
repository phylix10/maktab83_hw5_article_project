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


        printMainMenu();

        System.out.print("Please enter the desired operation number: ");
        int input = scanner.nextInt();

        switch (input){
            case 1:
                System.out.print("Enter your username: ");
                String username = scanner.next();

                System.out.print("Enter your password (If you are logging in for the first time, your password is your national code): ");
                String password = scanner.next();

                if (userService.login(username, password)){
                    printLoginMenu();

                    System.out.print("Please enter the desired operation number: ");
                    int input2 = scanner.nextInt();

                    switch (input2){
                        case 1:
                            System.out.print("Enter your old password: ");
                            String oldPassword = scanner.next();

                            System.out.print("Enter your new password: ");
                            String newPassword = scanner.next();

                            userService.changePassword(oldPassword, newPassword);
                            break;
                        case 2:
                            userService.showMyArticles();

                            System.out.print("Please enter the title of each article to view its details: ");
                            String title = scanner.next();
                            articleService.showDetailArticle(title);
                            break;
                        case 3:
                            printEditArticleMenu();

                            System.out.print("Please enter the title of each article to view its details: ");
                            int input3 = scanner.nextInt();

                            switch (input3) {
                                case 1:
                                    System.out.print("Enter the ID of the article you want to edit: ");
                                    int id1 = scanner.nextInt();

                                    System.out.print("Enter the title: ");
                                    String title1 = scanner.next();

                                    System.out.print("Enter the brief: ");
                                    String brief1 = scanner.next();

                                    System.out.print("Enter the content: ");
                                    String content1 = scanner.next();

                                    System.out.println("Enter the createDate (year-month-day): ");
                                    String createDate1 = scanner.next();

                                    Article article1 = new Article(title1, brief1, content1, Date.valueOf(createDate1));

                                    userService.editMyArticle(id1, article1);
                                    break;
                                case 2:
                                    System.out.print("Enter the title of the article you want to publish: ");
                                    String title2 = scanner.next();

                                    System.out.print("To publish or not to publish (true/false): ");
                                    boolean isPublished = scanner.hasNext();

                                    Article article2 = new Article(isPublished);

                                    userService.editMyPublishedArticle(title2, article2);
                                    break;
                                case 3:
                                    break;

                            }
                            break;
                        case 4:
                            System.out.print("Enter the title: ");
                            String title3 = scanner.next();

                            System.out.print("Enter the brief: ");
                            String brief3 = scanner.next();

                            System.out.print("Enter the content: ");
                            String content3 = scanner.next();

                            System.out.print("Enter the createDate (year-month-day): ");
                            String createDate3 = scanner.next();

                            Article article3 = new Article(title3,brief3,content3,Date.valueOf(createDate3));

                            userService.addNewArticle(article3);
                            break;
                        case 5:
                            break;
                    }
                    break;
                }
                else {
                    System.out.println("User with this username and password was not found. Please register");
                }
            case 2:
                System.out.print("Enter your username: ");
                String username1 = scanner.next();

                System.out.print("Enter your national code (10 digits): ");
                String nationalCode1 = scanner.next();

                System.out.print("Enter your date of birth into the lunar date (year-month-day): ");
                String birthDay1 = scanner.next();

                userService.register(username1,nationalCode1,birthDay1);
                break;
            case 3:
                articleService.showAllPublishedArticle();

                System.out.print("Please enter the title of each article to view its details: ");
                String title4 = scanner.next();

                articleService.showDetailArticle(title4);
                break;
            case 4:
                break;
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
    public static void printEditArticleMenu(){
        System.out.println(
                "――――― You are in the edit menu of your article ――――――\n " +
                        "✎ 1. Change article information\n " +
                        "✎ 2. Publishing or not publishing the article\n " +
                        "✎ 3. Exit");
    }
}

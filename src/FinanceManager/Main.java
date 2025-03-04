package FinanceManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a single DraftFinanceManager that sets up everything
        DraftFinanceManager manager = new DraftFinanceManager();
        manager.loadUserData(); // Attempt to load existing user data

        // Get references to specialized managers
        SessionManager sessionManager = manager.getSessionManager();
        TransactionManager transactionManager = manager.getTransactionManager();
        SavingsManager savingsManager = manager.getSavingsManager();

        Scanner scanner = new Scanner(System.in);

        // ASCII Art
        String asciiArt = """

                 ________  __                                                          __       __                                                                                        __             ______   _______   _______
                |        \\|  \\                                                        |  \\     /  \\                                                                                      |  \\           /      \\ |       \\ |       \\
                | $$$$$$$$ \\$$ _______    ______   _______    _______   ______        | $$\\   /  $$  ______   _______    ______    ______    ______   ______ ____    ______   _______   _| $$_         |  $$$$$$\\| $$$$$$$\\| $$$$$$$\\
                | $$__    |  \\|       \\  |      \\ |       \\  /       \\ /      \\       | $$$\\ /  $$$ |      \\ |       \\  |      \\  /      \\  /      \\ |      \\    \\  /      \\ |       \\ |   $$ \\        | $$__| $$| $$__/ $$| $$__/ $$
                | $$  \\   | $$| $$$$$$$\\  \\$$$$$$\\| $$$$$$$\\|  $$$$$$$|  $$$$$$\\      | $$$$\\  $$$$  \\$$$$$$\\| $$$$$$$\\  \\$$$$$$\\|  $$$$$$\\|  $$$$$$\\| $$$$$$\\$$$$\\|  $$$$$$\\| $$$$$$$\\ \\$$$$$$        | $$    $$| $$    $$| $$    $$
                | $$$$$   | $$| $$  | $$ /      $$| $$  | $$| $$      | $$    $$      | $$\\$$ $$ $$ /      $$| $$  | $$ /      $$| $$  | $$| $$    $$| $$ | $$ | $$| $$    $$| $$  | $$  | $$ __       | $$$$$$$$| $$$$$$$ | $$$$$$$
                | $$      | $$| $$  | $$|  $$$$$$$| $$  | $$| $$_____ | $$$$$$$$      | $$ \\$$$| $$|  $$$$$$$| $$  | $$|  $$$$$$$| $$__| $$| $$$$$$$$| $$ | $$ | $$| $$$$$$$$| $$  | $$  | $$|  \\      | $$  | $$| $$      | $$
                | $$      | $$| $$  | $$ \\$$    $$| $$  | $$ \\$$     \\ \\$$     \\      | $$  \\$ | $$ \\$$    $$| $$  | $$ \\$$    $$ \\$$    $$ \\$$     \\| $$ | $$ | $$ \\$$     \\| $$  | $$   \\$$  $$      | $$  | $$| $$      | $$
                 \\$$       \\$$ \\$$   \\$$  \\$$$$$$$ \\$$   \\$$  \\$$$$$$$  \\$$$$$$$       \\$$      \\$$  \\$$$$$$$ \\$$   \\$$  \\$$$$$$$ _\\$$$$$$$  \\$$$$$$$ \\$$  \\$$  \\$$  \\$$$$$$$ \\$$   \\$$    \\$$$$        \\$$   \\$$ \\$$       \\$$
                                                                                                                                 |  \\__| $$
                                                                                                                                  \\$$    $$
                                                                                                                                   \\$$$$$$
                """;
        String ascii = """


                $$\\      $$\\           $$\\                                                     $$\\                                                          $$$$$$\\
                $$ | \\$\\ $$ |          $$ |                                                    $$ |                                                        $$  __$$\\
                $$ |$$$\\ $$ | $$$$$$\\  $$ | $$$$$$$\\  $$$$$$\\  $$$$$$\\$$$$\\   $$$$$$\\        $$$$$$\\    $$$$$$\\         $$$$$$\\  $$\\   $$\\  $$$$$$\\        $$ /  $$ | $$$$$$\\   $$$$$$\\
                $$ $$ $$\\$$ |$$  __$$\\ $$ |$$  _____|$$  __$$\\ $$  _$$  _$$\\ $$  __$$\\       \\_$$  _|  $$  __$$\\       $$  __$$\\ $$ |  $$ |$$  __$$\\       $$$$$$$$ |$$  __$$\\ $$  __$$\\
                $$$$  _$$$$ |$$$$$$$$ |$$ |$$ /      $$ /  $$ |$$ / $$ / $$ |$$$$$$$$ |        $$ |    $$ /  $$ |      $$ /  $$ |$$ |  $$ |$$ |  \\__|      $$  __$$ |$$ /  $$ |$$ /  $$ |
                $$$  / \\$$$ |$$   ____|$$ |$$ |      $$ |  $$ |$$ | $$ | $$ |$$   ____|        $$ |$$\\ $$ |  $$ |      $$ |  $$ |$$ |  $$ |$$ |            $$ |  $$ |$$ |  $$ |$$ |  $$ |
                $$  /   \\$$ |\\$$$$$$$\\ $$ |\\$$$$$$$\\ \\$$$$$$  |$$ | $$ | $$ |\\$$$$$$$\\         \\$$$$  |\\$$$$$$  |      \\$$$$$$  |\\$$$$$$  |$$ |            $$ |  $$ |$$$$$$$  |$$$$$$$  |
                \\__/     \\__| \\_______|\\__| \\_______| \\______/ \\__| \\__| \\__| \\_______|         \\____/  \\______/        \\______/  \\______/ \\__|            \\__|  \\__|$$  ____/ $$  ____/
                                                                                                                                                                     $$ |      $$ |
                                                                                                                                                                     $$ |      $$ |
                                                                                                                                                                     \\__|      \\__|
                """;
        String Art = """
        

                        ________  __                            __              __      __                            ________                            __    __            __                            __    __           
                       /        |/  |                          /  |            /  \\    /  |                          /        |                          /  |  /  |          /  |                          /  |  /  |          
                       $$$$$$$$/ $$ |____    ______   _______  $$ |   __       $$  \\  /$$/   ______   __    __       $$$$$$$$/   ______    ______        $$ |  $$ |  _______ $$/  _______    ______        $$ |  $$ |  _______ 
                          $$ |   $$      \\  /      \\ /       \\ $$ |  /  |       $$  \\/$$/   /      \\ /  |  /  |      $$ |__     /      \\  /      \\       $$ |  $$ | /       |/  |/       \\  /      \\       $$ |  $$ | /       |
                          $$ |   $$$$$$$  | $$$$$$  |$$$$$$$  |$$ |_/$$/         $$  $$\\/  /$$$$$$  |$$ |  $$ |      $$    |   /$$$$$$  |/$$$$$$  |      $$ |  $$ |/$$$$$$$/ $$ |$$$$$$$  |/$$$$$$  |      $$ |  $$ |/$$$$$$$/ 
                          $$ |   $$ |  $$ | /    $$ |$$ |  $$ |$$   $$<           $$$$/    $$ |  $$ |$$ |  $$ |      $$$$$/    $$ |  $$ |$$ |  $$/       $$ |  $$ |$$      \\ $$ |$$ |  $$ |$$ |  $$ |      $$ |  $$ |$$      \\ 
                          $$ |   $$ |  $$ |/$$$$$$$ |$$ |  $$ |$$$$$$  \\           $$ |    $$ \\__$$ |$$ \\__$$ |      $$ |      $$ \\__$$ |$$ |            $$ \\__$$ | $$$$$$  |$$ |$$ |  $$ |$$ \\__$$ |      $$ \\__$$ | $$$$$$  |
                          $$ |   $$ |  $$ |$$    $$ |$$ |  $$ |$$ | $$  |          $$ |    $$    $$/ $$    $$/       $$ |      $$    $$/ $$ |            $$    $$/ /     $$/ $$ |$$ |  $$ |$$    $$ |      $$    $$/ /     $$/ 
                          $$/    $$/   $$/  $$$$$$$/ $$/   $$/ $$/   $$/           $$/      $$$$$$/   $$$$$$/        $$/        $$$$$$/  $$/              $$$$$$/  $$$$$$$/  $$/ $$/   $$/  $$$$$$$ |       $$$$$$/  $$$$$$$/  
                                                                                                                                                                                           /  \\__$$ |                          
                                                                                                                                                                                           $$    $$/                           
                                                                                                                                                                                            $$$$$$/                            
                """;
                

        while (true) {
            System.out.println(asciiArt);
            System.out.println(
                    "============================================================================================================================");
            System.out.println(
                    "|                                                  -Selection Menu-                                                        |");
            System.out.println(
                    "|                                   Please select from the following options:                                              |");
            System.out.println(
                    "|                                         1) Log in                                                                        |");
            System.out.println(
                    "|                                         2) Register Now                                                                  |");
            System.out.println(
                    "|                                         3) Exit                                                                          |");
            System.out.println(
                    "============================================================================================================================");

            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            if (option == 1) {
                sessionManager.login(scanner);
            } else if (option == 2) {
                sessionManager.registerUser(scanner);
            } else if (option == 3) {
                System.out.println(Art);
                System.out.println("Exiting program...");
                scanner.close();
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            // Show the second menu only if the user is logged in
            while (sessionManager.getLoggedInUser() != null) {
                System.out.println(ascii);
                System.out.println(
                        "============================================================================================================================");
                System.out.println(
                        "|                                          Personal Finance Manager                                                        |");
                System.out.println(
                        "|                                   Please select from the following options:                                              |");
                System.out.println(
                        "|                                         1) View User Account Status                                                      |");
                System.out.println(
                        "|                                         2) Add Transaction                                                               |");
                System.out.println(
                        "|                                         3) View Transaction History                                                      |");
                System.out.println(
                        "|                                         4) Savings                                                                       |");
                System.out.println(
                        "|                                         5) Logout                                                                        |");
                System.out.println(
                        "============================================================================================================================");
                System.out.print("Please choose an option: ");
                int userOption = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (userOption) {
                     case 1:
                        manager.viewStatus();
                        break;
                    case 2:
                        transactionManager.addTransaction(scanner);
                        break;
                    case 3:
                        transactionManager.viewTransactions(scanner);
                        break;
                    case 4:
                        savingsManager.savingsMenu(scanner);
                        break;
                    case 5:
                        sessionManager.logout();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
            
        }
        
    }
}

import basics.Input;
import basics.Print;

public class App {

    public static String[] data = {};

    public static void main(String[] args) {

        Auth authenticte = new Auth();
        Print.out("\nHit 'commands' to list all commands\n");

        String commandLists = "\nregister admin \nregister user \nlogin admin \nlogin user \nadmin pannel \nuser pannel \ncommands \nexit\n\n";
        while (true) {

            String command = Input.scan("Enter Command >>>");

            switch (command) {
                case "exit" -> {
                    return;
                }
                case "register admin" -> {
                    Registration admin = new Registration();
                    admin.adminRegistration();
                }
                case "register user" -> {
                    Registration user = new Registration();
                    user.userRegistration();
                }
                case "login user" -> {
                    if (authenticte.userLogin()) {
                        data = authenticte.detail();
                    }
                }
                case "login admin" -> {
                    if (authenticte.adminLogin()) {
                        data = authenticte.detail();
                    }
                }

                case "user pannel" -> {
                    try {
                        if (data[1].equals("user")) {
                            Dashboard user = new Dashboard(data);
                            user.userPannel();
                        } else {
                            Print.out("\nMust be logged in as user to access!\n\n");
                        }
                    } catch (Exception e) {
                        Print.out("\nMust be logged in as user to access!\n\n");
                    }
                }

                case "admin pannel" -> {
                    try {
                        if (data[1].equals("admin")) {
                            Dashboard admin = new Dashboard(data);
                            admin.adminPannel();

                        } else {
                            Print.out("\nMust be logged in as admin to access!\n\n");
                        }
                    } catch (Exception e) {
                        Print.out("\nMust be logged in as admin to access!\n\n");
                    }
                }

                case "commands" -> {
                    Print.out(commandLists);
                }

                default -> Print.out("\nInvalid Command. Hit 'commands' to list all commands.\n\n");
            }

        }

    }
}

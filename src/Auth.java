import basics.FileHandler;
import basics.Input;
import basics.Print;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Auth {
    private String data;
    private String role;

    public boolean userLogin() {
        String phone = Input.scan("Enter your Phone number : ");
        String password = Input.scan("Enter your Password : ");

        try {
            FileHandler file = new FileHandler("src/data/user.txt");
            String[] oldData = file.read();
            List<String> user = new ArrayList<>(Arrays.asList(oldData));

            for (String userData : user) {
                String[] userDetails = userData.split(",");
                // Exclude password and check if phone and password match
                if (userDetails[3].equals(phone) && userDetails[4].equals(password)) {
                    // Only store the details excluding the password
                    this.data = String.join(",", userDetails[0], userDetails[1], userDetails[2], userDetails[3]);
                    this.role = "user";
                    Print.out("\nYou logged in as a user!\n\n");
                    return true;
                }
            }
        } catch (Exception e) {
            Print.out("\nIncorrect Credentials!\n\n");
            return false;
        }
        Print.out("\nIncorrect Credentials!\n\n");
        return false;
    }

    public boolean adminLogin() {
        String phone = Input.scan("Enter your Phone number : ");
        String password = Input.scan("Enter your Password : ");

        try {
            FileHandler file = new FileHandler("src/data/admin.txt");
            String[] oldData = file.read();
            List<String> user = new ArrayList<>(Arrays.asList(oldData));

            for (String userData : user) {
                String[] userDetails = userData.split(",");
                // Exclude password and check if phone and password match
                if (userDetails[3].equals(phone) && userDetails[4].equals(password)) {
                    // Only store the details excluding the password
                    this.data = String.join(",", userDetails[0], userDetails[1], userDetails[2], userDetails[3]);
                    this.role = "admin";
                    Print.out("\nYou logged in as an admin!\n\n");
                    return true;
                }
            }
        } catch (Exception e) {
            Print.out("\nIncorrect Credentials!\n\n");
            return false;
        }
        Print.out("\nIncorrect Credentials!\n\n");
        return false;
    }

    public String[] detail() {
        if (this.data == null || this.role == null) {
            Print.out("\nYou are not logged in!\n\n");
        }
        String[] dataList = { this.data, this.role };
        return dataList;
    }
}

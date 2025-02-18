import basics.FileHandler;
import basics.Input;
import basics.Print;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Registration {

    private String fullname;
    private String citizenshipNumber;
    private String address;
    private String phone;
    private String password;

    public void takeInputs() {
        try {
            this.fullname = Input.scan("Enter Your name : ");
            if (this.fullname.isEmpty())
                throw new Exception("Name cannot be empty!");

            this.citizenshipNumber = Input.scan("Enter your Citizenship No : ");
            if (this.citizenshipNumber.isEmpty())
                throw new Exception("Citizenship Number cannot be empty!");

            this.address = Input.scan("Enter your Address : ");
            if (this.address.isEmpty())
                throw new Exception("Address cannot be empty!");

            this.phone = Input.scan("Enter your Phone : ");
            if (this.phone.isEmpty())
                throw new Exception("Phone cannot be empty!");

            this.password = Input.scan("Enter a password : ");
            if (this.password.isEmpty())
                throw new Exception("Password cannot be empty!");

        } catch (Exception e) {
            Print.out("\nError: " + e.getMessage() + "\n");
            takeInputs(); // Retry if any field is empty
        }
    }

    public boolean validAdmin() {
        String key = Input.scan("Enter Admin Key : ");
        return key.equals("iAmAdmin"); // Fixed string comparison
    }

    public void userRegistration() {
        this.takeInputs();
        FileHandler file = new FileHandler("src/data/user.txt");
        String[] oldData = file.read();
        List<String> userData = new ArrayList<>(Arrays.asList(oldData));

        for (String data : userData) {
            if (data.contains(this.phone) || data.contains(this.citizenshipNumber)) {
                Print.out("\nThe user with the same Phone or Citizenship No exists.\n\n");
                return;
            }
        }

        String newData = this.fullname + "," + this.citizenshipNumber + "," + this.address + ","
                + this.phone + "," + this.password;

        userData.add(newData);
        file.write(String.join("\n", userData));
        Print.out("\nRegistered Successfully!\n\n");
    }

    public void adminRegistration() {
        if (this.validAdmin()) {
            this.takeInputs();
            FileHandler file = new FileHandler("src/data/admin.txt");
            String[] oldData = file.read();
            List<String> adminData = new ArrayList<>(Arrays.asList(oldData));

            for (String data : adminData) {
                if (data.contains(this.phone) || data.contains(this.citizenshipNumber)) {
                    Print.out("\nThe Admin with the same Phone or Citizenship No exists.\n\n");
                    return;
                }
            }

            String newData = this.fullname + "," + this.citizenshipNumber + "," + this.address + ","
                    + this.phone + "," + this.password;

            adminData.add(newData);
            file.write(String.join("\n", adminData));
            Print.out("\nRegistered Successfully!\n\n");
        } else {
            Print.out("\nAdmin Key didn't match!\n\n");
        }
    }
}

import basics.FileHandler;
import basics.Input;
import basics.Print;
import java.util.ArrayList;
import java.util.Arrays;

public class Dashboard {

    private String[] data = {};

    public Dashboard(String[] user) {
        this.data = user;
    }

    private void confirm(String phone, String type) {
        try {
            FileHandler file = new FileHandler("src/data/applicants.txt");
            String[] strs = file.read();
            ArrayList<String> newStrList = new ArrayList<>();

            boolean found = false;
            for (String stringData : strs) {
                String[] splitted = stringData.split(",");
                if (splitted.length < 6) {
                    Print.out("\nError: Inconsistent data format in applicants file.\n\n");
                    return;
                }
                if (splitted[3].equals(phone) && splitted[5].equals("applied") && splitted[4].equals(type)) {
                    String newStr = stringData.replace("applied", "verified");
                    newStrList.add(newStr); // Add the updated string to the list
                    found = true;
                } else {
                    newStrList.add(stringData); // Add unchanged string if no match
                }
            }

            // Write all updated data to the file, replacing old data
            if (found) {
                file.write(String.join("\n", newStrList));
                Print.out("\nProvided license to " + phone + ". \n\n");
            } else {
                Print.out("\n" + phone + " is not in applicants list. \n\n");
            }
        } catch (Exception e) {
            Print.out("\nError processing the applicants list: " + e.getMessage() + "\n\n");
        }
    }

    private void listApplicants() {
        try {
            FileHandler file = new FileHandler("src/data/applicants.txt");
            String[] strs = file.read();
            ArrayList<String> newStrList = new ArrayList<>();

            for (String stringData : strs) {
                if (stringData.contains("applied")) {
                    newStrList.add(stringData);
                }
            }
            Print.out(String.join("\n", newStrList) + "\n\n");
        } catch (Exception e) {
            Print.out("\nError retrieving applicants: " + e.getMessage() + "\n\n");
        }
    }

    public void userPannel() {
        try {
            String type = Input.scan("Please Enter Application Type (A/B/C/D) : ");
            String userData = this.data[0];

            // Corrected String.join usage
            String newData = String.join(",", userData, type);

            FileHandler file = new FileHandler("src/data/applicants.txt");

            String[] StringLists = file.read();

            String status = "";

            for (String str : StringLists) {
                if (str.contains(newData)) {
                    String[] splittedStrings = str.split(",");
                    if (splittedStrings.length < 6) {
                        Print.out("\nError: Inconsistent data format.\n\n");
                        return;
                    }
                    status = splittedStrings[splittedStrings.length - 1];
                }
            }

            switch (status) {
                case "applied" -> {
                    Print.out("\nYou have already applied!\n\n");
                    return;
                }
                case "verified" -> {
                    Print.out("\nYou already have this licence!\n\n");
                    return;
                }
                default -> {
                    ArrayList<String> newArrayList = new ArrayList<>(Arrays.asList(StringLists));
                    // Corrected String.join usage for file write
                    newArrayList.add(String.join(",", newData, "applied"));
                    file.write(String.join("\n", newArrayList));
                    Print.out("\nYour application is registered!\n\n");
                }
            }
        } catch (Exception e) {
            Print.out("\nError processing your application: " + e.getMessage() + "\n\n");
        }
    }

    public void adminPannel() {
        while (true) {
            try {
                String cmd = Input.scan("Enter Commands (ADMIN) >>> ");

                // cmds
                if ("commands".equals(cmd)) {
                    Print.out("\nlist applicants\nconfirm <phone> <type>\nexit\n\n");
                } else if ("list applicants".equals(cmd)) {
                    this.listApplicants();
                } else if (cmd.startsWith("confirm ")) {
                    String[] cmdParts = cmd.split(" ");
                    if (cmdParts.length != 3) {
                        Print.out("\nError: Incorrect format for 'confirm' command. Use: confirm <phone> <type>\n\n");
                        continue;
                    }
                    String phone = cmdParts[1];
                    String type = cmdParts[2];
                    this.confirm(phone, type);
                } else if ("exit".equals(cmd)) {
                    Print.out("\nExiting admin panel... \n\n");
                    break;
                } else {
                    Print.out("\nIncorrect Command. Hit 'commands' to list all commands. \n\n");
                }

            } catch (Exception e) {
                Print.out("\nError processing command: " + e.getMessage() + "\n\n");
            }
        }
    }
}

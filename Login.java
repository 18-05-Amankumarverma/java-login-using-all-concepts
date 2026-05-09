import login.InputSanitizer;
import login.status;
import java.time.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

interface auth{
    public String createAccount(String username,String email,String password);
    public String login(String email,String password);
}

class ErrorsException extends Exception{
    ErrorsException(String message){
        super(message);
    }
}

public class Login implements auth{
    private String username;
    private String email;
    private String password;

    public String createAccount(String username,String email,String password){
        InputSanitizer obj = new InputSanitizer();

        this.username = obj.sanitize(username);
        this.email = obj.sanitize(email);
        this.password = obj.sanitize(password);

        LocalDateTime td =  LocalDateTime.now();

        try{
            FileWriter fw = new FileWriter("db_auth.txt",true);
            fw.write("\n"+td+","+this.username +","+ this.email +","+this.password );
            fw.close();
            return status.SUCCESS_ACC.getDescription();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return status.FAILED_ACC.getDescription();
    }

    public String login(String email,String password) {
        InputSanitizer obj = new InputSanitizer();

        String sanitizeEmail = obj.sanitize(email);
        String sanitizePassword = obj.sanitize(password);

       try{
           File fs = new File("db_auth.txt");
           Scanner sc = new Scanner(fs);
           while (sc.hasNextLine()) {
               String data = sc.nextLine();
               String[] splittedData = data.split(",");
               for(String value : splittedData){
                   System.out.println(value);
               }
               if(sanitizeEmail.equals(splittedData[2]) && sanitizePassword.equals(splittedData[3])) {
                   sc.close();
                   return status.SUCCESS_LOG.getDescription();
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
            return status.FAILED_LOG.getDescription();
    }



    public static void main(String[] args) {
        Login lg = new Login();
        Scanner sc = new Scanner(System.in);
    Boolean flag = true;
        while(flag){
            System.out.println("""
                    1. Create Account
                    2. Login
                    3. Exit
                    
                    """);
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter your Name :");
                    String name = sc.nextLine();
                    System.out.println("Enter your email :");
                    String email = sc.nextLine();
                    System.out.println("Enter your password :");
                    String password = sc.nextLine();

                    System.out.println("\n" + lg.createAccount(name,email,password));
                    break;
                case 2:
                    System.out.println("Enter your email : ");
                    String email_ = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password_ = sc.nextLine();
                    System.out.println("\n" + lg.login(email_,password_));
                    break;

                default :
                    System.out.println("authentication is  stoped ");
                    flag = false;

            }
        }



    }
}
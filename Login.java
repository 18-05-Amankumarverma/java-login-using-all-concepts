import login.InputSanitizer;
import login.status;

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

        return status.SUCCESS_ACC.getDescription();
    }

    public String login(String email,String password){
        InputSanitizer obj = new InputSanitizer();

        String sanitizeEmail = obj.sanitize(email);
        String sanitizePassword = obj.sanitize(password);

        if(sanitizeEmail.equals(this.email) && sanitizePassword.equals(this.password)){
            return status.SUCCESS_LOG.getDescription();
        }
        else{
            return status.FAILED_LOG.getDescription();
        }
    }

    public static void main(String[] args) {
        Login lg = new Login();
        System.out.println(lg.createAccount("aman","aman@gmail.com","aman@123"));
        System.out.println(lg.login("aman@gmail.com","aman@123"));
    }
}
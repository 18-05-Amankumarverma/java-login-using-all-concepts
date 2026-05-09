package login;
public enum status{
    SUCCESS_ACC("your account is created successfully"),
    FAILED_ACC("Account creation Failed"),
    SUCCESS_LOG("Login sucessfully"),
    FAILED_LOG("Login Failed"),
    FAILED("Input cannot be Blank");

    private String description;
    status(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }
}
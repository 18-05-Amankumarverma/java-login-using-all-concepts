package login;


public class InputSanitizer{
    public String sanitize(String input){
        if(input.length()==0) return status.FAILED.getDescription();
        return input.toLowerCase().trim();
    }
}
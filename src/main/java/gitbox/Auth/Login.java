package gitbox.Auth;

import org.springframework.boot.SpringApplication;
import gitbox.models.UserTable;
import gitbox.Repositry.UserRepositry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

class LoginResponse{
    private String message;
    private String token;
    public LoginResponse(String message, String token){
        this.message = message;
        this.token = token;
    }
    public String getMessage(){
        return message;
    }
    public String getToken(){
        return token;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setToken(String token){
        this.token = token;
    }
}

class LoginDetails{
    private String username;
    private String password;
    private String email;
    private Long id;
    public LoginDetails(String username, String password, String email, Long id){
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public Long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){
        this.email = email;
    }

}

@RestController
public class Login{
@Autowired
        private UserRepositry userRepositry;

    @PostMapping("/login") 
        public ResponseEntity<LoginResponse> login(@RequestBody LoginDetails loginDetails){
            Optional<UserTable> user = userRepositry.findById(loginDetails.getId());
            if(user.isEmpty()){
                return new ResponseEntity<>(new LoginResponse("User not found", null), HttpStatus.NOT_FOUND);
            }
            if(user.getPassword().equals(loginDetails.getPassword())){
                return new ResponseEntity<>(new LoginResponse("Login successful", "token"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new LoginResponse("Invalid password", null), HttpStatus.UNAUTHORIZED);
        }

    }

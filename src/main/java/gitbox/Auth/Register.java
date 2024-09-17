package gitbox.Auth;

import gitbox.models.UserTable;
import gitbox.Repositry.UserRepositry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.SecureRandom;
import java.math.BigInteger;


@RestController
public class Register {

    private static SecureRandom random = new SecureRandom();

    @Autowired
    private UserRepositry userRepositry;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDetails registerDetails) {
        String token = new BigInteger(130, random).toString(32);
        UserTable user = userRepositry.findByEmail(registerDetails.getEmail());
        if (user != null) {
            return new ResponseEntity<>(new RegisterResponse(registerDetails.getEmail(),"User already exists", null,null), HttpStatus.CONFLICT);
        }
        user = new UserTable(registerDetails.getUsername(), registerDetails.getPassword(), registerDetails.getEmail(), token);
        userRepositry.save(user);
        return new ResponseEntity<>(new RegisterResponse(registerDetails.getEmail(),"User created successfully", token, registerDetails.getUsername()), HttpStatus.CREATED);
    }
}

class RegisterResponse {
    private String email;
    private String message;
    private String token;
    private String username;

    public RegisterResponse(String email, String message, String token, String username) {
        this.email = email;
        this.message = message;
        this.token = token;
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}

class RegisterDetails {
    private String username;
    private String password;
    private String email;

    public RegisterDetails(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
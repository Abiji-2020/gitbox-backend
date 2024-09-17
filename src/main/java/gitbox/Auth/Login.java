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
public class Login {

    private static SecureRandom random = new SecureRandom();

    @Autowired
    private UserRepositry userRepositry;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDetails loginDetails) {
        UserTable user = userRepositry.findByEmail(loginDetails.getEmail());
        String token = new BigInteger(130, random).toString(32);
        if (user == null) {
            return new ResponseEntity<>(new LoginResponse(loginDetails.getEmail(), "User not found", null, null),
                    HttpStatus.NOT_FOUND);
        }

        // UserTable userDetails = user.get();
        if (user.getPassword().equals(loginDetails.getPassword())) {

            return new ResponseEntity<>(
                    new LoginResponse(loginDetails.getEmail(), "Login successful", token, user.getUsername()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new LoginResponse(loginDetails.getEmail(), "Invalid password", null, null),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}

class LoginResponse {
    private String email;
    private String message;
    private String token;
    private String username;

    public LoginResponse(String email, String message, String token, String username) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

class LoginDetails {
    private String password;
    private String email;

    public LoginDetails(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public LoginDetails() {
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

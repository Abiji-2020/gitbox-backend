package gitbox.Auth;

import gitbox.models.UserTable;
import gitbox.Repositry.UserRepositry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.security.SecureRandom;
import java.math.BigInteger;


@RestController
public class Login {


    private static SecureRandom random = new SecureRandom();
    
    @Autowired
    private UserRepositry userRepositry;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDetails loginDetails) {
        Optional<UserTable> user = userRepositry.findById(loginDetails.getId());
        String token = new BigInteger(130, random).toString(32);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new LoginResponse("User not found", token), HttpStatus.NOT_FOUND);
        }

        UserTable userDetails = user.get();
        if (userDetails.getPassword().equals(loginDetails.getPassword())) {
            
            return new ResponseEntity<>(new LoginResponse("Login successful", "token"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new LoginResponse("Invalid password", null), HttpStatus.UNAUTHORIZED);
        }
    }
}

class LoginResponse {
    private String message;
    private String token;

    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
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
}

class LoginDetails {
    private String username;
    private String password;
    private String email;
    private Long id;

    public LoginDetails(String username, String password, String email, Long id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


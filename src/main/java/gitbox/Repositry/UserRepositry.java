package gitbox.Repositry;

import gitbox.models.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<UserTable, Long> {
    UserTable findByUsername(String username);
    UserTable findByEmail(String email);
    UserTable findByUsernameAndPassword(String username, String password);
    String getPassword();
}
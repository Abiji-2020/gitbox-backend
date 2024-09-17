package gitbox.Repositry;

import gitbox.models.RepoBranch;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface RepoBranchRepositry extends JpaRepository<RepoBranch, String> {
    List<RepoBranch> findByUsername(String username);
    List<RepoBranch> findByUsernameAndRepoName(String username, String repoName);
    List<RepoBranch> findByUsernameAndRepoNameAndBranchName(String username, String repoName, String branchName);
    RepoBranch findByCommit(String commit);
}
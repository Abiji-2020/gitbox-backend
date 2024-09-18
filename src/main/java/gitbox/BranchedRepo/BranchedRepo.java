package gitbox.BranchedRepo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import gitbox.models.RepoBranch;
import gitbox.Repositry.RepoBranchRepositry;

@RestController
@RequestMapping("/branchedRepo")
public class BranchedRepo {

    @Autowired
    private RepoBranchRepositry repoBranchRepositry;

    @PostMapping("/create")
    public ResponseEntity<RepoBranch> createRepo(@RequestBody RepoEntity folder) {

        byte[] byteFolder = convertToByteArray(folder);

        System.out.println(folder);
        RepoBranch repoBranch = new RepoBranch(folder.getUsername(), folder.getRepoName(), folder.getBranchName(),
                folder.getFolderMetadata().getCommit(), folder.getFolderMetadata().getEditedAt(), byteFolder);
        repoBranchRepositry.save(repoBranch);

        return ResponseEntity.ok(repoBranch);
    }

    public static byte[] convertToByteArray(RepoEntity folder) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(folder);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static RepoEntity convertToRepoEntity(RepoBranch repoBranch) {
        try {
            return (RepoEntity) new ObjectInputStream(new ByteArrayInputStream(repoBranch.getFolder())).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<RepoEntity>> getAllRepos(@RequestBody getAllRequest request) {
        List<RepoBranch> repoBranch = repoBranchRepositry.findByUsername(request.getUsername());
        Collections.sort(repoBranch, (a, b) -> a.getEditedAt().compareTo(b.getEditedAt()));
        List<RepoEntity> repoEntities = new ArrayList<>();
        for (RepoBranch repoBranch_iter : repoBranch) {
            repoEntities.add(convertToRepoEntity(repoBranch_iter));
        }
        return ResponseEntity.ok(repoEntities);

    }

    @GetMapping("/getLatest")
    public ResponseEntity<RepoEntity> getLatest(@RequestBody getLatestRequest request) {

        List<RepoBranch> repoBranch = repoBranchRepositry.findByUsernameAndRepoNameAndBranchName(request.getUsername(),request.getRepoName(),request.getBranchName());
        Collections.sort(repoBranch, (a, b) -> a.getEditedAt().compareTo(b.getEditedAt()));
        int index = repoBranch.size() - 1;
        return ResponseEntity.ok(convertToRepoEntity(repoBranch.get(index)));

    }

    @PostMapping("/update")
    public ResponseEntity<updateResponse> updateRepo(@RequestBody RepoEntity folder) {

        byte[] byteFolder = convertToByteArray(folder);

        RepoBranch repoBranch = new RepoBranch(folder.getUsername(), folder.getRepoName(), folder.getBranchName(),
                folder.getFolderMetadata().getCommit(), folder.getFolderMetadata().getEditedAt(), byteFolder);
        repoBranchRepositry.save(repoBranch);

        return ResponseEntity.ok(new updateResponse("Updated Successfully" + repoBranch.getRepoName()));
    }

}

class updateResponse {
    private String message;

    public updateResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public updateResponse() {
    }
}

class getLatestRequest {
    private String username;
    private String branchName;
    private String repoName;

    public getLatestRequest(String username, String branchName, String repoName) {
        this.username = username;
        this.branchName = branchName;
        this.repoName = repoName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public getLatestRequest() {
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

}

class getAllRequest {
    private String username;

    public getAllRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public getAllRequest() {
    }
}

class CreateRepoResponse {
    private String message;

    public CreateRepoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CreateRepoResponse() {
    }
}

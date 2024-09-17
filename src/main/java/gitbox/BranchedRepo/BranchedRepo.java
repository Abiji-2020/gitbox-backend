package gitbox.BranchedRepo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        RepoBranch repoBranch = new RepoBranch(folder.getUsername(), folder.getRepoName(), folder.getBranchName(), folder.getFolderMetadata().getCommit(), folder.getFolderMetadata().getEditedAt(), byteFolder);
        repoBranchRepositry.save(repoBranch);

        return ResponseEntity.ok(repoBranch);
    }

    public static byte[] convertToByteArray(RepoEntity folder) {
        try{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(folder);
        oos.flush();
        return bos.toByteArray();
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }
    
}

class CreateRepoResponse{
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


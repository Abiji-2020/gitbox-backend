package gitbox.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class RepoBranch {
    
    private String specialId;
    private String username;
    private String repoName;
    private String branchName;
    @Id
    private String commit;
    private String editedAt;
    @Lob
    @Column(name = "folder")
    private byte[] folder;

    public RepoBranch() {
    }

    public RepoBranch(String username, String repoName, String branchName, String commit, String editedAt,
            byte[] folder) {
        this.specialId = username + repoName + branchName;
        this.username = username;
        this.repoName = repoName;
        this.branchName = branchName;
        this.commit = commit;
        this.editedAt = editedAt;
        this.folder = folder;
    }

    public String getSpecialId() {
        return specialId;
    }

    public void setSpecialId(String specialId) {
        this.specialId = specialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(String editedAt) {
        this.editedAt = editedAt;
    }

    public byte[] getFolder() {
        return folder;
    }

    public void setFolder(byte[] folder) {
        this.folder = folder;
    }

}

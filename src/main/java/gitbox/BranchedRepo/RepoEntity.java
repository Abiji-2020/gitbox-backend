package gitbox.BranchedRepo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Transient;

import java.io.Serializable;

public class RepoEntity  implements Serializable {
    @JsonProperty("folder")
    private Folder folder;
    @JsonProperty("folderMetadata")
    private FolderMetadata folderMetadata;

    private String specialId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("repoName")
    private String repoName;

    @JsonProperty("branchName")
    private String branchName;

    public RepoEntity() {

    }

    public RepoEntity(Folder folder, FolderMetadata folderMetadata, String username, String repoName,
            String branchName) {
        this.folder = folder;
        this.folderMetadata = folderMetadata;
        this.username = username;
        this.repoName = repoName;
        this.branchName = branchName;
        this.specialId = username + repoName + branchName;
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

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public FolderMetadata getFolderMetadata() {
        return folderMetadata;
    }

    public void setFolderMetadata(FolderMetadata folderMetadata) {
        this.folderMetadata = folderMetadata;
    }

}

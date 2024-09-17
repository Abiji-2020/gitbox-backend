package gitbox.BranchedRepo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FolderMetadata implements Serializable {
    @JsonProperty("branch")
    private String branch;
    @JsonProperty("commit")
    private String commit;
    @JsonProperty("EditedAt")
    private String EditedAt;

    public FolderMetadata() {

    }

    public FolderMetadata(String branch, String commit, String EditedAt) {
        this.branch = branch;
        this.commit = commit;
        this.EditedAt = EditedAt;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getEditedAt() {
        return EditedAt;
    }

    public void setEditedAt(String EditedAt) {
        this.EditedAt = EditedAt;
    }

}

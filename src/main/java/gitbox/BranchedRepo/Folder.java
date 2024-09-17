package gitbox.BranchedRepo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.List;
import java.io.Serializable;

public class Folder  implements Serializable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("subFolders")
    private Map<String, Folder> subFolders;
    @JsonProperty("files")
    private List<String> files;

    public Folder() {

    }

    public Folder(String name, Map<String, Folder> subFolders, List<String> files) {
        this.name = name;
        this.subFolders = subFolders;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(Map<String, Folder> subFolders) {
        this.subFolders = subFolders;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

}

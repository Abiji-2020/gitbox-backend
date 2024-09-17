package gitbox.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gitbox.service.ProjectUserService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectUserService projectUserService;

    @PostMapping("/create")
    public ResponseEntity<String> createProjectTable(@RequestBody CreateTableRequest projectRequest) {

        String tableName = projectRequest.getUsername();
        tableName = tableName.replaceAll("[^a-zA-Z0-9_]", "");
        projectUserService.createProjectTable(tableName);
        return ResponseEntity.ok("Table created successfully");

    }

}

// Models used in the request and response

class CreateTableRequest {
    private String username;

    public CreateTableRequest(String username, String projectName) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

class CreateProjectRequest {
    private String username;
    private String projectName;
    private String projectDescription;
    private String version;
    private String link;

    public CreateProjectRequest(String username, String projectName, String projectDescription, String version,
            String link) {
        this.username = username;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.version = version;
        this.link = link;
    }

    public String getUsername() {
        return username;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getVersion() {
        return version;
    }

    public String getLink() {
        return link;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
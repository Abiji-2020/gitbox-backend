package gitbox.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import gitbox.service.ProjectUserService;
import gitbox.models.ProjectEntity;
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectUserService projectUserService;

    @PostMapping("/create")
    public ResponseEntity<CreateTableResponse> createProjectTable(@RequestBody CreateTableRequest projectRequest) {

        String tableName = projectRequest.getUsername();
        tableName = tableName.replaceAll("[^a-zA-Z0-9_]", "");
        projectUserService.createProjectTable(tableName);
        return ResponseEntity.ok(new CreateTableResponse(tableName + " table created successfully"));

    }

    @PostMapping("/add")
    public ResponseEntity<AddProjectResponse> addProject(@RequestBody AddProjectRequest projectRequest) {
        String tableName = projectRequest.getUsername();
        tableName = tableName.replaceAll("[^a-zA-Z0-9_]", "");
        projectUserService.saveProject(tableName, projectRequest.getProjectName(),
                projectRequest.getProjectDescription(), projectRequest.getVersion(), projectRequest.getLink());
        return ResponseEntity.ok(new AddProjectResponse(tableName + " project added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<getProjectsResponse> getProjects(
            @RequestBody getProjectsRequest projectRequest
    ) {
        return ResponseEntity.ok( new getProjectsResponse(projectUserService.getProjects(projectRequest.getUsername())));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<CreateTableResponse> deleteProject(@RequestBody CreateTableRequest projectRequest) {
        String tableName = projectRequest.getUsername();
        tableName = tableName.replaceAll("[^a-zA-Z0-9_]", "");
        projectUserService.dropProjectTable(tableName);
        return ResponseEntity.ok(new CreateTableResponse(tableName + " table deleted successfully"));
    }

    @PostMapping("/update")
    public ResponseEntity<updateResponse> updateProject(@RequestBody updateRequest projectRequest) {
        String tableName = projectRequest.getUsername();
        tableName = tableName.replaceAll("[^a-zA-Z0-9_]", "");
        projectUserService.updateProject(tableName, projectRequest.getProjectName(), projectRequest.getProjectDescription(),
                projectRequest.getVersion(), projectRequest.getLink());
        return ResponseEntity.ok(new updateResponse("Project updated successfully"));
    }
}

// Models used in the request and response

class updateRequest{
    private String username;
    private String projectName;
    private String projectDescription;
    private String version;
    private String link;

    public updateRequest(String username, String projectName, String projectDescription, String version, String link) {
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

class updateResponse{
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
}

class getProjectsRequest {
    private String username;
    public getProjectsRequest() {
    }
    public getProjectsRequest(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}

class getProjectsResponse {
    private List<ProjectEntity> projects;

    public getProjectsResponse(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

}

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

class CreateTableResponse {
    private String message;

    public CreateTableResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

class AddProjectRequest {
    private String username;
    private String projectName;
    private String projectDescription;
    private String version;
    private String link;

    public AddProjectRequest(String username, String projectName, String projectDescription, String version,
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

class AddProjectResponse{
    private String message;


    public AddProjectResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
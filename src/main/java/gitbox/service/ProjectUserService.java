package gitbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gitbox.Repositry.ProjectRepositry;
import java.util.List;
import gitbox.models.ProjectEntity;

@Service
public class ProjectUserService {
    @Autowired
    private ProjectRepositry projectRepository;

    @Autowired
    private ProjectService projectService;

    public void createProjectTable(String tableName) {
        projectService.createProjectTable(tableName);
    }
    public void dropProjectTable(String tableName) {
        projectService.dropProjectTable(tableName);
    }

    public void saveProject(String tableName, String projectName, String projectDescription, String version, String link) {
        projectRepository.saveProject(tableName, projectName, projectDescription, version, link);
    }

    public void deleteProject(String tableName, String projectName) {
        projectRepository.deleteProject(tableName, projectName);
    }

    public void updateProject(String tableName, String projectName, String projectDescription, String version, String link, int id) {
        projectRepository.updateProject(tableName, projectName, projectDescription, version, link, id);
    }

    public List<ProjectEntity> getProjects(String tableName) {
        return projectRepository.getProjects(tableName);
    }
    
}

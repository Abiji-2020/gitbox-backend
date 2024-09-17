package gitbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gitbox.Repositry.ProjectRepositry;

import java.util.ArrayList;
import java.util.List;
import gitbox.models.ProjectEntity;
import jakarta.transaction.Transactional;

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
    @Transactional
    public void saveProject(String tableName, String projectName, String projectDescription, String version, String link) {
        projectRepository.saveProject(tableName, projectName, projectDescription, version, link);
    }
    @Transactional
    public void deleteProject(String tableName, String projectName) {
        projectRepository.deleteProject(tableName, projectName);
    }
    @Transactional
    public void updateProject(String tableName, String projectName, String projectDescription, String version, String link, int id) {
        projectRepository.updateProject(tableName, projectName, projectDescription, version, link, id);
    }

    @Transactional
    public List<ProjectEntity> getProjects(String tableName) {
    List<Object[]> results = projectRepository.getProjects(tableName);
    List<ProjectEntity> projects = new ArrayList<>();

    for (Object[] result : results) {
        // Assuming the order in Object[] matches the column order in the database
        // and that ProjectEntity has a constructor or setters for these values
        ProjectEntity project = new ProjectEntity();
        project.setId(((Number) result[0]).longValue()); // Adjust index based on column position
        project.setProjectName((String) result[1]);
        project.setProjectDescription((String) result[2]);
        project.setVersion((String) result[3]);
        project.setLink((String) result[4]);
        projects.add(project);
    }

    return projects;
}

    
}

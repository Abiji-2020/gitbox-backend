package gitbox.Repositry;

import java.util.List;

public interface ProjectCustomRepository {
    List<Object[]> getProjects(String tableName);
    void saveProject(String tableName, String projectName, String projectDescription, String version, String link);
    void deleteProject(String tableName, String projectName);
    void updateProject(String tableName, String projectName, String projectDescription, String version, String link, int id);
}

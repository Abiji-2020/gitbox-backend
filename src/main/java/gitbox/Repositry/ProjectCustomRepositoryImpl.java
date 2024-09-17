package gitbox.Repositry;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectCustomRepositoryImpl implements ProjectCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> getProjects(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public void saveProject(String tableName, String projectName, String projectDescription, String version, String link) {
        String sql = "INSERT INTO " + tableName + " (projectName, projectDescription, version, link) VALUES (:projectName, :projectDescription, :version, :link)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("projectName", projectName);
        query.setParameter("projectDescription", projectDescription);
        query.setParameter("version", version);
        query.setParameter("link", link);
        query.executeUpdate();
    }

    @Override
    public void deleteProject(String tableName, String projectName) {
        String sql = "DELETE FROM " + tableName + " WHERE projectName = :projectName";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("projectName", projectName);
        query.executeUpdate();
    }

    @Override
    public void updateProject(String tableName, String projectName, String projectDescription, String version, String link, int id) {
        String sql = "UPDATE " + tableName + " SET projectName = :projectName, projectDescription = :projectDescription, version = :version, link = :link WHERE id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("projectName", projectName);
        query.setParameter("projectDescription", projectDescription);
        query.setParameter("version", version);
        query.setParameter("link", link);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

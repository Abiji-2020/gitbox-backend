package gitbox.Repositry;

import gitbox.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface ProjectRepositry extends JpaRepository<ProjectEntity, Long> {

    @Query(value = "SELECT * FROM :tableName", nativeQuery = true)
    List<ProjectEntity> getProjects(@Param("tableName") String tableName);

    @Query(value = "INSERT INTO :tableName (projectName, projectDescription, version, link) VALUES (:projectName, :projectDescription, :version, :link)", nativeQuery = true)
    void saveProject(@Param("tableName") String tableName, @Param("projectName") String projectName,
            @Param("projectDescription") String projectDescription, @Param("version") String version,
            @Param("link") String link);

    @Query(value = "DELETE FROM :tableName WHERE projectName = :projectName", nativeQuery = true)
    void deleteProject(@Param("tableName") String tableName, @Param("projectName") String projectName);

    @Query(value = "UPDATE :tableName SET projectName = :projectName, projectDescription = :projectDescription, version = :version, link = :link WHERE id = :id", nativeQuery = true)
    void updateProject(@Param("tableName") String tableName, @Param("projectName") String projectName,
            @Param("projectDescription") String projectDescription, @Param("version") String version,
            @Param("link") String link, @Param("id") int id);

}

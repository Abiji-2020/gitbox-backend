package gitbox.Repositry;

import gitbox.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepositry extends JpaRepository<ProjectEntity, Long> ,ProjectCustomRepository{

}

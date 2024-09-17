package gitbox.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private SessionFactory sessionFactory;

    public void createProjectTable(String TableName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS " + TableName
                    + " (id SERIAL, projectName varchar(255), projectDescription varchar(255), version varchar(255), link varchar(255), PRIMARY KEY (id))";
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error creating a table", e);
        } finally {
            session.close();
        }
    }

    public void dropProjectTable(String TableName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS" + TableName;
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error dropping a table", e);
        } finally {
            session.close();
        }
    }

    public void saveProject(String tableName, String projectName, String projectDescription, String version,
            String link) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "INSERT INTO " + tableName
                    + " (projectName, projectDescription, version, link) VALUES (:projectName, :projectDescription, :version, :link)";
            session.createNativeQuery(sql)
                    .setParameter("projectName", projectName)
                    .setParameter("projectDescription", projectDescription)
                    .setParameter("version", version)
                    .setParameter("link", link)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error saving a project", e);
        } finally {
            session.close();
        }
    }

    List<Object[]> getProjects(String tableName) {
        Session session = sessionFactory.openSession();
        try {
            String sql = "SELECT * FROM " + tableName;
            return session.createNativeQuery(sql).list();
        } catch (Exception e) {
            throw new RuntimeException("Error getting projects", e);
        } finally {
            session.close();
        }
    }

    public void updateProject(String tableName, String projectName, String projectDescription, String version,
            String link, int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "UPDATE " + tableName
                    + " SET projectName = :projectName, projectDescription = :projectDescription, version = :version, link = :link WHERE id = :id";
            session.createNativeQuery(sql)
                    .setParameter("projectName", projectName)
                    .setParameter("projectDescription", projectDescription)
                    .setParameter("version", version)
                    .setParameter("link", link)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error updating a project", e);
        } finally {
            session.close();
        }
    }

    public void deleteProject(String tableName, String projectName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "DELETE FROM " + tableName + " WHERE projectName = :projectName";
            session.createNativeQuery(sql)
                    .setParameter("projectName", projectName)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("Error deleting a project", e);
        } finally {
            session.close();
        }
    }

}

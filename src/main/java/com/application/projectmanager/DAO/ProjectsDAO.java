package com.application.projectmanager.DAO;

import com.application.projectmanager.Classes.projectManager;
import com.application.projectmanager.Entity.ProjectsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class ProjectsDAO {
    projectManager projectManager = new projectManager();
    Session session = projectManager.getSession();
    Transaction transaction = null;
    public void createProject(String name, String deadLine, String describe, Integer userId){
        ProjectsEntity project = new ProjectsEntity();
        try {
            transaction = session.beginTransaction();
            project.setProjectName(name);
            project.setProjectDeadLine(deadLine);
            project.setProjectDescribe(describe);
            project.setProjectsUserid(userId);
            session.save(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<ProjectsEntity> projectsOutput(Integer userId) {
        List<ProjectsEntity> projects = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM ProjectsEntity WHERE projectsUserid = :userId";
            Query<ProjectsEntity> query = session.createQuery(hql, ProjectsEntity.class);
            query.setParameter("userId", userId);
            projects = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return projects;
    }
    public void deleteProject(ProjectsEntity project) {
        try {
            transaction = session.beginTransaction();
            session.delete(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void updateProject(ProjectsEntity project) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

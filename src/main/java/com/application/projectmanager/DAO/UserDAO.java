package com.application.projectmanager.DAO;

import com.application.projectmanager.Classes.projectManager;
import com.application.projectmanager.Entity.UsersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;


public class UserDAO {
    projectManager projectManager = new projectManager();
    Session session = projectManager.getSession();
    Transaction transaction = null;

    public boolean checkUsername(String username){
        UsersEntity user = new UsersEntity();
        try {
            transaction = session.beginTransaction();
            String sql = "FROM UsersEntity WHERE username = :username";
            Query<UsersEntity> query = session.createQuery(sql, UsersEntity.class);
            query.setParameter("username", username);
            user = query.uniqueResult();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user != null;
    }
    public boolean checkAccount(String username, String userpassword){
        UsersEntity user = new UsersEntity();
        try {
            transaction = session.beginTransaction();
            String sql = "FROM UsersEntity WHERE username = :username";
            Query<UsersEntity> query = session.createQuery(sql, UsersEntity.class);
            query.setParameter("username", username);
            user = query.uniqueResult();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if(user != null) {
            return BCrypt.checkpw(userpassword, user.getUserpassword());
        }else{return false;}
    }
}
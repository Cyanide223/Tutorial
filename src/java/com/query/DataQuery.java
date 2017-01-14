/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.query;

import com.entity.Login;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author James
 */
public class DataQuery {

    EntityManagerFactory emf;
    EntityManager em;

    public DataQuery() {
        emf = Persistence.createEntityManagerFactory("TutorialPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public Boolean adminCheck(String username, String password) {
        try {
            Login l = em.createNamedQuery("Login.check", Login.class).setParameter("username", username).setParameter("password", password).getSingleResult();
            if (l != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean loginControl(String username, String password) {
        try {
            Login l = em.createNamedQuery("Login.control", Login.class).setParameter("username", username).setParameter("password", password).getSingleResult();
            if (l != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}

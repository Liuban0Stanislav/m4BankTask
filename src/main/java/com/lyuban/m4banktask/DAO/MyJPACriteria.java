package com.lyuban.m4banktask.DAO;

import com.lyuban.m4banktask.models.Model;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class MyJPACriteria {

    @Transactional
    public void getAll(){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "myPersistenceUnit" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Model> from = criteriaQuery.from(Model.class);

        //select all records
        System.out.println("Select all records");
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        for(Object o:resultlist) {
            Model e = (Model)o;
            System.out.println("EID : " + e.getId() + " Ename : " + e.getName());
        }
    }

    @Transactional
    public Model save(Model model){

        return model;
    }

    @Transactional
    public boolean delete(String name){

        return true;
    }



//    @Transactional
//    public Model createModelUsingCriteria(Model model) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
//        CriteriaQuery<Model> cr = cb.createQuery(Model.class);
//        Root<Model> root = cr.from(Model.class);
//        cr.select(root);
//
//        Query<Model> query = (Query<Model>) session.createQuery(cr);
//        List<Model> results = query.getResultList();
//        return model;
//    }
}

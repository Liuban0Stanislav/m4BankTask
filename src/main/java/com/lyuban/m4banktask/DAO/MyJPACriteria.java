package com.lyuban.m4banktask.DAO;

import com.lyuban.m4banktask.DTO.RemoveRequestDTO;
import com.lyuban.m4banktask.models.Model;
import jakarta.persistence.criteria.CriteriaDelete;
import org.hibernate.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyJPACriteria {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Model save(Model model){
        Session session = getCurrentSession();
        return (Model) session.save(model);
    }

    @Transactional
    public boolean delete(String name){
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaDelete<Model> criteriaDelete = cb.createCriteriaDelete(Model.class);
        Root<Model> root = criteriaDelete.from(Model.class);
        criteriaDelete.where(cb.equal(root.get("name"), name));
        int counter = getCurrentSession().createQuery(criteriaDelete).executeUpdate();
        return (counter != 0) ? true : false;
    }

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
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

package com.lyuban.m4banktask.repositories;

import com.lyuban.m4banktask.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    /**
     * Метод поиска модели по полю {@link Model#name}
     * @return
     */
    Model findByName(String name);
}

package com.ak.db.mysqlOne.repo;

import com.ak.db.mysqlOne.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdectRepo extends JpaRepository<Product,Integer> {

    //Product FindByName(String name);
}

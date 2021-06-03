package com.rankinteractive.repository;

import com.rankinteractive.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select customer from Customer customer where customer.emailAddress = :emailAddress and customer.password = :password")
    Customer findByCredentials(@Param("emailAddress") String emailAddress, @Param("password") String password);

}

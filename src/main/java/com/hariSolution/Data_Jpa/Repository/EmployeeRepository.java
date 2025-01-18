package com.hariSolution.Data_Jpa.Repository;

import com.hariSolution.Data_Jpa.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class EmployeeRepository {
    private EntityManager entityManager;

    public EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> findBySimpleQuery(
            String firstname,
            String lastname,
            String email
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        //select *from employee
        Root<Employee> root = criteriaQuery.from(Employee.class);

        //prepare WHERE clause
        //WHERE firstname like '%hari%'
        Predicate firstnamePredicate = criteriaBuilder
                .like(root.get("firstname"), "%" + firstname + "%");

        Predicate lastnamePredicate = criteriaBuilder
                .like(root.get("lastname"), "%" + lastname + "%");
        Predicate emailPredicate = criteriaBuilder
                .like(root.get("email"), "%" + email + "%");

        Predicate firstnameOrLastnamePredicate = criteriaBuilder.or(
                firstnamePredicate, lastnamePredicate
        );
        //final query ==>select * from employee where firstname like '%hari%'
        //or lastname like '%hari%'and email like '%email%'
        var andEmailPredicate = criteriaBuilder.and(firstnameOrLastnamePredicate, emailPredicate);
        criteriaQuery.where(andEmailPredicate);

        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();


    }


    public List<Employee> findAllByCriteria(SearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        //select from employee
        Root<Employee> root = criteriaQuery.from(Employee.class);
        if (request != null) {
            Predicate firstnamePredicate = criteriaBuilder.like(
                    root.get("firstname"), "%" + request.getFirstname() + "%");
            predicates.add(firstnamePredicate);
        }

        if (request != null) {
            Predicate lastnamePredicate = criteriaBuilder.like(
                    root.get("lastname"), "%" + request.getLastname() + "%");
            predicates.add(lastnamePredicate);
        }
        if (request != null) {
            Predicate emailPredicate = criteriaBuilder.like(
                    root.get("lastname"), "%" + request.getEmail() + "%");
            predicates.add(emailPredicate);
        } else {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();


    }
}

package com.alterra.graphql.repository;

import com.alterra.graphql.model.dao.AuthorDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorDao, Long> {
}

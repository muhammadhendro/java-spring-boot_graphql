package com.alterra.graphql.repository;

import com.alterra.graphql.model.dao.PostDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostDao, Long> {
}

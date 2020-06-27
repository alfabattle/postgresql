package com.ashikhman.postgresql.repository;

import com.ashikhman.postgresql.entity.ArticleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {

    @Query("select a from Article a join fetch a.authors")
    Iterable<ArticleEntity> findAllJoinAuthors();

    @Query("select a from Article a join fetch a.authors where a.id = :id")
    Optional<ArticleEntity> findByIdJoinAuthors(Long id);
}

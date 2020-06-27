package com.ashikhman.postgresql.service;

import com.ashikhman.postgresql.dto.ArticleDto;
import com.ashikhman.postgresql.entity.ArticleEntity;
import com.ashikhman.postgresql.entity.AuthorEntity;
import com.ashikhman.postgresql.mapper.ArticleMapper;
import com.ashikhman.postgresql.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;
    private final ArticleMapper mapper;

    public void createSomeArticle() {
        var article = new ArticleEntity()
                .setTitle("Article title")
                .addAuthor(new AuthorEntity()
                        .setFullName("Author name"));

        repository.save(article);
    }

    public List<ArticleDto> getAll() {
        return StreamSupport.stream(repository.findAllJoinAuthors().spliterator(), false)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ArticleDto getById(Long id) {
        return repository.findByIdJoinAuthors(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("xaxa"));
    }
}

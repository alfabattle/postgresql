package com.ashikhman.postgresql.controller;

import com.ashikhman.postgresql.dto.ArticleDto;
import com.ashikhman.postgresql.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping()
    public List<ArticleDto> articles() {
//        articleService.createSomeArticle();

        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public ArticleDto article(@Valid @PathVariable() Long id) {
        return articleService.getById(id);
    }
}

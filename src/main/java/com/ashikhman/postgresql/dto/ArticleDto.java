package com.ashikhman.postgresql.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticleDto {

    private String title;

    private List<AuthorDto> authors;
}

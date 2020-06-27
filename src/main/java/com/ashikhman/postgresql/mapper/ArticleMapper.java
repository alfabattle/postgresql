package com.ashikhman.postgresql.mapper;

import com.ashikhman.postgresql.dto.ArticleDto;
import com.ashikhman.postgresql.entity.ArticleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto toDto(ArticleEntity entity);
}

package com.ashikhman.postgresql.mapper;

import com.ashikhman.postgresql.dto.ArticleDto;
import com.ashikhman.postgresql.dto.BranchDto;
import com.ashikhman.postgresql.entity.ArticleEntity;
import com.ashikhman.postgresql.entity.BranchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchDto toDto(BranchEntity entity);
}

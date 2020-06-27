package com.ashikhman.postgresql.repository;

import com.ashikhman.postgresql.entity.BranchEntity;
import org.springframework.data.repository.CrudRepository;

public interface BranchesRepository extends CrudRepository<BranchEntity, Integer> {

}

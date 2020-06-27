package com.ashikhman.postgresql.controller;

import com.ashikhman.postgresql.dto.ErrorDto;
import com.ashikhman.postgresql.mapper.BranchMapper;
import com.ashikhman.postgresql.repository.BranchesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchesController {

    private final BranchesRepository repository;

    private final BranchMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> branch(@PathVariable String id) {

        Integer branchId;
        try {
            branchId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new ErrorDto("branch not found"), HttpStatus.NOT_FOUND);
        }

        var branch = repository.findById(branchId);
        if (!branch.isPresent()) {
            return new ResponseEntity<>(new ErrorDto("branch not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mapper.toDto(branch.get()), HttpStatus.OK);
    }
}

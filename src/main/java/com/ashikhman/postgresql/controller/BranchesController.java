package com.ashikhman.postgresql.controller;

import com.ashikhman.postgresql.dto.ErrorDto;
import com.ashikhman.postgresql.entity.BranchEntity;
import com.ashikhman.postgresql.mapper.BranchMapper;
import com.ashikhman.postgresql.repository.BranchesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchesController {

    private final BranchesRepository repository;

    private final BranchMapper mapper;

    @GetMapping
    public ResponseEntity<?> branches(@RequestParam double lat, @RequestParam double lon) {
        var branches = repository.findAll();

        Double minDistance = null;
        BranchEntity foundBranch = null;
        for (var branch : branches) {
            var distance = distance(
                    Double.valueOf(branch.getLat()),
                    Double.valueOf(branch.getLon()),
                    lat,
                    lon
            );

            if (null == minDistance || minDistance > distance) {
                minDistance = distance;
                foundBranch = branch;
            }
        }

        if (null == foundBranch) {
            return new ResponseEntity<>(new ErrorDto("branch not found"), HttpStatus.NOT_FOUND);
        }

        var dto = mapper.toDto(foundBranch);
        dto.setDistance(Math.round(minDistance));

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

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


    double toRadians(double degree) {
        double one_deg = (Math.PI) / 180;
        return (one_deg * degree);
    }

    double distance(double lat1, double long1,
                    double lat2, double long2) {
        lat1 = toRadians(lat1);
        long1 = toRadians(long1);
        lat2 = toRadians(lat2);
        long2 = toRadians(long2);

        double dlong = long2 - long1;
        double dlat = lat2 - lat1;

        double ans = Math.pow(Math.sin(dlat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dlong / 2), 2);

        ans = 2 * Math.asin(Math.sqrt(ans));

        double R = 6371000;

        ans = ans * R;

        return ans;
    }
}

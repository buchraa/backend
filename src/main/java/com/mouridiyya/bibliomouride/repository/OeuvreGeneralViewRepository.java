package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.OeuvreGeneralView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OeuvreGeneralViewRepository extends PagingAndSortingRepository<OeuvreGeneralView, Long>, JpaSpecificationExecutor {

    Page<OeuvreGeneralView> findAll(Specification spec, Pageable pageable);
}

package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Theme;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ThemeRepository extends CrudRepository<Theme, Long> {
    Optional<Theme> findByThemeId(Long themeId);
    Optional<Theme> findByName(String name);
}

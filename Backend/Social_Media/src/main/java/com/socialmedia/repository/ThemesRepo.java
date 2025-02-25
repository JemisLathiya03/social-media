package com.socialmedia.repository;

import com.socialmedia.modal.Themes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemesRepo extends JpaRepository<Themes, Long> {

    List<Themes> findByThemeName(String themeName);

    List<Themes> findByModificationFalseAndCreatedUserEquals(Long createdUser);
}

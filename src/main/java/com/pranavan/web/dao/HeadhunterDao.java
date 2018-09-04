package com.pranavan.web.dao;

import com.pranavan.web.model.Headhunter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by pranavan on 7/12/18.
 */
public interface HeadhunterDao extends JpaRepository<Headhunter, Long> {
    @Query("FROM Headhunter WHERE id=:id")
    public Headhunter findById(@Param("id") Long id);

}

package com.pranavan.web.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by pranavan on 7/12/18.
 */
@Entity
@Table(name = "head_hunter")
@XmlRootElement
public class Headhunter {
    private Long id;
    private String name;
    private Boolean isActive;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "head_hunter_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "isactive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


}

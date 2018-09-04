package com.pranavan.web.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by pranavan on 7/12/18.
 */
@Entity
@Table(name = "job_detail")
@XmlRootElement
public class JobDetail {
    private Long id;
    private String title;
    private String code;
    private Double recruitCostPerMan;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "jobdetail_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title", unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "code", unique = true, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "recruit_cost_perman", nullable = false)
    public Double getRecruitCostPerMan() {
        return recruitCostPerMan;
    }

    public void setRecruitCostPerMan(Double recruitCostPerMan) {
        this.recruitCostPerMan = recruitCostPerMan;
    }
}

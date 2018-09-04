package com.pranavan.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pranavan.web.Enum.GenderType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by pranavan on 7/12/18.
 */

@Entity
@Table(name = "employee")
@XmlRootElement
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderType gender;
    private Headhunter headhunter;
    private JobDetail job;
    private Date recruitedDate;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "emp_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }



    @ManyToOne
    @JoinColumn(name="headhunter", nullable=false)
    public Headhunter getHeadhunter() {
        return headhunter;
    }

    public void setHeadhunter(Headhunter headhunter) {
        this.headhunter = headhunter;
    }


    @ManyToOne
    @JoinColumn(name="job_id", nullable=false)
    public JobDetail getJob() {
        return job;
    }

    public void setJob(JobDetail job) {
        this.job = job;
    }
    @Column(name = "recruited_date",nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getRecruitedDate() {
        return recruitedDate;
    }

    public void setRecruitedDate(Date recruitedDate) {
        this.recruitedDate = recruitedDate;
    }
}

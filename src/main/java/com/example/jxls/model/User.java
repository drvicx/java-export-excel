package com.example.jxls.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


/**
 *=JPA ENTITY CLASS "User"
 */
//--Lombok @Data -- known bug with OneToMany relation
//@Data
@Getter
@Setter
@Entity
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long userId;

    //--OneToMany relation with Timedata Entity/Table
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Timedata> timedata;

    @Column(name="PERSONALNUMBER")
    private Long personalNumber;

    @Column(name="FIRSTNAME")
    private String firstName;

    @Column(name="MIDLENAME")
    private String middleName;

    @Column(name="LASTNAME")
    private String lastName;

    @Column(name="FULLNAMEALIAS")
    private String fullNameAlias;

    @Column(name="BIRTHDATE")
    private LocalDate birthDate;

    @Column(name="LOGIN")
    private String login;

    @Column(name="POSITION_E")
    private String positionEng;

    @Column(name="POSITION_R")
    private String positionRus;

    @Column(name="PHONE_WORK")
    private String phoneWork;

    @Column(name="PHONE_PERS")
    private String phonePers;

    @Column(name="EMAIL_WORK")
    private String emailWork;

    @Column(name="EMAIL_PERS")
    private String emailPers;

    @Column(name="PHOTO_LINK")
    private String photoLink;

    @Column(name="SOCIAL_LINK1")
    private String socialLink1;

    @Column(name="SOCIAL_LINK2")
    private String socialLink2;

    @Column(name="SOCIAL_LINK3")
    private String socialLink3;

    @Column(name="ACCESSLEVEL")
    private String accessLevel;

    @Column(name="HIRED")
    private LocalDate hireDate;

    @Column(name="FIRED")
    private LocalDate fireDate;

}

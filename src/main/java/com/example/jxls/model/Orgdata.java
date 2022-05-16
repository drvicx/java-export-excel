package com.example.jxls.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import lombok.Data;
import java.time.LocalDate;


/**
 *=JPA ENTITY CLASS "Orgdata"
 */
@Data
@Entity
@Table(name="ORGDATA")
public class Orgdata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long recordId;

    @Column(name="ORGNAME")
    private String orgName;

    @Column(name="DEPNAME")
    private String depName;

    @Column(name="OKUD")
    private String okud;

    @Column(name="OKPO")
    private String okpo;

    @Column(name="DEPBOSS")
    private String depBoss;

    @Column(name="DEPBOSS_POS")
    private String depBossPos;

    @Column(name="RESPONDER")
    private String responder;

    @Column(name="RESPONDER_POS")
    private String responderPos;

    @Column(name="LOGO1")
    private String logo1;

    @Column(name="LOGO2")
    private String logo2;

    @Column(name="BEGINDATE")
    private LocalDate dateBegin;

    @Column(name="ENDDATE")
    private LocalDate dateEnd;

}

package com.example.jxls.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
//import javax.persistence.OneToMany;
//import javax.persistence.CascadeType;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
//import java.util.Set;


/**
 *=JPA ENTITY CLASS "ReportTitle"
 * - report Title data -- данные для Шапки отчета
 */
//--Lombok @Data -- known bug with OneToMany relation
//@Data
@Getter
@Setter
@Entity
@Table(name="REPORTTITLE")
public class ReportTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;                        //-- Идентификатор Отчета

    @Column(name="TABLENUM")                //-- Табель №
    private String tableNum;

    @Column(name="PERIOD_FROMDAY")          //-- за период с  1 (номер дня месяца)
    private Long periodFromDay;

    @Column(name="PERIOD_TODAY")            //--        .. по 31 (номер дня месяца)
    private Long periodToDay;

    @Column(name="PERIOD_MONTH")            //--        .. декабря (название месяца)
    private String periodMonthName;

    @Column(name="PERIOD_YEAR")             //--        .. 2020    (год отчета)
    private Long periodYear;

    @Column(name="DATE1")                   //-- Дата
    private String date1;
    //private LocalDate date1;

    @Column(name="DATE2")                   //-- Дата формирования документа
    private String date2;
    //private LocalDate date2;

    @Column(name="EDITNUM")                  //-- Номер корректировки
    private Long editNum;

    @Column(name="TABLETYPE")                //-- Вид табеля (первичный - 0; корректирующий - 1, 2, и т.д.)
    private Long tableType;

}

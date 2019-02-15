package com.bms.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "INST_BMSDATA")
@Data
public class BmsData {
	
	@Column(name = "SITEID")
    private Integer siteId;

	@Column(name = "BANKID")
    private String bankId;

	@Column(name = "BMSID")
    private String bmsId;
    
	@Column(name = "BATID")
    private Integer batId;
    
    @Id
    private LocalDate date;
    
    private LocalTime time;
    
    @Column(name = "B_V")
    private Double bV;
    
    @Column(name = "B_I")
    private Double bI;
    
    @Column(name = "B_T")
    private Double bT;
    
    @Column(name = "B_Z")
    private Double bZ;
    
    @Column(name = "SYS_DT")
    LocalDateTime sysDt;   

}

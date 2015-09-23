package com.ipinyou.optimus.console.sys.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.BaseEntity;

/**
 * @author yaohl
 *
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name="last_scan_status")
public class LastScanStatus extends BaseEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 2823992923074705983L;

    /**
     * 扫描的表名
     */
    @Index(name="entity")
    @NotNull
    private String entity="StrategyStatus";

    /**
     * 最后扫描时间
     */
    @NotNull
    private Timestamp lastScanned = new Timestamp(new Date().getTime());

    
    
}

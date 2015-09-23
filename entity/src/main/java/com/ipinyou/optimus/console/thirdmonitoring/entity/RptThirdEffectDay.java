package com.ipinyou.optimus.console.thirdmonitoring.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.ipinyou.optimus.console.third.model.ThirdPlatform;

/**
 * Created with IntelliJ IDEA.
 * Date: 14-10-16
 * Time: 下午5:02
 */
@Entity
public class RptThirdEffectDay {

    @Id
    private long id;

    private long strategyId;

    private long creativeId;

    private long imp;

    private long click;

    private Date day;

    private ThirdPlatform type;

    public long getClick() {
        return click;
    }

    public void setClick(long click) {
        this.click = click;
    }

    public long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(long creativeId) {
        this.creativeId = creativeId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getImp() {
        return imp;
    }

    public void setImp(long imp) {
        this.imp = imp;
    }

    public long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(long strategyId) {
        this.strategyId = strategyId;
    }

    public ThirdPlatform getType() {
        return type;
    }

    public void setType(ThirdPlatform type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RptThirdEffectDay{" +
                "click=" + click +
                ", id=" + id +
                ", strategyId=" + strategyId +
                ", creativeId=" + creativeId +
                ", imp=" + imp +
                ", day=" + day +
                ", type=" + type +
                '}';
    }

}
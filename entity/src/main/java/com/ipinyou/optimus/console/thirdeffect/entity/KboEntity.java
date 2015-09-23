package com.ipinyou.optimus.console.thirdeffect.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.ipinyou.api.remote.KboPlatform;

/**
 * Created with IntelliJ IDEA.
 * Date: 14-10-13
 * Time: 下午5:56
 * 酷宝操作实体类型
 */

@Entity
@Table(name = "rpt_kbo_effect_day")
@Data
public class KboEntity implements com.ipinyou.base.entity.Entity {

    private static final long serialVersionUID = -1699381882437486624L;
    
    @Id
    private Long id;
    
    /**
	 * 所属策略
	 */
	private Long strategyId;
	
	/**
	 * 所属策略
	 */
	private Long creativeId;

	/**
	 * 商品编号
	 */
	private Date day;

    
    private String mvcid;

    
    private String mvcidt;
    
    //到达数
    
    private int reachNum;
    
    //访客数
    
    private int visitorNum;
    
    //跳失率
    
    private float jumpLose;
    
    //拍下数
    
    private int orderNum;
    
    //成交数
    
    private int dealNum;
    
    //成交金额
    
    private float dealMoney;
    
    //成交转化率
    
    private float dealConvert;
    
    //访问深度
    
    private float perAccessDepth;
    
    //店铺收藏数
    
    private int shopFavoriteNum;
    
    //产品收藏数
    
    private int productFavoriteNum;
    
    //加入购物车数
    
    private int addCartNum;
    
    //拍下人数
    
    private int buyCustomerNum;
    
    //拍下金额
    
    private float buyMoney;
    
    //成交人数
    
    private int dealCustomerNum;

    @Enumerated(EnumType.STRING)
    
    private KboPlatform type;
    
    @Override
    public String toString() {
        return "KboEntity{" +
                "addCartNum=" + addCartNum +
                ", stragegyId=" + strategyId +
                ", creativeId=" + creativeId +
                ", mvcid='" + mvcid + '\'' +
                ", mvcidt='" + mvcidt + '\'' +
                ", reachNum=" + reachNum +
                ", visitorNum=" + visitorNum +
                ", jumpLose=" + jumpLose +
                ", orderNum=" + orderNum +
                ", dealNum=" + dealNum +
                ", dealMoney=" + dealMoney +
                ", dealConvert=" + dealConvert +
                ", perAccessDepth=" + perAccessDepth +
                ", shopFavoriteNum=" + shopFavoriteNum +
                ", productFavoriteNum=" + productFavoriteNum +
                ", buyCustomerNum=" + buyCustomerNum +
                ", buyMoney=" + buyMoney +
                ", dealCustomerNum=" + dealCustomerNum +
                ", day=" + day +
                ", type=" + type +
                '}';
    }
}
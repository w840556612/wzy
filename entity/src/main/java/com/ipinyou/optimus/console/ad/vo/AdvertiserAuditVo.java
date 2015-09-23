package com.ipinyou.optimus.console.ad.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.ad.entity.Advertiser.AdvertiserType;
import com.ipinyou.optimus.console.ad.entity.AdvertiserVerticalTag;

/**
 * @author yaohl
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserAuditVo extends BaseVo{/**
     * 
     */
    private static final long serialVersionUID = -1425892689137979464L;
    
    public  AdvertiserAuditVo(Advertiser advertiser) {
        this.id = advertiser.getId();
        this.account = advertiser.getCreator().getAccount();
        this.name = advertiser.getName();
        this.website = advertiser.getWebsite();
        this.vertical = getVertical(advertiser.getVerticalTag());
        this.contactName = advertiser.getContactName();
        this.email = advertiser.getEmail();
        this.phone = advertiser.getCellphone();
        this.type = getType(advertiser.getType()); 
//        this.legalPersonIdExt = advertiser.getLegalPersonIdExt();
//        this.licenseFileExt = advertiser.getLicenseFileExt();
        this.source = getSource(advertiser.getCreator().getSource());
        this.wangwang = advertiser.getWangwang();
//        this.icpFileExt = advertiser.getIcpFileExt();
		 this.serviceFeeRate = advertiser.getServiceFeeRate();
	}
    
    private String getVertical(AdvertiserVerticalTag vertical) {
        return vertical==null? com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.vo.AdvertiserAuditVo.1001")/*未填写*/ : vertical.getNameDetail();
    }
    
    private String getType(AdvertiserType type) {
        return type==null? com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.model.AdvertiserShowStatus.1000")/*未选择*/ : type.getText();
    }
    
    private String getSource(String source) {
        return source==null? com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.model.AdvertiserShowStatus.1000")/*未选择*/ : source;
    }
    
   

    private Long id;
    
    private String account;
    
    private String name;
    
    private String website;
    
    private String vertical;
    
    private String contactName;
    
    private String email;
    
    private String source;
    
    private String phone;
    
    private String type;
    
    private String wangwang;
    
//    private String licenseFileExt;
//    
//    private String legalPersonIdExt;
//    
//    private String icpFileExt;
    
    private String status;
    
    private BigDecimal rechargeTotal;
    
    private BigDecimal presentSum;
    
    private BigDecimal balance;
    
    private BigDecimal costTotal;
    
    private Long verticalId;
    
    private BigDecimal serviceFeeRate;
    
    private List<PlatformAuditStatusVo> platformAuditStatus;
}

package com.ipinyou.optimus.console.ad.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

import com.ipinyou.base.entity.Component;
import com.ipinyou.base.entity.UserDefineList;
import com.ipinyou.base.entity.UserDefineMap;

/**
 * 
 * @author xiaobo.wang
 * 移动DSP定向的一些共用属性
 *
 */
@Embeddable
@Data
public class MobileAttribute implements Component {

	private static final long serialVersionUID = -3052239637771447743L;
	
	/**
	 * 移动类别 web或app,默认为app, 用户选择非移动DSP时，值为空
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private MobileType mobileType = MobileType.App;
	
	/**
	 * 移动网络
	 */
	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="mobile_networks",length=64))
	private MobileNetworkList mobileNetworks;
	
	
	/**
	 * 移动设备 格式：Cellphone,Pad
	 */
	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="mobile_sets",length=64))
	private MobileSetList mobileSets;
	
	
	/**
	 * 移动设备品牌 格式:Cellphone=Apple,Samsung,HTC;Pad=iPad,Huawei
	 */
	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="mobile_set_brands",length=1000))
	private MobileSetBrandMap mobileSetBrands;
	
	
	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="app_categories", length=1000))
	private AppCategoryList appCategories;
	
	/*
	 * 移动设备属性定向
	 */
	@Column(length = 1000)
	private String mobileDeciveProperty;

	
	/**
	 * identifier for advertisers（广告商识别码）
	 * 用户定向(IDFA集合)
	 */
	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="idfas", length=2000))
	private IDFAList idfas;
	
	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="set_id_category",length=64))
	private SetIdCategoryList setIdCategory;
	
	public static enum MobileType {		
		App("App"),
		Web("Mobile Web");
		
		private String text;
		
		private MobileType(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
	public static enum MobileNetwork {
		Wifi("Wi-Fi"),
		Mobile("optimus.console.ad.model.MobileAttribute.1001"/*移动*/),
		Unicom("optimus.console.model.Platform.1018"/*联通*/),
		Telecom("optimus.console.ad.model.MobileAttribute.1002"/*电信*/),
		Other("optimus.console.ad.model.AgentProduct.1003"/*其它*/);
		
		private String text;
		
		private MobileNetwork(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}		
	}
	
	public static enum MobileSet {
		Cellphone("optimus.console.ad.model.MobileAttribute.1003"/*手机*/),		 
		Pad("PAD"),
		MobileOther("optimus.console.ad.model.MobileAttribute.1004"/*其它移动设备*/);
		 
		private String text;
		 
		private MobileSet(String text){
			this.text = text;
		}
		 
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
	
	
	public static enum CellphoneBrand {
		Apple("optimus.console.ad.model.MobileAttribute.1005"/*苹果*/),
		Samsung("optimus.console.ad.model.MobileAttribute.1006"/*三星*/),
		HTC("HTC"),
		Nokia("optimus.console.ad.model.MobileAttribute.1007"/*诺基亚*/),
		Motorola("optimus.console.ad.model.MobileAttribute.1008"/*摩托罗拉*/),
		Sony("optimus.console.ad.model.MobileAttribute.1009"/*索尼*/),		
		
		Huawei("optimus.console.ad.model.MobileAttribute.1010"/*华为*/),
		Lenovo("optimus.console.ad.model.MobileAttribute.1011"/*联想*/),
		Meizu("optimus.console.ad.model.MobileAttribute.1012"/*魅族*/),
		Xiaomi("optimus.console.ad.model.MobileAttribute.1013"/*小米*/),
		Other("optimus.console.ad.model.AgentProduct.1003"/*其它*/);
		
		private String text;
		 
		private CellphoneBrand(String text){
			this.text = text;
		}
		 
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
	
	public static enum PadBrand {
		Apple("optimus.console.ad.model.MobileAttribute.1005"/*苹果*/),
		Samsung("optimus.console.ad.model.MobileAttribute.1006"/*三星*/),
		Lenovo("optimus.console.ad.model.MobileAttribute.1011"/*联想*/),
		Microsoft("optimus.console.ad.model.MobileAttribute.1014"/*微软*/),
		Asus("optimus.console.ad.model.MobileAttribute.1015"/*华硕*/),		
		
		Acer("optimus.console.ad.model.MobileAttribute.1016"/*宏碁*/),
		BenQ("optimus.console.ad.model.MobileAttribute.1017"/*明基*/),
		Dell("optimus.console.ad.model.MobileAttribute.1018"/*戴尔*/),
		HP("optimus.console.ad.model.MobileAttribute.1019"/*惠普*/),	
		LG("LG"),
		Other("optimus.console.ad.model.AgentProduct.1003"/*其它*/);
		
		private String text;
		 
		private PadBrand(String text){
			this.text = text;
		}
		 
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
	
	public static enum MobileSetBrand {
		Apple("optimus.console.ad.model.MobileAttribute.1005"/*苹果*/),
		Samsung("optimus.console.ad.model.MobileAttribute.1006"/*三星*/),
		HTC("HTC"),
		Nokia("optimus.console.ad.model.MobileAttribute.1007"/*诺基亚*/),
		Motorola("optimus.console.ad.model.MobileAttribute.1008"/*摩托罗拉*/),
		Sony("optimus.console.ad.model.MobileAttribute.1009"/*索尼*/),	
		
		Lenovo("optimus.console.ad.model.MobileAttribute.1011"/*联想*/),
		Microsoft("optimus.console.ad.model.MobileAttribute.1014"/*微软*/),
		Newsmy("optimus.console.ad.model.MobileAttribute.1020"/*纽曼*/),
		Asus("optimus.console.ad.model.MobileAttribute.1015"/*华硕*/),		
		
		Teclast("optimus.console.ad.model.MobileAttribute.1021"/*台电*/),
		Ramos("optimus.console.ad.model.MobileAttribute.1022"/*蓝魔*/),
		Acer("optimus.console.ad.model.MobileAttribute.1016"/*宏碁*/),
		EE("optimus.console.ad.model.MobileAttribute.1023"/*e人e本*/),	
		
		BenQ("optimus.console.ad.model.MobileAttribute.1017"/*明基*/),
		Dell("optimus.console.ad.model.MobileAttribute.1018"/*戴尔*/),
		HP("optimus.console.ad.model.MobileAttribute.1019"/*惠普*/),	
		LG("LG"),
		
		Huawei("optimus.console.ad.model.MobileAttribute.1010"/*华为*/),
		Meizu("optimus.console.ad.model.MobileAttribute.1012"/*魅族*/),
		Xiaomi("optimus.console.ad.model.MobileAttribute.1013"/*小米*/),
		Other("optimus.console.ad.model.AgentProduct.1003"/*其它*/);
		
		private String text;
		 
		private MobileSetBrand(String text){
			this.text = text;
		}
		 
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
	
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class MobileNetworkList extends UserDefineList<MobileNetwork> {
		private static final long serialVersionUID = 7396579259085541839L;
		
		public MobileNetworkList(){
			super(",");
		}	
		@Override
		public String getStrValue() {
			return super.getStrValue();
		}
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
		
		@Override
		public boolean needOdered(){
			return true;
		}
		@Override
		public MobileNetwork asObject(String strValue) {
			return Enum.valueOf(MobileNetwork.class, strValue);
		}		
	}
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class MobileSetList extends UserDefineList<MobileSet> {
		private static final long serialVersionUID = 7396579259085541839L;
		
		public MobileSetList(){
			super(",");
		}	
		@Override
		public String getStrValue() {
			return super.getStrValue();
		}
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}		
		@Override
		public MobileSet asObject(String strValue) {
			return Enum.valueOf(MobileSet.class, strValue);
		}		
	}
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class MobileSetBrandList extends UserDefineList<MobileSetBrand> {
		private static final long serialVersionUID = 7396579259085541839L;
		
		public MobileSetBrandList(){
			super(",");
		}	
		@Override
		public String getStrValue() {
			return super.getStrValue();
		}
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}		
		@Override
		public MobileSetBrand asObject(String strValue) {
			return Enum.valueOf(MobileSetBrand.class, strValue);
		}		
	}

	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class MobileSetBrandMap extends UserDefineMap<MobileSet,MobileSetBrandList> {
		private static final long serialVersionUID = 7396579259085541839L;
		
		public MobileSetBrandMap(){
			super(";", "=");
		}	
		@Override
		public String getStrValue() {
			return super.getStrValue();
		}
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}	
		
		@Override
		public MobileSet mapKeyObject(String key){
			return Enum.valueOf(MobileSet.class, key);
		}
		
		@Override
		public MobileSetBrandList mapValueObject(String value){
			MobileSetBrandList mslist = new MobileSetBrandList();
			mslist.setStrValue(value);
			return mslist;
		}	
	}
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class AppCategoryList extends UserDefineList<String> {
		private static final long serialVersionUID = 5779146575049904362L;
		
		public AppCategoryList(){
			super(",");
		}
		
		@Override
		public String getStrValue() {
			return super.getStrValue();
		}
		
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}			
	}
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class IDFAList extends UserDefineList<String> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4091085392674162169L;

		public IDFAList() {
			super("\n");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class SetIdCategoryList extends UserDefineList<SetIdCategory> {
		private static final long serialVersionUID = 7396579259085541839L;
		
		public SetIdCategoryList(){
			super(",");
		}	
		@Override
		public String getStrValue() {
			return super.getStrValue();
		}
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}		
		@Override
		public SetIdCategory asObject(String strValue) {
			return Enum.valueOf(SetIdCategory.class, strValue);
		}		
	}
	
	public static enum SetIdCategory {
		IOSId("optimus.console.ad.model.MobileAttribute.1024"/*苹果id（含idfa）*/),
		AndroidId("optimus.console.ad.model.MobileAttribute.1025"/*安卓id（含Device Id或Android Id）*/);
		
		private String text;
		 
		private SetIdCategory(String text){
			this.text = text;
		}
		 
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
}

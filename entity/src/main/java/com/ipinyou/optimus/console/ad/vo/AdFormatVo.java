package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author kg.gu
 * 
 */
@Data
@ToString(callSuper = true)
public class AdFormatVo {

	private int height;
	private int width;
	
	public AdFormatVo(int height,int width){
		this.height = height;
		this.width = width;
	}
	
	public AdFormatVo(){}
	
	
	@Override
	public String toString(){
		return this.height+"*"+this.width;
	}
	
	@Override
	public boolean equals(Object o) {
	        if(o==null) return false;
	        if(o==this) return true;
	         
	        if(o instanceof AdFormatVo ){
	        	AdFormatVo p=(AdFormatVo)o;
	            if(p.getHeight()==this.height||p.getWidth()==this.width){
	                return true;
	            }else{
	                return false;
	            }
	        }else{
	            return false;
	        }
	     
	}
	
}

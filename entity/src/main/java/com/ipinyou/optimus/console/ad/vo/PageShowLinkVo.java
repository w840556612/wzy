/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;


/**
 * RedirectAttributes时返回给页面的内容和操作的VO
 * @author caobaozhu
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PageShowLinkVo extends BaseVo{
	
	private static final long serialVersionUID = -2121079003541202934L;

	public PageShowLinkVo(String text,String href){
		this.href = href;
		this.text = text;
	}
	/**
	 * 标识链接或者js
	 */
	private String href;
	
	/**
	 * 返回到前台展示的内容
	 */
	private String text;
	
}

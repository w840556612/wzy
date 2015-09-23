/**
 * 
 */
package com.ipinyou.optimus.console.model;

/**
 * @author lijt
 * 
 */
public enum AdLocation {
	Na("未知"), // 未知
	FirstView("第一屏"), // 第一屏
    SecondView("第二屏"),// 
    ThirdView("第三屏"),// 
    FourthView("第四屏"),// 
    FifthView("第五屏"),// 
    SixthView("第六屏"),// 
    SeventhView("第七屏"),// 
    EighthView("第八屏"),// 
    NinthView("第九屏"),// 
    TenthView("第十屏"),// 
    OtherView("其它屏");// 
	
	private AdLocation(String title){
		this.title = title;
	}
	
	private String title;
	
	public String getTitle(){
		return this.title;
	}
}

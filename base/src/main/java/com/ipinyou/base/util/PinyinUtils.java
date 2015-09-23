package com.ipinyou.base.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipinyou.base.model.PinyinEntity;

/**
 * 获取拼音字串（含拼音首字母）的工具类
 * @author wujun
 * @version 1.0 2013/11/07
 */
public class PinyinUtils {
	/**
	 * 日志记录实例
	 */
	protected final static transient Logger logger = LoggerFactory
			.getLogger(PinyinUtils.class);
	
	/**
	 * 将源字串解析出拼音字串（含拼音首字母），以“,”分割。不限制解析出的拼音字串的长度
	 * 例如：“重重”-->",ZZ,ZC,CZ,CC,ZHONGZHONG,ZHONGCHONG,CHONGZHONG,CHONGZHONG"
	 * add by wujun 20131105
	 * @param source				需要z转换拼音字串的源字串
	 */
	public static String getPinYin(String source){
		
		return getPinYin(source,-1);
		
	}

	
	/**
	 * 将源字串解析出拼音字串（含拼音首字母），以“,”分割
	 * 例如：“重重”-->",ZZ,ZC,CZ,CC,ZHONGZHONG,ZHONGCHONG,CHONGZHONG,CHONGZHONG"
	 * add by wujun 20131105
	 * @param source				需要z转换拼音字串的源字串
	 * @param max					由于考虑到全是多音字的极端情况造成该处理后的字串超长，故加入长度限制
	 */
	public static String getPinYin(String source,int max){
		
		if( source==null || source.isEmpty() || !(source.length()>0) ) return "";//高可靠处理，对于空值输入，不抛异常
		
		char[] cs = source.toString().toCharArray();		

		HanyuPinyinOutputFormat f = new HanyuPinyinOutputFormat();
		f.setToneType( HanyuPinyinToneType.WITHOUT_TONE );	
		
		//字串长度
		int len = cs.length;
		ArrayList<String> hanyupinyins = new ArrayList<String>();//拼音字处理串保存集合					
		ArrayList<String> firstpinyins = new ArrayList<String>();//拼音首字母处理串保存集合
		
		int sblen = 0;
		
		for(int index = 0; index < len ;index++) {
			
			char c = cs[index]; 
			
			String[] pinyinResults = null;//定义每个字拼音装换的结果					

			if ((c >= 0x4e00)&&(c <= 0x9fbb)){//中文汉字区间
				
				Set<String> set = null;
				try {
					set = new HashSet<String>( Arrays.asList(PinyinHelper.toHanyuPinyinStringArray(c,f)));
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}//排重
				if( set!=null ) pinyinResults = (String[])set.toArray(new String[set.size()]);	

			}else{//非汉字区间
				pinyinResults = new String[]{(String.valueOf(c)).toUpperCase()};
			}
			
			if( hanyupinyins.size()==0  ){								
				for(int k=0; k < pinyinResults.length ; k++) {//初始化								
					hanyupinyins.add( pinyinResults[k].toUpperCase() );									
					firstpinyins.add( (String.valueOf(pinyinResults[k].charAt(0))).toUpperCase() );//拼音首字母
				}								
			}else{	
				ArrayList<String> oldhanyupinyins = null;
				ArrayList<String> oldfirstpinyins = null;
				
				if(pinyinResults.length >1){//对于多音字需增加新串
					oldhanyupinyins =  (ArrayList<String>) hanyupinyins.clone();
					oldfirstpinyins =  (ArrayList<String>) firstpinyins.clone();;//拼音首字母
				} 

				for(int k=0; k < pinyinResults.length ; k++) {
					int oldlen = 0;
					if(k>0){
						oldlen = hanyupinyins.size();
						hanyupinyins.addAll(oldhanyupinyins);
						firstpinyins.addAll(oldfirstpinyins);
					}
					for(int i=oldlen; i < hanyupinyins.size() ;i++) {										
						String old = hanyupinyins.get(i);										
						hanyupinyins.set(i, old + pinyinResults[k].toUpperCase() );	
						
						String oldfirst = firstpinyins.get(i);
						firstpinyins.set(i, oldfirst + ((String.valueOf(pinyinResults[k].charAt(0))).toUpperCase()) );
						sblen = sblen + old.length() + 3;//估算拼音缩写长度,专门针对多音字，减少该辅助计算的处理量
					}
				}
				
			} 
			
			if(max>0 && sblen>max) break;							
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i < firstpinyins.size() ;i++ ){
			sb.append(",");
			sb.append(firstpinyins.get(i));
		}
		
		for(int i=0;i < hanyupinyins.size() ;i++ ){
			sb.append(",");
			sb.append(hanyupinyins.get(i));			
		}
		
		if(max>0 && sb.length()>max){//超长就缩短处理
			sb.setLength(max);							
		}
		
		return sb.toString();
		
	}

	/**
	 * 只解析内容为id,name数组的list,返回的结果已去重
	 */
	public static Set<PinyinEntity> getPinyinEntities(List<Object[]> idNames) {
		Set<PinyinEntity> entities = new TreeSet<>();
		if (CollectionUtils.isNotEmpty(idNames)) {
			for (Object[] p : idNames) {
				if (p.length != 2) {
					throw new IllegalArgumentException();
				}
				entities.add(new PinyinEntity(p[0], p[1]));
			}
		}
		return entities;
	}

}

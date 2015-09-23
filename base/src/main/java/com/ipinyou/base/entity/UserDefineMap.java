package com.ipinyou.base.entity;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;

/**
 * 提供MAP接口支持的用户自定义对象(支持JPA持久化)
 * @author zhyhang
 *
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
public abstract class UserDefineMap<K, V> extends UserDefineType implements Map<K, V> {

	private static final long serialVersionUID = 5309585543586676054L;
	private String kvSplitToken;
	
	public UserDefineMap(String entrySplitToken,String kvSplitToken) {
		super(entrySplitToken);
		this.kvSplitToken=kvSplitToken;
	}
	
	public String getKvSplitToken() {
		return kvSplitToken;
	}


	@Override
	public void clear() {
		invokeMapMethod("clear", null);
	}

	@Override
	public boolean containsKey(Object key) {
		return (boolean) invokeMapMethod("containsKey", new Object[]{key}, Object.class);
	}

	@Override
	public boolean containsValue(Object value) {
		return (boolean) invokeMapMethod("containsValue", new Object[]{value}, Object.class);
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return (Set<java.util.Map.Entry<K, V>>) invokeMapMethod("entrySet", null);
	}

	@Override
	public V get(Object key) {
		return (V) invokeMapMethod("get", new Object[]{key}, Object.class);
	}

	@Override
	public boolean isEmpty() {
		return (boolean) invokeMapMethod("isEmpty", null);
	}

	@Override
	public Set<K> keySet() {
		return (Set<K>) invokeMapMethod("keySet", null);
	}

	@Override
	public V put(K key, V value) {
		return (V) invokeMapMethod("put", new Object[]{key,value}, Object.class,Object.class);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		invokeMapMethod("putAll", new Object[]{map}, Map.class);
	}

	@Override
	public V remove(Object key) {
		return (V) invokeMapMethod("remove", new Object[]{key}, Object.class);
	}

	@Override
	public int size() {
		return (int) invokeMapMethod("size", null);
	}

	@Override
	public Collection<V> values() {
		return (Collection<V>) invokeMapMethod("values", null);
	}
	
	private Object invokeMapMethod(String name,Object[] args,Class<?>... paramTypes){
		Map<K,V> map=decodeMapValue();
		Method m=BeanUtils.findMethod(map.getClass(), name, paramTypes);
		Object result;
		try {
			result = m.invoke(map, args);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		createFrom(map);
		return result;
		
	}
	
	protected void createFrom(Map<? extends Object, ? extends Object> values){
		if(MapUtils.isEmpty(values)){
			setStrValue(null);
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (Entry<? extends Object, ? extends Object> entry : values.entrySet()) {
			Object objKey=entry.getKey();
			Object objValue=entry.getValue();
			sb.append(asString(objKey)).append(this.kvSplitToken).append(asString(objValue)).append(this.splitToken);
		}
		sb.delete(sb.length()-this.splitToken.length(), sb.length());
		setStrValue(sb.toString());
	}
	
	private Map<K,V> decodeMapValue(){
		String[] valueArray=getStrValueArray();
		Map<K, V> decodeValues=new HashMap<>();
		for (int i = 0; i < valueArray.length; i++) {
			String obj=valueArray[i];
			String[] kv=obj.split(this.kvSplitToken);
			if(kv != null){
				if(kv.length == 1){
					kv = new String[]{kv[0], ""};
				}
				decodeValues.put((K)mapKeyObject(kv[0]),(V)mapValueObject(kv[1]));
			}
		}
		return decodeValues;
	}
	
	/**
	 *  将字串对象反序列化为原类型对象，作为MAP的key
	 * @param key
	 * @return
	 */
	protected Object mapKeyObject(String key){
		return key;
	}
	
	/**
	 *  将字串对象反序列化为原类型对象，作为MAP的value
	 * @param value
	 * @return
	 */
	protected Object mapValueObject(String value){
		return value;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserDefineMap<String, Long> udt=new UserDefineMap<String,Long>(",", "="){
			private static final long serialVersionUID = 1L;
			
		};
		udt.put("a", 1L);
		udt.put("b", 2L);
		udt.put("c", 3L);
		MapUtils.debugPrint(System.out, "after put", udt);
		System.out.println("entrySet:"+udt.entrySet());
		System.out.println("containsKey(b):"+udt.containsKey("b"));
		System.out.println("containsValue(3L):"+udt.containsValue(3L));
		System.out.println("get(b):"+udt.get("b"));
		System.out.println("isEmpty:"+udt.isEmpty());
		System.out.println("keySet:"+udt.keySet());
		Map<String,Long> allMap=new HashMap<String,Long>();
		allMap.put("d", 4L);
		allMap.put("e", 5L);
		udt.putAll(allMap);
		MapUtils.debugPrint(System.out, "after putALL", udt);
		udt.remove("e");
		MapUtils.debugPrint(System.out, "after remove", udt);
		System.out.println("size:"+udt.size());
		System.out.println("values:"+udt.values());
		udt.clear();
		MapUtils.debugPrint(System.out, "after clear", udt);
		

	}


}

package com.ipinyou.base.entity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

/**
 * 
 * 提供List接口支持的用户自定义对象(支持JPA持久化)
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public abstract class UserDefineList<E  extends Comparable<? super E>> extends UserDefineType implements List<E> {

	private static final long serialVersionUID = -9109455873686401806L;
	
	public UserDefineList(String splitToken) {
		super(splitToken);
	}

	@Override
	public boolean add(E e) {
		return (boolean)invokeListMethod("add", new Object[]{e}, Object.class);
	}

	@Override
	public void add(int i, E e) {
		invokeListMethod("add", new Object[]{i,e}, int.class,Object.class);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return (boolean)invokeListMethod("addAll", new Object[]{c}, Collection.class);
	}

	@Override
	public boolean addAll(int i, Collection<? extends E> collection) {
		return (boolean)invokeListMethod("addAll", new Object[]{collection}, int.class,Collection.class);
	}

	@Override
	public void clear() {
		invokeListMethod("clear", null);
	}

	@Override
	public boolean contains(Object e) {
		return (boolean)invokeListMethod("contains", new Object[]{e}, Object.class);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return (boolean)invokeListMethod("containsAll", new Object[]{c}, Collection.class);
	}

	
	@Override
	public E get(int i) {
		return (E)invokeListMethod("get", new Object[]{i}, int.class);
	}

	@Override
	public int indexOf(Object e) {
		return (int) invokeListMethod("indexOf", new Object[]{e}, Object.class);
	}

	@Override
	public boolean isEmpty() {
		return (boolean) invokeListMethod("isEmpty", null);
	}

	@Override
	public Iterator<E> iterator() {
		return (Iterator<E>) invokeListMethod("iterator", null);
	}

	@Override
	public int lastIndexOf(Object e) {
		return (int) invokeListMethod("lastIndexOf", new Object[]{e}, Object.class);
	}

	@Override
	public ListIterator<E> listIterator() {
		return (ListIterator<E>) invokeListMethod("listIterator", null);
	}

	@Override
	public ListIterator<E> listIterator(int i) {
		return (ListIterator<E>) invokeListMethod("listIterator", new Object[]{i},int.class);
	}

	@Override
	public boolean remove(Object e) {
		return (boolean) invokeListMethod("remove", new Object[]{e}, Object.class);
	}

	@Override
	public E remove(int i) {
		return ((E) invokeListMethod("remove", new Object[]{i}, int.class));
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return (boolean) invokeListMethod("removeAll", new Object[]{c}, Collection.class);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return (boolean) invokeListMethod("retainAll", new Object[]{c}, Collection.class);
	}

	@Override
	public E set(int i, E e) {
		return (E) invokeListMethod("set", new Object[]{i,e}, int.class,Object.class);
	}

	@Override
	public int size() {
		return (int) invokeListMethod("size",null);
	}

	@Override
	public List<E> subList(int b, int e) {
		return (List<E>) invokeListMethod("subList",new Object[]{b,e},int.class,int.class);
	}

	@Override
	public Object[] toArray() {
		return (Object[]) invokeListMethod("toArray",null); 
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) invokeListMethod("toArray",new Object[]{a},Object[].class); 
	}
	
	private Object invokeListMethod(String name,Object[] args,Class<?>... paramTypes){
		List<E> list=decodeListValue();
		Method m=BeanUtils.findMethod(list.getClass(), name, paramTypes);
		Object result;
		try {
			result = m.invoke(list, args);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		createFrom(list);
		return result;
		
	}
	
	private void createFrom(List<E> values) {
		if (CollectionUtils.isEmpty(values)) {
			setStrValue(null);
			return;
		}
		if(needOdered()){//以有序串存储
			Collections.sort(values);
		}
		StringBuilder sb = new StringBuilder();
		for (Object obj : values) {
			sb.append(asString(obj)).append(this.splitToken);
		}
		sb.delete(sb.length()-this.splitToken.length(), sb.length());
		setStrValue(sb.toString());
	}
	
	private List<E> decodeListValue() {
		String[] valueArray = getStrValueArray();
		List<E> decodeValues = new ArrayList<E>(valueArray.length);
		for (int i = 0; i < valueArray.length; i++) {
			decodeValues.add((E) asObject(valueArray[i]));
		}
		return decodeValues;
	}
	
	/**
	 * 将字串对象反序列化为原类型对象，字串类型不需要覆写此方法
	 * @param strValue
	 * @return
	 */
	protected Object asObject(String strValue){
		return strValue;
	}
		
	public static void main(String[] argv){
		UserDefineList<String> udt=new UserDefineList<String>(","){
			private static final long serialVersionUID = -1318143690057259516L;};
		udt.add("a");
		udt.add("b");
		udt.add("c");
		udt.add(0, "A");
		udt.add(1, "B");
		udt.addAll(Arrays.asList(new String[]{"a","b","c","d","e","f"}));
		System.out.println("contains:"+udt.contains("a"));
		System.out.println("containsAll:"+udt.containsAll(Arrays.asList(new String[]{"a","b","c"})));
		System.out.println("get(1):"+udt.get(1));
		System.out.println("indexOf:"+udt.indexOf("B"));
		System.out.println("isEmpty:"+udt.isEmpty());
		System.out.println("iterator:"+udt.iterator());
		System.out.println("lastIndexOf:"+udt.lastIndexOf("a"));
		System.out.println("listIterator:"+udt.listIterator());
		System.out.println("listIterator:"+udt.listIterator(2));
		System.out.println("remove:"+udt.remove(1));
		System.out.println(udt.getStrValue());
		System.out.println("remove(c):"+udt.remove("c"));
		System.out.println(udt.getStrValue());
		System.out.println("removeAll:"+udt.removeAll(Arrays.asList(new String[]{"e"})));
		System.out.println("retainAll:"+udt.retainAll(Arrays.asList(new String[]{"A","a","b","d"})));
		System.out.println(udt.getStrValue());
		System.out.println("retainAll:"+udt.set(1, "1"));
		System.out.println(udt.getStrValue());
		System.out.println("size:"+udt.size());
		System.out.println("toArray:"+udt.toArray());
		System.out.println("toArray(T[]):"+udt.toArray(new String[0]));
		System.out.println(udt.getStrValue());
		udt.clear();
		System.out.println(udt.getStrValue());
	}

}


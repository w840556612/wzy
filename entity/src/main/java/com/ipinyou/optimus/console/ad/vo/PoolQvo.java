package com.ipinyou.optimus.console.ad.vo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ipinyou.optimus.console.user.entity.Pool.Hierarchy;
import com.ipinyou.optimus.console.user.entity.Pool.PoolType;

/**
 * 代理商查询对象
 * @author zgd
 *
 */
public class PoolQvo {

	private Long id;

	// 查询哪些代理商
	private List<Long> ids;
	
	// 代理商名称，模糊搜索
	private String name;

	// 用户ID
	private Long userId;
	
	// 查询哪个父级代理商下的代理商
	private Long parentId;
	
	// 当前登录用户可以查询的代理商类型
	private List<PoolType> typeList;
	
	// 要查询哪些层级(单级一级二级)的代理商
	private List<Hierarchy> hierarchyList;
	
	// 目标用户ID
	private Long targetUserId;

	// 查询目标用户拥有的(true)或是不拥有的(false)代理商
	private boolean targetUserOwned;
	
	//目标用户所属代理商id
	private Long targetUserPoolId;	
	
	// 分页对象
	private Pageable pageable;
	
	// 排序对象。若pageable!=null，则使用pageable中的sort；pageable==null时使用这里的sort
	private Sort sort;

	
	/**
	 * 查询哪个代理商
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 查询哪个代理商
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 查询哪些代理商
	 * @return
	 */
	public List<Long> getIds() {
		return ids;
	}

	/**
	 * 查询哪些代理商
	 * @param ids
	 */
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	/**
	 * 代理商名称，模糊搜索
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 代理商名称，模糊搜索
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 用户ID
	 * @return
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 查询哪个父级代理商下的代理商
	 * @return
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 查询哪个父级代理商下的代理商
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 当前登录用户可以查询的代理商类型
	 * @return
	 */
	public List<PoolType> getTypeList() {
		return typeList;
	}

	/**
	 * 当前登录用户可以查询的代理商类型
	 * @param typeList
	 */
	public void setTypeList(List<PoolType> typeList) {
		this.typeList = typeList;
	}

	/**
	 * 要查询哪些层级(单级一级二级)的代理商
	 * @return
	 */
	public List<Hierarchy> getHierarchyList() {
		return hierarchyList;
	}

	/**
	 * 要查询哪些层级(单级一级二级)的代理商
	 * @param hierarchyList
	 */
	public void setHierarchyList(List<Hierarchy> hierarchyList) {
		this.hierarchyList = hierarchyList;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	/**
	 * 排序对象。若pageable!=null，则使用pageable中的sort；pageable==null时使用这里的sort
	 * @return
	 */
	public Sort getSort() {
		return sort;
	}

	/**
	 * 排序对象。若pageable!=null，则使用pageable中的sort；pageable==null时使用这里的sort
	 * @param sort
	 */
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/**
	 * 目标用户所属代理商ID
	 * @return
	 */
	public Long getTargetUserPoolId() {
		return targetUserPoolId;
	}

	/**
	 * 目标用户所属代理商ID
	 * @param targetUserPoolId
	 */
	public void setTargetUserPoolId(Long targetUserPoolId) {
		this.targetUserPoolId = targetUserPoolId;
	}

	/**
	 * 目标用户ID
	 * @return
	 */
	public Long getTargetUserId() {
		return targetUserId;
	}

	/**
	 * 目标用户ID
	 * @param targetUserId
	 */
	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}

	/**
	 * 查询目标用户拥有的(true)或是不拥有的(false)代理商
	 * @return
	 */
	public boolean isTargetUserOwned() {
		return targetUserOwned;
	}

	/**
	 * 查询目标用户拥有的(true)或是不拥有的(false)代理商
	 * @param targetUserOwned
	 */
	public void setTargetUserOwned(boolean targetUserOwned) {
		this.targetUserOwned = targetUserOwned;
	}

}

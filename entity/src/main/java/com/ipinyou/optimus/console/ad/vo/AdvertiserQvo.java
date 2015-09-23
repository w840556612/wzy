package com.ipinyou.optimus.console.ad.vo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 广告主查询对象
 * @author zgd
 *
 */
public class AdvertiserQvo {
	
	// 广告主ID
	private Long id;
	
	// 广告主ID列表
	private List<Long> ids;

	// 广告主名称，模糊搜索
	private String name;

	// 是否保护，默认查询未被保护的广告主
	private boolean protect;

	// 是否删除，默认查询未被删除的广告主
	private boolean removed;

	// 当前登录用户是否“拥有本代理商下所有广告主”权限
	private boolean ownDirectlyPoolAll;

	// 当前登录用户是否“拥有所管理代理商且非本代理商下所有广告主”权限
	private boolean ownManagePoolAll;

	// 当前登录用户ID
	private Long loginUserId;
	
	// 当前登录用户所属的代理商ID
	private Long loginUserPoolId;
	
	// 当前登录用户管理的代理商ID列表
	private List<Long> loginUserManagePoolIds;
	
	// 目标用户所属的代理商，比如给用户分配广告主时，目标用户所属的代理商
	private Long poolId;
	
	// 查询哪些代理商ID
	private List<Long> poolIds;
	
	// 目标用户ID，比如给用户分配广告主时，要为哪个targetUserId分配广告主。
	private Long targetUserId;
	
	// 查询用户拥有的广告主还是不拥有的广告主
	private boolean targetUserOwned;
	
	// 分页对象
	private Pageable pageable;
	
	// 排序对象。若pageable!=null，则使用pageable中的sort；pageable==null时使用这里的sort
	private Sort sort;

	
	/**
	 * 广告主ID
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 广告主ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 广告主ID列表
	 * @return
	 */
	public List<Long> getIds() {
		return ids;
	}

	/**
	 * 广告主ID列表
	 * @param ids
	 */
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	/**
	 * 广告主名称，模糊搜索
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 广告主名称，模糊搜索
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 是否保护，默认查询未被保护的广告主
	 * @return
	 */
	public boolean isProtect() {
		return protect;
	}

	/**
	 * 是否保护，默认查询未被保护的广告主
	 * @param protect
	 */
	public void setProtect(boolean protect) {
		this.protect = protect;
	}

	/**
	 * 是否删除，默认查询未被删除的广告主
	 * @return
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * 是否删除，默认查询未被删除的广告主
	 * @param removed
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	/**
	 * 当前登录用户是否“拥有本代理商下所有广告主”权限
	 * @return
	 */
	public boolean isOwnDirectlyPoolAll() {
		return ownDirectlyPoolAll;
	}

	/**
	 * 当前登录用户是否“拥有本代理商下所有广告主”权限
	 * @param ownDirectlyPoolAll
	 */
	public void setOwnDirectlyPoolAll(boolean ownDirectlyPoolAll) {
		this.ownDirectlyPoolAll = ownDirectlyPoolAll;
	}

	/**
	 * 当前登录用户是否“拥有所管理代理商且非本代理商下所有广告主”权限
	 * @return
	 */
	public boolean isOwnManagePoolAll() {
		return ownManagePoolAll;
	}

	/**
	 * 当前登录用户是否“拥有所管理代理商且非本代理商下所有广告主”权限
	 * @param ownManagePoolAll
	 */
	public void setOwnManagePoolAll(boolean ownManagePoolAll) {
		this.ownManagePoolAll = ownManagePoolAll;
	}

	/**
	 * 当前登录用户ID
	 * @return
	 */
	public Long getLoginUserId() {
		return loginUserId;
	}

	/**
	 * 当前登录用户ID
	 * @param loginUserId
	 */
	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	/**
	 * 当前登录用户所属的代理商ID
	 * @return
	 */
	public Long getLoginUserPoolId() {
		return loginUserPoolId;
	}

	/**
	 * 当前登录用户所属的代理商ID
	 * @param loginUserPoolId
	 */
	public void setLoginUserPoolId(Long loginUserPoolId) {
		this.loginUserPoolId = loginUserPoolId;
	}

	/**
	 * 当前登录用户所管理的代理商ID列表
	 * @return
	 */
	public List<Long> getLoginUserManagePoolIds() {
		return loginUserManagePoolIds;
	}

	/**
	 * 当前登录用户所管理的代理商ID列表
	 * @param loginUserManagePoolIds
	 */
	public void setLoginUserManagePoolIds(List<Long> loginUserManagePoolIds) {
		this.loginUserManagePoolIds = loginUserManagePoolIds;
	}

	/**
	 * 目标用户所属的代理商，比如给用户分配广告主时，目标用户所属的代理商
	 * @return
	 */
	public Long getPoolId() {
		return poolId;
	}

	/**
	 * 目标用户所属的代理商，比如给用户分配广告主时，目标用户所属的代理商
	 * @param poolId
	 */
	public void setPoolId(Long poolId) {
		this.poolId = poolId;
	}

	/**
	 * 查询哪些代理商ID
	 * @return
	 */
	public List<Long> getPoolIds() {
		return poolIds;
	}

	/**
	 * 查询哪些代理商ID
	 * @param poolIds
	 */
	public void setPoolIds(List<Long> poolIds) {
		this.poolIds = poolIds;
	}

	/**
	 * 目标用户ID，比如给用户分配广告主时，要为哪个targetUserId分配广告主。
	 * @return
	 */
	public Long getTargetUserId() {
		return targetUserId;
	}

	/**
	 * 目标用户ID，比如给用户分配广告主时，要为哪个targetUserId分配广告主。
	 * @param targetUserId
	 */
	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}

	public boolean isTargetUserOwned() {
		return targetUserOwned;
	}

	public void setTargetUserOwned(boolean targetUserOwned) {
		this.targetUserOwned = targetUserOwned;
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

}

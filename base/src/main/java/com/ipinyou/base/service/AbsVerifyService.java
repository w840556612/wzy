/**
 * 
 */
package com.ipinyou.base.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.Assert;

/**
 * @author lijt
 * 
 */
public abstract class AbsVerifyService {
	/**
	 * 日志记录实例
	 */
	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());
	
	protected <T, ID extends Serializable> boolean checkDbData(
			PagingAndSortingRepository<T, ID> dao, String... orderField) {
		return checkDbData(dao, true, orderField);
	}

	protected <T, ID extends Serializable> boolean checkDbData(
			PagingAndSortingRepository<T, ID> dao, boolean checkAllData,
			String... orderField) {
		if (orderField.length == 0) {
			orderField = new String[] { "id" };
		}
		long count = dao.count();
		if (count == 0) {
			return true;
		}
		final int page_size = 200;
		long find = 0;
		for (int i = 0, num = 0; i < count; i += page_size) {
			Pageable p = new PageRequest(num++, page_size, Direction.ASC,
					orderField);
			Page<T> page = dao.findAll(p);
			find += page.getNumberOfElements();
			if (!checkAllData) {
				break;
			}
		}
		if (checkAllData) {
			Assert.isTrue(count == find);
		}
		return true;
	}

}

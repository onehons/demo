package com.duantian.cms.core.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

public class Pagination {

	private Integer pageNo;
	private Integer pageSize;
	private String sort;
	private String direction;

	public static PageRequest getPageRequest(Integer page, Integer size) {
		if (page == null || page.intValue() < 0)
			page = 0;
		if (size == null || size.intValue() < 1)
			size = 20;
		return new PageRequest(page, size);
	}

	public PageRequest getPageRequest() {
		if (StringUtils.isEmpty(getSort()))
			return new PageRequest(getPageNo(), getPageSize());
		return new PageRequest(getPageNo(), getPageSize(), new Sort(
				Direction.valueOf(getDirection()), getSort()));
	}

	public Integer getPageNo() {
		if (pageNo == null || pageNo.intValue() < 0)
			return 0;
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize.intValue() < 1)
			pageSize = 20;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDirection() {
		if (StringUtils.isEmpty(direction))
			return "ASC";
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}

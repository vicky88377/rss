package com.mindtree.restaurantsearchservice.model;

import java.util.List;

public class RestaurantsResponse extends ResponseStatusModel{
	private List<RestaurantModel> data;
	private int pageNo;
	private int pageSize;
	private int totalElements;
	private int totalpages;

	public List<RestaurantModel> getData() {
		return data;
	}

	public void setData(List<RestaurantModel> data) {
		this.data = data;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}

}

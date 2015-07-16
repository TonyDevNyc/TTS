package com.target.trak.system.web.views;

import java.util.List;

public class MenuItem implements Comparable<MenuItem> {

	private Long id;
	private String name;
	private int displayOrder;
	private String link;
	private Long parentId;
	private List<MenuItem> childMenuItems;

	@Override
	public int compareTo(MenuItem menuItem) {
		return this.displayOrder - menuItem.getDisplayOrder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<MenuItem> getChildMenuItems() {
		return childMenuItems;
	}

	public void setChildMenuItems(List<MenuItem> childMenuItems) {
		this.childMenuItems = childMenuItems;
	}
}
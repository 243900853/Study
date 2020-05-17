package com.xiaobi.bean;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Menu {
	@Id
	@GeneratedValue
	private Long menuId;

	@Column(name = "parent_id", nullable = true, length = 20)
	private Long parentId;

	@Column(name = "name", nullable = true, length = 60)
	private String name;

	@Column(name = "url", nullable = true, length = 255)
	private String url;

	@Column(name = "level", nullable = true, length = 2)
	private Long level;

	@Column(name = "description", nullable = true, length = 255)
	private String description;

	@Column(name = "status", nullable = true, length = 11)
	private Long status;

	@Column(name = "update_staff_id", nullable = true, length = 255)
	private String updateStaffId;

	@Column(name = "update_time", nullable = true)
	private Timestamp updateTime;

	@Column(name = "create_staff_id", nullable = true, length = 255)
	private String createStaffId;

	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;

	@Column(name = "type", nullable = true, length = 2)
	private Long type;

	private String parentName;

	@Override
	public String toString() {
		return "Menu{" +
				"menuId=" + menuId +
				", parentId=" + parentId +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", level=" + level +
				", description='" + description + '\'' +
				", status=" + status +
				", updateStaffId='" + updateStaffId + '\'' +
				", updateTime=" + updateTime +
				", createStaffId='" + createStaffId + '\'' +
				", createTime=" + createTime +
				", type=" + type +
				'}';
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getUpdateStaffId() {
		return updateStaffId;
	}

	public void setUpdateStaffId(String updateStaffId) {
		this.updateStaffId = updateStaffId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

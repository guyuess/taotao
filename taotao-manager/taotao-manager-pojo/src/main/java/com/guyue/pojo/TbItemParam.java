package com.guyue.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbItemParam implements Serializable{
    private Long id;

    private Long itemcatId;

    private Date created;

    private Date updated;

    private String paramData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemcatId() {
		return itemcatId;
	}

	public void setItemcatId(Long itemcatId) {
		this.itemcatId = itemcatId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}

	@Override
	public String toString() {
		return "TbItemParam [id=" + id + ", itemcatId=" + itemcatId + ", created=" + created + ", updated=" + updated
				+ ", paramData=" + paramData + "]";
	}

   
}
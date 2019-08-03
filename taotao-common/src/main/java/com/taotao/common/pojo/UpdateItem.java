package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class UpdateItem implements Serializable {

    private String status;

    private String itemDesc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @Override
    public String toString() {
        return "UpdateItem{" +
                ", status=" + status +
                ", itemDesc='" + itemDesc + '\'' +
                '}';
    }
}

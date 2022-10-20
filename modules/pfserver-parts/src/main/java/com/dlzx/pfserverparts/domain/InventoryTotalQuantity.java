package com.dlzx.pfserverparts.domain;

import com.dlzx.common.core.annotation.Excel;

public class InventoryTotalQuantity {
    /** 出入库数量 */
    @Excel(name = "出入库数量")
    private int inventoryOutTotalQuantity;

    /** 入库总数量 */
    @Excel(name = "入库总数量")
    private int inventoryTotalQuantity;

    public InventoryTotalQuantity() {
    }

    public InventoryTotalQuantity(int inventoryOutTotalQuantity, int inventoryTotalQuantity) {
        this.inventoryOutTotalQuantity = inventoryOutTotalQuantity;
        this.inventoryTotalQuantity = inventoryTotalQuantity;
    }

    public int getInventoryOutTotalQuantity() {
        return inventoryOutTotalQuantity;
    }

    public void setInventoryOutTotalQuantity(int inventoryOutTotalQuantity) {
        this.inventoryOutTotalQuantity = inventoryOutTotalQuantity;
    }

    public int getInventoryTotalQuantity() {
        return inventoryTotalQuantity;
    }

    public void setInventoryTotalQuantity(int inventoryTotalQuantity) {
        this.inventoryTotalQuantity = inventoryTotalQuantity;
    }

    @Override
    public String toString() {
        return "InventoryTotalQuantity{" +
                "inventoryOutTotalQuantity=" + inventoryOutTotalQuantity +
                ", inventoryTotalQuantity=" + inventoryTotalQuantity +
                '}';
    }
}

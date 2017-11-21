package edu.brandeis.spring.mvc.service;

import java.util.List;

import edu.brandeis.spring.mvc.domain.InventoryItem;

public class InventoryItemGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<InventoryItem> itemData;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<InventoryItem> getItemData() {
        return itemData;
    }

    public void setItemData(List<InventoryItem> itemData) {
        this.itemData = itemData;
    }
}

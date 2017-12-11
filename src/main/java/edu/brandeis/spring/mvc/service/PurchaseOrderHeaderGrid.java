package edu.brandeis.spring.mvc.service;

import java.util.List;

import edu.brandeis.spring.mvc.domain.PurchaseOrderHeader;
import edu.brandeis.spring.mvc.domain.Supplier;

public class PurchaseOrderHeaderGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<PurchaseOrderHeader> purchaseOrderHeaderData;

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

    public List<PurchaseOrderHeader> getPurchaseOrderHeaderData() {
        return purchaseOrderHeaderData;
    }

    public void setPurchaseOrderHeaderData(List<PurchaseOrderHeader> purchaseOrderHeaderData) {
        this.purchaseOrderHeaderData = purchaseOrderHeaderData;
    }
}

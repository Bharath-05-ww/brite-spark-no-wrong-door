package com.britespark.nowrongdoor.dto;

import java.util.List;

public class ResidentPage {

    private int page;
    private int page_size;
    private int total;
    private boolean has_more;
    private List<Resident> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<Resident> getResults() {
        return results;
    }

    public void setResults(List<Resident> results) {
        this.results = results;
    }
}

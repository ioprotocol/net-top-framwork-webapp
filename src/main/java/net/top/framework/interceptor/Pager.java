package net.top.framework.interceptor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.interceptor
 * @Description: 全自动分页生成器 主要解决分页参数的动态绑定
 * @author: xsy
 * @date： 2016/6/23
 * @version： V1.0
 */
public class Pager {
    // service --> jsp
    private Integer pageSize;
    private Integer pageOffset;
    private Long totalRows;

    private Integer totalPages;
    private Integer currentPageNo;

    private String pageSizeChangeUrl;

    private LinkedHashMap<Integer, String> pageUrls = new LinkedHashMap<Integer, String>();

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public String getPageSizeChangeUrl() {
        return pageSizeChangeUrl;
    }

    public void setPageSizeChangeUrl(String pageSizeChangeUrl) {
        this.pageSizeChangeUrl = pageSizeChangeUrl;
    }

    public LinkedHashMap<Integer, String> getPageUrls() {
        return pageUrls;
    }

    public void setPageUrls(LinkedHashMap<Integer, String> pageUrls) {
        this.pageUrls = pageUrls;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }
}

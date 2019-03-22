package net.top.framework.webform;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.webform
 * @Description:
 * @author: xsy
 * @date： 2016/11/18
 * @version： V1.0
 */
public class DataTable {
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private Object data;
    private String error;

    public DataTable() {
    }

    public DataTable(Integer draw, Long recordsTotal, Object data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = this.recordsTotal;
        this.data = data;
    }

    public DataTable(Integer draw, String error) {
        this.draw = draw;
        this.error = error;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

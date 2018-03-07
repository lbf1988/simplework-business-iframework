package com.simplework.business.iframework.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/16
 */
public class Page<T> implements Serializable {
    public final static int PAGE_RESULT_CODE = 0;
    private int code;
    private String msg;
    private int count;
    private List<T> data;
    private int pageNumber;
    private int pageSize;

    public Page() {
    }

    public Page(int count, List<T> data) {
        this.code = PAGE_RESULT_CODE;
        this.msg = "";
        this.count = count;
        this.data = data;
    }

    public Page(int count, List<T> data, int pageNumber, int pageSize) {
        this.code = PAGE_RESULT_CODE;
        this.msg = "";
        this.count = count;
        this.data = data;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 总页面
     * @return
     */
    public int totalPage(){
        if(0 == this.pageSize){
            return 0;
        }
        return ((this.count / this.pageSize) + ((this.count % this.pageSize) == 0? 0 : 1));
    }

    /**
     * 是否首页
     * @return
     */
    public boolean isFirstPage(){
        return this.pageNumber == 1;
    }

    /**
     * 是否最后一页
     * @return
     */
    public boolean isLastPage(){
        if(0 == this.pageSize){
            return false;
        }
        return this.pageNumber == ((this.count / this.pageSize) + ((this.count % this.pageSize) == 0? 0 : 1));
    }
}

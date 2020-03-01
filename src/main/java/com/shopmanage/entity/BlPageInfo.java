package com.shopmanage.entity;

import lombok.Data;

import java.util.List;

@Data
public class BlPageInfo<T> extends BaseBean {
        private long total;
        private List<T> list;
        private int pages;
        private int pageNum;

        public BlPageInfo() {
        }

        public BlPageInfo(long total, List<T> list, int pages, int pageNum) {
                this.total = total;
                this.list = list;
                this.pages = pages;
                this.pageNum = pageNum;
        }

        public long getTotal() {
                return total;
        }

        public void setTotal(long total) {
                this.total = total;
        }

        public List<T> getList() {
                return list;
        }

        public void setList(List<T> list) {
                this.list = list;
        }

        public int getPages() {
                return pages;
        }

        public void setPages(int pages) {
                this.pages = pages;
        }

        public int getPageNum() {
                return pageNum;
        }

        public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
        }
}

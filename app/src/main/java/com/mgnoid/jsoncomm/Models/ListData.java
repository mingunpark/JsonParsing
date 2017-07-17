package com.mgnoid.jsoncomm.Models;

/**
 * Created by MG_PARK on 2017-07-16.
 */

public class ListData {

    int rank;
    String order;
    String url;

    @Override
    public String toString() {
        return "ListData{" +
                "rank=" + rank +
                ", order='" + order + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
} //Json 통신을 통해 get 해온 데이터들을 저장할 객체

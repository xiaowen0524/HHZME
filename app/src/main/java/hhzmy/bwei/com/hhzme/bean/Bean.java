package hhzmy.bwei.com.hhzme.bean;

import java.util.List;

/**
 * Created by asus on 2016/11/4.
 */
public class Bean {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Bean(List<Data> data) {
        this.data = data;
    }

    public Bean() {
        super();
    }

    @Override
    public String toString() {
        return "Bean{" +
                "data=" + data +
                '}';
    }
}

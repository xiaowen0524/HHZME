package hhzmy.bwei.com.hhzme.bean;

import java.util.List;

/**
 * Created by asus on 2016/11/9.
 */
public class Data {
    List<Tag> tag;

    public Data(List<Tag> tag) {
        this.tag = tag;
    }

    public List<Tag> getTag() {

        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public Data() {
        super();
    }

    @Override
    public String toString() {
        return "Data{" +
                "tag=" + tag +
                '}';
    }
}

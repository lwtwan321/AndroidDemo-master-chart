package com.xinshiwi.power.progressview.month;

import java.io.Serializable;

/**
 * Created by apple on 2018/1/13.
 */

public class DateBean implements Serializable {
    private int year;
    private int month;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}

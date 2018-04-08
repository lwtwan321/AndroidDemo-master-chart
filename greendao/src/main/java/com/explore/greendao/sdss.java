package com.explore.greendao;

import rx.plugins.RxJavaErrorHandler;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class sdss {

    public static void main(String[] args) throws Exception {
        RxJavaErrorHandler j = new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
            }
        };
    }
}

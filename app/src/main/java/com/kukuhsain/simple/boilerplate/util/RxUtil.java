package com.kukuhsain.simple.boilerplate.util;

import rx.Subscription;

/**
 * Created by kukuh on 27/01/17.
 */

public class RxUtil {
    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.isUnsubscribed();
    }
}

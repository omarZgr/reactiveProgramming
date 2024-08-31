package org.courseUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubsriber implements Subscriber<Object> {

    private String name = " - " ;

    public DefaultSubsriber(String name)
    {
        this.name = name ;
    }

    public DefaultSubsriber(){}


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(Object o) {
        System.out.println(name + "Received : " + o);

    }

    @Override
    public void onError(Throwable t) {
        System.out.println(name + "Error : " + t);


    }

    @Override
    public void onComplete() {
        System.out.println(name + "Completed");
    }


}

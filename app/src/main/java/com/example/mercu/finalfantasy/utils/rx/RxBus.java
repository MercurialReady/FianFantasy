package com.example.mercu.finalfantasy.utils.rx;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by qicheng on 2018/9/27.
 */

public class RxBus
{
    private static volatile RxBus sInstance;

    private Subject bus = PublishSubject.create().toSerialized();

    private RxBus()
    {

    }

    public static RxBus getsInstance()
    {
        if(sInstance == null)
        {
            synchronized (RxBus.class)
            {
                if(sInstance == null)
                {
                    sInstance = new RxBus();
                }
            }
        }
        return sInstance;
    }

    @SuppressWarnings("unchecked")
    public void post(int code,Object object)
    {
        bus.onNext(new RxWrapper(code, object));
        //bus.onComplete();
    }

    @SuppressWarnings("unchecked")
    public <T> Observable<T> toObservable(final int code, final Class<T> eventType)
    {
        return bus.ofType(RxWrapper.class)
            .filter(new Predicate<RxWrapper>()
            {
                @Override
                public boolean test(RxWrapper o) throws Exception
                {
                    return (o.getCode() == code && eventType.isInstance(o.getObject()));
                }
            })
            .map(new Function<RxWrapper,Object>()
            {
                @Override
                public Object apply(RxWrapper o) throws Exception
                {
                    return o.getObject();
                }
            })
            .cast(eventType);
    }
}

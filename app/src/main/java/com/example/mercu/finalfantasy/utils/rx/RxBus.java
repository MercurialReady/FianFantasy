package com.example.mercu.finalfantasy.utils.rx;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by qicheng on 2018/9/27.
 */

public class RxBus
{
    private static volatile RxBus sInstance;

    private PublishSubject bus = (PublishSubject) PublishSubject.create().toSerialized();

    private RxBus()
    {}

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

    public void post(int code,Class clz)
    {
        bus.onNext(new RxWrapper(code, clz));
    }

    public void toObservable()
    {

    }

//    public void post(int code,Object object)
//    {
//        mBus.onNext(new RxMessage(code,object));
//    }
//
//    public <T> Flowable<T> toObservable(final int code, final Class<T> eventType)
//    {
//        return mBus.ofType(RxMessage.class)
//                .filter(new Predicate<RxMessage>()
//                {
//                    @Override
//                    public boolean test(RxMessage rxMessage) throws Exception
//                    {
//                        return (rxMessage.getCode() == code && eventType.isInstance(rxMessage
//                                .getObject()));
//                    }
//                })
//                .map(new Function<RxMessage, Object>()
//                {
//                    @Override
//                    public Object apply(RxMessage rxMessage) throws Exception
//                    {
//                        return rxMessage.getObject();
//                    }
//                })
//                .cast(eventType);
//    }
}

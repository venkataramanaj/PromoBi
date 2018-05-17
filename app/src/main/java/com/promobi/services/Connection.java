package com.promobi.services;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.promobi.R;
import com.promobi.services.Network.APIS;
import com.promobi.services.Network.ApiService;
import com.promobi.services.Network.RetrofitClient;
import com.promobi.view.FetchResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Rajesh Kumar on 02-05-2018.
 */
public class Connection {

    public static Connection connection;

    public static Connection getInstance(){
        if(null==connection){
            connection = new Connection();
        }

        return connection;
    }

    public void callingApi(final FetchResponse fetchResponse){


            flatOperator().subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {
//                    Log.e("onSubscribe calling", "<><>" );
                }

                @Override
                public void onNext(String s) {
//                    Log.e("inner On Next calling", "<><>" + s);
                    fetchResponse.getResponse(s);
                }

                @Override
                public void onError(Throwable e) {
//                    Log.e("onError calling", "<><>" + e.getMessage());
                }

                @Override
                public void onComplete() {
//                    Log.e("onComplete calling", "<><>" );
                }
            });

    }



    private Observable<String> flatOperator(){

        return   Observable.defer(new ObservableFromCallable<>(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                try {
                    return getObserable();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;

            }
        }));
    }
    

    @SuppressLint("CheckResult")
    private Observable<String > getObserable() {
            return   (new RetrofitClient().getClient(APIS.BASEURL).create(ApiService.class))
                    .getData( getParams())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    }


    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("api-key","57af699f5c0f484bbce7e7204c8d86e6");




        return params;
    }

}

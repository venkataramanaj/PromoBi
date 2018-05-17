package com.promobi.presenter;

import com.promobi.services.Connection;
import com.promobi.view.FetchResponse;

/**
 * Created by Rajesh Kumar on 16-05-2018.
 */
public class DashBoardImpl implements DashboardPresenter {
    FetchResponse fetchResponse;
    public DashBoardImpl(FetchResponse fetchResponse){
        this.fetchResponse = fetchResponse;
    }
    @Override
    public void fetchApi() {
        Connection.getInstance().callingApi(fetchResponse);
    }

    @Override
    public void getResponse(String response) {
        fetchResponse.getResponse(response);
    }

}

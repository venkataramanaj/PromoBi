package com.promobi.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.promobi.R;
import com.promobi.databinding.DashboardBinding;
import com.promobi.model.CartDetailsPojo;
import com.promobi.model.Result;
import com.promobi.presenter.DashBoardImpl;
import com.promobi.presenter.DashboardPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajesh Kumar on 16-05-2018.
 */
public class DashBoard extends AppCompatActivity implements FetchResponse {
    DashboardPresenter presenter;
    DashboardBinding binding;
    boolean loading;
    CartDetailsPojo data;
    private boolean scrollflag = true;
    int spage = 1;
    Adapter adapter;
    CartDetailsPojo temp_pojo;
    List<Result> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.dashboard);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
//        binding.recyclerview.addOnScrollListener(new EndlessScrollListener(binding.recyclerview));
        presenter = new DashBoardImpl(this);
        presenter.fetchApi();
    }


    @Override
    public void getResponse(String response) {
//        Log.e("response is ","<><><"+response);
        data = new Gson().fromJson(response, CartDetailsPojo.class);

        binding.recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.recyclerview.setAdapter(new Adapter(this, data.getResults()));



       /* if (spage == 1) {
            temp_pojo = data;
        } else {
            items = temp_pojo.getResults();
            items.addAll(data.getResults());
            temp_pojo.setResults(items);
//                nextPageToken = data.getNextPageToken();
        }

        if (spage == 1) {
//            adapter = new Adapter(this, temp_pojo.getItems());
//            recyclerview.setAdapter(adapter);
            adapter = new Adapter(this, loadSomedata(temp_pojo.getResults(),spage));
            binding.recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }


        for(int i=0;i<10;++i){
            Log.e("++i value is ","<><"+i);
        }

        for(int i=0;i<10;i++){
            Log.e("i++ value is ","<><"+i);
        }*/



    }

    private List<Result> loadSomedata(List<Result> list, int number) {
        List<Result> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            if (i >= number) {
                arrayList.add(list.get(i));
            }

            if(arrayList.size()==10){
                break;
            }

        }
        return arrayList;

    }

    public class EndlessScrollListener extends RecyclerView.OnScrollListener {
        private RecyclerView listView;

        public EndlessScrollListener(RecyclerView listView) {
            this.listView = listView;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }

        @Override
        public void onScrollStateChanged(RecyclerView view, int scrollState) {
            //Log.e("onScrollStateChanged","<><><>");
            LinearLayoutManager layoutManager = ((LinearLayoutManager) view.getLayoutManager());
            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            if (null != data) {
                if (scrollState == 0 && scrollflag
                        && lastVisiblePosition == listView.getAdapter().getItemCount() - 1) {
                    if (!loading) {
                        loading = true;

                        spage += 10;

                        presenter.fetchApi();

                    }

                }
            }
        }
    }


}

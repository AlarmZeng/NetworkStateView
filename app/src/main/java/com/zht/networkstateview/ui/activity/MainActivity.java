package com.zht.networkstateview.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zht.networkstateview.App;
import com.zht.networkstateview.R;
import com.zht.networkstateview.ui.widget.NetworkStateView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NetworkStateView.OnRefreshListener {

    private NetworkStateView networkStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        networkStateView = (NetworkStateView) findViewById(R.id.nsv_state_view);

        Button bt_loading = (Button) findViewById(R.id.bt_loading);
        Button bt_error = (Button) findViewById(R.id.bt_error);
        Button bt_noNetwork = (Button) findViewById(R.id.bt_no_network);
        Button bt_empty = (Button) findViewById(R.id.bt_empty);

        bt_loading.setOnClickListener(this);
        bt_error.setOnClickListener(this);
        bt_noNetwork.setOnClickListener(this);
        bt_empty.setOnClickListener(this);

        networkStateView.setOnRefreshListener(this);
        networkStateView.showLoading();
        showSuccess();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_loading:
                networkStateView.showLoading();
                break;

            case R.id.bt_error:
                networkStateView.showError();
                break;

            case R.id.bt_no_network:
                networkStateView.showNoNetwork();
                break;

            case R.id.bt_empty:
                networkStateView.showEmpty();
                break;

            default:
                break;
        }

        showSuccess();
    }

    @Override
    public void onRefresh() {
        networkStateView.showLoading();
        showSuccess();
    }

    private void showSuccess() {
        App.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                networkStateView.showSuccess();
            }
        }, 2000);
    }
}

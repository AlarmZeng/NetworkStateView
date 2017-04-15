package com.zht.networkstateview.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zht.networkstateview.R;

/**
 * Created by ZHT on 2017/4/15.
 * 加载状态的自定义View
 */

public class NetworkStateView extends LinearLayout {

    //当前的加载状态
    private int mCurrentState;
    private static final int STATE_SUCCESS = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_NETWORK_ERROR = 2;
    private static final int STATE_NO_NETWORK = 3;
    private static final int STATE_EMPTY = 4;

    private int mLoadingViewId;
    private int mErrorViewId;
    private int mNoNetworkViewId;
    private int mEmptyViewId;

    private View mLoadingView;
    private View mErrorView;
    private View mNoNetworkView;
    private View mEmptyView;

    private LayoutInflater mInflater;
    private ViewGroup.LayoutParams params;

    private OnRefreshListener mRefreshListener;

    public NetworkStateView(@NonNull Context context) {
        this(context, null);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetworkStateView, defStyleAttr, 0);
        mLoadingViewId = typedArray.getResourceId(R.styleable.NetworkStateView_loadingView, R.layout.view_loading);
        mErrorViewId = typedArray.getResourceId(R.styleable.NetworkStateView_errorView, R.layout.view_network_error);
        mNoNetworkViewId = typedArray.getResourceId(R.styleable.NetworkStateView_noNetworkView, R.layout.view_no_network);
        mEmptyViewId = typedArray.getResourceId(R.styleable.NetworkStateView_emptyView, R.layout.view_empty);
        typedArray.recycle();

        mInflater = LayoutInflater.from(context);
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setGravity(Gravity.CENTER);
        setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        showSuccess();
    }

    /**
     * 加载成功，隐藏加载状态的View
     */
    public void showSuccess() {
        mCurrentState = STATE_SUCCESS;
        showViewByState(mCurrentState);
    }

    /**
     * 显示加载中的状态
     */
    public void showLoading() {
        mCurrentState = STATE_LOADING;
        if (null == mLoadingView) {
            mLoadingView = mInflater.inflate(mLoadingViewId, null);
            addView(mLoadingView, 0, params);
        }
        showViewByState(mCurrentState);
    }

    /**
     * 显示加载失败(网络错误)状态
     */
    public void showError() {
        mCurrentState = STATE_NETWORK_ERROR;
        if (null == mErrorView) {
            mErrorView = mInflater.inflate(mErrorViewId, null);
            View errorRefreshView = mErrorView.findViewById(R.id.error_refresh_view);
            if (null != errorRefreshView) {
                errorRefreshView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != mRefreshListener) {
                            mRefreshListener.onRefresh();
                        }
                    }
                });
            }
            addView(mErrorView, 0, params);
        }
        showViewByState(mCurrentState);
    }

    /**
     * 显示没有网络状态
     */
    public void showNoNetwork() {
        mCurrentState = STATE_NO_NETWORK;
        if (null == mNoNetworkView) {
            mNoNetworkView = mInflater.inflate(mNoNetworkViewId, null);
            View networkRefreshView = mNoNetworkView.findViewById(R.id.no_network_refresh_view);
            if (null != networkRefreshView) {
                networkRefreshView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != mRefreshListener) {
                            mRefreshListener.onRefresh();
                        }
                    }
                });
            }
            addView(mNoNetworkView, 0, params);
        }
        showViewByState(mCurrentState);
    }

    /**
     * 显示无数据状态
     */
    public void showEmpty() {
        mCurrentState = STATE_EMPTY;
        if (null == mEmptyView) {
            mEmptyView = mInflater.inflate(mEmptyViewId, null);
            View emptyRefreshView = mEmptyView.findViewById(R.id.empty_refresh_view);
            if (null != emptyRefreshView) {
                emptyRefreshView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != mRefreshListener) {
                            mRefreshListener.onRefresh();
                        }
                    }
                });
            }
            addView(mEmptyView, 0, params);
        }
        showViewByState(mCurrentState);
    }

    private void showViewByState(int state) {

        //如果当前状态为加载成功，隐藏此View，反之显示
        this.setVisibility(state == STATE_SUCCESS ? View.GONE : View.VISIBLE);

        if (null != mLoadingView) {
            mLoadingView.setVisibility(state == STATE_LOADING ? View.VISIBLE : View.GONE);
        }

        if (null != mErrorView) {
            mErrorView.setVisibility(state == STATE_NETWORK_ERROR ? View.VISIBLE : View.GONE);
        }

        if (null != mNoNetworkView) {
            mNoNetworkView.setVisibility(state == STATE_NO_NETWORK ? View.VISIBLE : View.GONE);
        }

        if (null != mEmptyView) {
            mEmptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }
}

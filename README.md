# NetworkStateView
一个在进行数据加载时显示多种状态的自定义View，包括加载中丶加载成功丶加载失败丶网络异常丶没有网络丶无数据

![](https://github.com/AlarmZeng/NetworkStateView/blob/master/screenshots/screenshot1.png)
![](https://github.com/AlarmZeng/NetworkStateView/blob/master/screenshots/screenshot2.png)
![](https://github.com/AlarmZeng/NetworkStateView/blob/master/screenshots/screenshot3.png)
![](https://github.com/AlarmZeng/NetworkStateView/blob/master/screenshots/screenshot4.png)

## 使用

### 设置状态布局文件和默认样式

* 直接在布局文件中声明

		<com.zht.networkstateview.ui.widget.NetworkStateView xmlns:android="http://schemas.android.com/apk/res/android"
		    xmlns:app="http://schemas.android.com/apk/res-auto"
		    android:id="@+id/nsv_state_view"
		    android:layout_width="match_parent"
		    android:layout_height="match_parent"
		    android:layout_centerInParent="true"
		    android:orientation="vertical"
		    android:visibility="visible"
		    app:emptyView="@layout/view_empty"
		    app:errorView="@layout/view_network_error"
		    app:loadingView="@layout/view_loading"
		    app:noNetworkView="@layout/view_no_network"
		    app:nsvTextColor="@color/gray_text_default"
		    app:nsvTextSize="16sp">
		
		</com.zht.networkstateview.ui.widget.NetworkStateView>

* 在styles文件进行设置

		<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
	        <!-- Customize your theme here. -->
	        <item name="styleNetworkStateView">@style/NetworkStateViewTheme</item>
			...
	    </style>

		<style name="NetworkStateViewTheme" parent="NetworkStateView.Style">
	        <item name="nsvTextSize">16sp</item>
			<item name="nsvTextColor">#ffffff</item>
			...
	    </style>

### 属性

	<declare-styleable name="NetworkStateView">

        <!-- 加载中的布局id -->
        <attr name="loadingView" format="reference" />

        <!-- 加载错误的布局id -->
        <attr name="errorView" format="reference" />
        <!-- 加载错误的布局图片 -->
        <attr name="nsvErrorImage" format="reference" />
        <!-- 加载错误的布局文字 -->
        <attr name="nsvErrorText" format="string" />

        <!-- 没有数据的布局id -->
        <attr name="emptyView" format="reference" />
        <!-- 没有数据的布局图片 -->
        <attr name="nsvEmptyImage" format="reference" />
        <!-- 没有数据的布局文字 -->
        <attr name="nsvEmptyText" format="string" />

        <!-- 没有网络的布局id -->
        <attr name="noNetworkView" format="reference" />
        <!-- 没有数据的布局图片 -->
        <attr name="nsvNoNetworkImage" format="reference" />
        <!-- 没有数据的布局文字 -->
        <attr name="nsvNoNetworkText" format="string" />

        <!-- 刷新的ImageView图片id -->
        <attr name="nsvRefreshImage" format="reference"/>

        <!-- 文字大小 -->
        <attr name="nsvTextSize" format="dimension" />
        <!-- 文字颜色 -->
        <attr name="nsvTextColor" format="color" />
    </declare-styleable>

### 示例

	NetworkStateView networkStateView = (NetworkStateView) findViewById(R.id.nsv_state_view);

	//加载中
	networkStateView.showLoading();
	//加载失败(网络错误)
	networkStateView.showError();
	//没有网络
	networkStateView.showNoNetwork();
	//没有数据
	networkStateView.showEmpty();

## 注意

在对状态View进行控件id设置时，需要根据下面规则进行设置：

* 加载失败显示的图片id：error_image
* 加载失败显示的文字id：error_text
* 没有数据显示的图片id：empty_image
* 没有数据显示的文字id：empty_text
* 没有网络显示的图片id：no_network_image
* 没有网络显示的文字id：no_network_text
* 进行刷新显示的图片id：refresh_view

详细可见代码

## 文章

对**NetworkStateView**的详细介绍可以查看文章[NetworkStateView](http://www.jianshu.com/p/858d41972d15)
package com.example.googleplay35.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.googleplay35.adapter.MyAdapter;
import com.example.googleplay35.bean.AppInfo;
import com.example.googleplay35.holder.BaseHolder;
import com.example.googleplay35.holder.HomeHolder;
import com.example.googleplay35.http.HttpHelper;
import com.example.googleplay35.http.HttpHelper.HttpResult;
import com.example.googleplay35.protocol.HomeProtocol;
import com.example.googleplay35.utils.UIUtils;
import com.mwqi.ui.widget.BaseListView;
import com.mwqi.ui.widget.LoadingPage.LoadResult;

public class HomeFragment extends BaseFragment {


	private List<AppInfo> mDatas;

	@Override
	protected LoadResult load() {

		HomeProtocol protocol = new HomeProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
		
		
	}

	@Override
	public View loadView() {
		BaseListView listView = new BaseListView(UIUtils.getContext());
		DefalAdapter adapter = new DefalAdapter(listView,mDatas);
		listView.setAdapter(adapter);
		return listView;
	}

	public class DefalAdapter extends MyAdapter<AppInfo>{

		public DefalAdapter(AbsListView mAbsListView, List<AppInfo> mDatas) {
			super(mAbsListView, mDatas);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected BaseHolder getHolder() {
			// TODO Auto-generated method stub
			return new HomeHolder();
		}

		@Override
		public List onLoadMore() {
			HomeProtocol protocol = new HomeProtocol();
			return protocol.load(getData().size());
		}
	}
	
//	public class MyAdapter extends BaseAdapter {
//
//		private ViewHolder holder;
//
//		
//		@Override
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return mDatas.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			// TODO Auto-generated method stub
//			return mDatas.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			if (convertView == null) {
//				holder = new ViewHolder();
//			} else {
//				holder = (ViewHolder) convertView.getTag();
//			}
//			holder.setData(mDatas.get(position));
//			return holder.getRootView();
//		}
//	}
	class ViewHolder extends BaseHolder<String> {
		public TextView tv;
		@Override
		public View initView() {
			tv = new TextView(UIUtils.getContext());
			return tv;
		}
		@Override
		public void refreshView() {
			tv.setText(getData());

		}

	}

}

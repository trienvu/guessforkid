package com.gtotek.kidquiz.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.dao.Subject;

public class SubjectAdapter extends BaseAdapter {
	private Context mContext;
	private List<Subject> mSubjects;
	private LayoutInflater mLayoutInflater;

	public SubjectAdapter(Context context, List<Subject> subjects) {
		this.mContext = context;
		this.mSubjects = subjects;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mSubjects.size();
	}

	@Override
	public Object getItem(int position) {
		return mSubjects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mSubjects.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = (View) mLayoutInflater.inflate(
					R.layout.activity_objective, null);
			viewHolder = new ViewHolder(convertView);
		} else
			viewHolder = (ViewHolder) convertView.getTag();

		return null;
	}

	private static class ViewHolder {
		ImageView ivAvatar;
		TextView tvName;

		public ViewHolder(View v) {
			ivAvatar = (ImageView) v.findViewById(R.id.img1);
			tvName = (TextView) v.findViewById(R.id.grv_answer);
			v.setTag(this);
		}
	}
}

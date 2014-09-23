package com.gtotek.kidquiz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView; 

import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.dao.QuestionEntity;

public class DecodingAdapter extends BaseAdapter {

	private QuestionEntity mQuestionEntity;

	private Context mContext;

	private LayoutInflater mLayoutInflater;

	public DecodingAdapter(Context context, QuestionEntity questionEntity) {
		mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mQuestionEntity = questionEntity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mQuestionEntity.getDapAnCoDau().length();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mQuestionEntity.getDapAnCoDau().charAt(position) + "";
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		String letter = (String) getItem(position);

		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.cell_answer, null);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (!letter.trim().equals("")) { 
			viewHolder.tvLetter.setText(letter);
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
		}else{ 
			viewHolder.tvLetter.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

	private static class ViewHolder {
		TextView tvLetter;

		public ViewHolder(View v) {
			tvLetter = (TextView) v.findViewById(R.id.tv_letter);
			v.setTag(this);
		}
	}

}

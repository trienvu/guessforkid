package com.gtotek.kidquiz.adapter;

import java.util.List; 

import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.dao.Letter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SuggestionAdapter extends BaseAdapter {

	private Context mContext;

	private LayoutInflater mLayoutInflater; 
	
	private List<Letter> mLetters;

	public SuggestionAdapter(Context context,List<Letter> letters) { 
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) this.mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mLetters = letters;
	}
	 
	public void restore(Letter letter){
		for(int i = 0;i < mLetters.size();++i){
			Letter item = mLetters.get(i) ;
			
			if(item .getId()== letter.getId()){
				item.setVisible(true);
			}
		}
		
		notifyDataSetChanged();
	}
 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mLetters.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mLetters.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder viewHolder;

		Letter letter = (Letter) getItem(position);

		if (convertView == null) {
			convertView = this.mLayoutInflater.inflate(
					R.layout.cell_suggestion, null);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.tvLetter.setText(letter.getLetter());

		if(letter.isVisible()){
			convertView.setVisibility(View.VISIBLE);
		}else{
			convertView.setVisibility(View.INVISIBLE);
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

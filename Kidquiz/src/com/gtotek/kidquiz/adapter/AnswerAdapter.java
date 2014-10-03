package com.gtotek.kidquiz.adapter;

import java.util.List;

import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.dao.Letter;
import com.gtotek.kidquiz.dao.QuestionEntity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnswerAdapter extends BaseAdapter {

	private Context mContext;

	private LayoutInflater mLayoutInflater;

	private List<Letter> mAnswerLetters;

	public AnswerAdapter(Context context, List<Letter> answerLetters) {
		mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mAnswerLetters = answerLetters;

	}

	@SuppressLint("DefaultLocale")
	public Integer hepl(QuestionEntity questionEntity, List<Letter> letters) {
		int len = questionEntity.getDapAnKoDau().length();
		Letter let = null;
		Letter clone = null;

		// Integer[] array = new Integer[2];

		for (int i = 0; i < len; ++i) {

			Letter answerLetter = mAnswerLetters.get(i);

			if (!answerLetter.isHepl()) {
				let = answerLetter;
				clone = (Letter) answerLetter.clone();
				answerLetter.setLetter(questionEntity.getDapAnKoDau().charAt(i)
						+ "");
				answerLetter.setHepl(true);
				notifyDataSetChanged();
				break;
			}
		}

		// find position when restore
		if (null != clone) {
			String strClone = clone.getLetter().trim().toUpperCase();
			for (int i = 0; i < letters.size(); ++i) {
				Letter letter = letters.get(i);
				String strLetter = letter.getLetter().trim().toUpperCase();
				if (strClone.equals(strLetter) && !letter.isVisible()) {
					letter.setVisible(true);
					break;
				}
			}
		}

		// find position when remove
		if (null != let) {
			String strLet = let.getLetter().trim().toUpperCase();
			for (int i = 0; i < letters.size(); ++i) {
				Letter letter = letters.get(i);
				String strLetter = letter.getLetter().trim().toUpperCase();
				if (strLet.equals(strLetter)) {
					return i;
				}
			}
		}

		return null;

	}

	public boolean suscess(QuestionEntity questionEntity) {

		int len = questionEntity.getDapAnKoDau().length();

		for (int i = 0; i < len; ++i) {

			String letter = String.valueOf(questionEntity.getDapAnKoDau()
					.charAt(i));

			Letter answerLetter = mAnswerLetters.get(i);

			if (!letter.equals(answerLetter.getLetter())) {
				return false;
			}
		}

		return true;

	}

	public boolean isHaveEmpty() {
		for (int i = 0; i < mAnswerLetters.size(); ++i) {
			Letter item = mAnswerLetters.get(i);

			if (item.getLetter().equals("")) {
				return true;
			}
		}
		notifyDataSetChanged();
		return false;
	}

	public void add(Letter letter) {
		for (int i = 0; i < mAnswerLetters.size(); ++i) {
			Letter item = mAnswerLetters.get(i);

			if (item.getLetter().equals("")) {
				item.setLetter(letter.getLetter());
				item.setId(letter.getId());

				break;
			}
		}

		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAnswerLetters.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mAnswerLetters.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		Letter letter = (Letter) getItem(position);

		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.cell_answer, null);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvLetter.setText(letter.getLetter());

		if (letter.isHepl()) {
			viewHolder.tvLetter.setTextColor(Color.parseColor("#DC143C"));
		} else {
			viewHolder.tvLetter.setTextColor(Color.parseColor("#FFFFFF"));
		}

		viewHolder.llCellAnswer.setPadding(-5, -5, -5, -5);
		return convertView;
	}

	private static class ViewHolder {
		TextView tvLetter;
		LinearLayout llCellAnswer;
		public ViewHolder(View v) {
			llCellAnswer = (LinearLayout) v.findViewById(R.id.llCellAnswer);
			tvLetter = (TextView) v.findViewById(R.id.tv_letter);
			v.setTag(this);
		}
	}

}

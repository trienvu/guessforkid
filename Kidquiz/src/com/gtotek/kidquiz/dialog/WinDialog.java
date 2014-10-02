package com.gtotek.kidquiz.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.TextView;

import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.adapter.DecodingAdapter;
import com.gtotek.kidquiz.dao.QuestionEntity;
import com.gtotek.kidquiz.util.SoundUtil;

public class WinDialog extends Dialog {
	private TextView mTvWonderful;
	private TextView mTvGuessed;
	private TextView mTvContinue;
	private QuestionEntity mQuestionEntity;
	private Context mContext;
	private GridView mGrvDecoding;
	private OnCloseWinDialog mOnCloseWinDialog;
	
	public WinDialog(Context context, QuestionEntity questionEntity) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.dialog_win);

		getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#33DEDEDE")));
		getWindow().getAttributes().windowAnimations = R.style.dialog_animation_grow;

		this.mContext = context;
		this.mQuestionEntity = questionEntity;
		this.mTvWonderful = (TextView) this.findViewById(R.id.tvWonderful);
		this.mTvGuessed = (TextView) this.findViewById(R.id.tvGuessed);
		this.mTvContinue = (TextView) this.findViewById(R.id.tvContinue);
		this.mGrvDecoding = (GridView) this.findViewById(R.id.gvWin);

		setCancelable(false);
		init();
	}
	
	public void setOnCloseWinDialog(OnCloseWinDialog onCloseWinDialog){
		mOnCloseWinDialog = onCloseWinDialog;
	}
	
	private void init() {
		Animation animTopDown = AnimationUtils.loadAnimation(mContext,
				R.anim.grow_from_top);
		Animation animLeftRight = AnimationUtils.loadAnimation(mContext,
				R.anim.slide_in_left);
		animTopDown.setDuration(2000);
		animLeftRight.setDuration(2000);
		mTvGuessed.setAnimation(animTopDown);
		mGrvDecoding.setAnimation(animLeftRight);

		mTvContinue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				mOnCloseWinDialog.closeDialog();
			}
		});

		DecodingAdapter decodingAdapter = new DecodingAdapter(mContext,
				mQuestionEntity);

		mGrvDecoding.setAdapter(decodingAdapter);
		
		if(mQuestionEntity.getDapAnKoDau().trim().length() < 7){
			mGrvDecoding.setNumColumns(mQuestionEntity.getDapAnKoDau().trim().length());
		}
		else{
			mGrvDecoding.setNumColumns(7);
		}
		
		
		SoundUtil.hexat(mContext, SoundUtil.SFX_PASS);
	}
	
	public static interface OnCloseWinDialog{
		public void closeDialog();
	}
}

package com.gtotek.kidquiz.activity;


import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.base.Constans;
import com.gtotek.kidquiz.util.PreferenceUtil;
import com.gtotek.kidquiz.util.SoundUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class VictoryActivity extends Activity {

	private Context mContext = this;
	private TextView mTvAdmire;
	private TextView mTvWait;
	private TextView mTvClose;
	private ImageView mImgBall;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_victory);
		
		mImgBall = (ImageView) this.findViewById(R.id.imgBall);
		mTvAdmire = (TextView) this.findViewById(R.id.tvAdmire);
		mTvWait = (TextView) this.findViewById(R.id.tvWait);
		mTvClose = (TextView) this.findViewById(R.id.tvClose);

		init();
	}

	private void init() {
		PreferenceUtil.removeValue(mContext, Constans.KEY_INDEX_IMAGE);
		SoundUtil.hexat(mContext, SoundUtil.M_CONGRAT);

		Animation animTopDown = AnimationUtils.loadAnimation(mContext,
				R.anim.grow_from_top);
		Animation animLeftRightCycle = AnimationUtils.loadAnimation(mContext,
				R.anim.shaking);
		Animation animRotate = AnimationUtils.loadAnimation(mContext,
				R.anim.rotate);
		
		Animation animBrounce = AnimationUtils.loadAnimation(mContext,
				R.anim.brounce);
		
		animTopDown.setDuration(2000);
		animLeftRightCycle.setDuration(70000);
		animRotate.setDuration(2000);
		
		mTvAdmire.setAnimation(animTopDown);
		mTvWait.setAnimation(animLeftRightCycle);
		mImgBall.setAnimation(animBrounce);
		mTvClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}

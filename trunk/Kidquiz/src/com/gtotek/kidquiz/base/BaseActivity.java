package com.gtotek.kidquiz.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gtotek.kidquiz.R;
import com.androidquery.AQuery;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd; 
import com.gtotek.kidquiz.activity.VictoryActivity;
import com.gtotek.kidquiz.adapter.AnswerAdapter;
import com.gtotek.kidquiz.adapter.SuggestionAdapter;
import com.gtotek.kidquiz.dao.Letter;
import com.gtotek.kidquiz.dao.QuestionDao;
import com.gtotek.kidquiz.dao.QuestionEntity;
import com.gtotek.kidquiz.dao.QuestionImpl;
import com.gtotek.kidquiz.util.CaptureLayoutUtil;
import com.gtotek.kidquiz.util.ConnectionDetectorUtil;
import com.gtotek.kidquiz.util.LetterUtil;
import com.gtotek.kidquiz.util.PreferenceUtil;
import com.gtotek.kidquiz.util.SoundUtil;

public abstract class BaseActivity extends Activity {

	/** Your ad unit id. Replace with your actual ad unit id. */
	private static String AD_UNIT_ID = null;

	/** The interstitial ad. */
	private InterstitialAd interstitialAd;

	protected AlertDialog.Builder mBuilder;
	protected Context mContext = this;
	protected GridView mGrvQuestion;
	protected GridView mGrvSuggestions;
	protected TextView mTvLevel;
	protected TextView mTvRuby;
	protected ImageView mImgBack;
	protected ImageView mImgHelp;
	protected ImageView mImgShare;
	protected RelativeLayout mRltFrame;

	protected SuggestionAdapter mSuggestionAdapter;

	protected AnswerAdapter mAnswerAdapter;

	protected QuestionImpl mQuestionImpl;

	protected QuestionEntity mQuestionEntity;

	protected AQuery mAQuery;

	protected List<Letter> mSuggestionLetters = new ArrayList<Letter>();

	protected List<Letter> mAnswerLetters = new ArrayList<Letter>();

	protected int ruby = 0;

	protected int size = 0;

	protected int index = 0;

	private int max_height = 0;
	private int min_height = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*if (!ConnectionDetectorUtil.isConnectingToInternet(mContext)) {
			Toast.makeText(mContext, R.string.internet_error_msg,
					Toast.LENGTH_LONG).show();
			finish();
		}*/

//		 setInterstitialAd();
		initialize();
	}

	protected void initialize() {

		max_height = mContext.getResources().getInteger(
				R.integer.maximum_height);
		min_height = mContext.getResources().getInteger(
				R.integer.minimum_height);

		this.mRltFrame = (RelativeLayout) this.findViewById(R.id.rltFrame);

		this.mImgHelp = (ImageView) this.findViewById(R.id.iv_help);
		this.mImgHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mBuilder.show();
			}
		});

		this.mImgShare = (ImageView) this.findViewById(R.id.iv_share);
		this.mImgShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				share(v);
			}
		});

		this.mTvRuby = (TextView) this.findViewById(R.id.tv_ruby);

		this.mQuestionImpl = new QuestionImpl(mContext);

		size = this.mQuestionImpl.getSize();
		ruby = PreferenceUtil.getValue(mContext, Constans.KEY_RUBY,
				Constans.DEFAULT_VALUE_RUBY);

		this.mAQuery = new AQuery(mContext);

		this.mGrvQuestion = (GridView) findViewById(R.id.grv_answer);

		this.mAnswerAdapter = new AnswerAdapter(mContext, mAnswerLetters);
		this.mGrvQuestion.setAdapter(mAnswerAdapter);
		this.mGrvQuestion.setOnItemClickListener(answerOnItemClickListener);

		this.mSuggestionAdapter = new SuggestionAdapter(mContext,
				mSuggestionLetters);
		this.mGrvSuggestions = (GridView) findViewById(R.id.grv_suggestions);
		this.mGrvSuggestions.setAdapter(this.mSuggestionAdapter);
		this.mGrvSuggestions
				.setOnItemClickListener(suggestionsOnItemClickListener);

		this.mTvLevel = (TextView) this.findViewById(R.id.tv_lever);

		this.mImgBack = (ImageView) this.findViewById(R.id.iv_back);
		mImgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		mBuilder = new AlertDialog.Builder(mContext);
		mBuilder.setMessage(R.string.builder_message);
		mBuilder.setPositiveButton(R.string.btn_help_positive,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						help();
					}
				});

		mBuilder.setNegativeButton(R.string.btn_help_negative,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

		next();

	}

	private void setInterstitialAd() {
		AD_UNIT_ID = getResources().getString(R.string.id_admob);

		// Create an ad.
		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(AD_UNIT_ID);
		AdRequest adRequest = new AdRequest.Builder().build();

		// Set the AdListener.
		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				interstitialAd.show();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {

			}
		});

		// Load the interstitial ad.
		interstitialAd.loadAd(adRequest);
	}

	protected void incrementIndex() {
		index++;

	}

	protected void help() {

		if (ruby > 20) {
			if (!mAnswerAdapter.suscess(mQuestionEntity)) {
				Integer position = mAnswerAdapter.hepl(mQuestionEntity,
						mSuggestionLetters);
				if (null != position) {
					Letter letter = mSuggestionLetters.get(position);
					letter.setVisible(false);
					mSuggestionAdapter.notifyDataSetChanged();

					ruby -= 20;
					PreferenceUtil.setValue(mContext, Constans.KEY_RUBY, ruby);
					mTvRuby.setText(ruby + "");
				}
				checkWin();
			}
		} else {
			Toast.makeText(mContext, R.string.toast_help_error_message,
					Toast.LENGTH_LONG).show();
		}

	}

	private void share(View v) {
		View root = v.getRootView().findViewById(R.id.parent);
		Bitmap bitmap = CaptureLayoutUtil.captureLayoutGoodQuality(root);
		// handle to facebook

		Uri u = CaptureLayoutUtil.getImageUri(mContext, bitmap);
		CaptureLayoutUtil.shareToFacebook(mContext, u);

		Toast.makeText(mContext, R.string.toast_share_message,
				Toast.LENGTH_LONG).show();

		ruby += 5;
		PreferenceUtil.setValue(mContext, Constans.KEY_RUBY, ruby);
		mTvRuby.setText(ruby + "");
	}

	private void youWin() {
		finish();
		Intent intent = new Intent(mContext, VictoryActivity.class);
		// intent.putExtra(Constans.KEY_TYPE, type);
		startActivity(intent);
	}

	protected abstract void passQuestion();

	protected void failQuestion() {

		Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.shaking);
		mGrvQuestion.setAnimation(anim);
		anim.start();

		SoundUtil.hexat(mContext, SoundUtil.OVER);
	}

	protected void next() {
		mTvRuby.setText(ruby + "");
		mTvLevel.setText((index + 1) + "");

		if (index >= size) {
			youWin();
			return;
		}

		this.mQuestionEntity = this.mQuestionImpl.getQuestionByPosition(index);

		if (this.mQuestionEntity.getDapAnKoDau().length() > Constans.MAX_LEN) {
			mRltFrame.getLayoutParams().height = min_height;
		} else {
			mRltFrame.getLayoutParams().height = max_height;
		}

		mAnswerLetters.clear();
		this.mAnswerLetters.addAll(LetterUtil
				.getEmptyAnswerDefault(this.mQuestionEntity));

		mSuggestionLetters.clear();
		this.mSuggestionLetters.addAll(LetterUtil
				.generateSuggestion(this.mQuestionEntity));

		if (mAnswerLetters.size() <= 7)
			mGrvQuestion.setNumColumns(mAnswerLetters.size());
		else
			mGrvQuestion.setNumColumns(7);

		mAnswerAdapter.notifyDataSetChanged();
		mSuggestionAdapter.notifyDataSetChanged();

		incrementIndex();
	}

	private void checkWin() {

		if (mAnswerAdapter.isHaveEmpty()) {
			return;
		}

		if (mAnswerAdapter.suscess(mQuestionEntity)) {
//		if (true) {
			passQuestion();
			ruby += Constans.RUBY_BONUS;
			PreferenceUtil.setValue(mContext, Constans.KEY_RUBY, ruby);
			// next();
		} else {
			failQuestion();
		}
	}

	protected OnItemClickListener answerOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Letter letter = (Letter) parent.getItemAtPosition(position);

			if (!letter.isHepl()) {
				mSuggestionAdapter.restore(letter);
				letter.setId(-1);
				letter.setLetter("");
				mAnswerAdapter.notifyDataSetChanged();
			}
		}
	};

	protected OnItemClickListener suggestionsOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Letter letter = (Letter) parent.getItemAtPosition(position);
			if (mAnswerAdapter.isHaveEmpty()) {
				mAnswerAdapter.add(letter);
				letter.setVisible(false);
				mSuggestionAdapter.notifyDataSetChanged();
			}
			checkWin();

		}
	};
}

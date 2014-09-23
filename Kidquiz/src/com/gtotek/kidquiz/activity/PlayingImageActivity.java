package com.gtotek.kidquiz.activity;

import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
 
import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.base.BaseActivity;
import com.gtotek.kidquiz.base.Constans;
import com.gtotek.kidquiz.dialog.WinDialog;
import com.gtotek.kidquiz.dialog.WinDialog.OnCloseWinDialog;
import com.gtotek.kidquiz.util.PreferenceUtil;
import com.winsontan520.WScratchView;

public class PlayingImageActivity extends BaseActivity implements
		OnCloseWinDialog {
	private Context mContext = this;
	private ImageView mImgQuestion;
	private WinDialog mWinDialog;
	

	@Override
	protected void initialize() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_playing_image);
		this.mImgQuestion = (ImageView) this.findViewById(R.id.img_question);
		index = PreferenceUtil.getValue(mContext, Constans.KEY_INDEX_IMAGE, 0);
		
		mWScratchView  = (WScratchView)this.findViewById(R.id.scratch_view);
		mWScratchView.setOnScratchCallback(new WScratchView.OnScratchCallback() {

			@Override
			public void onScratch(float percentage) {
				if(percentage > 50){
					mWScratchView.setScratchable(false);
					  Toast.makeText(mContext, "Bạn chỉ được cào 50%", Toast.LENGTH_LONG).show();
				}
					
			}

		});
		
		super.initialize();
	}

	@Override
	protected void incrementIndex() {
		// TODO Auto-generated method stub
		PreferenceUtil.setValue(mContext, Constans.KEY_INDEX_IMAGE, index);
		super.incrementIndex();

	}

	@Override
	protected void next() {

		super.next();

		Bitmap bitmap = getBitmapFromAsset(this.mQuestionEntity.getImage());
		this.mImgQuestion.setImageBitmap(bitmap);
		

		// bitmap.recycle();
		// bitmap = null;

	}

	private Bitmap getBitmapFromAsset(String name) {
		AssetManager assetManager = getAssets();
		InputStream istr = null;

		try {
			istr = assetManager.open("logofootball/" + name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		return bitmap;
	}

	@Override
	protected void passQuestion() {
		// TODO Auto-generated method stub
		/*
		 * Intent intent = new Intent(mContext, WinActivity.class);
		 * intent.putExtra(Constans.KEY_QUESTIONENTITY, mQuestionEntity);
		 * startActivity(intent);
		 */
		mWinDialog = new WinDialog(mContext, mQuestionEntity);
		mWinDialog.setOnCloseWinDialog(this);
		mWinDialog.show();
		
		mWScratchView.resetView();
		mWScratchView.setScratchable(true);
	}

	@Override
	public void closeDialog() {
		next();
	}

}

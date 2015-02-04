package com.gtotek.kidquiz.util;
 

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.base.Constans;

 

public class SoundUtil { 
	public static int M_CONGRAT = R.raw.m_congrat;
	public static int M_TITLE = R.raw.m_title;
	public static int OVER = R.raw.over;
	public static int SFX_CLICK = R.raw.sfx_click;
	public static int SFX_CONFIRM = R.raw.sfx_confirm;
	public static int SFX_LEVEL_UP = R.raw.sfx_level_up;
	public static int SFX_OVER = R.raw.sfx_over;
	public static int SFX_PASS = R.raw.sfx_pass;
	public static int LIMIT_SCRATCH = R.raw.buzzer;
	
	public static void hexat(Context context, int sound) {
		boolean soundState =  PreferenceUtil.getValue(context, Constans.KEY_SOUND, true);
		if(!soundState){
			return;
		}		
	
		final MediaPlayer mediaPlayer = MediaPlayer.create(context, sound);
		mediaPlayer.start();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mediaPlayer.release();
			}
		});
	}

}

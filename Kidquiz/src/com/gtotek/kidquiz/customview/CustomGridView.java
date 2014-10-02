package com.gtotek.kidquiz.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.GridView;

import com.gtotek.kidquiz.R;

public class CustomGridView extends GridView {
	private Context mContext;
	private int mRequestedNumColumns = 0;

	public CustomGridView(Context context) {
		super(context);
		mContext = context;
	}

	public CustomGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	@Override
	public void setNumColumns(int numColumns) {
		super.setNumColumns(numColumns);

		if (numColumns != mRequestedNumColumns) {
			mRequestedNumColumns = numColumns;
		}
	}

	@SuppressLint("NewApi")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Resources res = mContext.getResources();
		int hSpace;
		if (android.os.Build.VERSION.SDK_INT >= 16)
			hSpace = getHorizontalSpacing();
		else
			hSpace = (int) res.getDimension(R.dimen.horizontal_spacing);
		if (mRequestedNumColumns > 0) {
			int width = (mRequestedNumColumns * getColumnWidth(mContext, this)
					+ ((mRequestedNumColumns - 1) * hSpace)
					+ getListPaddingLeft() + getListPaddingRight());

			setMeasuredDimension(width, getMeasuredHeight());
		}
	}

	@SuppressLint("NewApi")
	private int getColumnWidth(Context context, GridView gridView) {
		if (android.os.Build.VERSION.SDK_INT >= 16)
			return gridView.getColumnWidth();

		Resources res = context.getResources();
		int lPad = (int) res.getDimension(R.dimen.left_padding);
		int rPad = (int) res.getDimension(R.dimen.right_padding);
		int hSpace = (int) res.getDimension(R.dimen.horizontal_spacing);
		// int cols = AUTO_FIT;
		int cols = gridView.getNumColumns();
		int width = gridView.getWidth();

		return (width - lPad - rPad + hSpace) / cols - hSpace;
	}
}
package com.gtotek.kidquiz.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gtotek.kidquiz.R;
import com.gtotek.kidquiz.adapter.SubjectAdapter;
import com.gtotek.kidquiz.dao.Subject;

public class SubjectActivity extends Activity {
	private Context mContext;
	private GridView mGvSubject;
	private ArrayList<Subject> mSubjects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_objective);
		this.mContext = this;
		this.mGvSubject = (GridView) this.findViewById(R.id.lvChanel);
		this.mSubjects = new ArrayList<Subject>();
		init();
	}

	private void init() {
		for (int i = 0; i < 10; i++) {
			mSubjects.add(new Subject(i, "xx", "yyyy"));
		}
		SubjectAdapter subjectAdapter = new SubjectAdapter(mContext, mSubjects);
		mGvSubject.setAdapter(subjectAdapter);
		mGvSubject
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent();
						intent.setClass(mContext, PlayingImageActivity.class);
						startActivity(intent);
					}
				});
	}
}

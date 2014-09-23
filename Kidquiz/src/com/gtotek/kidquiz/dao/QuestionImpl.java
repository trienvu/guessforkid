package com.gtotek.kidquiz.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gtotek.kidquiz.util.DatabaseUtil;

public class QuestionImpl extends DatabaseUtil implements QuestionDao {

	private static final String TABLE_NAME = "cauhoi";

	public QuestionImpl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		SQLiteDatabase database = getReadableDatabase();

		Cursor cursor = database.rawQuery("SELECT count(*) AS size FROM "
				+ TABLE_NAME, null);
		cursor.moveToFirst();

		int size = cursor.getInt(0);

		cursor.close();
		database.close();

		return size;
	}

	@Override
	public QuestionEntity getQuestionByPosition(int index) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = getReadableDatabase();

		Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME
				+ " LIMIT " + index + ",1", null);

		cursor.moveToFirst();

		int id = cursor.getInt(0);
		String image = cursor.getString(1);
		String dapAnCoDau = cursor.getString(2);
		String dapAnKoDau = cursor.getString(3); 
		String content = cursor.getString(4);

		QuestionEntity entity = new QuestionEntity();
		entity.setId(id);
		entity.setImage(image);
		entity.setDapAnCoDau(dapAnCoDau);
		entity.setDapAnKoDau(dapAnKoDau);
		entity.setContent(content); 

		cursor.close();
		database.close();

		return entity;
	}

	@Override
	public List<QuestionEntity> getAllQuestionByType() {
		// TODO Auto-generated method stub
		SQLiteDatabase database = getReadableDatabase();

		Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

		List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();

		while (cursor.moveToNext()) {

			int id = cursor.getInt(0);
			String image = cursor.getString(1);
			String dapAnCoDau = cursor.getString(2);
			String dapAnKoDau = cursor.getString(3); 
			String content = cursor.getString(4);

			QuestionEntity entity = new QuestionEntity();
			entity.setId(id);
			entity.setImage(image);
			entity.setDapAnCoDau(dapAnCoDau);
			entity.setDapAnKoDau(dapAnKoDau);
			entity.setContent(content); 

			questionEntities.add(entity);
		}

		cursor.close();
		database.close();

		return questionEntities;
	}
}

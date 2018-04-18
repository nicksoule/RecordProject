package com.gc.dao;

import java.util.ArrayList;

import com.gc.model.Record;

public interface RecordDao {
	
	public ArrayList<Record> getAllRecs();
	public Record getRec();
	public void addRec(ArrayList<Record> recArr);

}

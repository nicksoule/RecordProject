package com.gc.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gc.model.Record;
import com.gc.util.HibernateUtil;

public class RecordDaoImp implements RecordDao {

	@Override
	public ArrayList<Record> getAllRecs() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(Record.class);
		ArrayList<Record> recList = (ArrayList<Record>) crit.list();
		tx.commit();
		session.close();

		return recList;
	}

	@Override
	public Record getRec() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(Record.class);
		Record rec = (Record) crit.list();
		tx.commit();
		session.close();
		return rec;

	}

	@Override
	public void addRec(ArrayList<Record> recArr) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(recArr);
		tx.commit();
		session.close();

	}

}

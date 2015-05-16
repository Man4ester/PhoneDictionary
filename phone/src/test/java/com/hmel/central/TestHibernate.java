package com.hmel.central;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.hmel.central.utils.HibernateUtils;

public class TestHibernate {
	
	public static void main(String[] args) {
		String str = "SELECT id FROM client";
		SessionFactory sessionFactory = HibernateUtils.getSessionfactory();
		sessionFactory.getCurrentSession().beginTransaction();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(str);
		List<Integer> lst = query.list();
		sessionFactory.getCurrentSession().close();
		System.out.println("Size:"+lst.size());
	}

}

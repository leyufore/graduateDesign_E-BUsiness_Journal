package com.leyufore.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.leyufore.domain.User;

public class UserDao {
	public Integer insert(User user){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer) session.save(user);
		tx.commit();
		session.close();
		sf.close();
		return id; 
	}
	
	
	public User findByUserName(String username){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sql = "SELECT * FROM multiplePlatform.user WHERE username = '" + username +"'";
		List resultList = session.createSQLQuery(sql).addEntity(User.class).list();
		Iterator<User> iterator = resultList.iterator();
		User result = null;
		if(iterator.hasNext()){
			result = iterator.next();
		}
		tx.commit();
		session.close();
		sf.close();
		return result;
	}
	
	public static void main(String[] args){
		/**
		User user = new User();
		user.setUsername("leyufore1");
		user.setPassword("leyufore");
		user.setSession_taobao("610230519f76d16e0f64b6c0974a361eb587c672fb9d7dd3651880377");
		user.setSession_yhd("2cc2238c8c12da12ef05937db0d01f24");
		new UserDao().insert(user);
		**/
		System.out.println(new UserDao().findByUserName("leyufore").getPassword());
	}
}

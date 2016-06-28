package com.leyufore.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.leyufore.domain.Product;
import com.leyufore.domain.User;
import com.leyufore.util.ConstantUtil;
import com.leyufore.util.FactoryUtil;
import com.yhd.object.logistics.CartonInfo;

import net.sf.json.JSONObject;

public class ProductDao {
	//数据库添加商品
	public Integer insert(Product product){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer) session.save(product);
		tx.commit();
		session.close();
		sf.close();
		return id;
	}
	//数据库更新商品
	public void update(Product product){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(product);
		tx.commit();
		session.close();
		sf.close();
	}
	//数据库删除商品
	public void delete(Product product){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(product);
		tx.commit();
		session.close();
		sf.close();	
	}
	//数据库通过itemId查询商品
	public Product findByProductItemId(long itemId){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sql = "SELECT * FROM multiplePlatform.product WHERE item_id = " + itemId;
		List resultList = session.createSQLQuery(sql).addEntity(Product.class).list();
		Iterator<Product> iterator = resultList.iterator();
		Product result = null;
		if(iterator.hasNext()){
			result = iterator.next();
		}
		tx.commit();
		session.close();
		sf.close();
		return result;
	}
	//数据库通过商品标题查询商品
	public Product findByProductTitle(String title){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sql = "SELECT * FROM multiplePlatform.product WHERE title = '" + title +"'";
		List resultList = session.createSQLQuery(sql).addEntity(Product.class).list();
		Iterator<Product> iterator = resultList.iterator();
		Product result = null;
		if(iterator.hasNext()){
			result = iterator.next();
		}
		tx.commit();
		session.close();
		sf.close();
		return result;
	}
	//数据库通过商品Id查询商品
	public Product findByProductId(int id){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sql = "SELECT * FROM multiplePlatform.product WHERE id = " + id;
		List resultList = session.createSQLQuery(sql).addEntity(Product.class).list();
		Iterator<Product> iterator = resultList.iterator();
		Product result = null;
		if(iterator.hasNext()){
			result = iterator.next();
		}
		tx.commit();
		session.close();
		sf.close();
		return result;
	}
	//数据库查询所有商品
	public List<Product> findAll(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sql = "SELECT * FROM multiplePlatform.product";
		List<Product> resultList = session.createSQLQuery(sql).addEntity(Product.class).list();
		tx.commit();
		session.close();
		sf.close();
		return resultList;
	}
	
	public static void main(String[] args){
		/**
		ProductDao productDao = new ProductDao();
		Product product = productDao.findByProductId(5);
		System.out.println(product.getCity());
		**/
		/**
		ProductDao productDao = new ProductDao();
		Product product = FactoryUtil.getTestProduct();
		product.setState(ConstantUtil.ITEM_STATE_SALING);
		productDao.insert(product);
		**/
		ProductDao productDao = new ProductDao();
		List<Product> productList = productDao.findAll();
		for(int i=0; i < productList.size(); i++){
			System.out.println(productList.get(i).getTitle());
		}
	}
}

package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//need to inject Hibernate session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override	
	public List<Customer> getCustomer() {
		
		//get current hibernate sessiion
		Session currentSession = sessionFactory.getCurrentSession();
		//create a query and add sort by
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		//return the results
		
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		//Get the current hibernte session
		Session currentSesion = sessionFactory.getCurrentSession();
		//Save the customer to databse
		//currentSesion.save(theCustomer);
		currentSesion.saveOrUpdate(theCustomer);
	}

	@Override	
	public Customer getCustomer(int theId) {
		// TODO Auto-generated method stub
		//Get the current hibernte session		
		Session currentSession = sessionFactory.getCurrentSession();
		//Now retrieve/read from database using the primary key
		Customer thecustomer = currentSession.get(Customer.class,theId);
		return thecustomer;
	}

}

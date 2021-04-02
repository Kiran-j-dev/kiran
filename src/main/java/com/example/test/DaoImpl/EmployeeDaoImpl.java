package com.example.test.DaoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Config.HibernateConfig;
import com.example.test.Dao.EmployeeDAO;
import com.example.test.Model.Employees;
import com.example.test.RequestClass.EmployeeRequest;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {
	
	@Autowired
	HibernateConfig conf;

	@Override
	public Employees saveEmployee(Employees employee) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
	}

	@Override
	public List<Employees> getAllEmployees() {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		List<Employees> employee = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employees> criteria = builder.createQuery(Employees.class);
			Root<Employees> root = criteria.from(Employees.class);
			employee = session.createQuery(criteria.select(root).orderBy(builder.desc(root.get("salary"))))
					.getResultList();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return employee;
	}

	@Override
	public Employees getEmployeeById(EmployeeRequest request) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Employees employee = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employees> criteria = builder.createQuery(Employees.class);
			Root<Employees> root = criteria.from(Employees.class);
			employee = session.createQuery(criteria.select(root).where(builder.equal(root.get("employeeId"),request.getEmployeeId())))
					.uniqueResult();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return employee;
	}

	@Override
	public Employees updateEmployee(Employees employee) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
		
	}

	@Override
	public Employees deleteEmployee(Employees employee) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
		
	}

}

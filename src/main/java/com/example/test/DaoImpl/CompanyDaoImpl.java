package com.example.test.DaoImpl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.test.Config.HibernateConfig;
import com.example.test.Dao.CompanyDAO;
import com.example.test.Model.Company;
import com.example.test.RequestClass.CompanyRequest;

@Repository
public class CompanyDaoImpl implements CompanyDAO {
	
	@Autowired
	HibernateConfig conf;
	
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public Company saveCompany(Company company) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(company);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return company;
		
	}

	@Override
	public List<Company> getAllCompanies() {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		List<Company> company = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
			Root<Company> root = criteria.from(Company.class);
			company = session.createQuery(criteria.select(root))
					.getResultList();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return company;
	}

	@Override
	public Company getCompanyByCompanyId(CompanyRequest request) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Company company = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
			Root<Company> root = criteria.from(Company.class);
			company = session.createQuery(criteria.select(root).where(builder.equal(root.get("companyId"),request.getCompanyId())))
					.uniqueResult();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return company;
	}

	@Override
	public Company updateCompany(Company company) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(company);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return company;
	}

	@Override
	public Company deleteCompany(Company company) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(company);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return company;
	}

	@Override
	public Company getCompanyByCompanyId(int companyId) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Company company = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
			Root<Company> root = criteria.from(Company.class);
			company = session.createQuery(criteria.select(root).where(builder.equal(root.get("companyId"),companyId)))
					.uniqueResult();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return company;
	}
}

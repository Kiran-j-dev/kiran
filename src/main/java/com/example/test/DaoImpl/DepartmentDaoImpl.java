package com.example.test.DaoImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Config.HibernateConfig;
import com.example.test.Dao.DepartmentDAO;
import com.example.test.Model.Departments;

@Repository
public class DepartmentDaoImpl implements DepartmentDAO {
	
	@Autowired
	HibernateConfig conf;

	@Override
	public Departments getDepartmentName(int deptId) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Departments department = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Departments> criteria = builder.createQuery(Departments.class);
			Root<Departments> root = criteria.from(Departments.class);
			department = session.createQuery(criteria.select(root).where(builder.equal(root.get("departmentId"),deptId)))
					.getSingleResult();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return department;
	}

}

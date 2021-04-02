package com.example.test.DaoImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Config.HibernateConfig;
import com.example.test.Dao.SkillDAO;
import com.example.test.Model.Employees;
import com.example.test.Model.Skills;

@Repository
public class SkillDaoImpl implements SkillDAO {
	
	@Autowired
	HibernateConfig conf;

	@Override
	public Skills getSkillBySkillId(String sk) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Skills skill = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Skills> criteria = builder.createQuery(Skills.class);
			Root<Skills> root = criteria.from(Skills.class);
			skill = session.createQuery(criteria.select(root).where(builder.equal(root.get("skillId"),sk)))
					.uniqueResult();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return skill;
	}

	@Override
	public Skills saveSkill(Skills skill) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(skill);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return skill;
	}

	
}

package com.example.test.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.SkillDAO;
import com.example.test.Model.Skills;
import com.example.test.RequestClass.SkillRequest;
import com.example.test.Service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {
	
	@Autowired
	SkillDAO skillDAO;

	@Override
	public String createSkill(SkillRequest request) {
		Skills skill = new Skills();
		skill.setSkillId(request.getSkillId());
		skill.setSkillName(request.getSkillName());
		skill = skillDAO.saveSkill(skill);
		if (skill != null) {
			return "success";
		} else {
			return "error";
		}
	}

}

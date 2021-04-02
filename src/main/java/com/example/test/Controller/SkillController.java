package com.example.test.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.RequestClass.SkillRequest;
import com.example.test.Service.SkillService;

@CrossOrigin(origins = "*")
@RestController
public class SkillController {
	
	@Autowired
	SkillService skillService;

	@PostMapping("createSkill")
	public Map<String, Object> createSkill(@RequestBody SkillRequest request) throws HibernateException, Exception {
		Map<String, Object> response = new LinkedHashMap<>();
		String status = skillService.createSkill(request);
		if (status.equals("success")) {
			response.put("Success", "Skill Created Successfully");
		} else {
			response.put("Error", "Skill Not Created");
		}
		return response;
	}
}

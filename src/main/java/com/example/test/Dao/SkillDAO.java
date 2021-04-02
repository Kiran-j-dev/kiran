package com.example.test.Dao;

import com.example.test.Model.Skills;

public interface SkillDAO {

	Skills getSkillBySkillId(String sk);

	Skills saveSkill(Skills skill);

}

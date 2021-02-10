package com.company.mentorconnect.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mentor_skill_details")
public class MentorSkillDetails {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "mentor_id")

	private Mentor mentor;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "skill_id")
	private Skills skill;

	@Column(name = "self_rating")
	private float selfRating;

	@Column(name = "years_of_experience")
	private int yearsOfExperience;

	public MentorSkillDetails(Mentor mentor, Skills skill, float selfRating, int yearsOfExperience) {
		super();
		this.mentor = mentor;
		this.skill = skill;
		this.selfRating = selfRating;
		this.yearsOfExperience = yearsOfExperience;
	}

	public MentorSkillDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public Skills getSkill() {
		return skill;
	}

	public void setSkill(Skills skill) {
		this.skill = skill;
	}

	public float getSelfRating() {
		return selfRating;
	}

	public void setSelfRating(float selfRating) {
		this.selfRating = selfRating;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	@Override
	public String toString() {
		return "Mentor_Skills [id=" + id + ", mentor=" + mentor + ", skill=" + skill + ", selfRating=" + selfRating
				+ ", yearsOfExperience=" + yearsOfExperience + "]";
	}

}

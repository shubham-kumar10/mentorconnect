package com.company.mentorconnect.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="skills")
public class Skills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "toc")
	private String toc;

	@Column(name = "prerequites")
	private String prerequites;

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Mentor> mentors;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToc() {
		return toc;
	}

	public void setToc(String toc) {
		this.toc = toc;
	}

	public String getPrerequites() {
		return prerequites;
	}

	public void setPrerequites(String prerequites) {
		this.prerequites = prerequites;
	}
	
	public List<Mentor> getMentors() {
		return mentors;
	}

	public void setMentors(List<Mentor> mentors) {
		this.mentors = mentors;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skills other = (Skills) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (prerequites == null) {
			if (other.prerequites != null)
				return false;
		} else if (!prerequites.equals(other.prerequites))
			return false;
		if (toc == null) {
			if (other.toc != null)
				return false;
		} else if (!toc.equals(other.toc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Skills [id=" + id + ", name=" + name + ", toc=" + toc + ", prerequites=" + prerequites + "]";
	}
	
}

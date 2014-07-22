package br.com.dmarin.testmaker.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ScenarioConfigPK.class)
public class ScenarioConfig implements Serializable {

	@Id 
	@ManyToOne(optional = false)
    private Scenario scenario;

    @Id
    @ManyToOne(optional = false)
    private QuestionGroup group;
    
    @Id
    @ManyToOne(optional = false)
    private QuestionLevel level;
    
    @Column
    private long questionCount;

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public QuestionGroup getGroup() {
		return group;
	}

	public void setGroup(QuestionGroup group) {
		this.group = group;
	}

	public QuestionLevel getLevel() {
		return level;
	}

	public void setLevel(QuestionLevel level) {
		this.level = level;
	}

	public long getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(long questionCount) {
		this.questionCount = questionCount;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result
				+ (int) (questionCount ^ (questionCount >>> 32));
		result = prime * result
				+ ((scenario == null) ? 0 : scenario.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScenarioConfig other = (ScenarioConfig) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (questionCount != other.questionCount)
			return false;
		if (scenario == null) {
			if (other.scenario != null)
				return false;
		} else if (!scenario.equals(other.scenario))
			return false;
		return true;
	}

	public String toString() {
		return "ScenarioConfig [scenario=" + scenario + ", group=" + group
				+ ", level=" + level + ", questionCount=" + questionCount + "]";
	}
    
    

}

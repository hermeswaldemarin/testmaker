package br.com.dmarin.testmaker.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(length = 2000, nullable = false)
    private String question;
    
    @Column(length = 2000, nullable = false)
    private String answer;

    @ManyToOne(targetEntity = QuestionGroup.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTIONGROUP_ID", nullable = false)
    private QuestionGroup questionGroup = new QuestionGroup();
    
    @ManyToOne(targetEntity = QuestionLevel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTIONLEVEL_ID", nullable = false)
    private QuestionLevel questionLevel = new QuestionLevel();

    public Question() {
        super();
    }

    public Question(final String question) {
        super();
        this.question = question;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public QuestionGroup getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(QuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}

	public QuestionLevel getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(QuestionLevel questionLevel) {
		this.questionLevel = questionLevel;
	}

}

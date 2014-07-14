package br.com.dmarin.testmaker.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "question_group")
public class QuestionGroup implements Serializable {
	
	@Id
    @Column(name="id")
    @GeneratedValue
    private Long id;
	
	@Column(name="name")
	private String name;
	
	public QuestionGroup(final String name) {
        super();
        this.name = name;
    }
	
	public QuestionGroup(){
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}

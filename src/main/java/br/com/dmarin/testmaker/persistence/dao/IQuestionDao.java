package br.com.dmarin.testmaker.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.dmarin.testmaker.persistence.model.Question;

public interface IQuestionDao extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    
}

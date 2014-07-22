package br.com.dmarin.testmaker.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.dmarin.testmaker.persistence.model.QuestionGroup;
import br.com.dmarin.testmaker.persistence.model.QuestionLevel;

public interface IQuestionLevelDao extends JpaRepository<QuestionLevel, Long>, JpaSpecificationExecutor<QuestionGroup> {

    
}

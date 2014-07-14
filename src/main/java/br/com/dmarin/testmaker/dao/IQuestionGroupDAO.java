package br.com.dmarin.testmaker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.dmarin.testmaker.model.QuestionGroup;

public interface IQuestionGroupDAO extends JpaRepository<QuestionGroup, Long>, JpaSpecificationExecutor<QuestionGroup> {

    @Query("SELECT f FROM QuestionGroup f WHERE LOWER(f.name) = LOWER(:name)")
    QuestionGroup retrieveByName(@Param("name") String name);
}

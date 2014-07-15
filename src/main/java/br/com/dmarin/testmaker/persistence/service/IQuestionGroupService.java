package br.com.dmarin.testmaker.persistence.service;

import br.com.dmarin.testmaker.persistence.IOperations;
import br.com.dmarin.testmaker.persistence.model.QuestionGroup;

public interface IQuestionGroupService extends IOperations<QuestionGroup> {

    QuestionGroup retrieveByName(String name);

}

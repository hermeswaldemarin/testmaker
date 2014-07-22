package br.com.dmarin.testmaker.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dmarin.testmaker.persistence.dao.IQuestionDao;
import br.com.dmarin.testmaker.persistence.model.Question;
import br.com.dmarin.testmaker.persistence.service.IQuestionService;
import br.com.dmarin.testmaker.persistence.service.common.AbstractService;

import com.google.common.collect.Lists;

@Service
@Transactional
public class QuestionService extends AbstractService<Question, Long> implements IQuestionService {

    @Autowired
    private IQuestionDao dao;

    public QuestionService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<Question, Long> getDao() {
        return dao;
    }

   
    // overridden to be secured

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Question> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

}

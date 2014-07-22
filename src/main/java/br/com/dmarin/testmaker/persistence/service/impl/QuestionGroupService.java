package br.com.dmarin.testmaker.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dmarin.testmaker.persistence.dao.IQuestionGroupDao;
import br.com.dmarin.testmaker.persistence.model.QuestionGroup;
import br.com.dmarin.testmaker.persistence.service.IQuestionGroupService;
import br.com.dmarin.testmaker.persistence.service.common.AbstractService;

import com.google.common.collect.Lists;

@Service
@Transactional
public class QuestionGroupService extends AbstractService<QuestionGroup, Long> implements IQuestionGroupService {

    @Autowired
    private IQuestionGroupDao dao;

    public QuestionGroupService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<QuestionGroup, Long> getDao() {
        return dao;
    }

    // custom methods

    public QuestionGroup retrieveByName(final String name) {
        return dao.retrieveByName(name);
    }

    // overridden to be secured

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<QuestionGroup> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

}

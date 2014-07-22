package br.com.dmarin.testmaker.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dmarin.testmaker.persistence.dao.IQuestionLevelDao;
import br.com.dmarin.testmaker.persistence.model.QuestionLevel;
import br.com.dmarin.testmaker.persistence.service.IQuestionLevelService;
import br.com.dmarin.testmaker.persistence.service.common.AbstractService;

import com.google.common.collect.Lists;

@Service
@Transactional
public class QuestionLevelService extends AbstractService<QuestionLevel, Long> implements IQuestionLevelService {

    @Autowired
    private IQuestionLevelDao dao;

    public QuestionLevelService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<QuestionLevel, Long> getDao() {
        return dao;
    }

   
    // overridden to be secured

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<QuestionLevel> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

}

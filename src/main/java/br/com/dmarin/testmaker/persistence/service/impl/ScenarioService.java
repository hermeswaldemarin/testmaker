package br.com.dmarin.testmaker.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dmarin.testmaker.persistence.dao.IScenarioDao;
import br.com.dmarin.testmaker.persistence.model.Scenario;
import br.com.dmarin.testmaker.persistence.service.IScenarioService;
import br.com.dmarin.testmaker.persistence.service.common.AbstractService;

import com.google.common.collect.Lists;

@Service
@Transactional
public class ScenarioService extends AbstractService<Scenario, Long> implements IScenarioService {

    @Autowired
    private IScenarioDao dao;

    public ScenarioService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<Scenario, Long> getDao() {
        return dao;
    }

   
    // overridden to be secured

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Scenario> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

}

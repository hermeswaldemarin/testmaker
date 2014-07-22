package br.com.dmarin.testmaker.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dmarin.testmaker.persistence.dao.IScenarioConfigDao;
import br.com.dmarin.testmaker.persistence.model.ScenarioConfig;
import br.com.dmarin.testmaker.persistence.model.ScenarioConfigPK;
import br.com.dmarin.testmaker.persistence.service.IScenarioConfigService;
import br.com.dmarin.testmaker.persistence.service.common.AbstractService;

import com.google.common.collect.Lists;

@Service
@Transactional
public class ScenarioConfigService extends AbstractService<ScenarioConfig, ScenarioConfigPK> implements IScenarioConfigService {

    @Autowired
    private IScenarioConfigDao dao;

    public ScenarioConfigService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<ScenarioConfig, ScenarioConfigPK> getDao() {
        return dao;
    }

   
    // overridden to be secured

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ScenarioConfig> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

}

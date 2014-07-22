package br.com.dmarin.testmaker.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.dmarin.testmaker.persistence.model.ScenarioConfig;
import br.com.dmarin.testmaker.persistence.model.ScenarioConfigPK;

public interface IScenarioConfigDao extends JpaRepository<ScenarioConfig, ScenarioConfigPK>, JpaSpecificationExecutor<ScenarioConfig> {

    
}

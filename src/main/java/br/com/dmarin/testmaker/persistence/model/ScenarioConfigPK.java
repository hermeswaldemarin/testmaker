package br.com.dmarin.testmaker.persistence.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ScenarioConfigPK implements Serializable {

    long scenario;
    long group;
    long level;

}

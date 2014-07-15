package br.com.dmarin.testmaker.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.dmarin.testmaker.persistence.IOperations;
import br.com.dmarin.testmaker.persistence.model.QuestionGroup;
import br.com.dmarin.testmaker.persistence.service.IQuestionGroupService;
import br.com.dmarin.testmaker.spring.PersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class QuestionGroupServicePersistenceIntegrationTest extends AbstractServicePersistenceIntegrationTest<QuestionGroup> {

    @Autowired
    private IQuestionGroupService service;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoExceptions() {
        //
    }

    @Test
    public final void whenEntityIsCreated_thenNoExceptions() {
        service.create(new QuestionGroup(randomAlphabetic(6)));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public final void whenInvalidEntityIsCreated_thenDataException() {
        service.create(new QuestionGroup());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public final void whenEntityWithLongNameIsCreated_thenDataException() {
        service.create(new QuestionGroup(randomAlphabetic(2048)));
    }

    // custom Query method

    @Test
    public final void givenUsingCustomQuery_whenRetrievingEntity_thenFound() {
        final String name = randomAlphabetic(6);
        service.create(new QuestionGroup(name));

        final QuestionGroup retrievedByName = service.retrieveByName(name);
        assertNotNull(retrievedByName);
    }

    // work in progress

    @Test(expected = InvalidDataAccessApiUsageException.class)
    @Ignore("Right now, persist has saveOrUpdate semantics, so this will no longer fail")
    public final void whenSameEntityIsCreatedTwice_thenDataException() {
        final QuestionGroup entity = new QuestionGroup(randomAlphabetic(8));
        service.create(entity);
        service.create(entity);
    }

    // API

    @Override
    protected final IOperations<QuestionGroup> getApi() {
        return service;
    }

}

package br.com.dmarin.testmaker.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.Serializable;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import br.com.dmarin.testmaker.persistence.IOperations;
import br.com.dmarin.testmaker.persistence.model.QuestionGroup;
import br.com.dmarin.testmaker.util.IDUtil;

public abstract class AbstractServicePersistenceIntegrationTest<T extends Serializable> {

    // tests

    // find - one

    @Test
    /**/public final void givenResourceDoesNotExist_whenResourceIsRetrieved_thenNoResourceIsReceived() {
        // When
        final QuestionGroup createdResource = getApi().findOne(IDUtil.randomPositiveLong());

        // Then
        assertNull(createdResource);
    }

    @Test
    public void givenResourceExists_whenResourceIsRetrieved_thenNoExceptions() {
        final QuestionGroup existingResource = persistNewEntity();
        getApi().findOne(existingResource.getId());
    }

    @Test
    public void givenResourceDoesNotExist_whenResourceIsRetrieved_thenNoExceptions() {
        getApi().findOne(IDUtil.randomPositiveLong());
    }

    @Test
    public void givenResourceExists_whenResourceIsRetrieved_thenTheResultIsNotNull() {
        final QuestionGroup existingResource = persistNewEntity();
        final QuestionGroup retrievedResource = getApi().findOne(existingResource.getId());
        assertNotNull(retrievedResource);
    }

    @Test
    public void givenResourceExists_whenResourceIsRetrieved_thenResourceIsRetrievedCorrectly() {
        final QuestionGroup existingResource = persistNewEntity();
        final QuestionGroup retrievedResource = getApi().findOne(existingResource.getId());
        assertEquals(existingResource, retrievedResource);
    }

    // find - one - by name

    // find - all

    @Test
    /**/public void whenAllResourcesAreRetrieved_thenNoExceptions() {
        getApi().findAll();
    }

    @Test
    /**/public void whenAllResourcesAreRetrieved_thenTheResultIsNotNull() {
        final List<QuestionGroup> resources = getApi().findAll();

        assertNotNull(resources);
    }

    @Test
    /**/public void givenAtLeastOneResourceExists_whenAllResourcesAreRetrieved_thenRetrievedResourcesAreNotEmpty() {
        persistNewEntity();

        // When
        final List<QuestionGroup> allResources = getApi().findAll();

        // Then
        assertThat(allResources, not(Matchers.<QuestionGroup> empty()));
    }

    @Test
    /**/public void givenAnResourceExists_whenAllResourcesAreRetrieved_thenTheExistingResourceIsIndeedAmongThem() {
        final QuestionGroup existingResource = persistNewEntity();

        final List<QuestionGroup> resources = getApi().findAll();

        assertThat(resources, hasItem(existingResource));
    }

    @Test
    /**/public void whenAllResourcesAreRetrieved_thenResourcesHaveIds() {
        persistNewEntity();

        // When
        final List<QuestionGroup> allResources = getApi().findAll();

        // Then
        for (final QuestionGroup resource : allResources) {
            assertNotNull(resource.getId());
        }
    }

    // create

    @Test(expected = RuntimeException.class)
    /**/public void whenNullResourceIsCreated_thenException() {
        getApi().create(null);
    }

    @Test
    /**/public void whenResourceIsCreated_thenNoExceptions() {
        persistNewEntity();
    }

    @Test
    /**/public void whenResourceIsCreated_thenResourceIsRetrievable() {
        final QuestionGroup existingResource = persistNewEntity();

        assertNotNull(getApi().findOne(existingResource.getId()));
    }

    @Test
    /**/public void whenResourceIsCreated_thenSavedResourceIsEqualToOriginalResource() {
        final QuestionGroup originalResource = createNewEntity();
        final QuestionGroup savedResource = getApi().create(originalResource);

        assertEquals(originalResource, savedResource);
    }

    @Test(expected = RuntimeException.class)
    public void whenResourceWithFailedConstraintsIsCreated_thenException() {
        final QuestionGroup invalidResource = createNewEntity();
        invalidate(invalidResource);

        getApi().create(invalidResource);
    }

    /**
     * -- specific to the persistence engine
     */
    @Test(expected = DataAccessException.class)
    @Ignore("Hibernate simply ignores the id silently and still saved (tracking this)")
    public void whenResourceWithIdIsCreated_thenDataAccessException() {
        final QuestionGroup resourceWithId = createNewEntity();
        resourceWithId.setId(IDUtil.randomPositiveLong());

        getApi().create(resourceWithId);
    }

    // update

    @Test(expected = RuntimeException.class)
    /**/public void whenNullResourceIsUpdated_thenException() {
        getApi().update(null);
    }

    @Test
    /**/public void givenResourceExists_whenResourceIsUpdated_thenNoExceptions() {
        // Given
        final QuestionGroup existingResource = persistNewEntity();

        // When
        getApi().update(existingResource);
    }

    /**
     * - can also be the ConstraintViolationException which now occurs on the update operation will not be translated; as a consequence, it will be a TransactionSystemException
     */
    @Test(expected = RuntimeException.class)
    public void whenResourceIsUpdatedWithFailedConstraints_thenException() {
        final QuestionGroup existingResource = persistNewEntity();
        invalidate(existingResource);

        getApi().update(existingResource);
    }

    @Test
    /**/public void givenResourceExists_whenResourceIsUpdated_thenUpdatesArePersisted() {
        // Given
        final QuestionGroup existingResource = persistNewEntity();

        // When
        change(existingResource);
        getApi().update(existingResource);

        final QuestionGroup updatedResource = getApi().findOne(existingResource.getId());

        // Then
        assertEquals(existingResource, updatedResource);
    }

    // delete

    // @Test(expected = RuntimeException.class)
    // public void givenResourceDoesNotExists_whenResourceIsDeleted_thenException() {
    // // When
    // getApi().delete(IDUtil.randomPositiveLong());
    // }
    //
    // @Test(expected = RuntimeException.class)
    // public void whenResourceIsDeletedByNegativeId_thenException() {
    // // When
    // getApi().delete(IDUtil.randomNegativeLong());
    // }
    //
    // @Test
    // public void givenResourceExists_whenResourceIsDeleted_thenNoExceptions() {
    // // Given
    // final QuestionGroup existingResource = persistNewEntity();
    //
    // // When
    // getApi().delete(existingResource.getId());
    // }
    //
    // @Test
    // /**/public final void givenResourceExists_whenResourceIsDeleted_thenResourceNoLongerExists() {
    // // Given
    // final QuestionGroup existingResource = persistNewEntity();
    //
    // // When
    // getApi().delete(existingResource.getId());
    //
    // // Then
    // assertNull(getApi().findOne(existingResource.getId()));
    // }

    // template method

    protected QuestionGroup createNewEntity() {
        return new QuestionGroup(randomAlphabetic(6));
    }

    protected abstract IOperations<QuestionGroup, Long> getApi();

    private final void invalidate(final QuestionGroup entity) {
        entity.setName(null);
    }

    private final void change(final QuestionGroup entity) {
        entity.setName(randomAlphabetic(6));
    }

    protected QuestionGroup persistNewEntity() {
        return getApi().create(createNewEntity());
    }

}

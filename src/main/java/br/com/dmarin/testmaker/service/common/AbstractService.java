package br.com.dmarin.testmaker.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dmarin.testmaker.IOperations;

import com.google.common.collect.Lists;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	 // read - one

    @Transactional(readOnly = true)
    public T findOne(final long id) {
        return getDao().findOne(id);
    }

    // read - all

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    public Page<T> findPaginated(final int page, final int size) {
        return getDao().findAll(new PageRequest(page, size));
    }

    // write

    public T create(final T entity) {
        return getDao().save(entity);
    }

    public T update(final T entity) {
        return getDao().save(entity);
    }

    public void delete(final T entity) {
        getDao().delete(entity);
    }

    public void deleteById(final long entityId) {
        getDao().delete(entityId);
    }

    protected abstract PagingAndSortingRepository<T, Long> getDao();

}

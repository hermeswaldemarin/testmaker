package br.com.dmarin.testmaker.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IOperations<T extends Serializable, ID  extends Serializable> {

    // read - one

    T findOne(final ID id);

    // read - all

    List<T> findAll();

    Page<T> findPaginated(int page, int size);

    // write

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final ID entityId);

}

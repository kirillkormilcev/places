package ru.aston.places.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Common service
 * @param <N> dto New
 * @param <U> dto Update
 * @param <R> dto Response
 * @param <K> id
 * */
@Transactional(readOnly = true)
public interface Service<N, U, R, K> {

    @Transactional
    R create(N dto);

    R findById(K id);

    List<R> findAll();

    @Transactional
    R update(K id, U dto);

    @Transactional
    void delete(K id);
}

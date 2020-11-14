package br.com.cassio.hotelaria.model.mapper;

import java.util.List;

/**
 * @param <E> Entity
 * @param <D> DTO
 */
public interface EntityMapper<E, D> {

	public D toDto(E entity);

	public List<D> toDto(List<E> entity);

	public E toEntity(D dto);

	public List<E> toEntity(List<D> dto);

}

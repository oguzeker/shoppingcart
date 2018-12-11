/**
 * 
 */
package com.trendyol.tr.shoppingcart.service;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the base class for all service EJBs that require database access.
 * 
 * @author Oguz Erhan Eker
 */
public abstract class AbstractJpaService {

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Get an instance, whose state may be lazily fetched.If the requested
	 * instance does not exist in the database, the
	 * <code>EntityNotFoundException</code> is thrown when the instance state is
	 * first accessed.
	 * 
	 * @param entityClass
	 *        entity class
	 * @param id
	 *        primary key
	 * @return the found entity instance
	 * @see {@link EntityManager#getReference(Class, Object)}
	 */
	public <E> E getReference(Class<E> entityClass, Serializable id) {
		return entityManager.getReference(entityClass, id);
	}

	/**
	 * Find by primary key. Search for an entity of the specified class and
	 * primary key. If the entity instance is contained in the persistence
	 * context, it is returned from there.
	 * 
	 * @param entityClass
	 *        entity class
	 * @param id
	 *        primary key
	 * @return the found entity instance or null if the entity does not exist
	 * @see {@link EntityManager#find(Class, Object)}
	 */
	public <E> E find(Class<E> entityClass, Serializable id) {
		return entityManager.find(entityClass, id);
	}

	/**
	 * Make an instance managed and persistent.
	 * 
	 * @param entity
	 *        entity instance
	 * @see {@link EntityManager#persist(Object)}
	 */
	public void persist(Object entity) {
		entityManager.persist(entity);
	}

	/**
	 * Merge the state of the given entity into the current persistence context.
	 * 
	 * @param entity
	 *        entity instance
	 * @return the managed instance that the state was merged to
	 * @see {@link EntityManager#merge(Object)}
	 */
	public <E> E merge(E entity) {
		E mergedEntity = entityManager.merge(entity);
		return mergedEntity;
	}

	/**
	 * Remove the entity instance.
	 * 
	 * @param entity
	 *        entity instance
	 * @see {@link EntityManager#remove(Object)}
	 */
	public void remove(Object entity) {
		entityManager.remove(merge(entity));
	}

	/**
	 * Synchronize the persistence context to the underlying database.
	 * 
	 * @see {@link EntityManager#flush()}
	 */
	public void flush() {
		entityManager.flush();
	}

	/**
	 * Refresh the state of the instance from the database, overwriting changes
	 * made to the entity, if any.
	 * 
	 * @param entity
	 *        entity instance
	 * @see {@link EntityManager#refresh(Object)}
	 */
	public void refresh(Object entity) {
		Object rEntity;
		try {
			Class<?> c = entity.getClass();
			Method getId = c.getMethod("getId");
			Object id = getId.invoke(entity);
			rEntity = entityManager.getReference(c, id);
		} catch (Exception e) { // NOSONAR
			rEntity = entity;
		}
		entityManager.refresh(rEntity);
	}
	
		

	/**
	 * Remove the data for entities of the specified class (and its subclasses)
	 * from the cache.
	 * 
	 * @param cls
	 *        entity class
	 * @see {@link Cache#evict(Class)}
	 */
	public <E> void evict(Class<E> cls) {
		entityManager.getEntityManagerFactory().getCache().evict(cls);
	}

	/**
	 * Remove the data for the given entity from the cache.
	 * 
	 * @param cls
	 *        entity class
	 * @see {@link Cache#evict(Class, Object)}
	 */
	public <E> void evict(Class<E> cls, Object primaryKey) {
		entityManager.getEntityManagerFactory().getCache().evict(cls, primaryKey);
	}

	/**
	 * Clear the cache.
	 * 
	 * @see {@link Cache#evictAll()}
	 */
	public void evictAll() {
		entityManager.getEntityManagerFactory().getCache().evictAll();
	}

}

package io.d4e.data.domain.listeners;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HibernateEventLogsListener implements ApplicationContextAware
    , AutoFlushEventListener
    , ClearEventListener
    , DeleteEventListener
    , DirtyCheckEventListener
    , EvictEventListener
    , FlushEntityEventListener
    , FlushEventListener
    , InitializeCollectionEventListener
    , LoadEventListener
    , LockEventListener
    , MergeEventListener
    , PersistEventListener
    , PostCollectionRecreateEventListener
    , PostCollectionRemoveEventListener
    , PostCollectionUpdateEventListener
    , PostCommitDeleteEventListener
    , PostCommitInsertEventListener
    , PostCommitUpdateEventListener
    , PostDeleteEventListener
    , PostLoadEventListener
    , PostUpdateEventListener
    , PreCollectionRecreateEventListener
    , PreCollectionRemoveEventListener
    , PreCollectionUpdateEventListener
    , PreDeleteEventListener
    , PreInsertEventListener
    , PreLoadEventListener
    , PreUpdateEventListener
    , RefreshEventListener
    , ReplicateEventListener
    , ResolveNaturalIdEventListener
    , SaveOrUpdateEventListener
{
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EntityManagerFactory emf = applicationContext.getBean(EntityManagerFactory.class);

        EventListenerRegistry registry = emf.unwrap(SessionFactoryImplementor.class).getServiceRegistry().getService(EventListenerRegistry.class);
        registry.appendListeners(EventType.AUTO_FLUSH, this);
        registry.appendListeners(EventType.CLEAR, this);
        registry.appendListeners(EventType.DELETE, this);
        registry.appendListeners(EventType.DIRTY_CHECK, this);
        registry.appendListeners(EventType.EVICT, this);
        registry.appendListeners(EventType.FLUSH, this);
        registry.appendListeners(EventType.FLUSH_ENTITY, this);
        registry.appendListeners(EventType.INIT_COLLECTION, this);
        registry.appendListeners(EventType.LOAD, this);
        registry.appendListeners(EventType.LOCK, this);
        registry.appendListeners(EventType.MERGE, this);
        registry.appendListeners(EventType.PERSIST, this);
        registry.appendListeners(EventType.PERSIST_ONFLUSH, this);
        registry.appendListeners(EventType.POST_COLLECTION_RECREATE, this);
        registry.appendListeners(EventType.POST_COLLECTION_REMOVE, this);
        registry.appendListeners(EventType.POST_COLLECTION_UPDATE, this);
        registry.appendListeners(EventType.POST_COMMIT_DELETE, this);
        registry.appendListeners(EventType.POST_COMMIT_INSERT, this);
        registry.appendListeners(EventType.POST_COMMIT_UPDATE, this);
        registry.appendListeners(EventType.POST_DELETE, this);
        registry.appendListeners(EventType.POST_INSERT, this);
        registry.appendListeners(EventType.POST_LOAD, this);
        registry.appendListeners(EventType.POST_UPDATE, this);
        registry.appendListeners(EventType.PRE_COLLECTION_RECREATE, this);
        registry.appendListeners(EventType.PRE_COLLECTION_REMOVE, this);
        registry.appendListeners(EventType.PRE_COLLECTION_UPDATE, this);
        registry.appendListeners(EventType.PRE_DELETE, this);
        registry.appendListeners(EventType.PRE_INSERT, this);
        registry.appendListeners(EventType.PRE_LOAD, this);
        registry.appendListeners(EventType.PRE_UPDATE, this);
        registry.appendListeners(EventType.REFRESH, this);
        registry.appendListeners(EventType.REPLICATE, this);
        registry.appendListeners(EventType.RESOLVE_NATURAL_ID, this);
        registry.appendListeners(EventType.SAVE_UPDATE, this);
        registry.appendListeners(EventType.UPDATE, this);
    }

    @Override
    public void onAutoFlush(AutoFlushEvent event) throws HibernateException {
        log.debug("AUTO_FLUSH flush-required: {}", event.isFlushRequired() );
    }

    @Override
    public void onClear(ClearEvent event) {
        log.debug("CLEAR");
    }

    @Override
    public void onDelete(DeleteEvent event) throws HibernateException {
        log.debug("DELETE {} cascade: {} orphanRemoval {}", event.getEntityName(), event.isCascadeDeleteEnabled(), event.isOrphanRemovalBeforeUpdates());
    }

    @Override
    public void onDelete(DeleteEvent event, DeleteContext transientEntities) throws HibernateException {
        log.debug("DELETE {} cascade: {} orphanRemoval {}", event.getEntityName(), event.isCascadeDeleteEnabled(), event.isOrphanRemovalBeforeUpdates());
    }

    @Override
    public void onDirtyCheck(DirtyCheckEvent event) throws HibernateException {
        log.debug("DIRTY_CHECK isDirty: {}", event.isDirty());
    }

    @Override
    public void onEvict(EvictEvent event) throws HibernateException {
        log.debug("ON_EVICT");
    }

    @Override
    public void onFlushEntity(FlushEntityEvent event) throws HibernateException {
        log.debug("FLUSH_ENTITY {} hasDirtyCollection: {} dirtyCheckPossible: {} dirtyCheckHandledByInterceptor: {} allowedToReuse: {}"
                , toString(event.getEntityEntry()), event.hasDirtyCollection(), event.isDirtyCheckPossible(), event.isDirtyCheckHandledByInterceptor(), event.isAllowedToReuse()) ;
    }

    @Override
    public void onFlush(FlushEvent event) throws HibernateException {
        log.debug("FLUSH numberOfEntitiesProcessed {} numberOfCollectionsProcessed: {}"
                , event.getNumberOfEntitiesProcessed(), event.getNumberOfCollectionsProcessed());
    }

    @Override
    public void onInitializeCollection(InitializeCollectionEvent event) throws HibernateException {
        log.debug("INIT_COLLECTION {}", toString(event));
    }


    @Override
    public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
        log.debug("LOAD [{}]: entity {} id: {}"
                , loadType.getName(), event.getEntityClassName(), event.getEntityId());
    }

    @Override
    public void onLock(LockEvent event) throws HibernateException {
        log.debug("LOCK entity: {} mode: {}"
                , event.getEntityName(), event.getLockMode());
    }

    @Override
    public void onMerge(MergeEvent event) throws HibernateException {
        log.debug("MERGE entity {} id: {}", event.getEntityName(), event.getRequestedId());
    }

    @Override
    public void onMerge(MergeEvent event, MergeContext copiedAlready) throws HibernateException {
        log.debug("MERGE entity {} id: {}", event.getEntityName(), event.getRequestedId());
    }

    @Override
    public void onPersist(PersistEvent event) throws HibernateException {
        log.debug("PERSIST entity {}", event.getEntityName());
    }

    @Override
    public void onPersist(PersistEvent event, PersistContext createdAlready) throws HibernateException {
        log.debug("PERSIST entity {}", event.getEntityName());
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }

    @Override
    public void onPostRecreateCollection(PostCollectionRecreateEvent event) {
        log.debug("POST_COLLECTION_RECREATE {}", toString(event));
    }

    @Override
    public void onPostRemoveCollection(PostCollectionRemoveEvent event) {
        log.debug("POST_COLLECTION_REMOVE {}", toString(event));
    }

    @Override
    public void onPostUpdateCollection(PostCollectionUpdateEvent event) {
        log.debug("POST_COLLECTION_UPDATE {}", toString(event));
    }

    @Override
    public void onPostDeleteCommitFailed(PostDeleteEvent event) {
        log.debug("POST_DELETE failed entity: {} id: {}",
                toClassNameOrNull(event.getEntity()),
            event.getId()
        );
    }

    String toClassNameOrNull(Object object) {
        return object == null ? null : object.getClass().getSimpleName();
    }
    @Override
    public void onPostInsertCommitFailed(PostInsertEvent event) {
        log.debug("POST_INSERT failed entity: {} id: {}",
                toClassNameOrNull(event.getEntity()), event.getId());
    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent event) {
        log.debug("POST_UPDATE failed {}: id: {}", toClassNameOrNull(event.getEntity()), event.getId());
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        log.debug("POST_DELETE {}: id: {}", toClassNameOrNull(event.getEntity()), event.getId());
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        log.debug("POST_INSERT {}: id: {}", toClassNameOrNull(event.getEntity()), event.getId());
    }

    @Override
    public void onPostLoad(PostLoadEvent event) {
        log.debug("POST_LOAD {}: id: {}", toClassNameOrNull(event.getEntity()), event.getId());
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        log.debug("POST_UPDATE {}: id: {}", toClassNameOrNull(event.getEntity()), event.getId());
    }

    @Override
    public void onPreRecreateCollection(PreCollectionRecreateEvent event) {
        log.debug("PRE_COLLECTION_RECREATE {}", toString(event));
    }

    @Override
    public void onPreRemoveCollection(PreCollectionRemoveEvent event) {
        log.debug("PRE_COLLECTION_REMOVCE {}", toString(event));
    }

    @Override
    public void onPreUpdateCollection(PreCollectionUpdateEvent event) {
        log.debug("PRE_COLLECTION_UPDATE {}", toString(event));
    }

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        log.debug("PRE_DELETE {} id: {}", toClassNameOrNull(event.getEntity()), event.getId());
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        log.debug("PRE_INSERT {} id: {}", toClassNameOrNull(event.getEntity()), event.getId());
        return false;
    }

    @Override
    public void onPreLoad(PreLoadEvent event) {
        log.debug("PRE_LOAD {} id: {}", toClassNameOrNull(event.getEntity()), event.getId());
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        log.debug("PRE_UPDATE {} id: {}", toClassNameOrNull(event.getEntity()), event.getId());
        return false;
    }

    @Override
    public void onRefresh(RefreshEvent event) throws HibernateException {
        log.debug("REFRESH {} lock_mode: {}", event.getEntityName(), event.getLockMode());
    }

    @Override
    public void onRefresh(RefreshEvent event, RefreshContext refreshedAlready) throws HibernateException {
        log.debug("REFRESH {} lock_mode: {}", event.getEntityName(), event.getLockMode());
    }

    @Override
    public void onReplicate(ReplicateEvent event) throws HibernateException {
        log.debug("REPLICATE {} mode: {}", event.getEntityName(), event.getReplicationMode());
    }

    @Override
    public void onResolveNaturalId(ResolveNaturalIdEvent event) throws HibernateException {
        log.debug("RESOLVE_NATURAL_ID {} id: {}", event.getEntityName(), event.getEntityId());
    }

    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
        log.debug("SAVE_OR_UPDATE {}", toString(event.getEntry()));
    }

    private String toString(EntityEntry entry) {
        return entry.getEntityName() + "["
                + " id:" + entry.getId()
                + " status: " + entry.getStatus()
                + " version: " + entry.getVersion()
                + " ]";
    }

    private String toString(AbstractCollectionEvent event) {
        return "AffectedOwner " + event.getAffectedOwnerEntityName()
                + " [ id: " + event.getAffectedOwnerIdOrNull() + "]"
                + " collection ["
                + " empty: " + event.getCollection().empty()
                + " dirty: " + event.getCollection().isDirty()
                + " dirty-accessible: " + event.getCollection().isDirectlyAccessible()
                + " ]";
    }
}

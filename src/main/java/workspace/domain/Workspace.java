package workspace.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import workspace.events.WorkspaceCreatedEvent;
import workspace.events.WorkspaceDestroyedEvent;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

@Entity
@EntityListeners(Workspace.WorkspaceEventManager.class)
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Workspace() {
    }

    public Long getId() {
        return id;
    }

    /*
     * Making the event manager package visible solves the hanging test problem.
     */
    @Component
    public static class WorkspaceEventManager {
        /*
         * Making the initialization of the event publisher lazy solves the hanging test problem.
         */
        //@Lazy
        @Autowired
        private ApplicationEventPublisher publisher;

        @PostPersist
        public void onWorkspaceCreated(final Workspace workspace) {
            publisher.publishEvent(new WorkspaceCreatedEvent(workspace.getId()));
        }

        @PostRemove
        public void onWorkspaceDeleted(final Workspace workspace) {
            publisher.publishEvent(new WorkspaceDestroyedEvent(workspace.getId()));
        }
    }
}

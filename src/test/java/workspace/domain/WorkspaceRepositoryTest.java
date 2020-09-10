package workspace.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import workspace.events.WorkspaceCreatedEvent;
import workspace.events.WorkspaceEvent;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.transaction.annotation.Propagation.NEVER;

@DataJpaTest
@DisplayName("A workspace repository")
class WorkspaceRepositoryTest {

    /**
     * System under test.
     */
    @Autowired
    private WorkspaceRepository workspaceRepository;

    /*
     * If we simulate an event listener with a mock, then the hanging test issue is solved.
     */
    //@MockBean
    //private WorkspaceEventTestListener workspaceEventTestListener;

    @Test
    @DisplayName("stores workspaces")
    void storesWorkspaces() {
        final Workspace workspace = workspaceRepository.save(new Workspace());

        assertThat(workspaceRepository.findAll()).contains(workspace);

        //verify(workspaceEventTestListener, times(1)).onWorkspaceCreatedEvent(new WorkspaceCreatedEvent(workspace.getId()));
    }

    private interface WorkspaceEventTestListener {
        @EventListener
        void onWorkspaceCreatedEvent(final WorkspaceCreatedEvent event);
    }
}

package workspace.application;

import workspace.events.WorkspaceCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceLifecycleManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkspaceLifecycleManager.class);

    @Async
    @EventListener
    public void onWorkspaceCreated(WorkspaceCreatedEvent workspaceCreatedEvent) {
        LOGGER.info("Workspace created");
    }

}

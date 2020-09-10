package workspace.events;

public final class WorkspaceCreatedEvent extends WorkspaceEvent {
    public WorkspaceCreatedEvent(final Long workspaceId) {
        super(workspaceId);
    }
}

package workspace.events;

public class WorkspaceDestroyedEvent extends WorkspaceEvent {
    public WorkspaceDestroyedEvent(final long workspace) {
        super(workspace);
    }
}

package workspace.events;

import java.util.Objects;

public abstract class WorkspaceEvent extends DomainEvent {
    private final long workspaceId;

    protected WorkspaceEvent(long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public long getWorkspace() {
        return workspaceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(workspaceId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkspaceEvent other = (WorkspaceEvent) o;
        return Objects.equals(workspaceId, other.workspaceId);
    }
}

package workspace.events;

public abstract class DomainEvent {
    public abstract int hashCode();

    public abstract boolean equals(Object obj);
}

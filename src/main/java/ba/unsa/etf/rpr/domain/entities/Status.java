package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;

import java.util.Objects;

public class Status implements Idable {
    int id;
    String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return id == status.id && Objects.equals(state, status.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}

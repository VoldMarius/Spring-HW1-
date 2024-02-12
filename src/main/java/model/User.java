package model;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class User {

    private int id;

    private String firstName;

    private String lastName;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id && Objects.equals(firstName, user.getFirstName()) && Objects.equals(lastName, user.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
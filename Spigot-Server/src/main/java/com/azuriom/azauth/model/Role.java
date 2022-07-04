package com.azuriom.azauth.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Role {

    private final String name;
    private final String color;

    /**
     * Create a new Role.
     *
     * @param name  the role name
     */
    public Role(@NotNull String name, @NotNull String color) {
        this.name = Objects.requireNonNull(name, "name");
        this.color = Objects.requireNonNull(color, "color");
    }

    /**
     * Gets the role name.
     *
     * @return the role name
     */
    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull String getColor() {
        return this.color;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return this.name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return "Role{name='" + this.name + "'}";
    }
}

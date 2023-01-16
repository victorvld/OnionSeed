package io.essentials.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityTest {

    @Test
    public void entityIsEqualsToItself() {
        var e = Entity.builder().id("id1").build();
        ;

        Assertions.assertTrue(e.isEquals(e));
    }

    @Test
    public void twoDifferentEntitiesAreNotEquals() {
        var e1 = Entity.builder().id("id1").build();
        var e2 = Entity.builder().id("id2").build();

        Assertions.assertFalse(e1.isEquals(e2));
    }

    @Test
    public void sameEntitiesAreEquals() {
        var e1 = Entity.builder().id("id1").build();
        var e2 = Entity.builder().id("id1").build();

        Assertions.assertTrue(e1.isEquals(e2));
    }

    @Test
    public void entitiesWithNullIdAreNotEquals() {
        var e1 = Entity.builder().build();
        var e2 = Entity.builder().build();

        Assertions.assertFalse(e1.isEquals(e2));
    }
}
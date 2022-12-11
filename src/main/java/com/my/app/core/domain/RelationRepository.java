package com.my.app.core.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface RelationRepository<MainID extends EntityId<?>, RelateID extends EntityId<?>> {

    List<RelateID> findRelations(MainID mainId);

    List<MainID> findReverseRelations(RelateID relateId);

    default void addRelation(MainID mainId, RelateID relateId) {
        addRelations(mainId, List.of(relateId));
    };

    void addRelations(MainID mainId, Iterable<RelateID> relateIds);


    default void addReverseRelation(RelateID relateId, MainID mainId) {
        addReverseRelations(relateId, List.of(mainId));
    };

    void addReverseRelations(RelateID relateId, Iterable<MainID> mainIds);

    default void deleteRelation(MainID mainId, RelateID relateId) {
        deleteAllRelations(mainId, List.of(relateId));
    };

    void deleteAllRelations(MainID mainId, Iterable<RelateID> relateIdList);

}

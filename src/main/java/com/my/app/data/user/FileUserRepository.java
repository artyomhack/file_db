package com.my.app.data.user;

import com.my.app.core.data.FileEntityRepository;
import com.my.app.data.task.TaskEntity;
import com.my.app.domain.user.UserId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileUserRepository extends FileEntityRepository<UserEntity, UserId> {

    public FileUserRepository() {
        super(UserEntity.class);
    }

    @Override
    protected UserId mapOfSrc(Object src) {
        return UserId.of(src);
    }

    @Override
    public UserId getNextId() {
        List<UserId> list;

        if (Objects.equals(findAll(),Collections.emptyList())) {
            list = new ArrayList<>();
            list.add(UserId.of(420000));
            return list.get(0);
        }

        list = findAll().stream().map(UserEntity::getId).collect(Collectors.toList());

        var lastid = list.get(list.size()-1);

        list.add(UserId.of(lastid.getValue() + 1));

        return list.get(list.size()-1);
    }
}

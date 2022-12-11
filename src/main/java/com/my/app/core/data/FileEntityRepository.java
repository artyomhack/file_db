package com.my.app.core.data;

import com.my.app.core.domain.Entity;
import com.my.app.core.domain.DataRepository;
import com.my.app.core.domain.EntityId;
import com.my.app.data.task.TaskEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class FileEntityRepository<T extends Entity<ID>, ID extends EntityId<?>>
        implements DataRepository<T, ID> {

    public final static String DATA_PATH = "C:" + File.separator + "__work" + File.separator +".data";

    private final String entityPath;

    protected FileEntityRepository(Class<T> entityClass) {
        entityPath = DATA_PATH + File.separator +
                     entityClass.getSimpleName().replaceFirst("Entity", "");
    }

    public ID save(T entity) {

        var id = entity.getId();

        if (id.isEmpty()) {
            id = getNextId();
            entity = (T) entity.setIdForEntity(id);
        }

        try {
            var file = createFileByPath(Path.of(entityPath + File.separator));

            if (file.exists()) {
                var objectStream = new ObjectOutputStream(
                        new FileOutputStream(file + File.separator + id.getValue())
                );

                objectStream.writeObject(entity);
                objectStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


    @Override
    public T findById(ID id) {
        var entityFile = getFileById(id);

        return retrieveOfFile(entityFile);
    }

    @Override
    public List<T> findAll() {
        var allFiles = new File(entityPath).listFiles();
        if (Objects.nonNull(allFiles) && allFiles.length > 0) {
            return Arrays.stream(allFiles)
                    .map(this::retrieveOfFile)
                    .filter(Objects::nonNull)
                    .toList();
        }

        return Collections.emptyList();
    }


    @SuppressWarnings("unchecked")
    private T retrieveOfFile(File file) {
        try {
            if (file.exists()) {
                var stream = new ObjectInputStream(new FileInputStream(file));
                var entity = (T) stream.readObject();
                System.out.println(entity.getId());
                stream.close();
                return entity;
            }
        } catch (Exception ignored) {}
        return null;
    }

    @Override
    public ID getLastId() {
        var ids = findAll().stream().map(this::mapOfSrc).toList();
        return mapOfSrc(ids.get(ids.size() - 1));
    }

    @Override
    public boolean deleteById(ID id) {
        try {
            if (getFileById(id).exists()) {
                Files.deleteIfExists(getFileById(id).toPath());
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    private File getFileById(ID id) {
        return new File(entityPath + File.separator + id.getValue());
    }

    private File createFileByPath(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            var file = new File(String.valueOf(path));
            file.createNewFile();
            return file;
        } else if(Files.exists(path))
            return new File(String.valueOf(path));

        return null;
    }

    abstract protected  ID mapOfSrc(Object src);
}

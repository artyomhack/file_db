package com.my.app.core.data;

import com.my.app.core.domain.Entity;
import com.my.app.core.domain.EntityId;
import com.my.app.core.domain.RelationRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public abstract class FileRelationRepository<MainID extends EntityId<?>, RelateID extends EntityId<?>>
        implements RelationRepository<MainID, RelateID> {

    private final String relationsPath;


    public FileRelationRepository(Class<? extends Entity<MainID>> mainEntityClass, Class<? extends Entity<RelateID>> relateEntityClass) {
        var mainName = mainEntityClass.getSimpleName().replace("Entity", "");
        var relName = relateEntityClass.getSimpleName().replace("Entity", "");
        relationsPath = FileEntityRepository.DATA_PATH + File.separator +
                mainName + "_" + relName +  File.separator;
    }
//    main | relate
//    4200_2400
//    92_33


    @Override
    public List<RelateID> findRelations(MainID mainId) {
        var listFiles = new File(relationsPath).listFiles();

        if (Objects.isNull(listFiles)) {
            return Collections.emptyList();
        }

        return Arrays.stream(listFiles)
                .filter(file -> file.getName().startsWith(mainId + "_"))
                .map( file -> retrieveRelationIdFromName(file.getName()))
                .toList();
    }

    private RelateID retrieveRelationIdFromName(String fileName) {
        return mapRelateIdFromString(fileName.split("_")[1]);
    }

    @Override
    public List<MainID> findReverseRelations(RelateID relateId) {
        var listFiles = new File(relationsPath).listFiles();
        if (Objects.isNull(listFiles)) return Collections.emptyList();

        return Arrays.stream(listFiles)
                .filter( file -> file.getName().endsWith("_" + relateId))
                .map( file -> retrieveRelationMainIdFromName(file.getName()))
                .toList();
    }

    private MainID retrieveRelationMainIdFromName(String fileName) {
        return mapMainIdFromString(fileName.split("_")[0]);
    }

    @Override
    public void addRelations(MainID mainId, Iterable<RelateID> relateIds) {
        relateIds.forEach( relateId -> {
            var path = Path.of(relationsPath + mainId + "_" + relateId);
            try {
                Files.createFile(path);
            } catch (Exception ignored) {
                throw new IllegalStateException(
                        "Relation [ mainId: " + mainId + " relateId: " + relateId + " ] already exists");
            }
        });
    }

    @Override
    public void addReverseRelations(RelateID relateId, Iterable<MainID> mainIds) {
        mainIds.forEach(mainID -> {
            var path = Path.of(relationsPath + mainID + "_" + relateId);
            try {
                Files.createFile(path);
            } catch (Exception ignored) {
                throw new IllegalStateException(
                        "Relation [ mainId: " + mainID + " relateId: " + relateId + " ] already exists");
            }
        });
    }

    @Override
    public void deleteAllRelations(MainID mainId, Iterable<RelateID> relateIdList) {
        relateIdList.forEach(relateID -> {
            var path = Path.of(relationsPath + mainId + "_" + relateID);
            if (Files.exists(path)) {
                try {
                    Files.delete(path);
                } catch (Exception ignored) {
                    throw new IllegalStateException(
                            "Relation [ mainid: " + mainId + " relateId: " + relateID + " ] doesn't exist");
                }
            }
        });
    }

    abstract protected RelateID mapRelateIdFromString(String relateId);

    abstract protected MainID mapMainIdFromString(String mainId);

}

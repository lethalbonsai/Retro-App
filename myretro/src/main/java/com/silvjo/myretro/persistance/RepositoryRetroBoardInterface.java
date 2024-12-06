package com.silvjo.myretro.persistance;

import java.util.Optional;

public interface RepositoryRetroBoardInterface<D,ID> {
    D save(D domain);
    Optional<D> findById(ID id);
    Iterable<D> findAll();
    void deleteById(ID id);
}

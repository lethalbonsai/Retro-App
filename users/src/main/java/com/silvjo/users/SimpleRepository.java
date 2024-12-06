package com.silvjo.users;

import java.util.Optional;

public interface SimpleRepository <D, ID>{
    Optional<D> findById(ID id);
    Iterable<D> findAll();
    D save(D entity);
    void deleteById(ID id);
}

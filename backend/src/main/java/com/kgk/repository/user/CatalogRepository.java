package com.kgk.repository.user;

import com.kgk.model.user.Catalog;

import java.util.List;

public interface CatalogRepository {

    List<Catalog> listCatalogsByUserId(String userId);

    Catalog findCatalogByCatalogId(String userId, String catalogId);

    Catalog addCatalog(String userId, Catalog catalog);

    void deleteCatalog(String userId, String catalogId);

}

package com.kgk.repository;

import com.kgk.model.Catalog;

import java.util.List;

public interface CatalogRepository {

    List<Catalog> listCatalogsByUserId(String userId);

    Catalog findCatalogByCatalogId(String catalogId);

    Catalog addCatalog(Catalog catalog);

    //Catalog updateCatalog(String catalogId, Catalog catalog);

    void deleteCatalog(String catalogId);

}

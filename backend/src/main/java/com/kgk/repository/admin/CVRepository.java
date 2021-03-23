package com.kgk.repository.admin;

import com.kgk.model.admin.CV;

import java.util.Collection;

public interface CVRepository {

    Collection<CV> listAllCVs();
    CV findCVById(int CVId);
    void saveCV(CV cv);
    void updateCV(CV cv);

}

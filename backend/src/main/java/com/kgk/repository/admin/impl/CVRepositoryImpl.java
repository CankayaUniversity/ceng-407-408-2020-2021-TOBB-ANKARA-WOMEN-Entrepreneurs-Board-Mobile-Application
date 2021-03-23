package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kgk.model.admin.CV;
import com.kgk.repository.admin.CVRepository;

import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class CVRepositoryImpl implements CVRepository {
    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public CVRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }
    public Collection<CV> listAllCVs() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(CV.class, scanExpression);
    }

    public CV findCVById(int CVId) {
        return mapper.load(CV.class, CVId, config);
    }

    public void saveCV(CV cv) {
        mapper.save(cv);
    }

    public void updateCV(CV cv) {

      /* User CVRetrieved = mapper.load(CV.class, CV.getCVId()); //neden static yapmamı istiyor zaten public

       CVRetrieved.set.....(CV.g); PDF SET ETME OLACAK BURAYA DA

        mapper.save(CVRetrieved); */

    }

    public void deleteUser(CV cv) { mapper.delete(cv); }

}

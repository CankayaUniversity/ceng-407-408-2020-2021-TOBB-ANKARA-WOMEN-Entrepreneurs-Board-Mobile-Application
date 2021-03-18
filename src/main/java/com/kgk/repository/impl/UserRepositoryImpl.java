package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.User;
import com.kgk.model.profile.*;
import com.kgk.repository.UserRepository;
import io.micronaut.core.util.CollectionUtils;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class UserRepositoryImpl implements UserRepository {
    
    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public UserRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    public Collection<User> listAllUsers() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        Collection<User> users = mapper.scan(User.class, scanExpression);
        users.forEach(
                user -> setProfileComponents(user)
        );
        return users;
    }

    @Override
    public Collection<Hobby> findHobbiesByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Hobby> queryExpression = new DynamoDBQueryExpression<Hobby>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Hobby.class, queryExpression);
    }

    @Override
    public Collection<Competence> findCompetencesByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Competence> queryExpression = new DynamoDBQueryExpression<Competence>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Competence.class, queryExpression);
    }

    @Override
    public Collection<Project> findProjectsByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Project> queryExpression = new DynamoDBQueryExpression<Project>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Project.class, queryExpression);
    }

    @Override
    public Collection<Language> findLanguagesByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Language> queryExpression = new DynamoDBQueryExpression<Language>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Language.class, queryExpression);
    }

    @Override
    public Collection<Certificate> findCertificatesByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Certificate> queryExpression = new DynamoDBQueryExpression<Certificate>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Certificate.class, queryExpression);
    }

    public Collection<User> findUsersByRoleId(String roleId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("roleId = :roleId")
                .withExpressionAttributeValues(eav);

        return mapper.query(User.class, queryExpression);
    }

    public User findUserById(String userId) {
        User user = mapper.load(User.class, userId, config);
        return setProfileComponents(user);
    }

    public User saveUser(User user) {
        user.setRoleId("101");
        //user.setUserId(UUID.randomUUID().toString());
        mapper.save(user);

        return mapper.load(User.class, user.getUserId());
    }

    public User updateUser(String userId, User user) {
        User userRetrieved = findUserById(userId);

        if (CollectionUtils.isNotEmpty(user.getHobbies())) {
            userRetrieved.getHobbies().clear();
            user.getHobbies().forEach(
                    hobbyInput -> {
                        Hobby hobby = new Hobby();
                        hobby.setHobbyName(hobbyInput.getHobbyName());
                        hobby.setHobbyDesc(hobbyInput.getHobbyDesc());
                        hobby.setUserId(user.getUserId());
                        mapper.save(hobby);
                        userRetrieved.getHobbies().add(hobby);
                    }
            );
        }

        if (CollectionUtils.isNotEmpty(user.getCompetences())) {
            userRetrieved.getCompetences().clear();
            user.getCompetences().forEach(
                    competenceInput -> {
                        Competence competence = new Competence();
                        competence.setCompName(competenceInput.getCompName());
                        competence.setCompDesc(competenceInput.getCompDesc());
                        competence.setUserId(user.getUserId());
                        mapper.save(competence);
                        userRetrieved.getCompetences().add(competence);
                    }
            );
        }

        if (CollectionUtils.isNotEmpty(user.getProjects())) {
            userRetrieved.getProjects().clear();
            user.getProjects().forEach(
                    projectInput -> {
                        Project project = new Project();
                        project.setProjectName(projectInput.getProjectName());
                        project.setProjectDesc(projectInput.getProjectDesc());
                        project.setProjectDate(projectInput.getProjectDate());
                        project.setUserId(user.getUserId());
                        mapper.save(project);
                        userRetrieved.getProjects().add(project);
                    }
            );
        }

        if (CollectionUtils.isNotEmpty(user.getLanguages())) {
            userRetrieved.getLanguages().clear();
            user.getLanguages().forEach(
                    languageInput -> {
                        Language language = new Language();
                        language.setLangName(languageInput.getLangName());
                        language.setLangLevel(languageInput.getLangLevel());
                        language.setUserId(user.getUserId());
                        mapper.save(language);
                        userRetrieved.getLanguages().add(language);
                    }
            );
        }

        if (CollectionUtils.isNotEmpty(user.getCertificates())) {
            userRetrieved.getCertificates().clear();
            user.getCertificates().forEach(
                    certificateInput -> {
                        Certificate certificate = new Certificate();
                        certificate.setCertName(certificateInput.getCertName());
                        certificate.setCertDesc(certificateInput.getCertDesc());
                        certificate.setCertFile(certificateInput.getCertFile());
                        certificate.setValidity(certificateInput.getValidity());
                        certificate.setUserId(user.getUserId());
                        mapper.save(certificate);
                        userRetrieved.getCertificates().add(certificate);
                    }
            );
        }
        mapper.save(userRetrieved);

        return userRetrieved;
    }

    public void deleteUser(User user) { mapper.delete(user); }

    //TODO: delete methods for profile components

    private User setProfileComponents(User user) {
        user.setHobbies(findHobbiesByUserId(user.getUserId()));
        user.setCompetences(findCompetencesByUserId(user.getUserId()));
        user.setProjects(findProjectsByUserId(user.getUserId()));
        user.setLanguages(findLanguagesByUserId(user.getUserId()));
        user.setCertificates(findCertificatesByUserId(user.getUserId()));

        return user;
    }

}

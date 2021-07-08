package com.homecoming.homecoming.userdatacontainerdaoimpl;

import com.homecoming.homecoming.model.AppUserDo;
import com.homecoming.homecoming.userdatacontainerdao.UserDataContainerDao;
import com.homecoming.homecoming.utils.DocumentBuilder;
import com.mongodb.WriteConcernException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.USERNAME;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataContainerDaoImpl implements UserDataContainerDao {

    private MongoCollection<Document> documentMongoCollection;
    private DocumentBuilder documentBuilder;

    @Override
    public void save(AppUserDo appUserDo) {
        Document document = documentBuilder.getDocumentFromAppUserDo(appUserDo);
        try {
            documentMongoCollection.insertOne(document);
            log.info("document written to db successfully");
        } catch (WriteConcernException e) {
              log.error("WriteConcernException occurred while saving data", e);
        } catch (Exception e) {
            log.error("unknown exception occurred while saving data", e);
        }
    }

    @Override
    public void update(AppUserDo appUserDo) {

    }

    @Override
    public Document getUserByUsernameAndPassword(String username, String password) {
        AppUserDo appUserDo = AppUserDo.builder()
                .password(password)
                .username(username)
                .build();
        documentBuilder.validateAppUserData(appUserDo);
        Document document = documentBuilder.getDocumentFromAppUserDo(appUserDo);
        FindIterable<Document> findIterable = documentMongoCollection.find(document);

        if (findIterable.iterator().hasNext()) {
            return findIterable.iterator().next();
        }
        return null;
    }

    @Override
    public Document getUserByUsername(String username) {
        Validate.notEmpty(username);

        FindIterable<Document> findIterable = documentMongoCollection.find(
                new Document(USERNAME, username)
        );

        if (findIterable.iterator().hasNext()) {
            return findIterable.iterator().next();
        }
        return null;
    }
}

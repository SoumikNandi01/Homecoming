package com.homecoming.homecoming.userdatacontainerdaoimpl;

import com.homecoming.homecoming.model.AppUserDo;
import com.homecoming.homecoming.utils.DocumentBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.USERNAME;
import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.PASSWORD;
import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.LOCATION;
import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.NAME;
import static com.homecoming.homecoming.TestConstants.UserDocumentConstants.TEST_NAME;
import static com.homecoming.homecoming.TestConstants.UserDocumentConstants.TEST_USERNAME;
import static com.homecoming.homecoming.TestConstants.UserDocumentConstants.TEST_LOCATION;
import static com.homecoming.homecoming.TestConstants.UserDocumentConstants.TEST_PASSWORD;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserDataContainerDaoImplTest {

    @Mock
    private MongoCollection<Document> documentMongoCollection;
    @Mock
    private DocumentBuilder documentBuilder;
    @InjectMocks
    private UserDataContainerDaoImpl userDataContainerDao;

    private AppUserDo appUserDo;
    private InsertOneResult insertOneResult;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    private Document getSampleUser() {
        Document document = new Document();
        document.put(USERNAME, TEST_USERNAME);
        document.put(LOCATION, TEST_LOCATION);
        document.put(PASSWORD, TEST_PASSWORD);
        document.put(NAME, TEST_NAME);
        return document;
    }

    @Test
    public void testSave(){
        Document document = getSampleUser();

        when(documentBuilder.getDocumentFromAppUserDo(appUserDo)).thenReturn(document);
        when(documentMongoCollection.insertOne(document)).thenReturn(insertOneResult);

        userDataContainerDao.save(appUserDo);

        verify(documentBuilder, Mockito.times(1)).getDocumentFromAppUserDo(appUserDo);
        verify(documentMongoCollection, Mockito.times(1)).insertOne(document);
    }

    @Test
    public void testGetUserByUsernameAndPassword(){

        Document document = getSampleUser();
        appUserDo.builder().name(TEST_NAME).build();
        FindIterable<Document> documentFindIterable = Mockito.mock(FindIterable.class);
        MongoCursor cursor = Mockito.mock(MongoCursor.class);
        when(documentBuilder.getDocumentFromAppUserDo(any())).thenReturn(document);
        when(documentMongoCollection.find(document)).thenReturn(documentFindIterable);
        when(documentFindIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true);
        when(cursor.next()).thenReturn(document);

        Document getDoc = userDataContainerDao.getUserByUsernameAndPassword(TEST_USERNAME, TEST_PASSWORD);

        assertEquals(document, getDoc);
    }

    @Test
    public void testGetUserByUsername(){

        Document document = new Document(USERNAME, TEST_USERNAME);
        appUserDo.builder().name(TEST_NAME).build();
        FindIterable<Document> documentFindIterable = Mockito.mock(FindIterable.class);
        MongoCursor cursor = Mockito.mock(MongoCursor.class);
        when(documentMongoCollection.find(document)).thenReturn(documentFindIterable);
        when(documentFindIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true);
        when(cursor.next()).thenReturn(document);

        Document getDoc = userDataContainerDao.getUserByUsername(TEST_USERNAME);

        assertEquals(document, getDoc);
    }
}
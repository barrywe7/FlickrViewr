package com.barryirvine.flickr.api;


import com.barryirvine.flickr.model.server.Photo;
import com.barryirvine.flickr.model.server.PhotoResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class FlickrApiTest extends ApiTest {

    //TODO: Work out how to do this with dagger
    @Rule
    public final MockWebServer mMockWebServer = new MockWebServer();
    @Rule
    public final ExpectedException mThrown = ExpectedException.none();
    private FlickrAPI mFlickrAPI;


    @Before
    public void setUp() throws IOException {
        mFlickrAPI = new Retrofit.Builder()
                .baseUrl(mMockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
                .create(FlickrAPI.class);
    }

    @Test
    public void testApi_success() throws Exception {
        mMockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(getFileContents("photoResponse.json")));
        final PhotoResponse response = mFlickrAPI.getPhotos().blockingFirst();
        assertNotNull(response);
        assertNotNull(response.getPhotos());
        assertEquals(20, response.getPhotos().size());
    }

    @Test
    public void testApi_failure() throws Exception {
        mMockWebServer.enqueue(new MockResponse().setResponseCode(404));
        mThrown.expect(RuntimeException.class);
        mThrown.expectMessage("HTTP 404 Client Error");
        mFlickrAPI.getPhotos().blockingFirst();
    }

}

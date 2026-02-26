package com.example.mediatekformationmobile.model;

import com.example.mediatekformationmobile.api.FormationApi;

import junit.framework.TestCase;

import java.text.Normalizer;
import java.util.Date;

public class FormationTest extends TestCase {

    private static final int ID = 1;
    private static final int PLAYLIST_ID = 1;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final Date PUBLISHED_AT = new Date("01/01/2020");
    private static final String VIDEO_ID = "videoId";
    private static final int ERR_INT = 2;
    private static final String ERR_STR = "wrong";
    private static final Date ERR_DATE = new Date("02/02/2022");

    private Formation initFormation() {
        return new Formation(ID, PLAYLIST_ID, PUBLISHED_AT, TITLE, DESCRIPTION, VIDEO_ID);
    }

    public void testGetId() {
        Formation formation = initFormation();
        assertEquals(ID, formation.getId());
        assertNotSame(ERR_INT, formation.getId());
    }

    public void testGetPlaylistId() {
        Formation formation = initFormation();
        assertEquals(PLAYLIST_ID, formation.getPlaylistId());
        assertNotSame(ERR_INT, formation.getPlaylistId());
    }

    public void testGetPublishedAt() {
        Formation formation = initFormation();
        assertEquals(PUBLISHED_AT, formation.getPublishedAt());
        assertNotSame(ERR_DATE, formation.getPublishedAt());
    }

    public void testGetTitle() {
        Formation formation = initFormation();
        assertEquals(TITLE, formation.getTitle());
        assertNotSame(ERR_STR, formation.getTitle());
    }

    public void testGetDescription() {
        Formation formation = initFormation();
        assertEquals(DESCRIPTION, formation.getDescription());
        assertNotSame(ERR_STR, formation.getDescription());
    }

    public void testGetMiniature() {
        Formation formation = initFormation();
        String bonChemin = "https://i.ytimg.com/vi/" + VIDEO_ID + "/default.jpg";
        String mauvChemin = "https://i.ytimg.com/vi/" + ERR_STR + "/default.jpg";
        assertEquals(bonChemin, formation.getMiniature());
        assertNotSame(mauvChemin, formation.getMiniature());
    }

    public void testGetPicture() {
        Formation formation = initFormation();
        String bonChemin = "https://i.ytimg.com/vi/" + VIDEO_ID + "/mqdefault.jpg";
        String mauvChemin = "https://i.ytimg.com/vi/" + ERR_STR + "/mqdefault.jpg";
        assertEquals(bonChemin, formation.getPicture());
        assertNotSame(mauvChemin, formation.getPicture());
    }

    public void testGetVideoId() {
        Formation formation = initFormation();
        assertEquals(VIDEO_ID, formation.getVideoId());
        assertNotSame(ERR_STR, formation.getVideoId());
    }

    public void testIsFavorite() {
        Formation formation = initFormation();
        assertFalse(formation.isFavorite());
    }

    public void testSetFavorite() {
        Formation formation = initFormation();
        formation.setFavorite(true);
        assertTrue(formation.isFavorite());
    }
}
package org.musicbrainz.mobile.test.handler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.musicbrainz.android.api.data.ReleaseStub;
import org.musicbrainz.android.api.webservice.ResponseParser;

public class ReleaseSearchTest extends BaseXmlParsingTestCase {
    
    private static final String RELEASE_SEARCH_FIXTURE = "releaseSearch_songs about leaving.xml";
    private LinkedList<ReleaseStub> releases;
    
    @Before
    public void doParsing() throws IOException {
        InputStream stream = getFileStream(RELEASE_SEARCH_FIXTURE);
        assertNotNull(stream);
        releases = new ResponseParser().parseReleaseSearch(stream);
        stream.close();
    }

    @Test
    public void testReleaseSearch() {
        assertEquals(25, releases.size());
    }

}

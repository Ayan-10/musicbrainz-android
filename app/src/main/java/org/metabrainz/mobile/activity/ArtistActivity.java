package org.metabrainz.mobile.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.metabrainz.mobile.R;
import org.metabrainz.mobile.adapter.pager.ArtistPagerAdapter;
import org.metabrainz.mobile.api.data.UserData;
import org.metabrainz.mobile.api.data.search.entity.Artist;
import org.metabrainz.mobile.intent.IntentFactory.Extra;
import org.metabrainz.mobile.viewmodel.ArtistViewModel;

/**
 * Activity that retrieves and displays information about an artist given an
 * artist MBID.
 */
public class ArtistActivity extends MusicBrainzActivity {

    public static final String LOG_TAG = "DebugArtistInfo";
    private ArtistViewModel artistViewModel;

    private String mbid;
    private UserData userData;

    private View loading;
    private View error;

    private RatingBar ratingBar;
    private TextView tagView;

    private ArtistPagerAdapter pagerAdapter;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        artistViewModel = ViewModelProviders.of(this).get(ArtistViewModel.class);

        setContentView(R.layout.activity_artist);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pagerAdapter = new ArtistPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabs);

        viewPager.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String id = getIntent().getStringExtra(Extra.ARTIST_MBID);
        if(id != null && !id.isEmpty()) artistViewModel.setMBID(id);
            mbid = id;
        artistViewModel.setMBID(mbid);
        artistViewModel.getArtistData().observe(this, this::setArtist);
    }

    private void setArtist(Artist data){
        if(data != null){
            artistViewModel.setArtist(data);
            Log.d(LOG_TAG,data.getName());
            configurePager();
        }
    }

    private void configurePager() {
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);

        getSupportActionBar().setTitle(artistViewModel.getArtist().getName());
    }

    /*
    protected void populateLayout() {
        TextView artistText = findViewById(R.id.artist_artist);

        artistText.setText(artist.getName());
        ratingBar.setRating(artist.getRating());
        tagView.setText(StringFormat.commaSeparateTags(artist.getTags(), this));

        artistText.setSelected(true);
        tagView.setSelected(true);

        updateFragments();
        loading.setVisibility(View.GONE);
    }

    @SuppressWarnings("unchecked")
    private void updateFragments() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            ((EntityTab<Artist>) fm.findFragmentByTag(pagerAdapter.makeTag(i))).update(artist);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.artist, menu);
        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
        actionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        actionProvider.setShareIntent(Utils.shareIntent(Configuration.ARTIST_SHARE + mbid));
        return true;
    }

    private void showConnectionErrorWarning() {
        error.setVisibility(View.VISIBLE);
        Button retry = error.findViewById(R.id.retry_button);
        retry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                getSupportLoaderManager().restartLoader(ARTIST_LOADER, null, ArtistActivity.this);
            }
        });
    }

    @Override
    public UserData getUserData() {
        return userData;
    }

    @Override
    public void updateTags(List<Tag> tags) {
        artist.setTags(tags);
        tagView.setText(StringFormat.commaSeparateTags(tags, this));
        getSupportLoaderManager().destroyLoader(ARTIST_LOADER);
    }

    @Override
    public void updateRating(Float rating) {
        artist.setRating(rating);
        ratingBar.setRating(rating);
        getSupportLoaderManager().destroyLoader(ARTIST_LOADER);
    }
    */

}
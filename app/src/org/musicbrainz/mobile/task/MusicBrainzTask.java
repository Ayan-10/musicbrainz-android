/*
 * Copyright (C) 2011 Jamie McDonald
 * 
 * This file is part of MusicBrainz for Android.
 * 
 * MusicBrainz for Android is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * MusicBrainz for Android is distributed in the hope that it 
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MusicBrainz for Android. If not, see 
 * <http://www.gnu.org/licenses/>.
 */

package org.musicbrainz.mobile.task;

import org.musicbrainz.mobile.activity.base.DataQueryActivity;

public abstract class MusicBrainzTask extends IgnitedAsyncTask<DataQueryActivity, String, Void, Void> {

    String userAgent;
    
    public MusicBrainzTask() {
        super();
    }
    
    public MusicBrainzTask(DataQueryActivity activity) {
        super(activity);
    }
    
    @Override
    protected void onStart(DataQueryActivity activity) {
        userAgent = activity.getUserAgent();
    }
    
    @Override
    protected void onSuccess(DataQueryActivity activity, Void v) {
        completed(activity);
    }

    @Override
    protected void onError(DataQueryActivity activity, Exception e) {
        completed(activity);
    }
    
    private void completed(DataQueryActivity activity) {
        if (activity != null) {
            activity.onTaskFinished();
        }
    }
    
}
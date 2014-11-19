package com.detroitlabs.kyleofori.demoapp.parsers;

import android.util.Log;

import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kyleofori on 11/18/14.
 */
public class RedditJSONParser {
    RedditTextPost redditTextPost;
    ArrayList<RedditTextPost> redditPostArrayList;
    final String LOG_TAG = RedditJSONParser.class.getSimpleName();
    onFragmentListViewUpdated onFragmentListViewUpdated;


    public interface onFragmentListViewUpdated{
        public void redditListUpdated(ArrayList<RedditTextPost> updatedPostItems);
    }

    public void setOnFragmentListViewUpdated(onFragmentListViewUpdated onFragmentListViewUpdated){
        this.onFragmentListViewUpdated = onFragmentListViewUpdated;
    }

    public void convertJSONStringToRedditObject(String JSONString) throws JSONException{

        redditPostArrayList = new ArrayList<RedditTextPost>();
        final String redditDataObject = "data";
        final String redditChildrenArray = "children";
        final String redditPostAuthor = "author";
        final String redditPostTitle = "title";
        final String redditPostText = "selftext";
        final String redditPostUrl = "url";

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONObject jsonDataObject = jsonObject.getJSONObject(redditDataObject);
        JSONArray jsonChildrenArray = jsonDataObject.getJSONArray(redditChildrenArray);

        for(int i = 0; i < jsonChildrenArray.length(); i++){
            JSONObject currentRedditObject = jsonChildrenArray.getJSONObject(i);
            JSONObject currentRedditObjectData = currentRedditObject.getJSONObject(redditDataObject);
            String currentRedditPostAuthor = currentRedditObjectData.getString(redditPostAuthor);
            String currentRedditPostTitle = currentRedditObjectData.getString(redditPostTitle);
            String currentRedditPostText = currentRedditObjectData.getString(redditPostText);
            String currentRedditPostUrl = currentRedditObjectData.getString(redditPostUrl);

            redditTextPost = new RedditTextPost(currentRedditPostTitle, currentRedditPostAuthor,
                    currentRedditPostText, currentRedditPostUrl);

            Log.i(LOG_TAG, redditTextPost.getAuthor());


            redditPostArrayList.add(redditTextPost);
        }
        onFragmentListViewUpdated.redditListUpdated(redditPostArrayList);

    }

    public ArrayList<RedditTextPost> getRedditTextPostArrayList(){
        return redditPostArrayList;
    }


}

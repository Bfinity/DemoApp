package com.detroitlabs.kyleofori.demoapp.fragments;

/**
 * Created by BFineRocks on 11/17/14.
 */

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detroitlabs.kyleofori.demoapp.R;
import com.detroitlabs.kyleofori.demoapp.activities.DemoAppMainActivity;
import com.detroitlabs.kyleofori.demoapp.adapters.RedditListAdapter;
import com.detroitlabs.kyleofori.demoapp.backgroundthreadscheduler.RepeatingPostFetchExecutor;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;
import com.detroitlabs.kyleofori.demoapp.parsers.RedditJSONParser.onFragmentListViewUpdated;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class RedditListFragment extends ListFragment implements onFragmentListViewUpdated {
    ArrayList<RedditTextPost> redditPostArrayList;
    RedditListAdapter redditListAdapter;
    RepeatingPostFetchExecutor repeater;

    public RedditListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reddit_list, container, false);
      //  ListView redditPostList = getListView();
        setListAdapter(redditListAdapter);
     //   redditListAdapter.notifyDataSetChanged();
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
/*        redditTextPosts= new ArrayList<RedditTextPost>();
        RedditTextPost dummyPost1 = new RedditTextPost("fake title", "fake author", "no text", "url");
        RedditTextPost dummyPost2 = new RedditTextPost("fake title", "fake author", "no text", "url");
        redditTextPosts.add(dummyPost1);
        redditTextPosts.add(dummyPost2);
        redditListAdapter = new RedditListAdapter(getActivity(), R.layout.list_item_reddit_post, redditTextPosts);*/

        redditPostArrayList = new ArrayList<RedditTextPost>();
        RedditTextPost dummyPost1 = new RedditTextPost("fake title", "fake author", "no text", "url");
        RedditTextPost dummyPost2 = new RedditTextPost("fake title", "fake author", "no text", "url");
        redditPostArrayList.add(dummyPost1);
        redditPostArrayList.add(dummyPost2);
        redditListAdapter = new RedditListAdapter(getActivity(), R.layout.list_item_reddit_post, redditPostArrayList);
        repeater = new RepeatingPostFetchExecutor(getActivity().getIntent().getStringExtra(DemoAppMainActivity.SUBREDDIT_NAME));
        redditPostArrayList.add(dummyPost1);
        updatePosts(redditPostArrayList);

    }

    private void updatePosts(ArrayList<RedditTextPost> updatedPostsList) {
        redditPostArrayList = updatedPostsList;
        redditListAdapter.notifyDataSetChanged();
    }

    @Override
    public void redditListUpdated(ArrayList<RedditTextPost> updatedPostItems) {
        updatePosts(updatedPostItems);
    }
}
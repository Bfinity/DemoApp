package com.detroitlabs.kyleofori.demoapp.backgroundthreadscheduler;

import com.detroitlabs.kyleofori.demoapp.parsers.RedditJSONParser;
import com.detroitlabs.kyleofori.demoapp.tasks.GetRedditPostTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by BFineRocks on 11/19/14.
 */
public class RepeatingPostFetchExecutor {
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private String subredditName;
    GetRedditPostTask getRedditPostTask;
    RedditJSONParser redditJSONParser;

    public RepeatingPostFetchExecutor(String subredditName){
        this.subredditName = subredditName;
        getRedditPostTask = new GetRedditPostTask(subredditName);
        scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor)
                Executors.newScheduledThreadPool(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(getRedditPostTask, 5, 5, TimeUnit.SECONDS);
    }


/*
    Runnable periodicTask = new Runnable(){
        @Override
        public void run() {
                Log.i("repeater", "I'm a repeating task, or something like that");
            try{
                getRedditPostTask.execute(subredditName);

            }catch (Exception e){

            }
        }
    };*/
}

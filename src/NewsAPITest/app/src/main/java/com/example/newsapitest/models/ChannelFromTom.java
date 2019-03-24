package com.example.newsapitest.models;

import java.util.List;

public class ChannelFromTom {
    public static class ChannelList{
        String channelName;
        String channelId;

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }
    }
    public List<ChannelList> channelList;

    public List<ChannelFromTom.ChannelList> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<ChannelFromTom.ChannelList> channelList) {
        this.channelList = channelList;
    }
}

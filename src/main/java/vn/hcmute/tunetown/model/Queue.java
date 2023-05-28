package vn.hcmute.tunetown.model;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    //list of current songs
    private static List<Song> queue;
    public static Integer currentSongIndex;
    public static String replayState;

    private Queue() {

    }
    public static List<Song> getQueue() throws Exception {
        if (queue == null){
            queue = new ArrayList<>();
        }
        return queue;
    }

}

package vn.hcmute.tunetown.controller.MusicPlayer;

import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.tunetown.model.Queue;
import vn.hcmute.tunetown.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/nextSong"})
public class nextSong extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (Queue.currentSongIndex + 1 < Queue.getQueue().size()) {
                Queue.currentSongIndex++;
            }
            else {
                Queue.currentSongIndex = 0;
            }

            Song song = Queue.getQueue().get(Queue.currentSongIndex);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("songId", song.getSongId());
            jsonObject.put("songName", song.getSongName());
            jsonObject.put("songArtist", song.getArtists().getUserName());

            jsonObject.put("songData", song.getSongData());
            jsonObject.put("songPoster", song.getSongPoster());

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonArray.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

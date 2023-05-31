package vn.hcmute.tunetown.controller.Playlist.SuggestPlaylist;

import vn.hcmute.tunetown.DAO.GenreDAO;
import vn.hcmute.tunetown.DAO.PlaylistDAO;
import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.model.Genre;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@WebServlet(urlPatterns = {"/suggestPlaylist"})

public class suggestPlaylist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get list of favorite genres
        GenreDAO genreDAO = new GenreDAO();
        List<Genre> favoriteGenre = genreDAO.getFavoriteGenresByUserId(GlobalUser.globalUserId);
        PlaylistDAO playlistDAO = new PlaylistDAO();
        SongDAO songDAO = new SongDAO();

        Playlist suggestedPlaylist;


        // create suggestion on existing suggested playlist
        // if not existing --> create new one
        suggestedPlaylist = playlistDAO.findSuggestedPlaylist(GlobalUser.globalUserId);
        List<Song> songList = new ArrayList<>();
        suggestedPlaylist.setPlaylistSongs(songList);

//        if(suggestedPlaylist == null) {
//            System.out.println("Create new suggest playlist");
//            suggestedPlaylist = new Playlist();
//            suggestedPlaylist.setPlaylistType("Suggest");
//            suggestedPlaylist.setPlaylistName("Suggested Playlist");
//            suggestedPlaylist.setUserId(GlobalUser.globalUserId);
//
//            playlistDAO.addPlaylist(suggestedPlaylist);
//        }

        for(Genre g : favoriteGenre) {
            List<Song> songsBasedOnGenre = songDAO.getTop10SongByGenreId(g);

            // check whether adding song has already in the playlist or not
            for (int i = 0; i < 10; i++) {
                Song addedSong = getRandomSong(songsBasedOnGenre);
                boolean isAdded = false;

                for(Song song : suggestedPlaylist.getPlaylistSongs()) {
                    if(Objects.equals(addedSong.getSongId(), song.getSongId())){
                        isAdded = true;
                    }
                }


                if(!isAdded) {
                    suggestedPlaylist.getPlaylistSongs().add(addedSong);
                }
            }
        }

        playlistDAO.modifyPlaylist(suggestedPlaylist);

        getServletContext().getRequestDispatcher("/loadPlaylists").forward(req, resp);
    }

    Song getRandomSong(List<Song> songList) {
        return songList.get(getRandomIndex(songList.size()));
    }

    int getRandomIndex(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive integer greater than zero.");
        }

        Random random = new Random();
        return random.nextInt(length);
    }
}

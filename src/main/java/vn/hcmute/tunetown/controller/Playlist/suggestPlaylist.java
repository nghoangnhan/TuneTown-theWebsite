package vn.hcmute.tunetown.controller.Playlist;

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
import java.util.Random;

@WebServlet(urlPatterns = {"/suggestPlaylist"})

public class suggestPlaylist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenreDAO genreDAO = new GenreDAO();
        List<Genre> favoriteGenre = genreDAO.getFavoriteGenresByUserId(GlobalUser.globalUserId);

        Playlist suggestedPlaylist = new Playlist();
        PlaylistDAO playlistDAO = new PlaylistDAO();
        SongDAO songDAO = new SongDAO();

        suggestedPlaylist.setPlaylistType("Suggest");
        suggestedPlaylist.setPlaylistName("Suggested Playlist");
        suggestedPlaylist.setUserId(GlobalUser.globalUserId);

        List<Song> songList = new ArrayList<>();
        suggestedPlaylist.setPlaylistSongs(songList);


        for(Genre g : favoriteGenre) {
            List<Song> songsBasedOnGenre = songDAO.getTop10SongByGenreId(g);

            for (int i = 0; i < songsBasedOnGenre.size(); i++) {
                suggestedPlaylist.getPlaylistSongs().add(getRandomSong(songsBasedOnGenre));
            }
        }
        playlistDAO.addPlaylist(suggestedPlaylist);

        getServletContext().getRequestDispatcher("/loadPlaylists").forward(req, resp);
    }

    Song getRandomSong(List<Song> songList) {
        return songList.get(getRandomIndex(songList.size()));
    }

    int getRandomIndex(int length) {
        Random random = new Random();
        return random.nextInt(length);
    }
}

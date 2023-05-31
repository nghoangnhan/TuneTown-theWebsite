package vn.hcmute.tunetown.controller.Song;

import vn.hcmute.tunetown.DAO.SongDAO;
import vn.hcmute.tunetown.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

@WebServlet(urlPatterns = {"/downloadSong"})
public class downloadSong extends HttpServlet {
    private String downloadUrl;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String songId = req.getParameter("songId");
        Integer iSongId = Integer.parseInt(songId);

        SongDAO songDAO = new SongDAO();
        Song song = songDAO.getSong(iSongId);

        String downloadUrl = song.getSongData();

        URL url = new URL(downloadUrl);
        try (InputStream in = new BufferedInputStream(url.openStream())) {
            String fileName = song.getSongName();
            String fileExtension = ".mp3";

            // Set the appropriate response headers
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + fileExtension + "\"");
            resp.setHeader("Content-Type", "application/octet-stream");
            resp.setHeader("Content-Length", String.valueOf(url.openConnection().getContentLength()));

            // Write the file data to the response output stream
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                resp.getOutputStream().write(buffer, 0, bytesRead);
            }
            System.out.println("File downloaded successfully.");
        } catch (IOException e) {
            System.out.println("Error downloading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getFileNameFromUrl(String url) {
        // Extract the file name from the URL
        int lastSlashIndex = url.lastIndexOf('/');
        if (lastSlashIndex != -1 && lastSlashIndex < url.length() - 1) {
            return url.substring(lastSlashIndex + 1);
        } else {
            return "downloadedFile";
        }
    }
}

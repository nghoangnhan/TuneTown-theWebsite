package vn.hcmute.tunetown.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer genreId;
    private String genreName;
    private String genreDescription;
    @ManyToMany(mappedBy = "favoriteGenre")
    private List<User> listUser;

    public Genre() {
    }

    public Genre(Integer genreId, String genreName, String genreDescription) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.genreDescription = genreDescription;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreDescription() {
        return genreDescription;
    }

    public void setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
    }
}

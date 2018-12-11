package us.mattgreen.teams.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.mattgreen.teams.GenreRepository;
import us.mattgreen.teams.controllers.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Service annotation tells spring that this class is a service
@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    private static Log log = LogFactory.getLog(GenreService.class);

    public List<Genre> getAllGenres() {

        List<Genre> genres = new ArrayList<>();
        genreRepository.findAll().forEach(genres::add);
        if (genres.size() == 0){
            genreRepository.save(new Genre("Action", "5-Awesome"));
            genreRepository.save(new Genre("Romantic Comedy", "2-lame"));
            genreRepository.save(new Genre("Horror", "3-meh"));
            genreRepository.save(new Genre("Musicals", "1-kill me now"));
            genreRepository.save(new Genre("Sci-Fi", "4-beam me up goodness"));
            genreRepository.findAll().forEach(genres::add);
        }
        return genres;
    }

    public Genre getGenre(String id) {
        // lambda expression
        // This line gets the genres list and turns it into a stream
        // "filter" will filter the stream
        // inside of "filter" is a lambda expression
        // you define the variable "t"
        // the arrow indicates a lambda expression, similar to a foreach
        // t.getGenreName() gets the name, .contains(id) checks if genreName contains "id"
        // .findFirst() returns the first match from contains
        // .get() gets the selected object
        log.debug("Getting genre by id: \"" + id + "\"");
        return (Genre) genreRepository.findById(id).orElse(new Genre("Missing Genre", "NA"));
    }

    public void addGenre(Genre genre) {
        log.debug("Adding genre: \"" + genre + "\"");
        genreRepository.save(genre);
        log.trace("Genre \"" + genre + "\" added successfully.");
    }

    public void updateGenre(Genre genre, String id) {
        log.debug("Searching for genre by ID: \"" + id + "\"");
        genreRepository.save(genre);
    }

    public void deleteGenre(String id) {
        log.debug("Deleting genre id " + id + "\" ...");
        genreRepository.delete(getGenre(id));
        log.debug(id + " deleted successfully.");
    }
}

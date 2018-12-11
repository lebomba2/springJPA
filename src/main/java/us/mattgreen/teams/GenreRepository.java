package us.mattgreen.teams;

import org.springframework.data.repository.CrudRepository;
import us.mattgreen.teams.controllers.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {
}

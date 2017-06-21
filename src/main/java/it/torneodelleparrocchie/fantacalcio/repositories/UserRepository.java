package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dsalvatore on 21/06/17.
 */
public interface UserRepository extends CrudRepository<User, String>{
}

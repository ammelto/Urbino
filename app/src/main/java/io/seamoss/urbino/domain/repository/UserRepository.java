package io.seamoss.urbino.domain.repository;

import java.util.List;


import io.seamoss.urbino.data.models.User;
import rx.Observable;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public interface UserRepository {

    Observable<List<User>> users();

    Observable<User> user(final int uuid);
}

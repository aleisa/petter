package com.eminem.service.intf;

import com.eminem.entity.User;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ysen6 on 2017/2/8.
 */
public interface UserRepository extends MongoRepository<User,String> {

}

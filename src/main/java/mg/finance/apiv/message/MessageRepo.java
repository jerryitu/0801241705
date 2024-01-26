package mg.finance.apiv.message;


import mg.finance.apiv.message.collection.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepo extends MongoRepository<Message, String> {
}

package mg.finance.apiv.message;


import mg.finance.apiv.message.collection.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepo extends MongoRepository<Conversation, String> {
}

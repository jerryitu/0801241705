package mg.finance.apiv.message.sequence.dao;


import mg.finance.apiv.message.collection.Message;
import mg.finance.apiv.message.sequence.model.SequenceId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepo extends MongoRepository<SequenceId, String> {
}

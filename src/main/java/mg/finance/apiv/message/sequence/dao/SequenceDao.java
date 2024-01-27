package mg.finance.apiv.message.sequence.dao;


import mg.finance.apiv.message.sequence.exception.SequenceException;

public interface SequenceDao {

	long getNextSequenceId(String key) throws Exception;

}
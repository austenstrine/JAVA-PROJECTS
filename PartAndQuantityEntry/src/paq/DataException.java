package paq;

public class DataException extends Exception {

	/**
	 * Dunno why this has to be here.
	 */
	private static final long serialVersionUID = -529233855888792192L;
	
	public DataException(String dataRes) {
		super(dataRes);
	}
	public DataException() {
		super(new ExceptionStrings("data", "resolution").MESSAGES_BY_KEY.get(Key.GENERAL));
	}
}

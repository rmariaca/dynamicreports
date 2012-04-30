package net.sf.dynamicreports.adhoc.exception;

import net.sf.dynamicreports.report.constant.Constants;

public class AdhocException extends RuntimeException {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;;

	public AdhocException(String message) {
		super(message);
	}

	public AdhocException(Throwable cause) {
		super(cause);
	}

	public AdhocException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

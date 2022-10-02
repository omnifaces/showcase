package org.omnifaces.showcase.exceptionhandlers;

import java.sql.SQLException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ExceptionBean {

	public void throwRuntimeException() {
		throw new RuntimeException("peek-a-boo");
	}

	public void throwSQLException() throws SQLException {
		throw new SQLException("DB fail");
	}

	public void throwEpicFailException() throws EpicFailException {
		throw new EpicFailException();
	}

}
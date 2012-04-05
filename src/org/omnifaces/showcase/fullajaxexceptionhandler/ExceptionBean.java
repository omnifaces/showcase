package org.omnifaces.showcase.fullajaxexceptionhandler;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ExceptionBean {

	public void throwRuntimeException() {
		throw new RuntimeException("peek-a-boo");
	}

	public void throwSQLException() throws SQLException {
		throw new SQLException("DB fail");
	}

}
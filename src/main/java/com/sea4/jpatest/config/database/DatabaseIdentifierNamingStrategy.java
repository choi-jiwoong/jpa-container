package com.sea4.jpatest.config.database;

import java.io.Serializable;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class DatabaseIdentifierNamingStrategy extends CamelCaseToUnderscoresNamingStrategy implements Serializable {

	@Override public Identifier toPhysicalCatalogName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return toUpperCase(name, jdbcEnvironment);
	}

	@Override public Identifier toPhysicalSchemaName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return toUpperCase(name, jdbcEnvironment);
	}

	@Override public Identifier toPhysicalTableName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return toUpperCase(name, jdbcEnvironment);
	}

	/**
	 * 컬럼명 대문자 SNAKE_CASE로 생성.
	 * Column 애노테이션을 지정하지 않은 컬럼의 경우, 변수명(camelcase) 그대로 생성되는 부분을 보완한다.
	 * */
	@Override public Identifier toPhysicalColumnName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		String columnName = name.getText();
		if (Character.isLowerCase(columnName.charAt(0))) {
			columnName = camelCaseToUnderscores(columnName).toUpperCase();
		}
		return new Identifier(columnName, name.isQuoted());
	}

	private Identifier toUpperCase(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		if (name == null) {
			return super.toPhysicalTableName(name, jdbcEnvironment);
		}
		return new Identifier(name.getText().toUpperCase(), name.isQuoted());
	}

	private String camelCaseToUnderscores(final String camel) {
		final StringBuilder builder = new StringBuilder();
		builder.append(Character.toLowerCase(camel.charAt(0)));
		for (int i = 1; i < camel.length(); i++) {
			if (Character.isLowerCase(camel.charAt(i))) {
				builder.append(camel.charAt(i));
			} else {
				builder.append("_").append(Character.toLowerCase(camel.charAt(i)));
			}
		}
		return builder.toString();
	}

}

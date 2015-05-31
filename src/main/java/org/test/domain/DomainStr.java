package org.test.domain;

public class DomainStr {

    public static final String UNUSED                   = "unused ";
    public static final String CREATING                 = "New object ";
    // Tables
    public static final String CATEGORY                 = "category";
    public static final String SUBCATEGORY              = "subcategory";
    public static final String LOGIN                    = "login";
    // Columns
    public static final String ID                       = "id";
    public static final String CREATED                  = "created";
    public static final String LAST_UPDATE              = "last_update";
    public static final String NAME                     = "name";
    public static final String EMAIL                    = "email";
    public static final String PASSWORD                 = "password";
    public static final String ROLE                     = "role";
    // Column Definition
    public static final String BIGINT$10$_UNSIGNED      = "BIGINT(10) UNSIGNED";
    public static final String VARCHAR$100$             = "VARCHAR(100)";
    public static final String VARCHAR$128$             = "VARCHAR(128)";
    public static final String VARCHAR$255$             = "VARCHAR(255)";
    public static final String DATETIME                 = "DATETIME";
    public static final String ENUM_$ADMIN$USER         = "ENUM('ADMIN','USER') DEFAULT 'USER'";

	private DomainStr() {
		throw new AssertionError();
	}
}

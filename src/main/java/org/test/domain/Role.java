package org.test.domain;

@SuppressWarnings(DomainStr.UNUSED)
public enum Role {

	ADMIN("ADMIN"), USER("USER");

	private final String description;

	private Role(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}

package br.com.cassio.hotelaria.helper.enumeration;

public enum LogMessage {
	FIND_ALL_BY_REQUEST("find.all.by.request"), FIND_ALL_BY_SUCESS("find.all.by.sucess"),

	FIND_BY_REQUEST("find.by.request"), FIND_BY_SUCCESS("find.by.sucess"), FIND_BY_FAIL("find.by.fail"),

	SAVE_REQUEST("save.request"), SAVE_SUCCESS("save.sucess"), SAVE_FAIL("save.fail"),

	UPDATE_REQUEST("update.request"), UPDATE_SUCESS("update.sucess"), UPDATE_FAIL("update.fail"),

	PATCH_REQUEST("patch.request"), PATCH_SUCESS("patch.sucess"), PATCH_FAIL("patch.fail"),

	DELETE_REQUEST("delete.request"), DELETE_SUCCESS("delete.sucess"), DELETE_FAIL("delete.fail"),

	DELETE_SOFT_REQUEST("delete.soft.request"), DELETE_SOFT_SUCCESS("delete.soft.sucess"),
	DELETE_SOFT_FAIL("delete.soft.fail"),

	BATCH_REQUEST("batch.request"), BATCH_SUCESS("batch.sucess"), BATCH_FAIL("batch.fail"),

	CREATE_RELATION_REQUEST("create.relation.request"), CREATE_RELATION_SUCESS("create.relation.sucess"),
	CREATE_RELATION_FAIL("create.relation.fail"),

	FOUND("found"), SAVING("saving"), UPDATING("updating"), PATCHING("patching"), DELETING("deleting"),
	DELETING_SOFT("deleting.soft"),

	EXCEPTION_BATCH_ENTRY_FAILED("exception.batch.entry.failed"),
	EXCEPTION_BUSINESS_RULE_BROKEN("exception.business.rule.broken"),
	EXCEPTION_ENTITY_NOT_FOUND("exception.entity.not.found"),

	ENTITY_ALREADY_EXISTS("entity.already.exists");

	private String value;

	LogMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

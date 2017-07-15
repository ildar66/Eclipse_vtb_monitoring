package ru.masterdm.km.common.entity;

/**
 * Результаты исполнения контрольных мероприятий.
 * 
 * @author Shafigullin Ildar
 * 
 */
@SuppressWarnings("serial")
public class EventResult extends BaseNamedEntity<Long> {
	private KmEventStatus eventStatus;
	private boolean commentMandatory;
	private boolean sanction;
	private boolean processCRF;

	public KmEventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(KmEventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	public boolean isCommentMandatory() {
		return commentMandatory;
	}

	public void setCommentMandatory(boolean commentMandatory) {
		this.commentMandatory = commentMandatory;
	}

	public boolean isSanction() {
		return sanction;
	}

	public void setSanction(boolean sanction) {
		this.sanction = sanction;
	}

	public boolean isProcessCRF() {
		return processCRF;
	}

	public void setProcessCRF(boolean processCRF) {
		this.processCRF = processCRF;
	}
}

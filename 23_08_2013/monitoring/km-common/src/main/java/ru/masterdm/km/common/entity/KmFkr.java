/**
 * 
 */
package ru.masterdm.km.common.entity;

import java.util.Date;

/**
 * Экземпляры ФКР.
 * 
 * @author Shafigullin Ildar
 * 
 */
@SuppressWarnings("serial")
public class KmFkr extends BaseIndexedEntity<Long> {
	private FkrType type;
	private String notes;
	private Deal deal;
	private boolean significant;
	private Date created;
	private Date finished;
	private KmFkrStatus status;
	private String regReason;
	private String who;
	private String docID;

	public FkrType getType() {
		return type;
	}

	public void setType(FkrType type) {
		this.type = type;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public boolean isSignificant() {
		return significant;
	}

	public void setSignificant(boolean significant) {
		this.significant = significant;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getFinished() {
		return finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

	public KmFkrStatus getStatus() {
		return status;
	}

	public void setStatus(KmFkrStatus status) {
		this.status = status;
	}

	public String getRegReason() {
		return regReason;
	}

	public void setRegReason(String regReason) {
		this.regReason = regReason;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

}

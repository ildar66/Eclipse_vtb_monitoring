package ru.masterdm.km.common.entity;

import java.util.Date;

/**
 * 
 * @author Alexey Chalov
 */
@SuppressWarnings("serial")
public class Deal extends BaseIndexedEntity<Long> {

	private Long amount;
	private Currency currency;
	private DealType type;
	private String spkpNumber;
	private String contractNumber;
	private Date validTo;
	private Long pupProcessID;
	private String crmCode;
	private Contractor borrower;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public DealType getType() {
		return type;
	}

	public void setType(DealType type) {
		this.type = type;
	}

	public String getSpkpNumber() {
		return spkpNumber;
	}

	public void setSpkpNumber(String spkpNumber) {
		this.spkpNumber = spkpNumber;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Long getPupProcessID() {
		return pupProcessID;
	}

	public void setPupProcessID(Long pupProcessID) {
		this.pupProcessID = pupProcessID;
	}

	public String getCrmCode() {
		return crmCode;
	}

	public void setCrmCode(String crmCode) {
		this.crmCode = crmCode;
	}

	public Contractor getBorrower() {
		return borrower;
	}

	public void setBorrower(Contractor borrower) {
		this.borrower = borrower;
	}
}

package ru.masterdm.km.common.entity;

/**
 * 
 * Сущность Списка ФКР.
 * 
 * @author Shafigullin Ildar
 * 
 */
public class KmFkrInstance {
	private KmFkr fkr;
	private Contractor client;
	private KmFkrStatusChange change;
	private KmEventInstance event;
	private AppFile docFile;
	private OfficialInfo info;

	public KmFkr getFkr() {
		return fkr;
	}

	public void setFkr(KmFkr fkr) {
		this.fkr = fkr;
	}

	public Contractor getClient() {
		return client;
	}

	public void setClient(Contractor client) {
		this.client = client;
	}

	public KmFkrStatusChange getChange() {
		return change;
	}

	public void setChange(KmFkrStatusChange statusChange) {
		this.change = statusChange;
	}

	public KmEventInstance getEvent() {
		return event;
	}

	public void setEvent(KmEventInstance event) {
		this.event = event;
	}

	public AppFile getDocFile() {
		return docFile;
	}

	public void setDocFile(AppFile docFile) {
		this.docFile = docFile;
	}

	public OfficialInfo getInfo() {
		return info;
	}

	public void setInfo(OfficialInfo info) {
		this.info = info;
	}
}

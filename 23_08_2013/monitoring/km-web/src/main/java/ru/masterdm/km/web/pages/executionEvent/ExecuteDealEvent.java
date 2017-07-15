package ru.masterdm.km.web.pages.executionEvent;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import ru.masterdm.km.common.entity.Deal;
import ru.masterdm.km.common.entity.KmFkr;
import ru.masterdm.km.web.base.event.ExecuteEventBasePage;
import ru.masterdm.km.web.util.ExecuteEventUtil;
import ru.masterdm.km.web.util.ReportUtil;

/**
 * Карточка контрольного мероприятий для сделки.
 * 
 * @author Shafigullin Ildar
 * 
 */
public class ExecuteDealEvent extends ExecuteEventBasePage {
	@Inject
	private BeanModelSource beanModelSource;

	@Persist
	@Property
	private String spkpNumber;

	@Property
	private KmFkr fkr;

	void onActivate(long eventID, String spkpNumber) {
		// System.out.println("onActivate =" + eventID + "; spkpNumber=" + spkpNumber);
		init(eventID);
		this.spkpNumber = spkpNumber;
	}

	@OnEvent(value = EventConstants.SELECTED, component = "executeEventButton")
	void executeRequested() {
		action = Action.EXECUTE_EVENT;
	}

	@OnEvent(value = EventConstants.SELECTED, component = "saveEventButton")
	void saveRequested() {
		action = Action.SAVE_EVENT;
	}

	@OnEvent(value = EventConstants.SELECTED, component = "cancelButton")
	Object cancelRequested() {
		action = Action.CANCEL;
		// componentResources.discardPersistentFieldChanges();
		return ByDate.class;
	}

	void onValidateFromExecuteDealEventForm() {
		System.out.println("onValidateFromExecuteDealEventForm ACTION = " + action);// TODO
		switch (action) {
		case EXECUTE_EVENT:
			validateOnExecuteEvent();
			break;
		case SAVE_EVENT:
			validateOnSaveEvent();
			break;
		default:
			break;
		}
	}

	Object onSuccessFromExecuteDealEventForm() {
		System.out.println("onSuccessFromExecuteDealEventForm ACTION = " + action);// TODO
		switch (action) {
		case EXECUTE_EVENT:
			executeEvent();
			return ByDate.class;
		case SAVE_EVENT:
			saveEvent();
			return null;
		default:
			return null;
		}
	}

	public String getDealInfo() {
		return ExecuteEventUtil.getDealInfo(event.getEvent().getDeal(), getDateViewFormat(), getAmountViewFormat());
	}

	public String getDocInfo() {
		return ExecuteEventUtil.getDocumentInfo(event.getEvent().getDeal(), getDateListFormat(), getAmountViewFormat());
	}

	public String getCombNumber() {
		Deal deal = event.getEvent().getDeal();
		return ReportUtil.generateCombinedNumber(deal.getCrmCode(), deal.getContractNumber());
	}

	public String getPageName() {
		return "executionEvent/ExecuteDealEvent";
	}

	public String getApplySanctionClass() {
		if (event != null && event.isApplySanction()) {
			return "visible";
		}
		return "collapse";
	}

	public String getRevokeSanctionClass() {
		if (event != null && event.isRevokeSanction()) {
			return "visible";
		}
		return "collapse";
	}

	public BeanModel<KmFkr> getFkrModel() {
		BeanModel<KmFkr> fkrModel = beanModelSource.createDisplayModel(KmFkr.class, getMessages());
		fkrModel.include("created", "notes", "finished");
		fkrModel.addEmpty("edit").sortable(false);
		fkrModel.addEmpty("fkrType").sortable(false);
		fkrModel.addEmpty("sign").sortable(false);
		fkrModel.addEmpty("status").sortable(false);
		fkrModel.reorder("edit", "created", "fkrType", "sign", "status");
		return fkrModel;
	}
}

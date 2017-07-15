package ru.masterdm.km.web.pages.eventInstances;

import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import ru.masterdm.km.common.entity.Contractor;
import ru.masterdm.km.common.entity.KmEvent;
import ru.masterdm.km.common.entity.KmEventCalendar;
import ru.masterdm.km.common.entity.KmEventInstance;
import ru.masterdm.km.common.entity.KmEventType;
import ru.masterdm.km.common.entity.KmEventTypeGroup;
import ru.masterdm.km.common.entity.MonitoredObjectType;
import ru.masterdm.km.dao.ContractorDao;
import ru.masterdm.km.dao.DictionaryDao;
import ru.masterdm.km.dao.EventDao;
import ru.masterdm.km.web.util.BaseEntityValueEncoder;

/**
 * Календарь мероприятий для клиента.
 * 
 * @author Shafigullin Ildar
 * 
 */
public class ContractorCalendar {
	@Persist
	@Property
	private KmEvent filter;

	@Persist
	@Property
	private Contractor contractor;

	@Inject
	private DictionaryDao dictionaryDao;
	@Inject
	private ContractorDao contractorDao;
	@Inject
	private EventDao eventDao;
	@Inject
	private SelectModelFactory selectModelFactory;
	@Inject
	private BeanModelSource beanModelSource;
	@Inject
	private Messages messages;
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@InjectComponent
	private Zone contractorCalendarZone;

	@Property
	private KmEventInstance eventInstance;
	@Property
	private KmEventCalendar calendar;

	private List<MonitoredObjectType> monitoredObjectTypes;
	private List<KmEventTypeGroup> kmEventTypeGroups;
	private List<KmEventType> kmEventTypes;

	void onActivate(String contractorID) {
		contractor = dictionaryDao.getContractor(contractorID);
		if (filter == null) {
			filter = new KmEvent();
		}
	}

	@OnEvent(component = "acceptEventType")
	void acceptEventType(long eventCalendarID) {
		// System.out.println("acceptEventType ID = " + eventCalendarID);
		contractorDao.acceptEventType(contractor.getId(), eventCalendarID);
	}

	@OnEvent(component = "removeEventInstance")
	void removeEventInstance(long eventInstanceID) {
		eventDao.removeEventInstance(eventInstanceID);
	}

	@OnEvent(value = EventConstants.SUCCESS, component = "contractorCalendarForm")
	Object submitContractorCalendarForm() {
		contractorDao.acceptPlan(contractor.getId(), filter);
		ajaxResponseRenderer.addRender(contractorCalendarZone);
		return null;
	}

	public GridDataSource getPlan() {
		return new GridDataSource() {
			private int startIndex;
			private List<KmEventCalendar> instances;

			@Override
			public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {
				this.startIndex = startIndex;
				instances = contractorDao.getContractorPlan(startIndex, endIndex - startIndex + 1, contractor.getId(), filter);
			}

			@Override
			public Object getRowValue(int index) {
				return instances.get(index - startIndex);
			}

			@Override
			public Class<?> getRowType() {
				return KmEventCalendar.class;
			}

			@Override
			public int getAvailableRows() {
				return contractorDao.getContractorPlanCount(contractor.getId(), filter);
			}
		};
	}

	public GridDataSource getEventInstances() {
		return new GridDataSource() {
			private int startIndex;
			private List<KmEventInstance> instances;

			@Override
			public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {
				this.startIndex = startIndex;
				instances = contractorDao.getContractorCalendar(startIndex, endIndex - startIndex + 1, contractor.getId(), filter);
			}

			@Override
			public Object getRowValue(int index) {
				return instances.get(index - startIndex);
			}

			@Override
			public Class<?> getRowType() {
				return KmEventInstance.class;
			}

			@Override
			public int getAvailableRows() {
				return contractorDao.getContractorCalendarCount(contractor.getId(), filter);
			}
		};
	}

	public BeanModel<KmEventInstance> getEventInstanceModel() {
		BeanModel<KmEventInstance> eventModel = beanModelSource.createDisplayModel(KmEventInstance.class, messages);
		for (String prop : eventModel.getPropertyNames()) {
			eventModel.get(prop).sortable(false);
		}
		return eventModel;
	}

	public SelectModel getMonitoredObjectTypeSelectModel() {
		return selectModelFactory.create(getMonitoredObjectTypes(), "name");
	}

	private List<MonitoredObjectType> getMonitoredObjectTypes() {
		if (monitoredObjectTypes == null) {
			monitoredObjectTypes = dictionaryDao.getMonitoredObjectTypes();
		}
		return monitoredObjectTypes;
	}

	public ValueEncoder<MonitoredObjectType> getMonitoredObjectTypeValueEncoder() {
		return new BaseEntityValueEncoder<MonitoredObjectType>(getMonitoredObjectTypes());
	}

	public SelectModel getEventTypeGroupSelectModel() {
		return selectModelFactory.create(getKmEventTypeGroups() != null ? getKmEventTypeGroups() : Collections.emptyList(), "name");
	}

	private List<KmEventTypeGroup> getKmEventTypeGroups() {
		if (kmEventTypeGroups == null && filter.getMonitoredObjectType() != null) {
			kmEventTypeGroups = dictionaryDao.getEventTypeGroups(filter.getMonitoredObjectType().getId());
		}
		return kmEventTypeGroups;
	}

	public ValueEncoder<KmEventTypeGroup> getEventTypeGroupValueEncoder() {
		return new BaseEntityValueEncoder<KmEventTypeGroup>(getKmEventTypeGroups());
	}

	public SelectModel getEventTypeSelectModel() {
		return selectModelFactory.create(getKmEventTypes() != null ? getKmEventTypes() : Collections.emptyList(), "name");
	}

	private List<KmEventType> getKmEventTypes() {
		if (kmEventTypes == null && filter.getEventTypeGroup() != null) {
			kmEventTypes = dictionaryDao.getKmEventTypes(filter.getEventTypeGroup().getId());
		}
		return kmEventTypes;
	}

	public ValueEncoder<KmEventType> getEventTypeValueEncoder() {
		return new BaseEntityValueEncoder<KmEventType>(getKmEventTypes());
	}

	public boolean isRenderEventTypeGroup() {
		return (filter != null) && (filter.getMonitoredObjectType() != null);
	}

	public boolean isRenderEventType() {
		return (filter != null) && (filter.getEventTypeGroup() != null);
	}

	public boolean isRenderAcceptAllButton() {
		return (filter != null) && (contractorDao.getContractorPlanCount(contractor.getId(), filter) > 0);
	}

	@OnEvent(value = EventConstants.VALUE_CHANGED, component = "monitoredObjectTypeSelect")
	void selectMonitoredObjectType(MonitoredObjectType monitoredObjectType) {
		filter.setMonitoredObjectType(monitoredObjectType);
		selectEventTypeGroup(null);
	}

	@OnEvent(value = EventConstants.VALUE_CHANGED, component = "eventTypeGroupSelect")
	void selectEventTypeGroup(KmEventTypeGroup eventTypeGroup) {
		filter.setEventTypeGroup(eventTypeGroup);
		selectEventType(null);
	}

	@OnEvent(value = EventConstants.VALUE_CHANGED, component = "eventTypeSelect")
	void selectEventType(KmEventType eventType) {
		filter.setEventType(eventType);
		ajaxResponseRenderer.addRender(contractorCalendarZone);
	}

}

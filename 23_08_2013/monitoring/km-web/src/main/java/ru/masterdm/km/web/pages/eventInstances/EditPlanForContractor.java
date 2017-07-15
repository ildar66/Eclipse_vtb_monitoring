package ru.masterdm.km.web.pages.eventInstances;

import static ru.masterdm.km.common.util.DataTypeUtil.isEmpty;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Environmental;
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
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import ru.masterdm.km.common.dictionary.EventRepeatTypeDictionary;
import ru.masterdm.km.common.entity.Contractor;
import ru.masterdm.km.common.entity.DayOfWeek;
import ru.masterdm.km.common.entity.DocumentGroup;
import ru.masterdm.km.common.entity.DocumentType;
import ru.masterdm.km.common.entity.KmConfirmationType;
import ru.masterdm.km.common.entity.KmEventCalendar;
import ru.masterdm.km.common.entity.KmRepeatType;
import ru.masterdm.km.common.entity.Month;
import ru.masterdm.km.common.entity.NotificationRecipient;
import ru.masterdm.km.common.entity.User;
import ru.masterdm.km.dao.ContractorDao;
import ru.masterdm.km.dao.DictionaryDao;
import ru.masterdm.km.web.base.SimpleBasePage;
import ru.masterdm.km.web.util.BaseEntityValueEncoder;

/**
 * Окно редактирования планового мероприятия для клиента.
 * 
 * @author Shafigullin Ildar
 * 
 */
public class EditPlanForContractor extends SimpleBasePage {
	@Persist
	@Property
	private Contractor contractor;
	@Persist
	@Property
	private KmEventCalendar event;
	@Property
	@Persist
	private String lastNamePattern;

	@Property
	private NotificationRecipient notificationRecipient;
	@Property
	private NotificationRecipient loopRcpt;
	@Property
	private Date loopDate;
	@Property(write = false)
	private boolean dialogAppeared;
	@Property
	private User user;
	@Property
	private DayOfWeek dayOfWeek;

	@Inject
	private ContractorDao contractorDao;
	@Inject
	private DictionaryDao dictionaryDao;
	@Inject
	private BeanModelSource beanModelSource;
	@Inject
	private Messages messages;
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	@Inject
	private SelectModelFactory selectModelFactory;
	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	@Environmental
	private ValidationTracker validationTracker;

	@InjectComponent
	private Zone userListZone;
	@InjectComponent
	private Zone recipientsZone;
	@InjectComponent
	private Zone repeatTypeSelectZone;
	@InjectComponent
	private Zone scheduleZone;
	@InjectComponent
	private Zone documentZone;

	@Persist
	Link returnPage;

	private List<KmRepeatType> repeatTypes;
	private List<DayOfWeek> dayOfWeeks;
	private List<Month> months;
	private List<KmConfirmationType> confirmationTypes;
	private List<DocumentGroup> documentGroups;
	private List<DocumentType> documentTypes;

	private Action action;

	private enum Action {
		SAVE_EVENT, CANCEL, ADD_RECIPIENT, DETAIL, ADD_DATE_TO_SCHEDULE;
	}

	@OnEvent(value = EventConstants.VALUE_CHANGED, component = "repeatTypeSelect")
	void selectRepeatTypes(KmRepeatType repeatType) {
		// action = Action.SAVE_EVENT;
		// System.out.println("repeatTypeSelect =" + repeatType);
		// event.setRepeatType(repeatType);
		ajaxResponseRenderer.addRender(repeatTypeSelectZone);
	}

	public boolean isRenderEventTypeOnce() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.ONCE.getId().equals(event.getRepeatType().getId());
	}

	public boolean isRenderEventTypeEveryDay() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.EVERY_DAY.getId().equals(event.getRepeatType().getId());
	}

	public boolean isRenderEventTypeEveryWeek() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.EVERY_WEEK.getId().equals(event.getRepeatType().getId());
	}

	public boolean isRenderEventTypeEveryMonth() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.EVERY_MONTH.getId().equals(event.getRepeatType().getId());
	}

	public boolean isRenderEventTypeEveryQuarter() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.EVERY_QUARTER.getId().equals(event.getRepeatType().getId());
	}

	public boolean isRenderEventTypeEveryHalfYear() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.EVERY_HALF_YEAR.getId().equals(event.getRepeatType().getId());
	}

	public boolean isRenderEventTypeYear() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.YEAR.getId().equals(event.getRepeatType().getId());
	}

	public boolean isRenderEventTypeRandom() {
		return event.getRepeatType() != null && EventRepeatTypeDictionary.RANDOM.getId().equals(event.getRepeatType().getId());
	}

	@OnEvent(value = EventConstants.SELECTED, component = "saveEventButton")
	void createRequested() {
		action = Action.SAVE_EVENT;
	}

	@OnEvent(value = EventConstants.SELECTED, component = "cancelButton")
	void cancelRequested() {
		action = Action.CANCEL;
		// componentResources.discardPersistentFieldChanges();
		// return Index.class;
	}

	@OnEvent(value = EventConstants.SUCCESS, component = "editPlanEventForm")
	Object editPlanEventForm() {
		switch (action) {
		case SAVE_EVENT:
			// componentResources.discardPersistentFieldChanges();
			System.out.println("Submit editPlanEventForm event=" + event);
			contractorDao.savePlanEventForContractor(event);
			// return Index.class;
			break;
		case ADD_RECIPIENT:
			event.getNotification().getRecipients().add(notificationRecipient);
			notificationRecipient = new NotificationRecipient();
			return null;
		case ADD_DATE_TO_SCHEDULE:
			if (event.getStartDate() != null) {
				event.getSchedule().add(event.getStartDate());
				event.setStartDate(null);
			}
			return null;
		case CANCEL:
			break;
		default:
			return null;
			// break;
		}

		// переход к вызывающей странице:
		return returnPage;
	}

	void onActivate(long eventPlanID, String contractorID, String returnPageName, long id) {
		System.out.println("onActivate eventPlanID=" + eventPlanID + "; contractorID=" + contractorID);// TODO
		contractor = dictionaryDao.getContractor(contractorID);
		event = contractorDao.getPlanEventForContractor(eventPlanID);
		if ("eventInstances/contractorClassifier".equalsIgnoreCase(returnPageName)) {
			returnPage = pageRenderLinkSource.createPageRenderLinkWithContext(returnPageName, contractorID, 0);
		} else if ("executionEvent/ExecuteContractorEvent".equalsIgnoreCase(returnPageName)) {
			returnPage = pageRenderLinkSource.createPageRenderLinkWithContext(returnPageName, id, contractorID);
		}
	}

	@OnEvent(value = EventConstants.ACTIVATE)
	void prepare() {
		// System.out.println("prepare =");
		notificationRecipient = new NotificationRecipient();
		// eventPeriodDate = "1";
	}

	@OnEvent(value = EventConstants.VALIDATE, component = "editPlanEventForm")
	void validatePlanEventForm() {
		System.out.println("validate editPlanEventForm");
		switch (action) {
		case SAVE_EVENT:
			break;
		case ADD_DATE_TO_SCHEDULE:
			if (event.getStartDate() == null) {
				validationTracker.recordError(messages.get("schedule-date-required-message"));
			}
			if (!validationTracker.getHasErrors()) {
				for (Date date : event.getSchedule()) {
					if (date.equals(event.getStartDate())) {
						validationTracker.recordError(messages.get("not-unique-schedule-date-message"));
					}
				}
			}
			break;
		default:
			break;
		}
	}

	@OnEvent(value = EventConstants.ACTION, component = "selectRecepientLink")
	void prepareUserSelectDialog() {
		dialogAppeared = true;
		lastNamePattern = null;
		ajaxResponseRenderer.addRender(userListZone);
	}

	@OnEvent(value = EventConstants.SELECTED, component = "userSearchButton")
	void searchUsers() {
		ajaxResponseRenderer.addRender(userListZone);
	}

	@OnEvent(value = EventConstants.ACTION, component = "selectUserLink")
	void addRecepient(Long userId) {
		User u = dictionaryDao.getUser(userId);
		if (u != null && !isEmpty(u.getEmail())) {
			NotificationRecipient rcpt = new NotificationRecipient();
			rcpt.setFullName(u.getFullName());
			rcpt.setEmail(u.getEmail());
			if (!event.getNotification().getRecipients().contains(rcpt)) {
				event.getNotification().getRecipients().add(rcpt);
				ajaxResponseRenderer.addRender(recipientsZone);
			}
		}
	}

	@OnEvent(value = "deleteRecipient")
	void deleteRecipient(String email) {
		for (ListIterator<NotificationRecipient> li = event.getNotification().getRecipients().listIterator(); li.hasNext();) {
			if (li.next().getEmail().equals(email)) {
				li.remove();
			}
		}
		ajaxResponseRenderer.addRender(recipientsZone);
	}

	@OnEvent(value = EventConstants.SELECTED, component = "addRecipientButton")
	void addRecepientRequested() {
		action = Action.ADD_RECIPIENT;
	}

	@OnEvent(value = EventConstants.SELECTED, component = "addDateToScheduleButton")
	void addDateToSchedule() {
		action = Action.ADD_DATE_TO_SCHEDULE;
	}

	@OnEvent(value = "deleteDateFromSchedule")
	void deleteDateFromSchedule(long time) {
		System.out.println("deleteDateFromSchedule date=" + time);
		for (Date date : event.getSchedule()) {
			if (date.getTime() == time) {
				event.getSchedule().remove(date);
				break;
			}
		}
		ajaxResponseRenderer.addRender(scheduleZone);
	}

	@OnEvent(value = EventConstants.VALUE_CHANGED, component = "eventDocumentGroup")
	void selectEventTypeGroup(DocumentGroup docGroup) {
		event.setDocumentGroup(docGroup);
		ajaxResponseRenderer.addRender(documentZone);
	}

	public BeanModel<User> getUserModel() {
		BeanModel<User> userModel = beanModelSource.createDisplayModel(User.class, messages);
		for (String prop : userModel.getPropertyNames()) {
			userModel.get(prop).sortable(false);
		}
		return userModel;
	}

	public GridDataSource getUsers() {
		return new GridDataSource() {

			private int startIndex;
			private List<User> users;

			@Override
			public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {
				this.startIndex = startIndex;
				users = dictionaryDao.getUsers(startIndex, endIndex - startIndex + 1, lastNamePattern);
			}

			@Override
			public Object getRowValue(int index) {
				return users.get(index - startIndex);
			}

			@Override
			public Class<?> getRowType() {
				return User.class;
			}

			@Override
			public int getAvailableRows() {
				return dictionaryDao.getUserCount(lastNamePattern);
			}
		};
	}

	public List<KmRepeatType> getRepeatTypes() {
		if (repeatTypes == null) {
			repeatTypes = dictionaryDao.getKmRepeatTypes();
		}
		return repeatTypes;
	}

	public SelectModel getRepeatTypeSelectModel() {
		return selectModelFactory.create(getRepeatTypes(), "name");
	}

	public ValueEncoder<KmRepeatType> getRepeatTypeValueEncoder() {
		return new BaseEntityValueEncoder<KmRepeatType>(getRepeatTypes());
	}

	public List<Month> getMonths() {
		if (months == null) {
			months = dictionaryDao.getMonths();
		}
		return months;
	}

	public SelectModel getMonthSelectModel() {
		return selectModelFactory.create(getMonths(), "name");
	}

	public ValueEncoder<Month> getMonthValueEncoder() {
		return new BaseEntityValueEncoder<Month>(getMonths());
	}

	public Month getMonth() {
		Month month = null;
		if (event.getPeriodDate() != null) {
			month = new Month();
			month.setId(event.getPeriodDate());
		}
		return month;
	}

	public void setMonth(Month month) {
		event.setPeriodDate(month.getId());
	}

	public List<DayOfWeek> getDaysOfWeek() {
		if (dayOfWeeks == null) {
			dayOfWeeks = dictionaryDao.getDaysOfWeek();
		}
		return dayOfWeeks;
	}

	public ValueEncoder<DayOfWeek> getDayOfWeekValueEncoder() {
		return new BaseEntityValueEncoder<DayOfWeek>(getDaysOfWeek());
	}

	public boolean isDayOfWeekSelected() {
		return event.getDaysOfWeek().contains(dayOfWeek);
	}

	public void setDayOfWeekSelected(boolean dayOfWeekSelected) {
		if (dayOfWeekSelected) {
			if (!event.getDaysOfWeek().contains(dayOfWeek)) {
				event.getDaysOfWeek().add(dayOfWeek);
			}
		} else {
			event.getDaysOfWeek().remove(dayOfWeek);
		}
	}

	public List<KmConfirmationType> getConfirmationTypes() {
		if (confirmationTypes == null) {
			confirmationTypes = dictionaryDao.getKmConfirmationTypes();
		}
		return confirmationTypes;
	}

	public SelectModel getConfirmationTypeSelectModel() {
		return selectModelFactory.create(getConfirmationTypes(), "name");
	}

	public ValueEncoder<KmConfirmationType> getConfirmationTypeValueEncoder() {
		return new BaseEntityValueEncoder<KmConfirmationType>(getConfirmationTypes());
	}

	public List<DocumentGroup> getDocumentGroups() {
		if (documentGroups == null) {
			documentGroups = dictionaryDao.getDocumentGroups();
		}
		return documentGroups;
	}

	public SelectModel getDocumentGroupSelectModel() {
		return selectModelFactory.create(getDocumentGroups(), "name");
	}

	public ValueEncoder<DocumentGroup> getDocumentGroupValueEncoder() {
		return new BaseEntityValueEncoder<DocumentGroup>(getDocumentGroups());
	}

	public List<DocumentType> getDocumentTypes() {
		if (documentTypes == null && event.getDocumentGroup() != null) {
			documentTypes = dictionaryDao.getDocumentTypes(event.getDocumentGroup().getId());
		}
		return documentTypes;
	}

	public SelectModel getDocumentTypeSelectModel() {
		return selectModelFactory.create(getDocumentTypes() != null ? getDocumentTypes() : Collections.emptyList(), "name");
	}

	public ValueEncoder<DocumentType> getDocumentTypeValueEncoder() {
		return new BaseEntityValueEncoder<DocumentType>(getDocumentTypes());
	}
}

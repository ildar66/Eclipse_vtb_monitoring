package ru.masterdm.km.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alexey Chalov
 */
@SuppressWarnings("serial")
public class KmEventNotification extends BaseEntity {

	private boolean alarmOnEventStartDay;
	private boolean continueToAlarmWhenUnfulfilled;
	private boolean includeLinkInTextMessage;

	private List<NotificationRecipient> recipients;

	private Long periodKind;
	private Long period;

	public boolean isAlarmOnEventStartDay() {
		return alarmOnEventStartDay;
	}

	public void setAlarmOnEventStartDay(boolean alarmOnEventStartDay) {
		this.alarmOnEventStartDay = alarmOnEventStartDay;
	}

	public boolean isContinueToAlarmWhenUnfulfilled() {
		return continueToAlarmWhenUnfulfilled;
	}

	public void setContinueToAlarmWhenUnfulfilled(boolean continueToAlarmWhenUnfulfilled) {
		this.continueToAlarmWhenUnfulfilled = continueToAlarmWhenUnfulfilled;
	}

	public List<NotificationRecipient> getRecipients() {
		if (recipients == null) {
			recipients = new ArrayList<NotificationRecipient>();
		}
		return recipients;
	}

	public void setRecipients(List<NotificationRecipient> recipients) {
		this.recipients = recipients;
	}

	public boolean isIncludeLinkInTextMessage() {
		return includeLinkInTextMessage;
	}

	public void setIncludeLinkInTextMessage(boolean includeLinkInTextMessage) {
		this.includeLinkInTextMessage = includeLinkInTextMessage;
	}

	public Long getPeriodKind() {
		return periodKind;
	}

	public void setPeriodKind(Long periodKind) {
		this.periodKind = periodKind;
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}
}

package ru.masterdm.km.web.model.event;

import java.util.List;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

import ru.masterdm.km.common.entity.KmEventChronology;
import ru.masterdm.km.dao.EventDao;

/**
 * Хронология исполнения мероприятия DataSource.
 * 
 * @author Shafigullin Ildar
 * 
 */
public class EventChronologyDataSource implements GridDataSource {
	private int startIndex;
	private long eventID;
	private List<KmEventChronology> instances;
	private EventDao eventDao;

	public EventChronologyDataSource(EventDao eventDao, long eventID) {
		this.eventDao = eventDao;
		this.eventID = eventID;
	}

	@Override
	public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {
		this.startIndex = startIndex;
		instances = eventDao.getEventChronology(startIndex, endIndex - startIndex + 1, eventID);
	}

	@Override
	public Object getRowValue(int index) {
		return instances.get(index - startIndex);
	}

	@Override
	public Class<?> getRowType() {
		return KmEventChronology.class;
	}

	@Override
	public int getAvailableRows() {
		return eventDao.getEventChronologyCount(eventID);
	}
}

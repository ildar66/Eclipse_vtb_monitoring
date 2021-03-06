package ru.masterdm.km.dao;

import java.util.List;

import ru.masterdm.km.common.entity.ContractorInstance;
import ru.masterdm.km.common.entity.KmEvent;
import ru.masterdm.km.common.entity.KmEventCalendar;
import ru.masterdm.km.common.entity.KmEventInstance;
import ru.masterdm.km.common.entity.KmFkr;
import ru.masterdm.km.common.searchfilter.EventsByContractorFilter;
import ru.masterdm.km.util.query.SortCriterion;

/**
 * Mok-реализация DAO интерфейса для сущности "Контрагент".
 * 
 * @author Shafigullin Ildar
 * 
 */
public class ContractorDaoMockImpl implements ContractorDao {

	@Override
	public List<KmEventInstance> getContractorCalendar(int startIndex, int amount, String contractorID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContractorCalendarCount(String contractorID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInstanceCount(EventsByContractorFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ContractorInstance> getInstances(int startIndex, int amount, EventsByContractorFilter filter, List<SortCriterion> sortCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KmEventCalendar> getContractorPlan(int startIndex, int i, String id, KmEvent filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContractorPlanCount(String id, KmEvent filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExistEventType(String id, long eventTypeID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEventType(String id, long eventTypeID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEventType(String id, long eventTypeID) {
		// TODO Auto-generated method stub

	}

	@Override
	public KmEventCalendar getPlanEventForContractor(long eventPlanID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePlanEventForContractor(KmEventCalendar event) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<KmEventInstance> getContractorCalendar(int startIndex, int amount, String contractorID, KmEvent filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContractorCalendarCount(String contractorID, KmEvent filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void acceptEventType(String id, long eventCalendarID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptPlan(String contractorID, KmEvent filter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFkrToContractor(KmFkr fkr, String contractorID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<KmFkr> getContractorFkr(int startIndex, int amount, String contractorID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContractorFkrCount(String contractorID) {
		// TODO Auto-generated method stub
		return 0;
	}
}

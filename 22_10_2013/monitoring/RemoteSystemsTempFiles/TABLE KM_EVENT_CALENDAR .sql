--<ScriptOptions statementTerminator=";"/>

CREATE TABLE KM_EVENT_CALENDAR (
		ID_EVENT_CALENDAR NUMBER(38 , 0) NOT NULL,
		EVENT_NAME VARCHAR2(1024) NOT NULL,
		ID_EVENT_PARENT NUMBER(38 , 0),
		ID_MDTASK NUMBER(38 , 0),
		ID_ORGANIZATION CHAR(12),
		ID_DEP_CREATE NUMBER(38 , 0),
		ID_DEP_EXEC NUMBER(38 , 0),
		ID_EVENT_TYPE NUMBER(38 , 0) NOT NULL,
		ID_REPEAT_TYPE NUMBER(38 , 0),
		REPEAT_PERIOD NUMBER(38 , 0),
		REPEAT_PERIOD_KIND NUMBER(38 , 0),
		DUE_DATE_PERIOD NUMBER(38 , 0),
		DUE_DATE_PERIOD_KIND NUMBER(38 , 0),
		NOTIFY_PERIOD NUMBER(38 , 0),
		NOTIFY_PERIOD_KIND NUMBER(38 , 0),
		START_DATE DATE,
		END_DATE DATE,
		CONFIRMATION_TYPE VARCHAR2(1024),
		DOCUMENTS_TYPE VARCHAR2(1024),
		SANCTION_TEXT VARCHAR2(1024),
		REPEAT_PERIOD_WEEKEND NUMBER(1 , 0) DEFAULT 0  NOT NULL,
		REPEAT_PERIOD_WEEKEND_EXCLUDE NUMBER(1 , 0) DEFAULT 0  NOT NULL,
		NOTIFY_AT_DAY NUMBER(1 , 0) DEFAULT 0  NOT NULL,
		NOTIFY_AFTER NUMBER(1 , 0) DEFAULT 0  NOT NULL,
		NOTIFY_LINK_MSG NUMBER(1 , 0) DEFAULT 0  NOT NULL
	);

CREATE UNIQUE INDEX KM_EVENT_CALENDAR_PK ON KM_EVENT_CALENDAR (ID_EVENT_CALENDAR ASC);

ALTER TABLE KM_EVENT_CALENDAR ADD CONSTRAINT KM_EVENT_CALENDAR_PK PRIMARY KEY (ID_EVENT_CALENDAR);

ALTER TABLE KM_EVENT_CALENDAR ADD CONSTRAINT KM_EVENT_CALENDAR_EVENT_CAL_FK FOREIGN KEY (ID_EVENT_PARENT)
	REFERENCES KM_EVENT_CALENDAR (ID_EVENT_CALENDAR)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;

ALTER TABLE KM_EVENT_CALENDAR ADD CONSTRAINT KM_EVENT_CAL_EVENTTYPE_FK FOREIGN KEY (ID_EVENT_TYPE)
	REFERENCES KM_EVENT_TYPE (ID_EVENT_TYPE)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;

ALTER TABLE KM_EVENT_CALENDAR ADD CONSTRAINT KM_EVENT_CAL_FK10 FOREIGN KEY (ID_REPEAT_TYPE)
	REFERENCES KM_REPEAT_TYPE (ID_REPEAT_TYPE)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;

ALTER TABLE KM_EVENT_CALENDAR ADD CONSTRAINT KM_EVENT_CAL_MDTASK_FK FOREIGN KEY (ID_MDTASK)
	REFERENCES MDTASK (ID_MDTASK)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;

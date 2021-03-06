--<ScriptOptions statementTerminator=";"/>

CREATE TABLE CD_EVENT_RESULT (
		ID_EVENT_RESULT NUMBER(38 , 0) NOT NULL,
		EVENT_RESULT VARCHAR2(1024) NOT NULL,
		COMMENT_MANDATORY NUMBER(1 , 0),
		PROCESS_CRF NUMBER(1 , 0),
		SANCTION NUMBER(1 , 0),
		ID_EVENT NUMBER(1 , 0) NOT NULL
	);

CREATE UNIQUE INDEX CD_EVENT_RESULT_PK ON CD_EVENT_RESULT (ID_EVENT_RESULT ASC);

ALTER TABLE CD_EVENT_RESULT ADD CONSTRAINT CD_EVENT_RESULT_PK PRIMARY KEY (ID_EVENT_RESULT);

ALTER TABLE CD_EVENT_RESULT ADD CONSTRAINT CD_EVENT_RESULT_FK01 FOREIGN KEY (ID_EVENT)
	REFERENCES KM_EVENT_STATUS (ID_EVENT_STATUS)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;


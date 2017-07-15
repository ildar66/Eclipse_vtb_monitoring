--<ScriptOptions statementTerminator=";"/>
select * from V_CPS_CREDIT_DEAL where rownum < 5

CREATE TABLE MDTASK (
		ID_MDTASK NUMBER(38 , 0) NOT NULL,
		ID_PUP_PROCESS NUMBER(38 , 0),
		ADDSUPPLY VARCHAR2(1024),
		ID_DEPARTMENT NUMBER(38 , 0),
		ID_USER NUMBER(38 , 0),
		MDTASK_NUMBER NUMBER(38 , 0),
		PRIORITY VARCHAR2(64),
		INITDEPARTMENT NUMBER(38 , 0),
		MANAGER VARCHAR2(1024),
		PLACE NUMBER(38 , 0),
		MDTASK_SUM NUMBER(38 , 5),
		CURRENCY CHAR(3),
		VALIDTO DATE,
		USEDATE DATE,
		PERIOD NUMBER(9 , 0),
		USEPERIOD NUMBER(9 , 0),
		ID_INDUSTRY CHAR(12),
		QUALITY_CATEGORY VARCHAR2(1024),
		QUALITY_CATEGORY_DESC VARCHAR2(1024),
		LOAN_CLASS VARCHAR2(1024),
		DISCHARGE VARCHAR2(1024),
		ID_PROCENT_ORDER NUMBER(38 , 0),
		ID_AUTHORIZEDBODY NUMBER(38 , 0),
		DESCRIPTION VARCHAR2(1024),
		QUESTION VARCHAR2(2048),
		PREAMBULO VARCHAR2(2048),
		ID_MEETING NUMBER(38 , 0),
		AGENDA_ORDER NUMBER(3 , 0),
		BOSS_COMMENT CLOB,
		SECRETARY_COMMENT CLOB,
		PARENTID NUMBER(38 , 0),
		ID_LIMIT_TYPE NUMBER(38 , 0),
		OPPORTUNITYID CHAR(12),
		TEMP_RESOLUTION CLOB,
		TEMP_MEETINGDATE null,
		TEMP_PLANMEETINGDATE null,
		ID_SDELKA_GROUP NUMBER(38 , 0),
		DELETED CHAR(1) DEFAULT 'N'  NOT NULL,
		ID_OPERATIONTYPE NUMBER(38 , 0),
		CRMID CHAR(12),
		CRMLIMITNAME VARCHAR2(256),
		CRMCODE VARCHAR2(32),
		CURRENCYLIST VARCHAR2(256),
		CRMINLIMIT CHAR(12),
		STATUSRETURN CHAR(12),
		STATUSRETURNTEXT VARCHAR2(256),
		STATUSRETURNDATE TIMESTAMP(11),
		EXCHANGE_RATE NUMBER(38 , 12) DEFAULT 1,
		IS_SUPPLY_EXIST CHAR(1) DEFAULT 'y',
		IS_3FACES CHAR(1) DEFAULT 'n',
		IS_FIXED CHAR(1) DEFAULT 'n',
		GUARANTEE_COND VARCHAR2(2000),
		EXTRA_SUM_INFO VARCHAR2(2000) DEFAULT null,
		REDISTRIB_RESIDUES CHAR(1) DEFAULT 'n',
		IS_RENEWABLE CHAR(1) DEFAULT null,
		IS_PROJECT_FIN CHAR(1) DEFAULT 'n',
		VALIDFROM DATE,
		USEDATEFROM DATE,
		MAY_BE_RENEWABLE CHAR(1) DEFAULT null,
		D_COND VARCHAR2(2000),
		CFACTOR NUMBER(38 , 5),
		TRANCE_COMMENT VARCHAR2(2000),
		CONTRACT VARCHAR2(2000) DEFAULT null,
		WARRANTY_ITEM VARCHAR2(2000) DEFAULT null,
		BENEFICIARY VARCHAR2(2000) DEFAULT null,
		PROPOSED_DT_SIGNING DATE,
		USEPERIODTYPE VARCHAR2(2000),
		PERIOD_COMMENT VARCHAR2(2000),
		LIMIT_ISSUE_SUM NUMBER(38 , 5),
		DEBT_LIMIT_SUM NUMBER(38 , 5),
		IS_LIMIT_SUM CHAR(1) DEFAULT 'n',
		IS_DEBT_SUM CHAR(1) DEFAULT 'n',
		IS_GUARANTEE CHAR(1) DEFAULT 'n',
		CC_CACHE_DATE TIMESTAMP(11),
		CC_CACHE_PROTOCOL VARCHAR2(100),
		CC_CACHE_STATUSID NUMBER(38 , 0),
		ID_QUESTION_TYPE NUMBER(38 , 0) DEFAULT 0,
		ID_RESOLUTION_STATUS NUMBER(38 , 0),
		MEETING_PROPOSED_DATE TIMESTAMP(11),
		ID_DISPLAY_STATUS NUMBER(38 , 0),
		ID_INSTANCE NUMBER(38 , 0),
		MEETING_PROPOSED_COMM_DEP_ID NUMBER(38 , 0),
		ADDITIONAL CHAR(1) DEFAULT 'n',
		IS_ABSENTEE_TYPE CHAR(1) DEFAULT 'n',
		ID_SUPERIOR NUMBER(38 , 0),
		ID_CLONE NUMBER(38 , 0),
		CLONE_DATE TIMESTAMP(11),
		SDO_DOC_PACKAGE_NUM VARCHAR2(24),
		CRM_QUEUE_ID CHAR(12),
		STATUSRETURNWHO NUMBER(38 , 0),
		ID_AUTHORIZED_PERSON NUMBER(38 , 0),
		STANDARD_PERIOD_VERSION NUMBER(22 , 0),
		WITH_SUBLIMIT CHAR(1) DEFAULT 'y',
		TURNOVER_PREMIUM NUMBER(22 , 0),
		TURNOVER NUMBER(38 , 5),
		RISKPREMIUM NUMBER(22 , 0),
		GENERALCONDITION VARCHAR2(2000),
		FIXRATE CHAR(1) DEFAULT 'n',
		FIXRATEDATE TIMESTAMP(11),
		RATE_DESC_DECISION VARCHAR2(2000),
		TRANCE_GRAPH CHAR(1),
		IND_RATE CHAR(12),
		EARLY_PAYMENT_PROHIBITION CHAR(1),
		EARLY_PAYMENT_PROH_PER NUMBER(22 , 0),
		RISKPREMIUM_CHANGE NUMBER(38 , 5),
		AMORTIZED_LOAN CHAR(1) DEFAULT 'n',
		INDCONDITION CHAR(1),
		RATE5 NUMBER(12 , 5),
		RATE6 NUMBER(12 , 5),
		RATE7 NUMBER(12 , 5),
		RATE8 NUMBER(12 , 5),
		RATE9 NUMBER(12 , 5),
		RATE10 NUMBER(12 , 5),
		EXCHANGEDATE TIMESTAMP(11),
		PREMIUMTYPE NUMBER(22 , 0),
		PREMIUMVALUE NUMBER(12 , 5),
		PREMIUMCURR CHAR(3),
		PREMIUMTEXT VARCHAR2(2000),
		ACREDETIV_SOURCE NUMBER(22 , 0),
		FUND_DOWN CHAR(12),
		CREDIT_DECISION_PROJECT VARCHAR2(128) DEFAULT null
	);

CREATE INDEX MDTASK_FK12_I ON MDTASK (STATUSRETURNWHO ASC);

CREATE INDEX MDTASK_LIMIT_TYPE_FK1_I ON MDTASK (ID_LIMIT_TYPE ASC);

CREATE INDEX MDTASK_MDTASK_FK1_I ON MDTASK (PARENTID ASC);

CREATE INDEX MDTASK_FK08_I ON MDTASK (ID_INSTANCE ASC);

CREATE UNIQUE INDEX MDTASK_PUPID_UNIQUE ON MDTASK (ID_PUP_PROCESS ASC);

CREATE UNIQUE INDEX MDTASK_PK ON MDTASK (ID_MDTASK ASC);

CREATE INDEX MDTASK_FK09_I ON MDTASK (MEETING_PROPOSED_COMM_DEP_ID ASC);

CREATE INDEX MDTASK_FK14_I ON MDTASK (PLACE ASC);

CREATE INDEX MDTASK_FK11_I ON MDTASK (ID_AUTHORIZEDBODY ASC);

CREATE INDEX MDTASK_FK10_I ON MDTASK (ID_SUPERIOR ASC);

CREATE UNIQUE INDEX MDTASK_CRMID_UNIQUE ON MDTASK (CRMID ASC);

CREATE INDEX MDTASK_FK01_I ON MDTASK (ID_MEETING ASC);

CREATE INDEX MDTASK_FK15_I ON MDTASK (INITDEPARTMENT ASC);

CREATE INDEX MDTASK_FK07_I ON MDTASK (ID_DISPLAY_STATUS ASC);

CREATE INDEX MDTASK_FK04_I ON MDTASK (ID_QUESTION_TYPE ASC);

CREATE INDEX MDTASK_FK05_I ON MDTASK (ID_RESOLUTION_STATUS ASC);

CREATE INDEX MDTASK_FK13_I ON MDTASK (STATUSRETURN ASC);

CREATE INDEX MDTASK_FK06_I ON MDTASK (ID_CLONE ASC);

ALTER TABLE MDTASK ADD CONSTRAINT MDTASK_PK PRIMARY KEY (ID_MDTASK);

ALTER TABLE MDTASK ADD CONSTRAINT MDTASK_MDTASK_FK1 FOREIGN KEY (PARENTID)
	REFERENCES MDTASK (ID_MDTASK)
	ON DELETE CASCADE
	ON UPDATE CASCADE;

ALTER TABLE MDTASK ADD CONSTRAINT MDTASK_FK06 FOREIGN KEY (ID_CLONE)
	REFERENCES MDTASK (ID_MDTASK)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;


--<ScriptOptions statementTerminator=";"/>

CREATE TABLE CPS_DEAL_CONDITION (
		ID_DEAL_CONDITION NUMBER(22 , 0) NOT NULL,
		ID_CONDITION NUMBER(22 , 0),
		ID_CONDITION_TYPE NUMBER(22 , 0) NOT NULL,
		PLANNED_COMPLETE_DATE DATE,
		IS_COMPLETED NUMBER(1 , 0) DEFAULT 0  NOT NULL,
		NAME VARCHAR2(4000),
		ID_CREDIT_DEAL NUMBER(38 , 0),
		ID_SYSTEM_MODULE NUMBER(38 , 0) DEFAULT 1,
		ID_CREDIT_DEAL_CONTRACTOR NUMBER(38 , 0),
		ID_PARENT_DEAL_CONDITION NUMBER(38 , 0)
	);
	
	-- вывод условий по данному контрольному мероприятию (плановому или уже фактическому)
		SELECT cond_t.id_type cond_t_id, cond_t.name cond_t_name, dc.id_deal_condition dc_id, dc.name dc_name
		FROM CPS_DEAL_CONDITION dc
		inner join condition_types cond_t on dc.id_condition_type = cond_t.id_type
			WHERE ID_CREDIT_DEAL = 23035 -- 'это как раз ID_MDTASK (mdtask_number = 202926)
				AND ID_SYSTEM_MODULE = 2
				AND ID_CONDITION_TYPE <> 1
				and id_condition in
				(select c.id_condition from r_condition_eventtype c
					where c.id_event_type = 5 -- подставить id мероприятия в классификаторе
				)
	
	select cond_t.id_type cond_t_id, cond_t.name cond_t_name, dc.id_deal_condition dc_id, dc.name dc_name 
		from cps_deal_condition dc
		inner join condition_types cond_t on dc.id_condition_type = cond_t.id_type
		where id_credit_deal = 23035 -- 'это как раз id_mdtask
		and id_system_module = 2
		and id_condition_type <> 1
		order by cond_t.name, dc.name

CREATE INDEX CPS_DEAL_COND_MDTASK_FK_I ON CPS_DEAL_CONDITION (ID_CREDIT_DEAL ASC);

CREATE UNIQUE INDEX PK_CPS_DEAL_CONDITION ON CPS_DEAL_CONDITION (ID_DEAL_CONDITION ASC);

CREATE INDEX CPS_DEAL_COND_COND_TYPE_FK_I ON CPS_DEAL_CONDITION (ID_CONDITION_TYPE ASC);

CREATE INDEX CPS_DEAL_COND_SYSMOD_FK_I ON CPS_DEAL_CONDITION (ID_SYSTEM_MODULE ASC);

CREATE INDEX CPS_DEAL_COND_COND_FK_I ON CPS_DEAL_CONDITION (ID_CONDITION ASC);

ALTER TABLE CPS_DEAL_CONDITION ADD CONSTRAINT PK_CPS_DEAL_CONDITION PRIMARY KEY (ID_DEAL_CONDITION);

ALTER TABLE CPS_DEAL_CONDITION ADD CONSTRAINT CPS_DEAL_CONDITION_PARENT_FK FOREIGN KEY (ID_PARENT_DEAL_CONDITION)
	REFERENCES CPS_DEAL_CONDITION (ID_DEAL_CONDITION)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;

ALTER TABLE CPS_DEAL_CONDITION ADD CONSTRAINT CPS_DEAL_COND_COND_FK FOREIGN KEY (ID_CONDITION)
	REFERENCES CONDITION (ID_CONDITION)
	ON DELETE SET NULL
	ON UPDATE CASCADE;

ALTER TABLE CPS_DEAL_CONDITION ADD CONSTRAINT CPS_DEAL_COND_MDTASK_FK FOREIGN KEY (ID_CREDIT_DEAL)
	REFERENCES MDTASK (ID_MDTASK)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;

ALTER TABLE CPS_DEAL_CONDITION ADD CONSTRAINT CPS_DEAL_COND_SYSMOD_FK FOREIGN KEY (ID_SYSTEM_MODULE)
	REFERENCES CD_SYSTEM_MODULE (ID_SYSTEM_MODULE)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;

ALTER TABLE CPS_DEAL_CONDITION ADD CONSTRAINT CPS_DEAL_COND_COND_TYPE_FK FOREIGN KEY (ID_CONDITION_TYPE)
	REFERENCES CONDITION_TYPES (ID_TYPE)
	ON DELETE SET NULL
	ON UPDATE CASCADE;


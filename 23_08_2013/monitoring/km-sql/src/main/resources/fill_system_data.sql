delete from KM_EVENT_INSTANCE_STATUS;
insert into KM_EVENT_INSTANCE_STATUS (id, name) values (1, 'Выявленные ФКР');
insert into KM_EVENT_INSTANCE_STATUS (id, name) values (2, 'Просроченные');
insert into KM_EVENT_INSTANCE_STATUS (id, name) values (3, 'Неисполненные');
insert into KM_EVENT_INSTANCE_STATUS (id, name) values (4, 'Исполненные');
commit;
delete from KM_EVENT_STATUS;
insert into KM_EVENT_STATUS (id_event_status, event_status) values (1, 'Ожидает обработки');
insert into KM_EVENT_STATUS (id_event_status, event_status) values (2, 'В работе');
insert into KM_EVENT_STATUS (id_event_status, event_status) values (3, 'Исполнено');
insert into KM_EVENT_STATUS (id_event_status, event_status) values (4, 'Отменено');
insert into KM_EVENT_STATUS (id_event_status, event_status) values (5, 'Приостановлено');
commit;
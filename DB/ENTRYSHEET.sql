create table ENTRYSHEET (
	ES_ID smallint,
	S_ID numeric(6) not null default(0),
	primary key(ES_ID),
	foreign key(S_ID)
		references SELLER(S_ID)
)
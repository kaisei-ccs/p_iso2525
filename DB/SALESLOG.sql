create table SALESLOG (
	T_ID smallint identity,
	T_DATE date not null,
	T_TIME time not null,
	TOTAL smallint not null,
	CHARGE smallint not null,
	CASHBACK smallint not null,
	primary key(T_ID)
	)
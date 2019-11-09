DROP TABLE USER IF EXISTS;
DROP TABLE USER_ID_UINDEX IF EXISTS;
DROP TABLE SNS IF EXISTS;

create table USER
(
	ID INTEGER default 1000 not null,
	NAME CHAR not null,
	ADDRESS CHAR,
	NICK_NAME CHAR,
	EMAIL CHAR,
	BIRTHDAY TIMESTAMP,
	ACTIVE BOOLEAN,
	CREATE_DATE TIMESTAMP,
	LEAVE_DATE TIMESTAMP,
	COLUMN_11 INTEGER,
	constraint USER_PK
		primary key (ID)
);

create unique index USER_ID_UINDEX
	on USER (ID);



create table SNS_TYPE
(
	SNS_KEY INTEGER not null
		unique,
	USER_ID INTEGER not null,
	constraint SNS_TYPE_USER_ID_FK
		foreign key (USER_ID) references USER
);

create table SNS
(
	SNS_KEY INTEGER not null,
	SNS_NAME CHAR not null,
	SNS_CID CHAR not null
		constraint SNS_SNS_CID_UINDEX
			unique,
	constraint SNS_SNS_TYPE_SNS_KEY_FK
		foreign key (SNS_KEY) references SNS_TYPE (SNS_KEY)
);





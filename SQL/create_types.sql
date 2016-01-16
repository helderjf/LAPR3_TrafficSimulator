create or replace type VARCHAR2_T is table of varchar2(255);

create or replace type INTEGER_T is table of integer;

create or replace type FLOAT_T is table of float;












create or replace type array_strings is table of VARCHAR2(255);

create or replace type array_values as VARRAY(200) of Numeric(19,0);

create or replace type SECTION as object(
    road numeric(19,0),
    origin numeric(19,0),
    destiny numeric(19,0),
    typology varchar2(255),
    direction varchar2(255),
    toll numeric(19,0),
    wind_direction numeric(19,0),
    wind_speed numeric(19,0)
    );

create or replace type array_sections as VARRAY(200) of SECTION;

create or replace TRAFFIC_PATTERN_TYPE AS OBJECT (
	PK_TRAFFIC_PATTERN integer,
	PK_SIMULATION integer,
	PK_B_NODE integer,
	PK_E_NODE integer,
	PK_VEHICLE integer,
	ARRIVAL_RATE float
	);
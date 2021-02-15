/* $PostgreSQL: pgsql/contrib/tablefunc/tablefunc.sql.in,v 1.12 2007/11/13 04:24:29 momjian Exp $ */

-- Adjust this setting to control where the objects get created.
SET search_path = public, clinlims;
ALTER ROLE clinlims SET search_path = public, clinlims;

CREATE OR REPLACE FUNCTION normal_rand(int4, float8, float8)
RETURNS setof float8
AS '$libdir/tablefunc','normal_rand'
LANGUAGE C VOLATILE STRICT;

-- the generic crosstab function:
CREATE OR REPLACE FUNCTION crosstab(text)
RETURNS setof record
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

-- examples of building custom type-specific crosstab functions:
CREATE TYPE tablefunc_crosstab_2 AS
(
	row_name TEXT,
	category_1 TEXT,
	category_2 TEXT
);

CREATE TYPE tablefunc_crosstab_3 AS
(
	row_name TEXT,
	category_1 TEXT,
	category_2 TEXT,
	category_3 TEXT
);

CREATE TYPE tablefunc_crosstab_4 AS
(
	row_name TEXT,
	category_1 TEXT,
	category_2 TEXT,
	category_3 TEXT,
	category_4 TEXT
);

CREATE OR REPLACE FUNCTION crosstab2(text)
RETURNS setof tablefunc_crosstab_2
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION crosstab3(text)
RETURNS setof tablefunc_crosstab_3
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION crosstab4(text)
RETURNS setof tablefunc_crosstab_4
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

-- obsolete:
CREATE OR REPLACE FUNCTION crosstab(text,int)
RETURNS setof record
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION crosstab(text,text)
RETURNS setof record
AS '$libdir/tablefunc','crosstab_hash'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,int,text)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,int)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text'
LANGUAGE C STABLE STRICT;

-- These 2 take the name of a field to ORDER BY as 4th arg (for sorting siblings)

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,text,int,text)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text_serial'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,text,int)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text_serial'
LANGUAGE C STABLE STRICT;

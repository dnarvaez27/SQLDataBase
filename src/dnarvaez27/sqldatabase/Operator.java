package dnarvaez27.sqldatabase;

public enum Operator
{
	NOT( "NOT" ),
	EQUAL( "%s = %s" ),
	NOT_EQUAL( "%s != %s" ),
	LESS( "%s < %s" ),
	LESS_EQUAL( "%s <= %s" ),
	GREAT( "%s > %s" ),
	GREAT_EQUAL( "%s >= %s" ),
	IS_NULL( "%s IS NULL%s" ),
	IS_NOT_NULL( "%s IS NOT NULL" ),
	BETWEEN( "BETWEEN %s AND %s" ),
	NOT_BETWEEN( "NOT BETWEEN %s AND %s" );
	
	private String op;
	
	Operator( String op )
	{
		this.op = op;
	}
	
	public String getOp( )
	{
		return op;
	}
}
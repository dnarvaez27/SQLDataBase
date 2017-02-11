package dnarvaez27.sqldatabase;

public class Conditional
{
	public static class Assignment extends Conditional
	{
		public Assignment( String firstArg, Object secondArg )
		{
			super( firstArg, secondArg, Operator.EQUAL );
			this.firstArg = firstArg;
		}
	}
	
	protected Object firstArg;
	
	protected Operator op;
	
	protected Object secondArg;
	
	public Conditional( Conditional c1, Conditional c2, Operator op )
	{
		firstArg = c1.toString( );
		secondArg = c2.toString( );
		this.op = op;
	}
	
	public Conditional( Conditional c1, Operator op )
	{
		firstArg = c1.toString( );
		this.op = op;
	}
	
	public Conditional( Object firstArg, Object secondArg, Operator op )
	{
		// if( firstArg instanceof String )
		// {
		// firstArg = SQLDataBase.formatString( String.valueOf( firstArg ) );
		// }XXX
		if( secondArg instanceof String )
		{
			secondArg = SQLDataBase.formatString( String.valueOf( secondArg ) );
		}
		this.firstArg = firstArg;
		this.secondArg = secondArg;
		this.op = op;
	}
	
	@Override
	public String toString( )
	{
		return String.format( op.getOp( ), firstArg, secondArg );
	}
}
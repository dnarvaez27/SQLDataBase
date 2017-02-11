package dnarvaez27.sqldatabase;

public class KeyWord
{
	public enum AvailableKeyWord
	{
		ORDER_BY_ASC( "ORDER BY %s ASC" ),
		ORDER_BY_DESC( "ORDER BY %s DESC" );
		
		private String string;
		
		private AvailableKeyWord( String string )
		{
			this.string = string;
		}
		
		public String getString( )
		{
			return string;
		}
	}
	
	private AvailableKeyWord keyWord;
	
	private Object value;
	
	public KeyWord( AvailableKeyWord keyWord, Object value )
	{
		super( );
		this.keyWord = keyWord;
		this.value = value;
	}
	
	@Override
	public String toString( )
	{
		return String.format( keyWord.getString( ), value );
	}
}
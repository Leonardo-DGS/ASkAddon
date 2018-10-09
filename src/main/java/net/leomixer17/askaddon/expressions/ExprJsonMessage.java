package net.leomixer17.askaddon.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.leomixer17.askaddon.utils.JSONMessage;

public class ExprJsonMessage extends SimplePropertyExpression<String, JSONMessage> {
	
	protected String getPropertyName()
	{
		return "json equivalent"; //JSON equivalent
	}
	
	public JSONMessage convert(String s)
	{
		return new JSONMessage(s);
	}
	
	public Class<? extends JSONMessage> getReturnType()
	{
		return JSONMessage.class;
	}
	
}
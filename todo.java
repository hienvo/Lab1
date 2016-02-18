package model;

public class todo {
	private String id;
	private String todomsg;
	private String timestamp;
	
	public void setTimestamp(String timestamp)
	{
	this.timestamp = timestamp;
	}
	
	public String getTimestamp()
	{
		return timestamp;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	public String getId()
	{
		return id;
	}
	public void setTodomsg(String todomsg)
	{
		this.todomsg = todomsg;
	}
	public String getTodomsg()
	{
		return todomsg;
	}
	
	public String toString(){
		return "ID:"+id;
	}
	
}

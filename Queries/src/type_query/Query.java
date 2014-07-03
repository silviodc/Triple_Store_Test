package type_query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Query {
 
	private String query="";
	private String endPoint;
	private String type;
	private String number;
	private double initial_time;
	private double final_time;
	private double time;
	private String result;
	
	public Query(){}
	
	public Query (String query,String endPoint, String number,String type) throws UnsupportedEncodingException{
		setQuery(query);
		setNumber(number);
		setEndPoint(endPoint);
		setType(type);
	}
	
	public String getQuery()  {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	private double getInitial_time() {
		return initial_time;
	}
	private void setInitial_time(double initial_time) {
		this.initial_time = initial_time;
	}
	private double getFinal_time() {
		return final_time;
	}
	private void setFinal_time(double final_time) {
		this.final_time = final_time;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void start(){
		setInitial_time( System.currentTimeMillis());
	}
	
	public void stop(){
		setFinal_time( System.currentTimeMillis());
		setTime();
	}
	
	private void setTime(){
		time = getFinal_time() - getInitial_time();
	}
	
	public double getTime(){
		return time;
	}
	public void dontRun(){
		setInitial_time(Double.NaN);
		setFinal_time(Double.NaN);
	}
	
	@SuppressWarnings("unused")
	private String buildQuery(String temp) throws UnsupportedEncodingException{
		temp = temp.replaceAll("\\n", " ").replaceAll("\\s", " ");
		return URLEncoder.encode(temp,"UTF-8");
	}
	
}

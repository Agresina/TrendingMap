package trendingmap.server.resource;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import trendingmap.shared.domain.Trend;



public class TrendResource {

	private String appid="id";
	private String uri = "https://api.twitter.com/1.1/trends/place.json?id=";
	
	
	public Collection<Trend> getAll(String zona) {
		ClientResource cr = null;
		Trend [] trends = null;
		String woeid = "";
		woeid = resuelveWoeid(zona);
		uri = uri + woeid;
		try {
			cr = new ClientResource(uri);
			trends = cr.get(Trend[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the collections of trends: " + cr.getResponse().getStatus());
		}
		
		return Arrays.asList(trends);

	}
	
	private String resuelveWoeid(String zona) {
		String uri = "http://where.yahooapis.com/v1/places.q('" + zona + "')?appid=" + appid;
		ClientResource cr = null;
		String woeid = null;
		try {
			cr = new ClientResource(uri);
			woeid = cr.get(String.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the woeid: " + cr.getResponse().getStatus());
		}
		
		return woeid;
	}
}

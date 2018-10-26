import java.util.*;

public class MapBox {
  
	public List<Location> locations;
	Set<String> names;
	
	double min_x = 0.0;
	double max_x = 0.0;
	double min_y = 0.0;
	double max_y = 0.0;
	
	public MapBox() {
		this.locations = new ArrayList<Location>();
		this.names = new HashSet<String>();
		
	}
	public double Westmost() {
		return min_x;
	}
	
	public double Eastmost() {
		return max_x;
		
	}
	public double Southmost() {
		return min_y;
		
	}
	public double Northmost()  {
		return max_y;
		
	}

	//public boolean duplicate(Location loc) {
		//if (names.contains(loc.name)) {
		//	return true;
		//} else 
		//	return false;
		
	//}
	
	public void update(Location loc) {
		//compare min and max
		if (locations.isEmpty()) {
		min_x = loc.longitude;
		min_y = loc.latitude;
		max_x = loc.longitude;
		max_y = loc.latitude;
		} else {
			
			if (loc.longitude < min_x) {
			min_x = loc.longitude;
			} 
			
			if (loc.longitude > max_x) {
				max_x = loc.longitude;
			} 
			
			if (loc.latitude < min_y) {
				min_y = loc.latitude;
			}
			
			if (loc.latitude>max_y) {
				max_y = loc.latitude;
			}
			
			
		}
		
		
	}
	
	public boolean recordLocation(Location loc) {
		// uses duplicate to see if there are duplicate names
		if (names.contains(loc.name)) {
			return false;
		}else {
			update(loc);
			names.add(loc.name);
			locations.add(loc);
			return true;
		}
		
		
	}
	
}
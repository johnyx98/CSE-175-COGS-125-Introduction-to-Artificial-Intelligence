//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method. The provided "good"
// heuristic function is admissible.
//
// Johny Xiong -- 10/10/2018
//


// IMPORT ANY PACKAGES THAT YOU NEED.
 import java.util.*;


public class GoodHeuristic extends Heuristic {

	
        // YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
        // ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.

public GoodHeuristic(Location destination) {
		
		
		super(destination);
		
		this.destination = destination;
		
		
	}
	
	// heuristicValue -- Return the appropriate heuristic values for the
	// given search tree node. Note that the given Node should not be
	// modified within the body of this function.
	public double heuristicValue(Node thisNode) {
		double hVal = 0.0;
		double dx, dy, distance;
		double velocity;
		
		dy = (this.destination.longitude -thisNode.loc.longitude);
		dx=(this.destination.latitude- thisNode.loc.latitude);
		
		distance = (Math.sqrt(dx*dx+dy*dy));
		velocity = 4.8;
		
		hVal = distance/velocity;
		
		
		
		//use max velocity from roads.
		
		

		return (hVal);
	}

}

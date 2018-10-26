//
// Frontier
//
// This class implements either a FIFO list (a queue) or a LIFO list (a stack)
// of Node objects, with the kind of list determined by which method is
// used to insert new Node objects into the list.  In either case, the
// "removeTop" method extracts and returns the next node to be ejected from
// the list.  If the "addToBottom" method is used to insert nodes, then the 
// Frontier object will act as a queue.  If the "addToTop" method is used to
// insert nodes, then the Frontier object will act as a stack.  Both of these
// insertion methods are overloaded to accept either individual Node
// objects or lists of multiple Node objects.  This class is intended to
// to be used to implement the frontier (i.e., the "fringe" or "open list")
// of nodes in a search tree.
//
// David Noelle -- Created Sun Feb 11 18:39:40 PST 2007
//                 Modified Wed Sep 15 00:09:35 PDT 2010
//                   (Implemented overloaded "contains" function.)
//                 Modified Tue Sep 11 16:03:41 PDT 2018
//                   (Improved efficiency of "contains" function.)
//


import java.util.*;


public class Frontier {
    List<Node> fringe;
    Set<String> fringeStateNames;

    // Default constructor ...
	public Frontier() {
		fringe = new LinkedList<Node>();
		fringeStateNames = new HashSet<String>();
	}

    // isEmpty -- Return true if and only if there are currently no nodes in 
    // the frontier.
	public boolean isEmpty() {
		return (fringe.isEmpty());
	}

    // removeTop -- Return the Node object at the top of the frontier
    // list.  Also, remove this node from the frontier.  Return null if the
    // frontier is empty.
	public Node removeTop() {
		if (fringe.isEmpty()) {
			return (null);
		} else {
			Node top = fringe.get(0);
			fringe.remove(0);
			fringeStateNames.remove(top.loc.name);
			return (top);
		}
	}

    // addToTop -- Add the given Node object to the top of the frontier
    // list.
	public void addToTop(Node leaf) {
		fringe.add(0, leaf);
		fringeStateNames.add(leaf.loc.name);
	}

    // addToTop -- Add the given list of Node objects to the top of the 
    // frontier list.
	public void addToTop(List<Node> leaves) {
		for (Node leaf : leaves) {
			addToTop(leaf);
		}
	}

    // addToBottom -- Add the given Node object to the bottom of the 
    // frontier list.
	public void addToBottom(Node leaf) {
		fringe.add(leaf);
		fringeStateNames.add(leaf.loc.name);
	}

    // addToBottom -- Add the given list of Node objects to the bottom of
    // the frontier list.
	public void addToBottom(List<Node> leaves) {
		for (Node leaf : leaves) {
			addToBottom(leaf);
		}
	}

    // contains -- Return true if and only if the frontier contains a
    // Node with the given Location name.
	public boolean contains(String name) {
		// This is an efficient way to check the frontier for a node
		// with a given state, by using a HashSet.
		if (fringeStateNames.contains(name)) {
			return (true);
		} else {
			// The location was not found in the fringe ...
			return (false);
		}
	}

    // contains -- Return true if and only if the frontier contains a
    // Node with the given Location object as its state.
	public boolean contains(Location loc) {
		return (contains(loc.name));
	}

    // contains -- Return true if and only if the frontier contains an
    // equivalent Node (with regard to the Location) to the one provided
    // as an argument.
	public boolean contains(Node leaf) {
		return (contains(leaf.loc));
	}

}


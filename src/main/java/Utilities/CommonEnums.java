/**
 * 
 */
package Utilities;

/**
 * @author Ankur Rana
 *
 */
public class CommonEnums {

	public static enum TabName{
		Flights,Hotels,Activities,Trains,Flight_Deals{
			public String toString(){
				return "Flight_Deals";
			}
		}, Mobile,Manage_Trips{
			public String toString(){
				return "Manage trips";
			}
		}
	}
}

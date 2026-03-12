import java.util.*;

public class RideNow {

static Map<String,double[]> locations=new HashMap<>();

public static void main(String[] args){

Scanner sc=new Scanner(System.in);

/* Locations */
locations.put("Hitech City",new double[]{17.4435,78.3772});
locations.put("Secunderabad",new double[]{17.4399,78.4983});
locations.put("Miyapur",new double[]{17.4969,78.3560});
locations.put("Kukatpally",new double[]{17.4849,78.4138});
locations.put("Ameerpet",new double[]{17.4374,78.4482});
locations.put("Gachibowli",new double[]{17.4401,78.3489});
locations.put("Kondapur",new double[]{17.4698,78.3621});
locations.put("Madhapur",new double[]{17.4483,78.3915});
locations.put("LB Nagar",new double[]{17.3457,78.5522});
locations.put("Begumpet",new double[]{17.4440,78.4626});
locations.put("Jubilee Hills",new double[]{17.4326,78.4071});
locations.put("Banjara Hills",new double[]{17.4126,78.4482});
locations.put("Uppal",new double[]{17.4058,78.5591});
locations.put("Charminar",new double[]{17.3616,78.4747});
locations.put("Mehdipatnam",new double[]{17.3924,78.4392});
locations.put("Shamshabad",new double[]{17.2403,78.4294});
locations.put("Kompally",new double[]{17.5440,78.4870});
locations.put("Lingampally",new double[]{17.4933,78.3158});
locations.put("Abids",new double[]{17.3891,78.4760});
locations.put("Koti",new double[]{17.3850,78.4867});

/* Show locations */
System.out.println("Available Locations:");
for(String l:locations.keySet()) System.out.println("- "+l);

/* Inputs */
System.out.print("\nPickup: ");
String pickup=sc.nextLine();

System.out.print("Drop: ");
String drop=sc.nextLine();

System.out.print("Vehicle (car/bike): ");
String vehicle=sc.nextLine();

if(!locations.containsKey(pickup)||!locations.containsKey(drop)){
System.out.println("Invalid location");
return;
}

/* Distance */
double[] s=locations.get(pickup);
double[] e=locations.get(drop);
double distance=haversine(s[0],s[1],e[0],e[1]);

/* Fare */
double base=vehicle.equalsIgnoreCase("car")?50:30;
double perkm=vehicle.equalsIgnoreCase("car")?10:6;
double fare=base+distance*perkm;

/* Driver + OTP */
String[] drivers={"Raj","Amit","Suresh","Vikram"};
Random r=new Random();
String driver=drivers[r.nextInt(drivers.length)];
int otp=1000+r.nextInt(9000);

/* Ride Details */
System.out.println("\nDriver Assigned: "+driver);
System.out.println("Distance: "+String.format("%.2f",distance)+" km");
System.out.println("Estimated Fare: ₹"+String.format("%.2f",fare));
System.out.println("OTP: "+otp);

/* Cancellation */
System.out.print("\nCancel ride? (yes/no): ");
String cancel=sc.nextLine();

if(cancel.equalsIgnoreCase("yes")){
System.out.println("❌ Ride Cancelled");
return;
}

/* OTP */
System.out.print("Enter OTP: ");
int entered=sc.nextInt();

if(entered!=otp){
System.out.println("Wrong OTP");
return;
}

/* Payment */
System.out.print("Tip: ");
double tip=sc.nextDouble();
sc.nextLine();

System.out.print("Payment Method (Cash/GPay/PhonePe/Paytm): ");
String payment=sc.nextLine();

double finalFare=fare+tip;

System.out.println("\nPayment: "+payment);
System.out.println("Final Fare: ₹"+String.format("%.2f",finalFare));

/* Rating */
System.out.print("Rate Driver (1-5): ");
int rating=sc.nextInt();
sc.nextLine();

System.out.println("⭐ Rating: "+rating+"/5");

/* Complaint */
System.out.print("Complaint (optional): ");
String complaint=sc.nextLine();

if(!complaint.isEmpty())
System.out.println("Complaint Recorded: "+complaint);

System.out.println("\n✅ Ride Completed. Thank you!");
}

/* Haversine Formula */
static double haversine(double lat1,double lon1,double lat2,double lon2){

double R=6371;

double dLat=Math.toRadians(lat2-lat1);
double dLon=Math.toRadians(lon2-lon1);

double a=Math.sin(dLat/2)*Math.sin(dLat/2)+
Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*
Math.sin(dLon/2)*Math.sin(dLon/2);

double c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

return R*c;
}
}
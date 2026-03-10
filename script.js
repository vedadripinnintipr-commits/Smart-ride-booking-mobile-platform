// Global Variables

let totalFare = 0;
let otp = Math.floor(1000 + Math.random() * 9000);

// Hyderabad Locations with Coordinates

const locations = {
"Hitech City":[17.4435,78.3772],
"Secunderabad":[17.4399,78.4983],
"Bachupally":[17.5449,78.3841],
"Miyapur":[17.4969,78.3560],
"Kukatpally":[17.4849,78.4138],
"Ameerpet":[17.4374,78.4482],
"Gachibowli":[17.4401,78.3489],
"Kondapur":[17.4698,78.3621],
"Madhapur":[17.4483,78.3915],
"LB Nagar":[17.3457,78.5522],
"Begumpet":[17.4440,78.4626],
"Jubilee Hills":[17.4326,78.4071],
"Banjara Hills":[17.4126,78.4482],
"Uppal":[17.4058,78.5591],
"Dilsukhnagar":[17.3688,78.5247]
};

// Scroll to Booking Section

function scrollToBooking(){
document.getElementById("bookingSection").scrollIntoView({
behavior:"smooth"
});
}

// Haversine Formula to Calculate Distance

function haversine(lat1, lon1, lat2, lon2){

let R = 6371;

let dLat = (lat2 - lat1) * Math.PI / 180;
let dLon = (lon2 - lon1) * Math.PI / 180;

let a =
Math.sin(dLat/2) * Math.sin(dLat/2) +
Math.cos(lat1 * Math.PI/180) *
Math.cos(lat2 * Math.PI/180) *
Math.sin(dLon/2) * Math.sin(dLon/2);

let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

return R * c;
}

// Calculate Fare

function calculateFare(){

let pickup = document.getElementById("pickup").value;
let drop = document.getElementById("drop").value;
let vehicle = document.getElementById("vehicle").value;

if(!locations[pickup] || !locations[drop]){
document.getElementById("result").innerHTML =
"❌ Select valid Hyderabad locations";
return;
}

let start = locations[pickup];
let end = locations[drop];

let distance = haversine(start[0], start[1], end[0], end[1]);

let baseFare = (vehicle === "car") ? 50 : 30;
let perKm = (vehicle === "car") ? 10 : 6;

totalFare = baseFare + (distance * perKm);

let drivers = ["Raj","Amit","Suresh","Vikram"];
let driver = drivers[Math.floor(Math.random()*drivers.length)];

document.getElementById("result").innerHTML =
"🚗 Driver Assigned: " + driver +
"<br>📏 Distance: " + distance.toFixed(2) + " km" +
"<br>💰 Fare: ₹" + totalFare.toFixed(2) +
"<br>🔐 OTP: " + otp;

// Show Hidden Elements

document.getElementById("driverPhoto").style.display = "block";
document.getElementById("otpBox").style.display = "inline-block";
document.getElementById("tip").style.display = "inline-block";
document.getElementById("payment").style.display = "inline-block";
document.getElementById("submitTip").style.display = "inline-block";
document.getElementById("ratingBox").style.display = "block";
document.getElementById("complaint").style.display = "inline-block";
document.getElementById("submitComplaint").style.display = "inline-block";
document.getElementById("cancelRide").style.display = "inline-block";

startTimer();
}

// Ride Arrival Timer

function startTimer(){

let t = 10;

let timer = setInterval(function(){

document.getElementById("timer").innerHTML =
"⏱️ Ride arrives in " + t + " sec";

t--;

if(t < 0){
clearInterval(timer);
document.getElementById("timer").innerHTML =
"✅ Driver Arrived";
}

},1000);
}

// Payment and Tip

function submitTip(){

let entered = document.getElementById("otpBox").value;

let tip = parseFloat(document.getElementById("tip").value) || 0;

let payment = document.getElementById("payment").value;

if(entered != otp){
alert("Wrong OTP");
return;
}

document.getElementById("extraResult").innerHTML =
"💳 Payment: " + payment +
"<br>💰 Tip: ₹" + tip +
"<br>💵 Final Fare: ₹" + (totalFare + tip).toFixed(2);
}

// Rating System

function rate(n){

let stars = document.querySelectorAll(".star");

stars.forEach(function(s,i){

if(i < n)
s.classList.add("selected");
else
s.classList.remove("selected");

});

document.getElementById("extraResult").innerHTML +=
"<br>⭐ Rating: " + n + "/5";
}

// Complaint Submission

function submitComplaint(){

let c = document.getElementById("complaint").value;

document.getElementById("extraResult").innerHTML +=
"<br>📝 Complaint: " + c;
}

// Cancel Ride

function cancelRide(){

document.getElementById("extraResult").innerHTML +=
"<br>❌ Ride Cancelled";
}
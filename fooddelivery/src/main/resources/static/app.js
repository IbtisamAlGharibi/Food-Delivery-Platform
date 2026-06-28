const API_BASE="http://localhost:8080/api";


let selectedRestaurant=null;
let cart=[];
let currentOrder=null;



function show(id){

    document.querySelectorAll("section")
        .forEach(s=>s.classList.add("hidden"));

    document.getElementById(id)
        .classList.remove("hidden");

}



function loading(v){

    document.getElementById("loading")
        .style.display=v?"block":"none";

}



function error(msg){

    document.getElementById("error")
        .innerHTML=msg;

}



async function api(url,opt={}){


    try{

        loading(true);


        let res =
            await fetch(API_BASE+url,opt);


        if(!res.ok)
            throw Error(await res.text());


        return await res.json();


    }

    catch(e){

        error(e.message);

    }

    finally{

        loading(false);

    }

}





/*
=========================
 RESTAURANTS
=========================
*/



async function loadRestaurants(){



    let restaurants =
        await api("/restaurants");



    let search =
        document.getElementById("search").value;


    let cuisine =
        document.getElementById("cuisine").value;



    let box =
        document.getElementById("restaurantList");


    box.innerHTML="";



    restaurants

        .filter(r=>
            r.name
                .toLowerCase()
                .includes(search.toLowerCase())
        )

        .filter(r=>
            !cuisine ||
            r.cuisineType==cuisine
        )


        .forEach(r=>{


            box.innerHTML+=

                `

<div class="card">


<h3>${r.name}</h3>

<p>
${r.cuisineType}
</p>


<p>
Delivery:
${r.deliveryFee}
</p>


<button onclick="openRestaurant(${r.restaurantId})">

View Menu

</button>


<button onclick="reviews(${r.restaurantId})">

Reviews

</button>


</div>

`;

        });


}




async function openRestaurant(id){


    selectedRestaurant=id;


    show("menu");



    let menu =
        await api(
            `/restaurants/${id}/menu`
        );



    let combos =
        await api(
            `/restaurants/${id}/combos`
        );



    let box =
        document.getElementById("menuItems");

    box.innerHTML="";



    menu.forEach(m=>{


        box.innerHTML+=`

<div class="card">

<h3>${m.name}</h3>

<p>
${m.price}
</p>


<button onclick="addCart(
${m.itemCode},
'${m.name}',
${m.price}
)">

Add

</button>

</div>

`;

    });




    combos.forEach(c=>{


        box.innerHTML+=`

<div class="card">


<h3>
Combo:
${c.comboName}
</h3>


<p>
${c.totalPrice}
</p>



</div>

`;

    });


}







/*
=========================
 CART / ORDER
=========================
*/


function addCart(id,name,price){


    cart.push({

        id,
        name,
        price,
        quantity:1

    });


    calculateTotal();

}



function calculateTotal(){


    let total=0;


    cart.forEach(i=>{

        total+=i.price*i.quantity;

    });


    document.getElementById("subtotal")
        .innerHTML=total;

}




async function placeOrder(){



    let order =
        await api(

            "/orders/create",

            {

                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },


                body:JSON.stringify({

                    customerId:1,

                    restaurantId:selectedRestaurant,

                    items:cart

                })


            });



    currentOrder =
        order.orderId;



    for(let item of cart){


        await api(

            `/orders/${currentOrder}/items/${item.id}?quantity=${item.quantity}`,

            {
                method:"POST"
            }

        );

    }



    alert("Order placed");

    show("tracking");


}







/*
=========================
 DELIVERY TRACKING
=========================
*/



async function pollStatus(){


    if(!currentOrder)
        return;



    let order =
        await api(
            `/orders/${currentOrder}`
        );



    let steps=[

        "PENDING",
        "CONFIRMED",
        "PREPARING",
        "READY",
        "DELIVERED"

    ];



    document
        .querySelectorAll("#progress div")
        .forEach((x,i)=>{


            x.classList.toggle(
                "active",
                steps.indexOf(order.status)>=i
            );


        });



    setTimeout(
        pollStatus,
        5000
    );


}






/*
=========================
 PAYMENTS
=========================
*/



async function pay(orderId,method){



    return await api(

        `/payments/${orderId}?method=${method}`,

        {

            method:"POST"

        }

    );


}




async function completePayment(paymentId){


    return await api(

        `/payments/${paymentId}/complete`,

        {

            method:"PUT"

        }

    );


}








/*
=========================
 REVIEWS
=========================
*/



async function leaveRestaurantReview(){



    let rating =
        prompt("Rating 1-5");


    let comment =
        prompt("Comment");



    await api(

        `/reviews/restaurant?customerId=1&restaurantId=${selectedRestaurant}&rating=${rating}&comment=${comment}`,

        {

            method:"POST"

        }

    );


    alert("Review saved");


}




async function reviews(id){


    let data =
        await api(

            `/reviews/restaurant/${id}`

        );



    console.log(data);


    alert(
        JSON.stringify(data)
    );


}




async function restaurantRating(id){


    let avg =
        await api(

            `/reviews/restaurant/${id}/average`

        );


    alert(
        "Rating "+avg
    );


}




/*
 DRIVER REVIEWS
*/



async function leaveDriverReview(driverId){



    let rating =
        prompt("Rating");


    let comment =
        prompt("Comment");



    await api(

        `/reviews/driver?customerId=1&driverId=${driverId}&rating=${rating}&comment=${comment}`,

        {

            method:"POST"

        }

    );


}





/*
=========================
 RESTAURANT ADMIN
=========================
*/


async function toggleRestaurant(id,status){


    return api(

        `/restaurants/${id}/accepting?status=${status}`,

        {

            method:"PUT"

        }

    );


}



async function updateDeliveryFee(id,fee){


    return api(

        `/restaurants/${id}/fee?fee=${fee}`,

        {

            method:"PUT"

        }

    );


}





async function topSellingItems(id){


    return api(

        `/restaurants/${id}/top-selling`

    );


}



async function restaurantRevenue(id){


    return api(

        `/restaurants/${id}/revenue`

    );


}





/*
=========================
 DRIVER
=========================
*/


async function driverLocation(
    driverId,
    lat,
    lng
){


    return api(

        `/drivers/${driverId}/location?lat=${lat}&lng=${lng}`,

        {

            method:"PUT"

        }

    );


}



async function nearbyDrivers(
    lat,
    lng,
    radius
){


    return api(

        `/drivers/nearby?lat=${lat}&lng=${lng}&radiusKm=${radius}`

    );


}




/*
=========================
 DASHBOARD
=========================
*/


async function loadDashboard(){



    let customers =
        await api(
            "/customers/top-loyal"
        );



    let drivers =
        await api(
            "/deliveries/leaderboard"
        );



    let revenue =
        await api(
            "/reports/revenue"
        );



    document.getElementById("customers")
        .innerHTML =
        JSON.stringify(customers);



    document.getElementById("drivers")
        .innerHTML =
        JSON.stringify(drivers);



    document.getElementById("summary")
        .innerHTML =
        JSON.stringify(revenue);



}
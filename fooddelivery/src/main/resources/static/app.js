// =====================================================
// CONFIGURATION
// =====================================================

const API_BASE = "http://localhost:8080/api";

let selectedRestaurant = null;
let currentOrder = null;
let cart = [];

// =====================================================
// COMMON FUNCTIONS
// =====================================================

function showSection(id) {

    document.querySelectorAll("main section")
        .forEach(s => s.classList.add("hidden"));

    document.getElementById(id)
        .classList.remove("hidden");

}

function showLoading(show) {

    document.getElementById("loading").style.display =
        show ? "block" : "none";

}

function showError(message) {

    document.getElementById("error").innerHTML =
        message;

}

async function api(url, options = {}) {

    try {

        showLoading(true);

        showError("");

        const response =
            await fetch(API_BASE + url, options);

        if (!response.ok) {

            throw new Error(await response.text());

        }

        if (response.status === 204)
            return null;

        return await response.json();

    } catch (e) {

        showError(e.message);

        console.error(e);

        return null;

    } finally {

        showLoading(false);

    }

}

// =====================================================
// RESTAURANTS
// =====================================================

async function loadRestaurants() {

    const restaurants =
        await api("/restaurants");

    if (!restaurants)
        return;

    const keyword =
        document.getElementById("search").value.toLowerCase();

    const cuisine =
        document.getElementById("cuisine").value;

    const container =
        document.getElementById("restaurantList");

    container.innerHTML = "";

    restaurants

        .filter(r =>
            r.name.toLowerCase().includes(keyword)
        )

        .filter(r =>
            cuisine === "" ||
            r.cuisineType === cuisine
        )

        .forEach(async restaurant => {

            let rating = "-";

            try {

                rating = await api(
                    `/reviews/restaurant/${restaurant.restaurantId}/average`
                );

            } catch (e) {
            }

            container.innerHTML += `

<div class="card">

<h3>${restaurant.name}</h3>

<p><strong>Cuisine:</strong> ${restaurant.cuisineType}</p>

<p><strong>Delivery Fee:</strong> ${restaurant.deliveryFee}</p>

<p><strong>Rating:</strong> ⭐ ${rating}</p>

<button onclick="openRestaurant(${restaurant.restaurantId})">

View Menu

</button>

<button onclick="viewReviews(${restaurant.restaurantId})">

Reviews

</button>

</div>

`;

        });

}

// =====================================================
// MENU
// =====================================================

async function openRestaurant(id) {

    selectedRestaurant = id;

    showSection("menuSection");

    const menu =
        await api(`/restaurants/${id}/menu`);

    const combos =
        await api(`/restaurants/${id}/combos`);

    const menuContainer =
        document.getElementById("menuItems");

    const comboContainer =
        document.getElementById("comboMeals");

    menuContainer.innerHTML = "";

    comboContainer.innerHTML = "";

    if (menu) {

        menu.forEach(item => {

            menuContainer.innerHTML += `

<div class="card">

<h3>${item.name}</h3>

<p>Price : ${item.price}</p>

<p>Calories : ${item.calories}</p>

<button onclick="addToCart(

${item.itemCode},

'${item.name}',

${item.price}

)">

Add To Cart

</button>

</div>

`;

        });

    }

    if (combos) {

        combos.forEach(combo => {

            comboContainer.innerHTML += `

<div class="card">

<h3>${combo.comboName}</h3>

<p>Total Price : ${combo.totalPrice}</p>

</div>

`;

        });

    }

}

// =====================================================
// CART
// =====================================================

function addToCart(id, name, price) {

    const existing =
        cart.find(i => i.id === id);

    if (existing) {

        existing.quantity++;

    } else {

        cart.push({

            id: id,

            name: name,

            price: price,

            quantity: 1

        });

    }

    renderCart();

}

function renderCart() {

    const body =
        document.getElementById("cartTable");

    body.innerHTML = "";

    let subtotal = 0;

    cart.forEach(item => {

        subtotal +=
            item.price * item.quantity;

        body.innerHTML += `

<tr>

<td>${item.name}</td>

<td>${item.quantity}</td>

<td>${item.price}</td>

<td>${item.price * item.quantity}</td>

</tr>

`;

    });

    document.getElementById("subtotal")
        .innerHTML = subtotal.toFixed(2);

}

// =====================================================
// ORDER
// =====================================================

async function placeOrder() {

    if (selectedRestaurant == null) {

        alert("Select a restaurant first.");

        return;

    }

    if (cart.length === 0) {

        alert("Cart is empty.");

        return;

    }

    const customerId = 1;

    const order = await api(

        `/orders/customer/${customerId}/restaurant/${selectedRestaurant}`,

        {

            method: "POST"

        }

    );

    if (!order)
        return;

    currentOrder = order.orderId;

    document.getElementById("currentOrder")
        .innerHTML = currentOrder;

    for (const item of cart) {

        await api(

            `/orders/${currentOrder}/items/${item.id}`,

            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify({

                    quantity: item.quantity,

                    unitPrice: item.price

                })

            }

        );

    }

    alert("Order placed successfully!");

    cart = [];

    renderCart();

    showSection("trackingSection");

    trackOrder();

}

// =====================================================
// ORDER TRACKING
// =====================================================

async function trackOrder() {

    if (currentOrder == null)
        return;

    const order =
        await api(`/orders/${currentOrder}`);

    if (!order)
        return;

    document.getElementById("orderStatus")
        .innerHTML = order.status;

    document.querySelectorAll("#progress div")
        .forEach(x => x.classList.remove("active"));

    switch (order.status) {

        case "PENDING":

            document.getElementById("pPending")
                .classList.add("active");

            break;

        case "PREPARING":

            document.getElementById("pPending")
                .classList.add("active");

            document.getElementById("pPreparing")
                .classList.add("active");

            break;

        case "READY":

            document.getElementById("pPending")
                .classList.add("active");

            document.getElementById("pPreparing")
                .classList.add("active");

            document.getElementById("pReady")
                .classList.add("active");

            break;

        case "DELIVERED":

            document.querySelectorAll("#progress div")
                .forEach(x => x.classList.add("active"));

            break;

    }

    setTimeout(trackOrder, 5000);

}

// =====================================================
// INITIAL LOAD
// =====================================================

window.onload = function () {

    showSection("restaurantsSection");

    loadRestaurants();

};
// =====================================================
// REVIEWS
// =====================================================

async function viewReviews(id) {

    document.getElementById("reviewRestaurantId").value = id;

    showSection("reviewsSection");

    await loadReviews();

}

async function loadReviews() {

    const id =
        document.getElementById("reviewRestaurantId").value;

    if (!id)
        return;

    const reviews =
        await api(`/reviews/restaurant/${id}?page=0&size=20`);

    const avg =
        await api(`/reviews/restaurant/${id}/average`);

    document.getElementById("averageRating").innerHTML =
        avg ?? "-";

    const table =
        document.getElementById("reviewsTable");

    table.innerHTML = "";

    if (!reviews)
        return;

    const list =
        reviews.content ? reviews.content : reviews;

    list.forEach(r => {

        table.innerHTML += `

<tr>

<td>${r.customerName ?? "-"}</td>

<td>${r.rating}</td>

<td>${r.comment}</td>

</tr>

`;

    });

}

async function leaveRestaurantReview() {

    const restaurantId =
        document.getElementById("reviewRestaurantId").value;

    if (!restaurantId) {

        alert("Enter restaurant ID.");

        return;

    }

    const rating =
        prompt("Rating (1-5)");

    if (rating == null)
        return;

    const comment =
        prompt("Comment");

    if (comment == null)
        return;

    await api(

        `/reviews/restaurant/${restaurantId}/customer/1?rating=${rating}&comment=${encodeURIComponent(comment)}`,

        {

            method: "POST"

        }

    );

    alert("Review submitted.");

    loadReviews();

}

// =====================================================
// PAYMENTS
// =====================================================

async function createPayment() {

    const orderId =
        document.getElementById("paymentOrderId").value;

    const method =
        document.getElementById("paymentMethod").value;

    if (!orderId) {

        alert("Enter order ID.");

        return;

    }

    const payment =
        await api(

            `/payments/order/${orderId}?method=${method}`,

            {

                method: "POST"

            }

        );

    if (!payment)
        return;

    document.getElementById("paymentResult").innerHTML =

        `
        Payment Created<br>
        Payment ID : ${payment.paymentId}<br>
        Status : ${payment.status}
        `;

}

async function completePayment() {

    const paymentId =
        document.getElementById("completePaymentId").value;

    if (!paymentId)
        return;

    const payment =
        await api(

            `/payments/${paymentId}/complete`,

            {

                method: "PUT"

            }

        );

    if (!payment)
        return;

    document.getElementById("paymentResult").innerHTML =

        `
        Payment Completed<br>
        Status : ${payment.status}
        `;

}

// =====================================================
// DRIVER
// =====================================================

async function updateDriverStatus() {

    const id =
        document.getElementById("driverId").value;

    const status =
        document.getElementById("driverStatus").value;

    await api(

        `/drivers/${id}/status?isOnline=${status}`,

        {

            method: "PUT"

        }

    );

    alert("Driver status updated.");

}

async function updateLocation() {

    const id =
        document.getElementById("driverLocationId").value;

    const lat =
        document.getElementById("lat").value;

    const lng =
        document.getElementById("lng").value;

    await api(

        `/drivers/${id}/location?lat=${lat}&lng=${lng}`,

        {

            method: "PUT"

        }

    );

    alert("Location updated.");

}

async function findNearbyDrivers() {

    const lat =
        document.getElementById("searchLat").value;

    const lng =
        document.getElementById("searchLng").value;

    const radius =
        document.getElementById("radius").value;

    const drivers =
        await api(

            `/deliveries/drivers/nearby?lat=${lat}&lng=${lng}&radiusKm=${radius}`

        );

    const table =
        document.getElementById("driversTable");

    table.innerHTML = "";

    if (!drivers)
        return;

    drivers.forEach(driver => {

        table.innerHTML += `

<tr>

<td>${driver.name}</td>

<td>${driver.phone}</td>

<td>${driver.online ? "Online" : "Offline"}</td>

</tr>

`;

    });

}

// =====================================================
// REPORTS
// =====================================================

async function loadReports() {

    loadTopCustomer();

    loadLeaderboard();

}

async function loadTopCustomer() {

    const customer =
        await api("/reports/customers/topLoyalty");

    if (!customer)
        return;

    const c =
        Array.isArray(customer) ? customer[0] : customer;

    document.getElementById("topCustomer").innerHTML =

        `
        <strong>${c.name}</strong><br>
        Email : ${c.email}<br>
        Loyalty Points : ${c.loyaltyPoints}
        `;

}

async function loadLeaderboard() {

    const drivers =
        await api("/reports/drivers/leaderboard");

    const table =
        document.getElementById("leaderboard");

    table.innerHTML = "";

    if (!drivers)
        return;

    drivers.forEach(driver => {

        table.innerHTML += `

<tr>

<td>${driver.name}</td>

<td>${driver.phone}</td>

</tr>

`;

    });

}

async function loadPlatformOrders() {

    const start =
        document.getElementById("ordersStart").value;

    const end =
        document.getElementById("ordersEnd").value;

    if (!start || !end)
        return;

    const count =
        await api(

            `/reports/platform/dailySummary/orders?start=${start}&end=${end}`

        );

    document.getElementById("platformOrders").innerHTML =
        count;

}

async function loadPlatformFees() {

    const start =
        document.getElementById("feesStart").value;

    const end =
        document.getElementById("feesEnd").value;

    if (!start || !end)
        return;

    const fees =
        await api(

            `/reports/platform/dailySummary/fees?start=${start}&end=${end}`

        );

    document.getElementById("platformFees").innerHTML =
        fees;

}
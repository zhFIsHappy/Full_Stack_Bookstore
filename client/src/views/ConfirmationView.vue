<template>
  <div id="noDetails" v-if="!orderDetailsStore.hasOrderDetails()">
    <p>We are sorry, the order you requested could not be found</p>
    <router-link class="homepage" :to="{ name: 'home-view' }"
      >Go to Home Page</router-link
    >
  </div>

  <div id="confirmationView" v-else>
    <ul id="orderId">
      <li>Order ID： {{ orderDetails.order.confirmationNumber }}</li>
      <li>Order Time: {{ new Date(orderDetails.order.dateCreated) }}</li>
    </ul>
    <p style="font-size: 20px">Your Order Details</p>
    <table>
      <tr>
        <th>Product</th>
        <th>Quantity</th>
        <th>Price</th>
      </tr>
      <tr
        v-for="(item, index) in orderDetails.lineItems"
        :key="orderDetails.order.orderId + '-' + item.bookId"
      >
        <td>
          {{ orderDetails.books[index].title }}
        </td>
        <td>{{ item.quantity }}</td>
        <td>
          <p>
            {{
              asDollarsAndCents(orderDetails.books[index].price * item.quantity)
            }}
          </p>
        </td>
      </tr>
      <tr>
        <td colspan="2">-- Delivery Surcharge --</td>
        <td>
          {{ asDollarsAndCents(cart.surcharge) }}
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <strong>Total</strong>
        </td>
        <td>
          <strong>
            {{ asDollarsAndCents(orderDetails.order.amount) }}
          </strong>
        </td>
      </tr>
    </table>
    <p style="font-size: 20px">Your Information Detail</p>
    <table>
      <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Credit Card Number</th>
      </tr>
      <tr>
        <td>
          {{ orderDetails.customer.customerName }}
        </td>
        <td>{{ orderDetails.customer.address }}</td>
        <td>
          <p>
            {{ orderDetails.customer.email }}
          </p>
        </td>
        <td>{{ orderDetails.customer.phone }}</td>

        <td>
          {{ orderDetails.customer.ccNumber.replace(/.(?=.{4})/g, "*") }}
          ({{ new Date(orderDetails.customer.ccExpDate).getMonth() + 1 }}/{{
            new Date(orderDetails.customer.ccExpDate).getFullYear()
          }})
        </td>
      </tr>
    </table>
    <!--    <td>1111{{ orderDetails.customer.ccExpiryYear }}</td>-->
    <router-link
      class="button"
      :to="{
        name: 'category-view',
        params: {
          name: categoryStore.selectedCategoryName,
        },
      }"
    >
      Continue Shopping</router-link
    >
  </div>
  <!--  <confirmation-table id="books"></confirmation-table>-->
</template>

<script setup lang="ts">
import { asDollarsAndCents } from "@/types";
import { useOrderDetailsStore } from "@/stores/OrderDetailsStore";
import { useCartStore } from "@/stores/CartStore";
import { useCategoryStore } from "@/stores/CategoryStore";
import { computed } from "vue";

const orderDetailsStore = useOrderDetailsStore();
const cartStore = useCartStore();
const categoryStore = useCategoryStore();
const cart = cartStore.cart;
const orderDetails = computed(() => {
  return orderDetailsStore.orderDetails;
});
</script>

<style scoped>
#orderId {
  text-align: center;
  align-items: center;
  align-content: center;
}
#confirmationView {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 1em auto;
}
ul {
  text-align: left;
}
ul > li {
  margin: 0.25em;
}

table {
  border: 2px black solid;
  width: 45%;
  margin-top: 1.5em;
  margin-bottom: 1.5em;
}
#total {
  /*right: 500px;*/
  color: #07a9ef;
  alignment: center;
  /*text-align: center;*/
}

td {
  display: table-cell;
  padding: 0.5em 0.5em;
  color: white;
  background-color: #07a9ef;
  vertical-align: middle;
}

tr:nth-child(even) > td {
  background-color: #07a9ef;
}

td:nth-child(1) {
  text-align: left;
}

td:nth-child(2) {
  text-align: center;
}

td:nth-child(3) {
  text-align: right;
}
th {
  height: 50px;
}
.button {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #07a9ef;
  border: 1px solid #000;
  border-radius: 10px;
  padding: 5px;
  width: 200px;
  color: white;
  transform: scale(1);
  font-weight: bold;
}
.button:hover {
  transform: scale(0.97);
}
#noDetails {
  flex-direction: column;
}
#noDetails p {
  text-align: center;
  color: #07a9ef;
  font-size: 25px;
  font-weight: bold;
}
.homepage {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #07a9ef;
  border: 1px solid #000;
  border-radius: 10px;
  padding: 5px;
  width: 200px;
  color: white;
  transform: scale(1);
  font-weight: bold;
  margin-left: 600px;
  margin-top: 30px;
  margin-bottom: 10px;
}
.homepage:hover {
  transform: scale(0.97);
}
</style>

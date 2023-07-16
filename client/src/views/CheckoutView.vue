<script setup lang="ts">
import { reactive, Ref } from "vue";
import useVuelidate from "@vuelidate/core";
import {
  email,
  helpers,
  maxLength,
  minLength,
  required,
} from "@vuelidate/validators";
import { useCartStore } from "@/stores/CartStore";
const cartStore = useCartStore();
const cart = cartStore.cart;
import { isCreditCard, isMobilePhone } from "@/utils";
import CheckoutFieldError from "@/components/CheckoutFieldError.vue";
import router from "@/router";
import { asDollarsAndCents } from "@/types";
import { useCategoryStore } from "@/stores/CategoryStore";
const categoryStore = useCategoryStore();
const months: string[] = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];
const year: string[] = [
  "2023",
  "2024",
  "2025",
  "2026",
  "2027",
  "2028",
  "2029",
  "2030",
  "2031",
  "2032",
  "2033",
  "2034",
  "2035",
  "2036",
  "2037",
];

const form = reactive({
  name: "",
  address: "",
  phone: "",
  email: "",
  ccNumber: "",
  ccExpiryMonth: new Date().getMonth() + 1,
  ccExpiryYear: new Date().getFullYear(),
  checkoutStatus: "",
});

const rules = {
  name: {
    required: helpers.withMessage("Please provide your name.", required),
    minLength: helpers.withMessage(
      "Name must have at least 4 letters.",
      (value: string) => !helpers.req(value.trim()) || value.length >= 4
    ),
    maxLength: helpers.withMessage(
      "Name can have at most 45 letters.",
      (value: string) => !helpers.req(value.trim()) || value.length <= 45
    ),
  },
  address: {
    required: helpers.withMessage("Please provide an address.", required),
    minLength: helpers.withMessage(
      "Address must have at least 4 letters.",
      (value: string) => !helpers.req(value.trim()) || value.length >= 4
    ),
    maxLength: helpers.withMessage(
      "Address can have at most 45 letters.",
      (value: string) => !helpers.req(value.trim()) || value.length <= 45
    ),
  },
  phone: {
    required: helpers.withMessage("Please provide a phone number.", required),
    phone: helpers.withMessage(
      "Please provide a valid phone number.",
      (value: string) =>
        !helpers.req(value.trim()) || isMobilePhone(value.trim())
    ),
  },

  ccExpiryMonth: {},
  ccExpiryYear: {},
  email: {
    required: helpers.withMessage("Please provide a email address.", required),
    email: helpers.withMessage("Please provide a valid email address", email),
  },
  ccNumber: {
    required: helpers.withMessage(
      "Please provide a credit card number.",
      required
    ),
    ccNumber: helpers.withMessage(
      "Please provide a valid credit card number",
      (value: string) =>
        !helpers.req(value.trim()) || isCreditCard(value.trim())
    ),
  },
};
const $v = useVuelidate(rules, form);

async function submitOrder() {
  console.log("Submit order");
  const isFormCorrect = await $v.value.$validate();
  if (!isFormCorrect) {
    form.checkoutStatus = "ERROR";
  } else {
    form.checkoutStatus = "PENDING";
    await cartStore
      .placeOrder({
        name: form.name,
        address: form.address,
        phone: form.phone,
        email: form.email,
        ccNumber: form.ccNumber,
        ccExpiryMonth: form.ccExpiryMonth,
        ccExpiryYear: form.ccExpiryYear,
      })
      .then(() => {
        form.checkoutStatus = "OK";
        router.push({ name: "confirmation-view" });
      })
      .catch((reason) => {
        form.checkoutStatus = "SERVER_ERROR";
        console.log("Error placing order", reason);
      });
  }
}

/* NOTE: For example yearFrom(0) == <current_year> */
function yearFrom(index: number) {
  return new Date().getFullYear() + index + 1;
}
</script>

<style scoped>
.checkout-page {
  background: #07a9ef;
  color: white;
  width: inherit;
  flex-direction: column;
}
.checkout-page-body {
  display: flex;
  padding: 2em;
  position: center;
  align-items: center;
  flex-direction: column;
}

form {
  display: flex;
  flex-direction: column;
  position: relative;

  height: 300px;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

form > div > input,
form > div > select {
  background-color: #666666;
  margin-left: 0.5em;
  color: white;
}

form > .error {
  color: red;
  text-align: right;
}

.checkoutStatusBox {
  display: inline-block;
  padding: 10px;
  color: red;
  text-align: center;
  margin-left: 950px;
  /*margin-top: -500px;*/
}
.check-out-details {
  padding: 10px;
}
input[type="submit"] {
  font-weight: bold;
  border: solid 1px black;
  border-radius: 12px;
  background-color: #07a9ef;
  cursor: pointer;
  display: inline-block;
  padding: 8px;
  margin: 4px 2px;
  transform: scale(1);
}
.CheckoutFieldError a {
  font-size: 1px;
}
.year {
  padding-left: 10px;
}

input.button:hover {
  transform: scale(0.97);
}

.normal-2x-button {
  margin-top: 20px;
  margin-left: 600px;
  margin-bottom: 20px;
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
.normal-2x-button:hover {
  cursor: pointer;
  transform: scale(0.96);
}
#checkout-empty p {
  text-align: center;
  font-weight: bold;
  color: #07a9ef;
  font-size: 30px;
}
</style>

<template>
  <section id="checkout-empty" v-if="cart.empty">
    <p>Your cart is empty. Please add an item to your cart to checkout.</p>

    <router-link
      :to="{
        name: 'category-view',
        params: { name: categoryStore.selectedCategoryName },
      }"
    >
      <button class="normal-2x-button">Continue Shopping</button>
    </router-link>
  </section>
  <div class="checkout-page">
    <div id="check-form-and-info">
      <section class="checkout-page-body" v-if="!cart.empty">
        <form id="checkout-form" @submit.prevent="submitOrder">
          <div class="form-element">
            <label for="name">Name</label>
            <input
              type="text"
              size="20"
              id="name"
              name="name"
              v-model.lazy="$v.name.$model"
            />
          </div>
          <CheckoutFieldError field-name="name" :validator="$v" />

          <div class="form-element">
            <label for="address">Address</label>
            <input
              type="text"
              size="20"
              id="address"
              name="address"
              v-model.lazy="$v.address.$model"
            />
          </div>
          <CheckoutFieldError field-name="address" :validator="$v" />

          <div class="form-element">
            <label for="phone">Phone</label>
            <input
              class="textField"
              type="text"
              size="20"
              id="phone"
              name="phone"
              v-model.lazy="$v.phone.$model"
            />
          </div>
          <CheckoutFieldError field-name="phone" :validator="$v" />

          <div class="form-element">
            <label for="email">Email</label>
            <input
              type="text"
              size="20"
              id="email"
              name="email"
              v-model.lazy="$v.email.$model"
            />
          </div>
          <CheckoutFieldError field-name="email" :validator="$v" />

          <div class="form-element">
            <label for="ccNumber">Credit card</label>
            <input
              type="text"
              size="20"
              id="ccNumber"
              name="ccNumber"
              v-model.lazy="$v.ccNumber.$model"
            />
          </div>
          <CheckoutFieldError field-name="ccNumber" :validator="$v" />
          <div class="exp-container">
            <div class="form-element">
              <label>Exp Month</label>
              <select v-model="$v.ccExpiryMonth.$model">
                <option
                  v-for="(month, index) in months"
                  :key="index"
                  :value="index + 1"
                >
                  {{ month }} ({{ index + 1 }})
                </option>
              </select>
            </div>

            <div class="form-element">
              <label class="year">Exp Year</label>
              <select v-model="$v.ccExpiryYear.$model">
                <option
                  v-for="(year, index) in year"
                  :key="index"
                  :value="yearFrom(index - 1)"
                >
                  {{ yearFrom(index - 1) }}
                </option>
              </select>
            </div>
          </div>
          <div id="checkout-button-area">
            <input
              id="checkout-button"
              type="submit"
              name="submit"
              class="button"
              :disabled="form.checkoutStatus === 'PENDING'"
              value="Complete Purchase"
            />
          </div>
        </form>

        <section class="check-out-details">
          Your credit card will be charged
          <strong>{{
            asDollarsAndCents(cart.subtotal + cart.surcharge)
          }}</strong>
          <br />
          <strong>({{ asDollarsAndCents(cart.subtotal) }}</strong>
          +
          <strong>{{ asDollarsAndCents(cart.surcharge) }}</strong>
          shipping)
        </section>
      </section>
      <section v-show="form.checkoutStatus !== ''" class="checkoutStatusBox">
        <div v-if="form.checkoutStatus === 'ERROR'">
          Error: Please fix the problems above and try again.
        </div>

        <div v-else-if="form.checkoutStatus === 'PENDING'">Processing...</div>

        <div v-else-if="form.checkoutStatus === 'OK'">Order placed...</div>

        <div v-else>An unexpected error occurred, please try again.</div>
      </section>
    </div>
  </div>
</template>

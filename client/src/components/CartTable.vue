<script setup lang="ts">
import { useCartStore } from "@/stores/CartStore";
const cartStore = useCartStore();
import { BookItem } from "@/types";
import { useCategoryStore } from "@/stores/CategoryStore";
import { asDollarsAndCents } from "@/types";
import { useRoute } from "vue-router";

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.jpeg`;
};
const getBookImageUrl = function (book: BookItem): string {
  let filename = book.title.toLowerCase();
  filename = filename.replace(/ /g, "-");
  filename = filename.replace(/'/g, "");
  filename = filename + ".jpg";
  try {
    return require("@/assets/images/books/" + filename);
  } catch (_) {
    return require("@/assets/images/books/no-img-139-187.gif");
  }
};

const updateCart = function (book: BookItem, quantity: number) {
  // cartStore.cart.update(book, quantity);
  cartStore.updateBookQuantity(book, quantity);
};

const clearCart = function () {
  cartStore.cart.clear();
  cartStore.clearCart();
};

const categoryStore = useCategoryStore();
// const bookPriceWrapped = asDollarsAndCents(item.book.price);
</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  width: fit-content;
  margin: 0 auto;
  background-color: aliceblue;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  background-color: #333;
  color: white;
}

.table-heading > * {
  background-color: #333;
  padding: 0.5em;
}

.heading-book {
  grid-column: 1 / 3;
}

.heading-price {
  grid-column: 3 / 5;
  text-align: right;
}

.heading-subtotal {
  text-align: right;
  grid-column: -2 / -1;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

img {
  display: block;
  width: 100px;
  height: auto;
}

.rect {
  background-color: #07a9ef;
}

.narrow-rect {
  width: 75px;
  height: 100px;
}

.square {
  width: 100px;
  height: 100px;
}

.wide-rect {
  width: 125px;
  height: 100px;
}

.cart-book-price {
  padding-left: 1em;
  text-align: right;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
}

.cart-book-subtotal {
  /*text-align: right;*/
  left: 300px;
  padding-left: 1.5em;
  padding-right: 2px;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: gray;
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
}

.inc-button {
  font-size: 1.125rem;
  color: #07a9ef;
  margin-right: 0.25em;
  cursor: pointer;
}

.inc-button:hover {
  color: black;
}

.dec-button {
  font-size: 1.125rem;
  color: #ccc;
  cursor: pointer;
}

.dec-button:hover {
  color: #aaa;
  left: 600px;
}

/*.icon-button inc-button {*/
/*  display: block;*/
/*  left: 1000px;*/
/*}*/
/* Chevron buttons */

.dec-arrow-button,
.inc-arrow-button {
  font-size: 1rem;
  color: #07a9ef;
}

.icon-button dec-button inc-img:hover,
.icon-button inc-button dcr-img:hover {
  background-color: black;
  -webkit-filter: invert(100%);
  opacity: 1;
}

input[type="number"] {
  width: 4em;
}

.proceed-checkout {
  margin-top: 30px;
  /*grid-area: right;*/
  position: relative;
  left: 190px;
  background-color: #07a9ef;
  border: 1px solid #000;
  border-radius: 10px;
  width: 250px;
  padding: 8px;
  transform: scale(1);
  cursor: pointer;
}
.proceed-checkout:hover {
  transform: scale(0.97);
}
.proceed-checkout:active {
  transform: scale(0.8);
}
.proceed-checkout a {
  font: 130% sans-serif;
  color: white;
}
/*.proceed-checkout a:hover {*/
/*  font-weight: bold;*/
/*  */
/*}*/

.inc-img {
  max-width: 20px;
  max-height: 20px;
  position: relative;
  alignment: left;
  -webkit-filter: invert(100%);
  background-color: #f85610;
}
.inc-img:hover {
  background-color: #f85610;
}
.dcr-img {
  max-width: 20px;
  max-height: 20px;
  position: relative;
  left: 5px;
  /*padding-left: 5px;*/
  background-color: #f85610;
  -webkit-filter: invert(100%);
}
.line-sep li {
  padding-left: 1.5em;
}
.summary {
  position: relative;
  margin-left: 100px;
}
.clear-cart {
  margin-top: 10px;
  /*grid-area: right;*/
  position: relative;
  bottom: 10px;
  left: 100px;
  width: 100px;
  text-decoration: underline;
}
.clear-cart:hover {
  font-weight: bold;
  cursor: pointer;
}

.continue-shopping {
  margin-top: 30px;
  /*grid-area: right;*/
  position: relative;
  left: -670px;
  background-color: #07a9ef;
  border: 1px solid #000;
  border-radius: 10px;
  padding: 2px;
  width: 120px;
  transform: scale(1);
  margin-bottom: 5px;
}
.continue-shopping a {
  color: white;
}
.continue-shopping:hover {
  transform: scale(0.97);
}
.continue-shopping:active {
  transform: scale(0.8);
}

.check-out p {
  position: relative;
  text-align: center;
  font-weight: bold;
  font-size: 30px;
}
.continue-shopping-empty {
  margin-top: 30px;
  /*grid-area: right;*/
  position: relative;
  left: 0px;
  background-color: #07a9ef;
  border: 1px solid #000;
  border-radius: 10px;
  padding: 2px;
  width: 120px;
  margin-bottom: 8px;
  transform: scale(1);
  cursor: pointer;
}
.continue-shopping-empty a {
  color: white;
}
.continue-shopping-empty:hover {
  transform: scale(0.97);
}
.cart-book-quantity img {
  cursor: pointer;
  transition: all 0.2s;
}
.cart-book-quantity img:hover {
  transform: scale(1.2);
}
.summary p {
  margin-left: 10px;
}
</style>

<template>
  <div class="cart-table">
    <template v-if="cartStore.count !== 0">
      <ul>
        <li class="table-heading">
          <div class="heading-book">Book</div>
          <div class="heading-price">Price / Quantity</div>
          <div class="heading-subtotal">Amount</div>
        </li>
        <template v-for="item in cartStore.cart.items" :key="item.book.bookId">
          <li>
            <div class="cart-book-image">
              <img
                :src="getBookImageUrl(item.book)"
                :alt="item.book.title"
                width="100px"
                height="auto"
              />
            </div>
            <div class="cart-book-title">{{ item.book.title }}</div>
            <div class="cart-book-price">
              {{ asDollarsAndCents(item.book.price) }}
            </div>
            <div class="cart-book-quantity">
              <button
                class="icon-button inc-button"
                @click="updateCart(item.book, item.quantity + 1)"
              >
                <img
                  src="@/assets/images/site/plus-solid.svg"
                  class="inc-img"
                  alt=""
                />
              </button>
              <span class="quantity">{{ item.quantity }}</span>
              <button
                class="icon-button dec-button"
                @click="updateCart(item.book, item.quantity - 1)"
              >
                <img
                  src="@/assets/images/site/minus-solid.svg"
                  class="dcr-img"
                  alt=""
                />
              </button>
            </div>
            <div class="cart-book-subtotal">
              {{ asDollarsAndCents(item.quantity * item.book.price) }}
            </div>
          </li>
          <li class="line-sep"></li>
        </template>
        <li class="line-sep"></li>
        <li class="summary">
          <template v-if="cartStore.count > 1">
            <p>You have {{ cartStore.count }} books in cart</p>
            <div class="cart-book-subtotal">
              Total: {{ asDollarsAndCents(cartStore.cart.subtotal) }}
            </div>
          </template>

          <template v-if="cartStore.count == 1">
            <p>You have {{ cartStore.count }} book in cart</p>
            <div class="cart-book-subtotal">
              Total: {{ asDollarsAndCents(cartStore.cart.subtotal) }}
            </div>
          </template>
        </li>
        <router-link to="/checkout">
          <button class="proceed-checkout">
            <a>Proceed To Checkout</a>
          </button>
        </router-link>

        <a class="clear-cart" @click="clearCart()"
          >Clear Cart <template id="empty-page"></template
        ></a>

        <button class="continue-shopping">
          <router-link :to="/category/ + categoryStore.selectedCategoryName">
            <a>Continue Shopping</a>
          </router-link>
        </button>
      </ul>
    </template>
    <template v-else>
      <div class="check-out">
        <p>You have nothing in your cart</p>
      </div>
      <button class="continue-shopping-empty">
        <router-link :to="/category/ + categoryStore.selectedCategoryName">
          <a>Continue Shopping</a>
        </router-link>
      </button>
    </template>
  </div>
  <template id="empty-page">
    <div class="check-out">
      <p>You have nothing in your cart</p>
    </div>
    <button class="continue-shopping">
      <router-link :to="/category/ + categoryStore.selectedCategoryName">
        <a>Continue Shopping</a>
      </router-link>
    </button>
  </template>
</template>

<script setup lang="ts">
import { defineProps } from "vue";
import { BookItem } from "@/types";
import { useCartStore } from "@/stores/CartStore";
import { asDollarsAndCents } from "@/types";
const cartStore = useCartStore();
const props = defineProps<{
  book: BookItem;
}>();

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

// function addToCart(book: BookItem) {
//   cartStore.addToCart(book);
// }
</script>
<style scoped>
* {
  padding: 0;
  margin: 0;
  list-style: none;
  font-size: 16px;
  font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
}
a {
  text-decoration: none;
  color: #333;
}
h1 {
  color: #fff;
  line-height: 60px;
  padding-bottom: 30px;
}

.list {
  padding: 15px 40px 30px;
}
.list ul {
  display: flex;
  justify-content: space-around;
  left: 500px;
  bottom: 600px;
  /*flex-wrap: wrap;*/
}
.list ul li {
  padding: 0 20px 10px;
  position: relative;
}
.list ul li img {
  width: 20px;
}
.list ul li h4 a {
  display: flex;
  color: #fff;
  border: 1px solid #000;
  justify-content: center;
  align-items: center;
  padding: 5px 0;
  border-radius: 10px;
  background-color: #07a9ef;
}
.list ul li h3 {
  color: #fff;
  padding: 3px 0;
}
.list ul li p {
  color: #fff;
  padding: 5px 0;
  font-style: italic;
}
.list ul li p strong {
  font-style: normal;
  color: #bf4612;
}
.list ul li h4 {
  width: 30px;
  color: #fff;
  margin-right: 10px;
}
.list ul li h4.read {
  position: absolute;
  top: 140px;
  left: 20px;
  width: 180px;
  box-sizing: border-box;
}

/* .container {
  overflow: hidden;
  display: inline-block;
  position: relative;
  flex-wrap: wrap;
  gap: 1em;
} */
button {
  border: solid 1px black;
  border-radius: 12px;
  background-color: #07a9ef;
  cursor: pointer;
  text-align: center;
  display: inline-block;
  padding: 8px;
  margin: 4px 2px;
}
button a {
  color: #fff;
}
.read a:hover {
  color: #b3c5cf;
}
.addToCart {
  transform: scale(1);
}
.addToCart a:hover {
  color: #b3c5cf;
}
.addToCart:active {
  transform: scale(0.97);
}
.cart {
  padding-right: 3px;
}
.author {
  font-style: italic;
  color: white;
}
.price {
  color: white;
  font-weight: bold;
}
.read {
  border: solid 1px black;
  position: relative;
  bottom: 105px;
  /*margin-bottom: 370px;*/
  margin-left: -110px;
  transform: scale(1);
  /*left: px;*/
  /*width: 120px;*/
  /*padding: 5px;*/
}
.read:active {
  transform: scale(0.97);
}
</style>
<style>
.item {
  height: auto;
  position: inherit;
  /*overflow-y: auto;*/
  column-gap: 20px;
  max-width: 1000px;
  column-count: 4;
  margin: auto;
  display: flex;
  flex-wrap: wrap;
}

@media only screen and (max-width: 1904px) {
  .item {
    column-count: 4;
  }
}
@media only screen and (max-width: 1264px) {
  .item {
    column-count: 3;
  }
}
@media only screen and (max-width: 960px) {
  .item {
    column-count: 2;
  }
}
@media only screen and (max-width: 600px) {
  .item {
    column-count: 1;
    column-gap: 0;
  }
}
.item__content {
  flex-wrap: wrap;
}
.item > .item__content {
  text-align: center;
  overflow: auto;
}
.featured {
  color: #2349cd;
  bottom: 250px;
  position: relative;
  font-weight: bold;
}
.cart {
  height: 1em;
  width: auto;
  display: inline-block;
}
</style>
<template>
  <li class="item__content" style="bottom: 1000px">
    <img
      :src="getBookImageUrl(book)"
      :alt="book.title"
      class="books"
      style="height: 155px"
    />
    <h3>{{ book.title }}</h3>
    <p class="author">{{ book.author }}</p>
    <p class="price">
      <strong>{{ asDollarsAndCents(book.price) }}</strong>
    </p>
    <button class="addToCart" @click="cartStore.addToCart(book)">
      <h4>
        <a href="#"
          ><img
            src="@/assets/images/books/cart.svg"
            alt=""
            class="cart"
            height="15"
            width="15"
          />Add to Cart</a
        >
      </h4>
    </button>
    <button class="read" v-if="book.isPublic">
      <a href="#"
        ><img
          src="@/assets/images/books/readme.svg"
          alt=""
          class="cart"
          height="10"
          width="15"
        />Read Now</a
      >
    </button>
    <p class="featured" v-if="book.isFeatured"><em>Recommend By ZhF</em></p>
  </li>
</template>

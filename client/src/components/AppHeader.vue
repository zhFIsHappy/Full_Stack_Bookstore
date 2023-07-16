<script setup lang="ts">
import HeaderDropdown from "@/components/HeaderDropdown.vue";
import { useCartStore } from "@/stores/CartStore";
const cartStore = useCartStore();
</script>

<style>
* {
  padding: 0;
  margin: 0;
  list-style: none;
  font-size: 16px;
  font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
}
.main {
  width: 1200px;
  margin: 0 auto;
}
.logo {
  display: flex;
  justify-content: space-between;
  align-items: center;
  float: left;
  margin-left: 35px;
  font-size: 32px;
  color: #2349cd;
  font-weight: bold;
}
.logo a {
  font-size: 32px;
  color: #2349cd;
  font-weight: bold;
}
.logo img {
  margin-right: 10px;
}

.nav {
  padding: 15px 0 20px 30px;
  display: flex;
}
.nav a:hover {
  color: #b3c5cf;
}
.cartbox:hover {
  color: #b3c5cf;
}
.search {
  border: 1px solid #000;
  height: 34px;
  padding: 0 5px;
  border-radius: 10px;
  width: 280px;
  display: flex;
  justify-content: space-between;
  overflow: hidden;
}
.searchbox {
  float: left;
  padding-left: 40px;
  padding-top: 5px;
}
.searchbox button {
  cursor: pointer;
}
.searchbox input,
.searchbox button {
  border: none;
}
.searchbox input {
  width: 240px;
  font-size: 18px;
  color: #2349cd;
  font-weight: bold;
}
.cartbox {
  display: flex;
  justify-content: space-between;
  text-align: center;
  align-items: center;
  float: right;
  height: 34px;
  padding-top: 5px;
}
.cartbox a {
  display: flex;
  align-items: center;
  font-size: 12px;
  justify-content: space-between;
  margin-left: 5px;
  border: 1px solid #07a9ef;
  background-color: #07a9ef;
  padding: 0 5px;
  border-radius: 5px;
  color: #fff;
  height: 30px;
  line-height: 30px;
}
.cartbox a.sign {
  height: 20px;
  line-height: 20px;
}
.cartImage {
  height: 30px;
  width: 26px;
}
.count {
  font-size: 100%;
  color: black;
  position: absolute;
  margin-left: 11px;
  margin-bottom: 6px;
  left: inherit;
  top: inherit;
  font-size-adjust: inherit;
}

::placeholder {
  color: #2349cd;
  opacity: 1;
}
</style>

<template>
  <div class="nav">
    <section>
      <header-dropdown></header-dropdown>
      <div class="logo">
        <router-link to="/">
          <img src="@/assets/images/header/logo.svg" height="44" />
        </router-link>

        <router-link to="/" class="text-logo">Otter BookStore</router-link>
      </div>
      <div class="searchbox">
        <div class="search">
          <input
            type="text"
            name=""
            width="240px"
            placeholder="Search by name, Author...     "
            id=""
          />
          <button>
            <img src="@/assets/images/header/search.jpg" alt="" />
          </button>
        </div>
      </div>
      <div class="cartbox">
        <a href="#">
          <router-link v-if="cartStore.count !== 0" to="/cart">
            <img
              src="@/assets/images/header/cart.jpg"
              alt=""
              class="cartImage"
            />
            <h3 class="count">{{ cartStore.count }}</h3>
          </router-link>
          <router-link v-else to="/cart">
            <img
              src="@/assets/images/header/cart.jpg"
              alt=""
              class="cartImage"
            />
            <h3 class="count">{{ cartStore.count }}</h3>
          </router-link>
        </a>
        <a href="#">
          <img src="@/assets/images/header/user.jpg" alt="" /> PROFILE
        </a>
        <a href="#" class="sign">Not registered? Sign In</a>
      </div>
    </section>
  </div>
</template>

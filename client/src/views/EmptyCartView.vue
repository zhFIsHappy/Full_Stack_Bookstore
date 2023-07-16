<script>
export default {
  name: "EmptyCartView",
};
</script>

<template>
  <div class="check-out">
    <p>You have nothing in your cart</p>
  </div>
  <button class="continue-shopping-empty">
    <router-link to="/category/Best%20Seller">
      <a>Continue Shopping</a>
    </router-link>
  </button>
</template>

<style scoped>
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
  left: 700px;
  background-color: #07a9ef;
  border: 1px solid #000;
  border-radius: 10px;
  padding: 2px;
  width: 120px;
}
.continue-shopping-empty a {
  color: white;
}
.continue-shopping-empty a:hover {
  font-weight: bold;
}
</style>

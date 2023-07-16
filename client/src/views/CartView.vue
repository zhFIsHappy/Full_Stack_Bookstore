<template>
  <div class="cart-view">
    <cart-table> </cart-table>
  </div>
</template>

<script>
import CartTable from "@/components/CartTable";
export default {
  name: "CartView",
  components: { CartTable },
};
</script>

<style scoped></style>

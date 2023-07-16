<script setup lang="ts">
import { defineProps, Ref } from "vue";
import { Validation } from "@vuelidate/core";

const props = defineProps<{
  fieldName: string;
  validator: Ref<Validation>;
}>();
</script>

<style scoped>
form > .error {
  color: red;
  text-align: right;
  font-size: 15px;
  margin-top: -5px;
  margin-left: 50px;
  margin-bottom: 10px;
}
.error {
  margin-left: 50px;
}
</style>

<template>
  <template v-if="validator[fieldName].$error">
    <span
      class="error"
      v-for="error of validator[fieldName].$errors"
      :key="error.$uid"
      >{{ error.$message }}</span
    >
  </template>
</template>

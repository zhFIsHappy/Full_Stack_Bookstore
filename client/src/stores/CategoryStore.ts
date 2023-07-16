import { defineStore } from "pinia";
import { CategoryItem } from "@/types";
import { apiUrl } from "@/services/ApiService";

export const useCategoryStore = defineStore("CategoryStore", {
  state: () => ({
    categoryList: [] as CategoryItem[],
    selectedCategoryName: "Horror",
  }),
  actions: {
    async fetchCategories() {
      const url = apiUrl + "categories/";
      this.categoryList = await fetch(url).then((response) => response.json());
    },
    setSelectedCategory(categoryName: string) {
      this.selectedCategoryName = categoryName;
    },
  },
  // getters
  //action
  //getter
});

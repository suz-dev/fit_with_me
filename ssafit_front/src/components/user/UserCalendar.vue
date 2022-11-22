<template>
  <v-calendar :attributes="attributes" />
</template>

<script>
export default {
  data() {
    const date = new Date();
    const year = date.getFullYear();
    const month = date.getMonth();
    return {
      attributes: [
        {
          key: "today",
          highlight: {
            color: "purple",
            fillMode: "solid",
            contentClass: "italic",
          },
          dates: [new Date(year, month, 12), new Date(year, month, 13)],
        },
        {
          highlight: {
            color: "purple",
            fillMode: "light",
          },
          dates: new Date(year, month, 13),
        },
        {
          highlight: {
            color: "purple",
            fillMode: "outline",
          },
          dates: new Date(year, month, 14),
        },
      ],
    };
  },
};
</script>

<style></style>

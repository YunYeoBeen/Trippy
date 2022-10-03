<template>
  <div>
    <p v-if="errorMessage !== ''">{{ errorMessage }}</p>
  </div>
</template>



<script>
export default {
    name: 'AccountErrorList.vue',
    props: {
      errorMessage: {
        type: String,
        default: ''
      },
    }
}
</script>

<style scoped>
  div {
      color: red;
      font-size: 12px;
  }

  p {
    margin: 0;
  }
</style>
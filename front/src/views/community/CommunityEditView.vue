<template>
  <div>
    <!-- {{post}} -->
    <community-form :post="post" action="update"></community-form>
  </div>
</template>

<script>
import CommunityForm from '@/components/community/CommunityForm.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
    name: 'CommunityEditView',
    components: {
      CommunityForm
    },
    computed: {
      ...mapGetters(['post'])
    },
    methods: {
      ...mapActions(['fetchPost'])
    },
    created() {
      this.fetchPost(this.$route.params.postPk)
    }
}
</script>

<style>

</style>
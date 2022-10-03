<template>
  <div class="container">
		
		<div class="profileimage" props="following">
			{{ name }}
		</div>
  </div>
</template>

<script>
export default {
  name: 'FollowingsListItem',
    props: {
		following: Object,
	},
  data() {
    return {
      name: this.$store.getters.profile.name
    }
  }

}
</script>

<style>
.profileimage {
	background-color: black;
	width: 100px;
	height: 100px;
	border-radius: 50%;
}
</style>
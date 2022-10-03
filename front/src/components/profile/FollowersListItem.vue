<template>
  <div class="container">
		
		<div class="profileimage" props="follower">
			image
		</div>
  </div>
</template>

<script>
export default {
  name: 'FollowersListItem',
	props: {
		follower: Object,
	},

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
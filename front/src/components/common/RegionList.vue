<template>
  <div>
		<div class="regional-menu">
			<p>지금 <span>트리피</span>에서 핫한 여행코스</p>
		</div>
			<region-list-item></region-list-item>
			<br>
  </div>
</template>

<script>
import RegionListItem from '@/components/common/RegionListItem.vue'

export default {
  name: 'RegionList',
	components: {
		RegionListItem
	},

}
</script>

<style scoped>
.regional-menu {
	display: flex;
	margin-left: 2%;
}

.regional-menu p {
	font-size: 2.5vh;
	font-weight: 500;
}

.regional-menu span {
	color: #F16B51;
	font-size: 2.5vh;
	font-weight: 500;
}


</style>
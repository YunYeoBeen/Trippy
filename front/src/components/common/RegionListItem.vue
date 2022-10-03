<template>
  <div>
    <div class="regions"
			data-flickity='{ "pageDots": false, "cellAlign": "left" }'>
			<div class="region">
				<div class="image" id="all" ><span @click="diaryFilter(this.target)">전체</span></div>
			</div>

			<div class="region">
				<div class="image" id="seoul" ><span @click="diaryFilter(this)">서울</span></div>
			</div>

			<div class="region">
				<div class="image" id="jeju"><span @click="diaryFilter(this)">제주</span></div>
			</div>

			<div class="region">
				<div class="image" id="busan"><span @click="diaryFilter(this)">부산</span></div>
			</div>

			<div class="region">
				<div class="image" id="tokyo"><span @click="diaryFilter(this)">도쿄</span></div>
			</div>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'RegionListItem',
  data() {
    return {
      region: {
				country: '',
				city: '',
			},
    }
  },
  methods: {
    ...mapActions(['fetchAllDiaries', 'fetchRegionDiaries']),
    diaryFilter() {
      console.log(event.currentTarget.innerText)
      if ( event.currentTarget.innerText == '전체') {
        console.log('all')
        this.fetchAllDiaries()
      } 
      else if ( event.currentTarget.innerText == '서울') {
        console.log('seoul')
				this.region.country = '대한민국'
        this.region.city = '서울특별시'
        this.fetchRegionDiaries(this.region)
      }
      else if ( event.currentTarget.innerText == '제주') {
        console.log('jeju')
				this.region.country = '대한민국'
        this.region.city = '제주'
				console.log(this.region)
        this.fetchRegionDiaries(this.region)
      }
      else if ( event.currentTarget.innerText == '부산') {
        console.log('busan')
				this.region.country = '대한민국'
        this.region.city = '부산'
        this.fetchRegionDiaries(this.region)
      }
      else if ( event.currentTarget.innerText == '도쿄') {
        console.log('tokyo')
				this.region.country = '대한민국'
        this.region.city = '도쿄'
        this.fetchRegionDiaries(this.region)
      }
    }
  },
}
</script>

<style scoped>
/* 
  * { 
    box-sizing: border-box; 
  } */

  /* .flickity-enabled { 
    margin: 0 60px;
  } */

  * { box-sizing: border-box; }

  body { font-family: sans-serif; }

  /* .regions {
    margin: 0 60px;
  } */

  /* smaller, dark, rounded square */
  .flickity-prev-next-button {
    width: 30px;
    height: 30px;
    border-radius: 5px;
    background: transparent !important;
  }

  .flickity-button {
    background: transparent;
    fill: #F16B51 !important;
    color: #F16B51 !important;
  }

  /* position outside */
  .flickity-prev-next-button.previous {
    left: -40px !important;
  }
  .flickity-prev-next-button.next {
    right: -40px !important;
  }

  .region {
    width: 60px;
    height: 60px;
    margin-right: 10px;
    border-radius: 50%;
    counter-increment: carousel-cell;
    overflow: hidden;
  }
  
  .region:hover {
    opacity: 0.4;
  }
  
  
  span {
    display: block;
    text-align: center;
    line-height: 60px;
    font-size: 20px;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }
  
  
  .region:nth-child(1) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 100px 100px;
    }
  .region:nth-child(1) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 100px 100px;
    }
    .region:nth-child(2) .image{
      background-image: url("@/assets/seoul.jpg");
      background-size: 100px 100px;
    }
    .region:nth-child(3) .image{
      background-image: url("@/assets/busan.jpg");
      background-size: 100px 100px;
    }
    .region:nth-child(4) .image{
      background-image: url("@/assets/jeju.jpg");
      background-size: 100px 60px;
    }
    .region:nth-child(5) .image{
      background-image: url("@/assets/tokyo.jpg");
      background-size: 100px 60px;
    }


</style>
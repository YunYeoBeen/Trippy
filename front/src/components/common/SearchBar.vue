<template>
  <div>
		<input class="search-bar-input" v-model="searchInput.title" type="text" placeholder="어디로 떠날까요?" @click="isClicked=true" @keyup.enter="showInput()"/>
		<div v-show="isClicked">
			<div class="switch-close-div">
				<el-switch v-model="isDetail" active-text="상세검색" inactive-text="전체검색"/>
					<icon-base viewBox="0 0 1024 1024" width="12" height="12" iconColor="#F16B51" icon-name="closeicon" @click="isClicked=false">
					<close-icon/>
				</icon-base>
			</div>
			<div v-if="isDetail">
				<div class="checkbox-div">
					<span>어떻게 이동하나요?</span>
					<el-checkbox-group v-model="selectTrans" size="small">
						<el-checkbox-button v-for="trans in checkTrans" :key="trans" :label="trans">{{ trans }}</el-checkbox-button>
					</el-checkbox-group>
				</div>
				<div class="checkbox-div">
					<span>누구와 함께 가나요?</span>
					<el-checkbox-group v-model="selectCompany" size="small">
						<el-checkbox-button v-for="company in checkCompany" :key="company" :label="company">{{ company }}</el-checkbox-button>
					</el-checkbox-group>
				</div>
				<hr>
				<el-button size="small" @click="showInput, isClicked=false">적용하기</el-button>
			</div>
		</div>
  </div>
</template>

<script>
import CloseIcon from '@/components/icon/CloseIcon.vue'
import { mapActions } from 'vuex'

export default {
	name: 'SearchBar',
	components: {
		CloseIcon
	},
	data() {
		return {
			isClicked: false,
			// isDetail이 true면 상세검색, false면 전체검색
			isDetail: true,
			searchInput: {
				title: '',
				company: '',
				transportId: '',
			},
			selectTrans: [],
			checkTrans: ['뚜벅이', '대중교통', '따릉이', '택시', '자차'],
			selectCompany: [],
			checkCompany: ['가족', '커플', '친구', '개인']
		}
	},
	methods: {
		...mapActions(['searchDiary']),
		showInput() {
			console.log(this.searchInput.title)
			//전체검색인지 상세검색인지
			console.log(this.isDetail)
			// 필터 저장되는지 확인함
			// console.log(this.selectMonth)
			console.log(this.selectTrans)
			console.log(this.selectCompany)
			this.transParsing()
			this.companyParsing()
			console.log(this.searchInput.title)
			this.searchDiary(this.searchInput)
			this.searchInput.title = ''
		},

		// 지금은 두 개 한꺼번에 보내는게 안됨 - 그래서 일단 하나로 함
		// 여러개 하고싶으면 transportId 를 배열로 만들고, array.push('추가할 value')
		transParsing() {
			let transportation = this.selectTrans
			for ( var key in this.selectTrans ) {
				console.log(transportation[key])
				if (transportation[key] === "뚜벅이" ) {
					this.searchInput.transportId = 1
				} if (transportation[key] === "대중교통") { 
					this.searchInput.transportId = 2
				} if (transportation[key] === "따릉이") { 
					this.searchInput.transportId = 3
				} if (transportation[key] === "택시") { 
					this.searchInput.transportId = 4
				} if (transportation[key] === "자차") { 
					this.searchInput.transportId = 5
				}
			}
		},

		companyParsing() {
			let companyNumber = this.selectCompany
			for ( var key in this.selectCompany ) {
				console.log(companyNumber[key])
				if (companyNumber[key] === "가족" ) {
					this.searchInput.company = 1
				} if (companyNumber[key] === "커플") { 
					this.searchInput.company = 2
				} if (companyNumber[key] === "친구") { 
					this.searchInput.company = 3
				} if (companyNumber[key] === "개인") { 
					this.searchInput.company = 4
				} 
			}
		},


	}
}
</script>

<style scoped>
	.search-bar-input {
		width: 85%;
		height: 30px;
		margin:5%;
		border-radius: 15px;
		padding-left: 15px;
		background-color: rgba(236, 236, 236, 0.735);
		border: none;
		outline: 1;
		outline-color: #F16B51;
		
	}

	.switch-close-div {
		display: flex;
		justify-content: space-between;
	}

	.checkbox-div {
		display: flex;
		align-items: center;
		margin-bottom: 5px;
	}

	.checkbox-div > span {
		font-size: 10px;
	}
</style>